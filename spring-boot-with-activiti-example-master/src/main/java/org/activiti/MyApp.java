package org.activiti;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// @EnableWebMvc
// @ComponentScan(basePackages = "org.activiti")
// @EnableAutoConfiguration
@SpringBootApplication
//@ContextConfiguration("classpath:spring-test-application-context.xml")
public class MyApp {

    public static void main(String[] args) {
    	ApplicationContext context = SpringApplication.run(MyApp.class, args);
        ProcessEngine engine = context.getBean(ProcessEngine.class);

		 Map<String, Object> variables = new HashMap<String, Object>();
		 variables.put("applicantName", "John Doe");
		 variables.put("email", "john.doe@activiti.com");
		 variables.put("phoneNumber", "123456789");
		 engine.getRuntimeService().startProcessInstanceByKey("hireProcess",
		 variables);

		System.out.println("Process iengine started ... ");
    }

//    @Bean
//    public CommandLineRunner init(final RepositoryService repositoryService,
//                                  final RuntimeService runtimeService,
//                                  final TaskService taskService) {
//
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... strings) throws Exception {
//                Map<String, Object> variables = new HashMap<String, Object>();
//                variables.put("applicantName", "John Doe");
//                variables.put("email", "john.doe@activiti.com");
//                variables.put("phoneNumber", "123456789");
//                runtimeService.startProcessInstanceByKey("hireProcess", variables);
//            }
//        };
//
//    }

    @Bean
    InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

        return new InitializingBean() {
            public void afterPropertiesSet() throws Exception {

                Group group = identityService.newGroup("user");
                group.setName("users");
                group.setType("security-role");
                identityService.saveGroup(group);

                User admin = identityService.newUser("admin");
                admin.setPassword("admin");
                identityService.saveUser(admin);

            }
        };
    }

}
