package com.nogroup.booster.codeModel;

import java.util.Date;

import javax.annotation.Nonnull;

import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.JCodeModelException;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JPackage;

public class Package extends JPackage{

	/**
	 * @author medzi
	 */
	private static final long serialVersionUID = 1L;
	
	protected Package(String sName, CodeModel aOwner) {
		super(sName, aOwner);
	}
	
	@Nonnull
	public JDefinedClass _enum(@Nonnull final String sName) throws JCodeModelException {
		JDefinedClass cl = _enum(JMod.PUBLIC, sName);
		((CodeModel)owner()).getClasses().put(sName, cl) ;
		
		cl.javadoc().add("Generated by DevBooster Module.\n");
		cl.javadoc().add("Developer   : medzied.arbi@gmail.com\n");
		cl.javadoc().add("Date        : " + new Date().toGMTString() + "\n");
		cl.javadoc().add("Archetype   : Factor\n");

		return cl;
	}

	@Nonnull
	public JDefinedClass _class(@Nonnull final String sName,String archetype) throws JCodeModelException {
		JDefinedClass cl = _class(JMod.PUBLIC, sName);
		((CodeModel)owner()).getClasses().put(sName, cl) ;
		
		cl.javadoc().add("Archetype   : " + archetype + "\n");
		return cl;
	}
	
	@Nonnull
	public JDefinedClass _annotationTypeDeclaration(@Nonnull final String sName) throws JCodeModelException {
		JDefinedClass cl = _annotationTypeDeclaration(JMod.PUBLIC, sName);
		((CodeModel)owner()).getClasses().put(sName, cl) ;
		return cl;
	}
	
	@Nonnull
	public JDefinedClass _annotationTypeDeclaration(@Nonnull final String sName,String archetype) throws JCodeModelException {
		JDefinedClass cl = _annotationTypeDeclaration(JMod.PUBLIC, sName);
		((CodeModel)owner()).getClasses().put(sName, cl) ;
		cl.javadoc().add("Archetype   : " + archetype + "\n");
		return cl;
	}
	
	@Nonnull
	public JDefinedClass _interface(@Nonnull final String sName,String label) throws JCodeModelException {
		JDefinedClass cl = _interface(JMod.PUBLIC, sName,label);
		((CodeModel)owner()).getClasses().put(sName, cl) ;
		return cl;
	}
	
	@Nonnull
	public JDefinedClass _interface(@Nonnull final String sName) throws JCodeModelException {
		JDefinedClass cl = _interface(JMod.PUBLIC, sName);
		((CodeModel)owner()).getClasses().put(sName, cl) ;
		
		cl.javadoc().add("Generated by DevBooster Module.\n");
		cl.javadoc().add("Developer   : medzied.arbi@gmail.com\n");
		cl.javadoc().add("Date        : " + new Date().toGMTString() + "\n");
		cl.javadoc().add("Archetype   : Boundary\n");
		return cl;
	}
	
	@Nonnull
	public JDefinedClass _enum(final int nMods, @Nonnull final String sName) throws JCodeModelException {
		JDefinedClass cl = _class (nMods, sName, EClassType.ENUM);
		((CodeModel)owner()).getClasses().put(sName, cl) ;
		return cl;
	}

	@Nonnull
	public JDefinedClass _class(final int nMods, @Nonnull final String sName,String archetype) throws JCodeModelException {
		JDefinedClass cl = _class (nMods, sName, EClassType.CLASS);
		((CodeModel)owner()).getClasses().put(sName, cl) ;
		
		cl.javadoc().add("Generated by DevBooster Module.\n");
		cl.javadoc().add("Developer   : medzied.arbi@gmail.com\n");
		cl.javadoc().add("Date        : " + new Date().toGMTString() + "\n");
		cl.javadoc().add("Archetype   : " + archetype + "\n");
		return cl;
	}
	
	@Nonnull
	public JDefinedClass _class(final int nMods, @Nonnull final String sName) throws JCodeModelException {
		JDefinedClass cl = _class (nMods, sName, EClassType.CLASS);
		((CodeModel)owner()).getClasses().put(sName, cl) ;
		
		cl.javadoc().add("Generated by DevBooster Module.\n");
		cl.javadoc().add("Developer   : medzied.arbi@gmail.com\n");
		cl.javadoc().add("Date        : " + new Date().toGMTString() + "\n");
		return cl;
	}
	
	@Nonnull
	public JDefinedClass _interface(final int nMods, @Nonnull final String sName) throws JCodeModelException {
		JDefinedClass cl = _class (nMods, sName, EClassType.INTERFACE);
		((CodeModel)owner()).getClasses().put(sName, cl) ;
		
		cl.javadoc().add("Generated by DevBooster Module.\n");
		cl.javadoc().add("Developer   : medzied.arbi@gmail.com\n");
		cl.javadoc().add("Date        : " + new Date().toGMTString() + "\n");
		cl.javadoc().add("Archetype   : Boundary\n");
		return cl;
	}
	
	@Nonnull
	public JDefinedClass _interface(final int nMods, @Nonnull final String sName,String label) throws JCodeModelException {
		JDefinedClass cl = _class (nMods, sName, EClassType.INTERFACE);
		((CodeModel)owner()).getClasses().put(sName, cl) ;
		
		cl.javadoc().add("Generated by DevBooster Module.\n");
		cl.javadoc().add("Developer   : medzied.arbi@gmail.com\n");
		cl.javadoc().add("Date        : " + new Date().toGMTString() + "\n");
		cl.javadoc().add("Archetype   : " + label + "\n");
		return cl;
	}
	
	@Nonnull
	public JDefinedClass _annotationTypeDeclaration(final int nMods, @Nonnull final String sName) throws JCodeModelException {
		JDefinedClass cl = _class (nMods, sName, EClassType.ANNOTATION_TYPE_DECL);
		((CodeModel)owner()).getClasses().put(sName, cl) ;
		
		cl.javadoc().add("Generated by DevBooster Module.\n");
		cl.javadoc().add("Developer   : medzied.arbi@gmail.com\n");
		cl.javadoc().add("Date        : " + new Date().toGMTString() + "\n");
		return cl;
	}
}