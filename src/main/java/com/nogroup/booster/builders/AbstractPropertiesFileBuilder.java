package com.nogroup.booster.builders;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;

public abstract class AbstractPropertiesFileBuilder extends AbstractFileBuilder{
	
	private Map<String, Object> context = Maps.newHashMap();
	private Properties properties = new Properties();
	
	public void build() {
		try {
			for(String tmp : context.keySet()) {
				properties.setProperty(tmp, "" + get(tmp));
			}
			
			File file = new File(outputPath());
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "Properties");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract String outputPath();
	
	public AbstractPropertiesFileBuilder fromTemplate(String templateFile) {
		String res = "" ;
		try {
			res = Resources.toString(Resources.getResource("templates/" + templateFile), Charsets.UTF_8);
		} catch (IOException e) {
			System.err.println("Could not open template file : " + "/templates/" +  templateFile);
			e.printStackTrace();
			System.exit(-1);
		}
		InputStream targetStream = new ByteArrayInputStream(res.getBytes());
		try {
			properties.load(targetStream);
		} catch (IOException e) {
			System.err.println("Could not load properties from file : " + "/templates/" +  templateFile);
			e.printStackTrace();
			System.exit(-1);
		}
		return this;
	}
	
	public AbstractPropertiesFileBuilder fromTemplateFile(String templateFile) {
		InputStream targetStream = null;
		try {
			targetStream = new FileInputStream(new File(templateFile));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			properties.load(targetStream);
		} catch (IOException e) {
			System.err.println("Could not load properties from file : " + "/templates/" +  templateFile);
			e.printStackTrace();
			System.exit(-1);
		}
		return this;
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

	public AbstractPropertiesFileBuilder put(String arg0, Object arg1) {
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
