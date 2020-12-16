package com.nogroup.booster.builders.classBuilders.embeddeds;

import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JMod;
import com.nogroup.booster.builders.classBuilders.AbstractPojoBuilder;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class EmbeddedBuilder extends AbstractPojoBuilder{

	public EmbeddedBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public void buildDefaultPojoMethods(JDefinedClass cls) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pojoImplements(JDefinedClass cls) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String archetype() {
		return "Embedded";
	}

	@Override
	public String packageName() {
		return getCm().rootPkg + ".backend.data.embedded.singular";
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

}
