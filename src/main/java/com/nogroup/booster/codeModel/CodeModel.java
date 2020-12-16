package com.nogroup.booster.codeModel;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.writer.AbstractCodeWriter;
import com.helger.jcodemodel.writer.FileCodeWriter;
import com.nogroup.booster.builders.AbstractClassBuilder;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class CodeModel extends JCodeModel {

	/**
	 * @author medzi
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, JDefinedClass> classes = new HashMap<String, JDefinedClass>() ;
	public String rootPkg = "" ;
	public List<AbstractClassBuilder> builders = new ArrayList<AbstractClassBuilder>(); 
	
	public Map<String, JPackage> pkgs() throws Exception {
		Field nameField = JCodeModel.class.getDeclaredField("m_aPackages");
		nameField.setAccessible(true);

		return (Map<String, JPackage>) nameField.get(this);
	}
	
	@Override
	@Nonnull
	public JPackage _package(@Nonnull final String sName) {
		try {
			return pkgs().computeIfAbsent(sName, k -> newPackage(k, this));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Nonnull
	public Package _package(@Nonnull final String sName,String label) {
		try {
			return (Package) pkgs().computeIfAbsent(sName, k -> newPackage(k, this,label));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Package newPackage(String k, CodeModel ZCodeModel,String label) {
		Package d = new Package(k, ZCodeModel) ;
		String nm = d.name().split("\\.")[d.name().split("\\.").length-1] ;
		String output = nm.substring(0, 1).toUpperCase() + nm.substring(1);
		return d ;
	}
	public Package newPackage(String k, CodeModel ZCodeModel) {
		Package d = new Package(k, ZCodeModel) ;
		return d ;
	}
	public AbstractJType resolve(String ss) {
		
		if(ss.equals("List<Integer>")) {
			return _ref(List.class).boxify().narrow(Integer.class) ;
		}
		if(ss.contains(":")) {
			String[] sps = ss.split(":") ;
			AbstractJClass m1 = (AbstractJClass) resolve(sps[0]) ;
			for(String tmp : sps[1].split(";")) {
				m1.narrow(resolve(tmp)) ;
			}
			return m1 ;
		}
		if(ss.equals("Serializable")) {
			return ref(Serializable.class) ;
		}if(ss.equals("Integer")) {
			return ref(Integer.class) ;
		}if(ss.equals("Object")) {
			return ref(Object.class) ;
		}if(ss.equals("int")) {
			return INT.boxify() ;
		}if(ss.equals("boolean")) {
			return BOOLEAN.boxify() ;
		}else if(ss.equals("Double")) {
			return ref(Double.class) ;
		}else if(ss.equals("File")) {
			return ref(File.class) ;
		}else if(ss.equals("Float")) {
			return ref(Float.class) ;
		}else if(ss.equals("Long")) {
			return ref(Long.class) ;
		}else if(ss.equals("String")) {
			return ref(String.class) ;
		}else if(ss.equals("Date")) {
			return ref(Date.class) ;
		}else if(ss.equals("Boolean")) {
			return ref(Boolean.class) ;
		}else if(ss.equals("Image")) {
			return ref(Image.class) ;
		}else if(ss.equals("List")) {
			return ref(List.class) ;
		}else if(ss.equals("Set")) {
			return ref(Set.class) ;
		}else if(ss.equals("HashSet")) {
			return ref(HashSet.class) ;
		}else if(ss.equals("ArrayList")) {
			return ref(ArrayList.class) ;
		}else if(ss.equals("TextField")) {
			return ref(TextField.class) ;
		}else if(ss.equals("PasswordField")) {
			return ref(PasswordField.class) ;
		}else if(ss.equals("ComboBox")) {
			return ref(ComboBox.class) ;
		}else if(ss.equals("TextArea")) {
			return ref(TextArea.class) ;
		}else if(ss.equals("Div")) {
			return ref(Div.class) ;
		}
		
		for(String tmp : classes.keySet()) {
			if(ss.equals(tmp)) {
				return classes.get(tmp) ;
			}
		}
		return null ;
	}


	public Charset m_aBuildingCharset() throws Exception {
		Field nameField = JCodeModel.class.getDeclaredField("m_aBuildingCharset");
		nameField.setAccessible(true);
		return (Charset) nameField.get(this);
	}
	
	public String m_sBuildingNewLine() throws Exception {
		Field nameField = JCodeModel.class.getDeclaredField("m_sBuildingNewLine");
		nameField.setAccessible(true);
		return (String) nameField.get(this);
	}
	@Override
	public void build (@Nonnull final File aSrcDir,
            @Nonnull final File aResourceDir,
            @Nullable final PrintStream aStatusPS) throws IOException
	{
		
		try {
			AbstractCodeWriter res = new FileCodeWriter(aResourceDir, m_aBuildingCharset(), m_sBuildingNewLine());
			AbstractCodeWriter src = new FileCodeWriter(aSrcDir, m_aBuildingCharset(), m_sBuildingNewLine());
			if (aStatusPS != null) {
				src = new ProgressCodeWriter_v2(src, aStatusPS::println);
				res = new ProgressCodeWriter_v2(res, aStatusPS::println);
			}
			build(src, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public Map<String, JDefinedClass> getClasses() {
		return classes;
	}

	public Class<?> implementation(Object col) {
		if(col.equals("Set")) {
			return HashSet.class ;
		}
		if(col.equals("List")) {
			return ArrayList.class ;
		}
		return null;
	}
	
	
}
