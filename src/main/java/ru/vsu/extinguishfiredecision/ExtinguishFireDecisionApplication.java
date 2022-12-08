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
    //    private final Map<String, List<String>> fireTypeMaterialsMap = new HashMap<>() {
//        {
//            put("ordinaryСombustibles", Arrays.asList("бумага", "дерево", "одежда"));
//            put("flammableAndCombustibleLiquids", Arrays.asList("нефть", "бензин"));
//            put("energizedElectronicalEquipment", List.of("электрообурудование"));
//            put("combustibleMetals", Arrays.asList("магний", "натрий", "калий"));
//        }
//    };
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
//        String category = "";
//        for (Map.Entry<String, List<String>> entry : fireTypeMaterialsMap.entrySet()) {
//            for (String value : entry.getValue()) {
//                if (value.equals(answer)) {
//                    category = entry.getKey();
//                    break;
//                }
//            }
//        }
        if (!parseFactValue(answer)) {
            return false;
        }
        Fire fire = new Fire(answer);
        workWithKieSession(kieSession, fire);
//        switch (category) {
//            case "ordinaryСombustibles" -> {
//                Fire fire = new Fire(answer);
//                OrdinaryCombustible ordinaryCombustible = new OrdinaryCombustible();
//                workWithKieSession(kieSession, ordinaryCombustible);
//            }
//            case "flammableAndCombustibleLiquids" -> {
//                FlammableLiquid flammableLiquid = new FlammableLiquid();
//                workWithKieSession(kieSession, flammableLiquid);
//            }
//            case "energizedElectronicalEquipment" -> {
//                EnergizedEquipment energizedEquipment = new EnergizedEquipment();
//                workWithKieSession(kieSession, energizedEquipment);
//            }
//            case "combustibleMetals" -> {
//                CombustibleMetal combustibleMetal = new CombustibleMetal();
//                workWithKieSession(kieSession, combustibleMetal);
//            }
//            default -> {
//                System.out.println("Неизвестный фактор");
//            }
//        }
        if (!result.isFireTypeInit()) {
            return false;
        }

        workWithKieSession(kieSession, result.getFire());

        if (result.isFireExtinguisherTypeInit()) {
            showSolution(result);
        }
        return true;
    }

    private boolean parseFactValue(String answer) {
        if (answer.equals("выход")) System.exit(0);
        if (!validInputValue.contains(answer)) {
            System.out.println("Недопустимое значение");
            return false;
            //  throw new IllegalArgumentException();
        }
        return true;
    }

    private void workWithKieSession(KieSession kieSession, Object ob) {
        kieSession.insert(ob);
        kieSession.fireAllRules();
    }

    private void showSolution(Result result) {
        switch (result.getFire().getType()) {
            case TYPE_A -> {
                System.out.println("Решение : огнетушитель А");
            }
            case TYPE_B -> {
                System.out.println("Решение : огнетушитель B");
            }
            case TYPE_C -> {
                System.out.println("Решение : огнетушитель C");
            }
            case TYPE_D -> {
                System.out.println("Решение : огнетушитель D");
            }
        }
    }

    private String readNextOperation(Scanner scanner) {
        System.out.print("Хотите выйти? : ");
        return scanner.next();
    }

}
