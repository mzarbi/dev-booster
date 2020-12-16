package com.nogroup.booster.flows;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.helger.jcodemodel.JCodeModelException;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JVar;
import com.nogroup.booster.builders.AbstractFolderBuilder;
import com.nogroup.booster.builders.AbstractPomBuilder;
import com.nogroup.booster.builders.AbstractPropertiesFileBuilder;
import com.nogroup.booster.builders.ClassBuilder;
import com.nogroup.booster.codeModel.CodeModel;
import com.nogroup.booster.descriptors.maven.MavenDescriptors;
import com.nogroup.booster.work.annotations.Message;
import com.nogroup.booster.work.annotations.Order;

public interface MavenPool {
	
	public MavenDescriptors descriptors() ;
	public CodeModel cm();
	
	@Order(0)
	@Message("Building Project Structure")
	public default void buildProjectStructure() throws IOException {
		new AbstractFolderBuilder()
			.addFolderFromPackage(descriptors().getParentFolder(), "src.main.java." + descriptors().getGroupId())
			.addFolderFromPackage(descriptors().getParentFolder(), "src.main.resources.META-INF.resources.icon")
			.addFolderFromPackage(descriptors().getParentFolder(), "src.main.resources.META-INF.resources.images")
			.build();
	}

	@Order(1)
	public default void buildingPomFile() throws JsonMappingException, JsonProcessingException, IOException {
		new AbstractPomBuilder() {
			
			@Override
			public String parentDirectory() throws JsonMappingException, JsonProcessingException, IOException {
				return descriptors().getParentFolder();
			}
		}
		
		.fromTemplateFile(descriptors().getPomTemplate())
		.setGroupId(descriptors().getGroupId())
		.setArtifactId(descriptors().getArtifactId())
		.setName(descriptors().getArtifactId())
		.setVersion("1.0.0")
		.build();
	}

	@Order(2)
	public default void buildingApplicationProperties() throws JsonMappingException, JsonProcessingException, IOException {
		new AbstractPropertiesFileBuilder() {
			
			@Override
			public String outputPath() {
				return resPath() + "\\application.properties";
			}
		}
		.fromTemplateFile(descriptors().getPropertiesTemplate())
		.build();
	}
	
	@Order(3)
	@Message("Pre-Building Application Class")
	public default void buildApplicationClass() throws JCodeModelException {
		new ClassBuilder(cm()) {
			
			@Override
			public String packageName() {
				return descriptors().getGroupId();
			}
			
			@Override
			public String name() {
				return "Application";
			}
			
			@Override
			public void buildDefaultMethods(JDefinedClass cls) {
				JMethod md = cls.method(JMod.PUBLIC|JMod.STATIC, getCm().VOID, "main") ;
				JVar p1 = md.param(getCm()._ref(String.class).array(), "args") ;
				md.body().add(
					getCm()._ref(SpringApplication.class).boxify().staticInvoke("run")
					.arg(cls.dotclass())
					.arg(p1)
				) ;
			}
			
			@Override
			public String archetype() {
				return "Entry Point";
			}
			
			@Override
			public void annotate(JDefinedClass cls) {
				cls.annotate(SpringBootApplication.class) ;
			}
		}.prebuild();
		
		cm().rootPkg = descriptors().getGroupId() ;
	}
	
	@Order(Integer.MAX_VALUE)
	@Message("Building All Classes")
	public default void buildAllClasses() throws JCodeModelException, IOException {

		for (String tmp : new String[] { "Entity Utils", "Factor", "Factor Collection", "Embedded",
				"Embedded Collection", "Converter", "Entity", "DTO", "Repository", "Service", "Controller",
				"Controller Utils", "Configuration", "Entry Point", "View", "Application","Unspecified" }) {
			cm().builders.stream().filter(p -> p.archetype().equals(tmp))
					.collect(Collectors.toList()).forEach(p -> p.build());
		}
		cm().build(new File(javaPath()));
	}
	
	public default String javaPath() {
		return descriptors().getParentFolder() + "/src/main/java" ;
	}
	
	public default String resPath(){
		return descriptors().getParentFolder() + "/src/main/resources" ;
	}
}
