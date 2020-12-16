package com.nogroup.booster.flows;

import com.nogroup.booster.builders.AbstractFolderBuilder;
import com.nogroup.booster.builders.AbstractTemplateHandler;
import com.nogroup.booster.work.annotations.Message;
import com.nogroup.booster.work.annotations.Order;

public interface KubernetesPool extends MavenPool{

	@Order(8)
	@Message("Building kubernetes folders")
	public default void buildKubernetesFolders() {
		new AbstractFolderBuilder() {
		}
		.addFolder(descriptors().getParentFolder() + "\\k8")
		.build();
	}
	
	@Order(9)
	@Message("Building kubernetes files")
	public default void buildKubernetesFiles() {
		new AbstractTemplateHandler() {
			
			@Override
			public String templateFile() {
				return descriptors().getK8DeploymentTemplate();
			}
			
			@Override
			public String outputFile() {
				return descriptors().getParentFolder() + "\\k8" + "\\k8-deployment.yaml";
			}
			public boolean isFullPath() {return true;};
		}
		.build();
		
		new AbstractTemplateHandler() {
			
			@Override
			public String templateFile() {
				return descriptors().getNamespaceTemplate();
			}
			
			@Override
			public String outputFile() {
				return descriptors().getParentFolder() + "\\k8" + "\\namespace.yaml";
			}
			public boolean isFullPath() {return true;};
		}
		.build();
	}
}