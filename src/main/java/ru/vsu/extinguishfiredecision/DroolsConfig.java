package ru.vsu.extinguishfiredecision;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {
    private static final String FIRE_TYPE_A_RULE = "rules/fire-type-rule.drl";

    private static final String FIRE_EXTINGUISHERS_TYPE_A_RULE = "rules/fire-extinguishers-types-rule.drl";

    private static final KieServices kieServices = KieServices.Factory.get();

    @Bean
    public KieContainer kieContainer() {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(FIRE_TYPE_A_RULE));

        kieFileSystem.write(ResourceFactory.newClassPathResource(FIRE_EXTINGUISHERS_TYPE_A_RULE));

        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        return kieServices.newKieContainer(kb.getKieModule().getReleaseId());
    }

}
