package com.nogroup.booster.flows;

import com.nogroup.booster.builders.AbstractFolderBuilder;
import com.nogroup.booster.builders.AbstractTemplateHandler;
import com.nogroup.booster.work.annotations.Message;
import com.nogroup.booster.work.annotations.Order;

public interface DockerPool extends MavenPool{
	
	@Order(5)
	@Message("Building dockerfile")
	public default void a_4() {
		new AbstractTemplateHandler() {
			
			@Override
			public String templateFile() {
				return descriptors().getDockerfileTemplate();
			}

			@Override
			public String outputFile() {
				return descriptors().getParentFolder() + "\\Dockerfile";
			}
			
			public boolean isFullPath() {return true;};
		}
		.build();
	}
	
	@Order(6)
	@Message("Building docker-compose folders")
	public default void a_5() {
		new AbstractFolderBuilder() {
		}
		.addFolder(descriptors().getParentFolder() + "\\docker-compose")
		.build();
	}
	
	@Order(7)
	@Message("Building docker-compose file")
	public default void a_6() {
		new AbstractTemplateHandler() {
			
			@Override
			public String templateFile() {
				return descriptors().getDockerComposeTemplate();
			}
			
			@Override
			public String outputFile() {
				return descriptors().getParentFolder() + "\\docker-compose" + "\\docker-compose.yml";
			}
			public boolean isFullPath() {return true;};
		}
		.build();
	}
}
