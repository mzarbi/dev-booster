package com.nogroup.booster.pools;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.nogroup.booster.work.AbstractWork;
import com.nogroup.booster.work.Work;
import com.nogroup.booster.work.WorkDefinition;
import com.nogroup.booster.work.WorkRunnable;
import com.nogroup.booster.work.annotations.Message;
import com.nogroup.booster.work.annotations.Order;

public abstract class AbstractWorkPool extends ArrayList<AbstractWork> implements WorkRunnable{
	
	public abstract Class clazz() ;
	public void run() {
		init() ;
		beforeDelay();
		try {
			TimeUnit.MILLISECONDS.sleep(delay());
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		afterDelayBeforeExecution();
		try {
			for(AbstractWork tmp : this) {
				tmp.run();
			}
		}catch (Exception e) {
			onExecutionError(e);
		}
		onExecutionComplete();
	}

	private void init() {
		List<Method> mds = new ArrayList<Method>() ;
		
		for(Method tmp : clazz().getMethods()) {
			Optional<Order> orderAnn = Optional.ofNullable(tmp.getAnnotation(Order.class));
			orderAnn.ifPresent(p -> mds.add(tmp));
		}
		
		for(Method tmp : mds.stream().sorted(new Comparator<Method>() {

			@Override
			public int compare(Method arg0, Method arg1) {
				Double ord0 = arg0.getAnnotation(Order.class).value() ;
				Double ord1 = arg1.getAnnotation(Order.class).value() ;
				return ord0.compareTo(ord1);
			}
		}).collect(Collectors.toList())) {
			add(new Work(getIndent() + 1) {
				
				@Override
				public void execute() throws Exception {
					tmp.invoke(AbstractWorkPool.this) ;
				}
				
				@Override
				public String label() {
					if(tmp.getAnnotation(Message.class) != null) {
						return tmp.getAnnotation(Message.class).value();
					}
					return camelCaseSplit(tmp.getName());
				}
			});
		}
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
			System.out.println(indent() + "[INFO] " + label());
		}
	}

	public void beforeDelay() {
	}
	
	public AbstractWorkPool addWork(WorkDefinition definition) {
		Work wrk = new Work(getIndent() + 1) {
			
			@Override
			public void execute() throws Exception {
				definition.function();
			}
			
			@Override
			public String label() {
				return definition.label();
			}
		};
		add(wrk);
		return this ;
	}
	
	public String camelCaseSplit(String name) {
		String[] camelCaseWords = name.split("(?=[A-Z0-9])");
		String ss = "" ;
		for(String tmp : camelCaseWords) {
			ss += tmp + " " ;
		}
		ss = ss.substring(0,ss.length()-1) ;
		return ss;
	}
}
