package com.nogroup.booster.builders.classBuilders;

import com.helger.jcodemodel.JDefinedClass;
import com.nogroup.booster.builders.AbstractClassBuilder;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class AttributeLessClassBuilder extends AbstractClassBuilder {

	public AttributeLessClassBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public void buildAttributes(JDefinedClass cls) {
		// NOTHING TO BE ADDED HERE
	}
}
