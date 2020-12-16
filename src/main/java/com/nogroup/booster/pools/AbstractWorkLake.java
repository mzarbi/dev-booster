package com.nogroup.booster.pools;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.nogroup.booster.work.WorkPoolDefinition;
import com.nogroup.booster.work.WorkRunnable;

public abstract class AbstractWorkLake extends ArrayList<AbstractWorkPool> implements Runnable,WorkRunnable{

	public void run() {
		beforeDelay();
		try {
			TimeUnit.MILLISECONDS.sleep(delay());
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		afterDelayBeforeExecution();
		try {
			for(WorkRunnable tmp : this) {
				tmp.run();
			}
		}catch (Exception e) {
			onExecutionError(e);
		}
		onExecutionComplete();
	}

	public void execute() throws Exception {
		
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
			System.out.println(indent() + "###### " + label() + " ######");
		}
	}

	public void beforeDelay() {
	}
	
	public AbstractWorkLake addWorkPool(AbstractWorkPool definition){
		add(definition);
		return this ;
	}

	@Override
	public int getIndent() {
		return 0;
	}
}
