package com.nogroup.booster.codeModel;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

public class BoosterUtils {

	public static boolean isEmpty(String ss) {
		return (ss == null || ss.trim().isEmpty() || ss.equals("null"));
	}

	public static String createFolder(String pth) throws IOException {
		Path path = Paths.get(pth);
		Files.createDirectories(path);
		return pth ;
	}
	
	public static Model pomToModel() throws Exception {
		ClassLoader classLoader = BoosterUtils.class.getClassLoader();
		 
        try (InputStream inputStream = classLoader.getResourceAsStream("templates/pom.tpl")) {
        	BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
    	    MavenXpp3Reader reader = new MavenXpp3Reader();
    	    Model model = reader.read(in);
    	    return model;	 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;   
	}
	
	public static String readFileAsString(String filePath) {
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	public static void WriteFileFromString(String ss, String fileName) throws IOException {
	    FileOutputStream outputStream = new FileOutputStream(fileName);
	    byte[] strToBytes = ss.getBytes();
	    outputStream.write(strToBytes);
	    outputStream.close();
	}
	
	public static String pkg2pth(String ss) {
		return ss.replaceAll("\\.", "//") ;
	}
}
