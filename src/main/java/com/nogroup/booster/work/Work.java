package com.nogroup.booster.work;

public abstract class Work extends AbstractWork{

	public Work(int indent) {
		super(indent);
	}

	public void onExecutionComplete() {
		
	}

	public void onExecutionError(Exception e) {
		if(decorate()) {
			System.out.println(indent() + "[ERROR] " + e.getCause().getMessage());
		}
		e.printStackTrace();
		System.exit(0); 
	}

	public void afterDelayBeforeExecution() {
		if(decorate()) {
			System.out.println(indent() + "[INFO] " + label());
		}
	}

	public void beforeDelay() {
	}
	
	
	public abstract String label() ; 
}
