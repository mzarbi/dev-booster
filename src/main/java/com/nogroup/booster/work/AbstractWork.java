package com.nogroup.booster.work;

import java.util.concurrent.TimeUnit;

public abstract class AbstractWork implements Runnable,WorkRunnable{

	private int indent = 0;
	
	public AbstractWork(int indent) {
		super();
		this.indent = indent;
	}
	
	public void run() {
		
		beforeDelay() ;
		try {
			TimeUnit.MILLISECONDS.sleep(delay());
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		afterDelayBeforeExecution() ;
		try {
			execute();
		} catch (Exception e) {
			onExecutionError(e) ;
		}
		onExecutionComplete() ;
	}
	
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
}
