package com.nogroup.booster.sample;

import com.nogroup.booster.codeModel.CodeModel;
import com.nogroup.booster.flows.MavenPoolImpl;
import com.nogroup.booster.pools.AbstractWorkLake;

public class Sample {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
		CodeModel cm = new CodeModel();
		
		new AbstractWorkLake() {

			@Override
			public String label() {
				return "Building Project";
			}
			
		}
		.addWorkPool(new MavenPoolImpl() {

			@Override
			public String descriptorsPath() {
				return "C:\\Users\\medzi\\eclipse-workspace-27-11-2020\\code-sprint\\src\\main\\resources\\maven.json";
			}

			@Override
			public CodeModel cm() {
				return cm;
			}
		})
		.run();
	}
}
