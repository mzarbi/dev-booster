package com.nogroup.booster.work;

public interface WorkRunnable {

	public void execute() throws Exception;
	public void onExecutionComplete();
	public void onExecutionError(Exception e);
	public void afterDelayBeforeExecution();
	public void beforeDelay();
	public int getIndent();
	public void run(); 
	
	public default int delay() {
		return 100;
	}
	
	public default boolean decorate() {
		return true;
	}
	
	public default String indent() {
		String ss = " " ;
		for(int i = 0 ; i < getIndent();i++) {
			ss += "\t" ;
		}
		return ss ;
	}
	public String label() ;
	
}
