package com.nogroup.booster.builders.classBuilders.factors;

import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JMod;
import com.nogroup.booster.builders.classBuilders.AbstractEnumBuilder;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class FactorBuilder extends AbstractEnumBuilder {

	public FactorBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public String archetype() {
		return "Factor";
	}

	@Override
	public String packageName() {
		return getCm().rootPkg + ".backend.data.factors.singular";
	}

	@Override
	public int modifiers() {
		return JMod.PUBLIC;
	}

	@Override
	public void annotate(JDefinedClass cls) {
		// NOTHING TO ADD HERE
	}

	@Override
	public void javadoc(JDefinedClass cls) {
		// NOTHING TO ADD HERE
	}

	@Override
	public void buildMethods(JDefinedClass cls) {
		// NOTHING TO ADD HERE
	}

	@Override
	public void implements_(JDefinedClass cls) {
		// NOTHING TO ADD HERE
	}

}
