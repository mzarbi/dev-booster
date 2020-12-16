package com.nogroup.booster.builders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AbstractFolderBuilder extends AbstractElementBuilder {

	
	private List<String> list = new ArrayList<String>() ;
	
	@Override
	public void build() {
		for(String tmp : list) {
			try {
				createFolder(tmp) ;
			} catch (IOException e) {
				System.err.println("Could not create folder : " + tmp) ;
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	public static String createFolder(String pth) throws IOException {
		Path path = Paths.get(pth);
		Files.createDirectories(path);
		return pth ;
	}
		
	public AbstractFolderBuilder addFolder(String arg1) {
		list.add(arg1);
		return this ;
	}
	
	public AbstractFolderBuilder addFolder(String ... arg1) {
		for(String tmp : arg1) {
			addFolder(tmp);
		}
		return this ;
	}
	
	public AbstractFolderBuilder addFolderFromPackage(String parent,String pkg) {
		list.add(parent + "\\" + pkg.replaceAll("\\.", "/"));
		return this ;
	}

	public boolean addAll(Collection<? extends String> arg0) {
		return list.addAll(arg0);
	}

	public void clear() {
		list.clear();
	}

	public boolean contains(Object arg0) {
		return list.contains(arg0);
	}

	public String get(int arg0) {
		return list.get(arg0);
	}

	
}
