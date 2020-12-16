package com.nogroup.booster.builders.classBuilders.cvrtrs;

import com.nogroup.booster.codeModel.CodeModel;

public abstract class EmbeddedCollectionConverterBuilder extends EmbeddConverterBuilder {

	public EmbeddedCollectionConverterBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public String packageName() {
		return getCm().rootPkg + ".backend.data.converters.collection";
	}

}
