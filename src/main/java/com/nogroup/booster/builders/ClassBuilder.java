package com.nogroup.booster.builders;

import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JMod;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class ClassBuilder extends AbstractClassBuilder {

	public ClassBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public String archetype() {
		return "UNSPECIFIED";
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
		// TODO Auto-generated method stub

	}

	@Override
	public void javadoc(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildConstants(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildAttributes(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildMethods(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildDefaultMethods(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void extends_(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void implements_(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

}
