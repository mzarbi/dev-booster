package com.nogroup.booster.builders.classBuilders.cvrtrs;

import javax.persistence.AttributeConverter;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JMod;
import com.nogroup.booster.builders.AbstractClassBuilder;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class AbstractConverterBuilder extends AbstractClassBuilder {

	public AbstractConverterBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public String archetype() {
		return "Converter";
	}

	@Override
	public EClassType type() {
		return EClassType.CLASS;
	}

	@Override
	public int modifiers() {
		return JMod.PUBLIC;
	}

	@Override
	public void annotate(JDefinedClass cls) {
		
	}

	@Override
	public void javadoc(JDefinedClass cls) {
		
	}

	@Override
	public void buildConstants(JDefinedClass cls) {
		
	}

	@Override
	public void buildAttributes(JDefinedClass cls) {
		
	}

	@Override
	public void buildMethods(JDefinedClass cls) {
		
	}

	@Override
	public void buildDefaultMethods(JDefinedClass cls) {
		initConvertToDatabaseColumn(cls,target()) ;
		initConvertToEntityAttribute(cls,target());
	}

	@Override
	public void extends_(JDefinedClass cls) {
		
	}

	@Override
	public void implements_(JDefinedClass cls) {
		cls._implements(
				getCm().ref(
					AttributeConverter.class
				).narrow(
					target()
				).narrow(String.class)
			) ;
	}

	public abstract void initConvertToEntityAttribute(JDefinedClass cls, AbstractJClass target);
	public abstract void initConvertToDatabaseColumn(JDefinedClass cls,AbstractJClass target);
	public abstract AbstractJClass target();

}
