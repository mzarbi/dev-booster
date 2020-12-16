package com.nogroup.booster.flows;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nogroup.booster.descriptors.maven.MavenDescriptors;
import com.nogroup.booster.pools.AbstractWorkPool;

public abstract class MavenPoolImpl extends AbstractWorkPool 
									implements DockerPool,
											   KubernetesPool,
											   SpringBootPool{

	@Override
	public Class clazz() {
		return MavenPoolImpl.class;
	}
	
	@Override
	public int getIndent() {
		return 0;
	}

	@Override
	public String label() {
		return "Building Maven Project";
	}

	@Override
	public MavenDescriptors descriptors() {
		try {
			return new ObjectMapper().readValue(new File(descriptorsPath()),MavenDescriptors.class);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	public abstract String descriptorsPath();
}
