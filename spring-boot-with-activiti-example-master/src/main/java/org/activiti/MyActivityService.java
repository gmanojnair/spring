package org.activiti;

import org.activiti.engine.delegate.DelegateExecution;

public class MyActivityService implements org.activiti.engine.delegate.JavaDelegate{
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		 System.out.println("service stated ..." + execution.getCurrentActivityName());
		 System.out.println("Call web service or send to JMS queue one completed rest or listener call complete");
		 // In new version we can use signal to continue next service
		
	}

}
