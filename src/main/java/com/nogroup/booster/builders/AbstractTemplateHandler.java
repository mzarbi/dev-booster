package com.nogroup.booster.builders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;

public abstract class AbstractTemplateHandler extends AbstractFileBuilder {

	
	private Jinjava jinjava = new Jinjava();
	private Map<String, Object> context = Maps.newHashMap();
	
	public String template() {
		
		if(isFullPath()) {
			String content = "" ;
			try {
				content = new String(Files.readAllBytes(Paths.get(templateFile()))) ;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return content ;
		}else {
			try {
				return Resources.toString(Resources.getResource("templates/" + templateFile()), Charsets.UTF_8);
			} catch (IOException e) {
				System.err.println("Could not open template file : " + "/templates/" +  templateFile());
				e.printStackTrace();
				System.exit(-1);
			}
			return "";
		}
		
	}
	
	public abstract String templateFile();
	public boolean isFullPath() {
		return false;
	}
	public abstract String outputFile();

	@Override
	public void build() {
		String renderedTemplate = jinjava.render(template(), context);
		try {
			Files.write(Paths.get(outputFile()), renderedTemplate.getBytes());
		} catch (IOException e) {
			System.err.println("Could not render template in file : " + outputFile());
			e.printStackTrace();
		}
	}

	

	public Jinjava getJinjava() {
		return jinjava;
	}

	public Map<String, Object> getContext() {
		return context;
	}

	public boolean containsKey(Object arg0) {
		return context.containsKey(arg0);
	}

	public boolean containsValue(Object arg0) {
		return context.containsValue(arg0);
	}

	public Object get(Object arg0) {
		return context.get(arg0);
	}

	public Set<String> keySet() {
		return context.keySet();
	}

	public AbstractTemplateHandler put(String arg0, Object arg1) {
		context.put(arg0, arg1);
		return this ;
	}

	public Object remove(Object arg0) {
		return context.remove(arg0);
	}

	public int size() {
		return context.size();
	}

	public Collection<Object> values() {
		return context.values();
	}
	
}
