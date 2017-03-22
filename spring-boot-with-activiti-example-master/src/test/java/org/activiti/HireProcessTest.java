package org.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.subethamail.wiser.Wiser;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MyApp.class})
@WebAppConfiguration
@ContextConfiguration("classpath:spring-test-application-context.xml")
@IntegrationTest
public class HireProcessTest {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

  
    private Wiser wiser;
    
    
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Before
    public void setup() {
        wiser = new Wiser();
        wiser.setPort(1025);
        wiser.start();
    }

    @After
    public void cleanup() {
        wiser.stop();
    }

   
   
    @Deployment(resources = {"org/activiti/one-task-process.bpmn20.xml"}) 
    @Test
    public void testNewHappyPath() {
    	
    	// Test with input == true
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        runtimeService.startProcessInstanceByKey("oneTaskProcess");
        TaskService taskService = activitiRule.getTaskService();
        
        Task task0 = taskService.createTaskQuery().singleResult();
        Assert.assertEquals("my task", task0.getName());
        // user task
        taskService.complete(task0.getId());
        
       //  no service task as it gets executed without intervention
       Task task1 = taskService.createTaskQuery().singleResult();
       // Assert.assertEquals("my validation service", task1.getName());
       // taskService.complete(task1.getId());
    	
    }

}
