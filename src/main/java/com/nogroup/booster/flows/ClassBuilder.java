package com.nogroup.booster.flows;

import com.nogroup.booster.codeModel.CodeModel;
import com.nogroup.booster.descriptors.maven.MavenDescriptors;

public interface ClassBuilder {

	public MavenDescriptors descriptors() ;
	public CodeModel cm();
	
	
}
