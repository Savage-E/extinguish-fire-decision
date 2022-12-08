package ru.vsu.extinguishfiredecision;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.vsu.extinguishfiredecision.model.*;

import java.util.*;

@Component
@ComponentScan(basePackages = {"ru.vsu.extinguishfiredecision"})
public class ExtinguishFireDecisionApplication {
    private final Set<String> validInputValue = new HashSet<>(Arrays.asList("бумага", "дерево", "одежда", "нефть", "бензин", "электрообурудование", "магний", "натрий", "калий"));

    @Autowired
    private KieContainer kieContainer;

    public static void main(String[] args) {
        SpringApplication.run(ExtinguishFireDecisionApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(ExtinguishFireDecisionApplication.class);
        ExtinguishFireDecisionApplication e = context.getBean(ExtinguishFireDecisionApplication.class);
        e.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для выхода из программы напишите 'выход'");
        String nextOp = " ";
        while (!Objects.equals(nextOp, "выход")) {
            try {
                System.out.print("--------------\n");
                KieSession kieSession = kieContainer.newKieSession();
                Result result = new Result();
                kieSession.setGlobal("resultObj", result);
                kieSession.fireAllRules();
                boolean check = false;
                while (!check) {
                    check = checkResult(scanner, kieSession, result);
                    result.setFireExtinguisher(null);
                    result.setFire(null);
                }

                nextOp = readNextOperation(scanner);
            } catch (Exception e) {
                nextOp = readNextOperation(scanner);
            }
        }
    }

    private boolean checkResult(Scanner scanner, KieSession kieSession, Result result) {
        System.out.print("Какой материал подвержен горению?: ");
        String answer = scanner.next();

        if (!parseFactValue(answer)) {
            return false;
        }
        Fire fire = new Fire(answer);
        workWithKieSession(kieSession, fire);

        if (!result.isFireTypeInit()) {
            return false;
        }

        workWithKieSession(kieSession, result.getFire());

        if (result.isFireExtinguisherTypeInit()) {
            showSolution(result);
            return true;
        }
        return false;
    }

    private boolean parseFactValue(String answer) {
        if (answer.equals("выход")) System.exit(0);
        if (!validInputValue.contains(answer)) {
            System.out.println("Недопустимое значение");
            return false;
        }
        return true;
    }

    private void workWithKieSession(KieSession kieSession, Object ob) {
        kieSession.insert(ob);
        kieSession.fireAllRules(8);
    }

    private void showSolution(Result result) {
        switch (result.getFire().getType()) {
            case TYPE_A -> {
                System.out.println("Решение : так как у нас класс пожара А, используем огнетушители " + result.getfireExtinguisher().toString() + "\n");
                System.out.println("Можно использольовать простую воду");
            }
            case TYPE_B -> {
                System.out.println("Решение : так как у нас класс пожара B, используем огнетушители " + result.getfireExtinguisher().toString());
            }
            case TYPE_C -> {
                System.out.println("Решение : так как у нас класс пожара C, используем огнетушители " + result.getfireExtinguisher().toString());
            }
            case TYPE_D -> {
                System.out.println("Решение : так как у нас класс пожара D, используем огнетушитель " + result.getfireExtinguisher().toString() + "\n");
                System.out.println("По возможности постарайтесь отключить электроприбор от розетки или иного источника питания");
            }
        }
    }

    private String readNextOperation(Scanner scanner) {
        System.out.print("Хотите выйти? : ");
        return scanner.next();
    }

}
