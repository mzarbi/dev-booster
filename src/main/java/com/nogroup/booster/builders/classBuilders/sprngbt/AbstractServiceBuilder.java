package com.nogroup.booster.builders.classBuilders.sprngbt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JVar;
import com.nogroup.booster.builders.AbstractClassBuilder;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class AbstractServiceBuilder extends AbstractClassBuilder {

	public AbstractServiceBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public String archetype() {
		return "Service";
	}

	@Override
	public String packageName() {
		return getCm().rootPkg + ".backend.services";
	}

	@Override
	public EClassType type() {
		return EClassType.CLASS;
	}

	@Override
	public int modifiers() {
		return JMod.PUBLIC;
	}

	@Override
	public void annotate(JDefinedClass cls) {
		cls.annotate(Service.class) ;
		cls.annotate(Transactional.class) ;
	}

	@Override
	public void javadoc(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildConstants(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildAttributes(JDefinedClass cls) {
		JFieldVar fd = cls.field(JMod.PRIVATE, repository(), "repository") ;
		fd.annotate(Autowired.class) ;
	}

	public abstract AbstractJType repository();

	@Override
	public void buildMethods(JDefinedClass cls) {
		
		JMethod md = cls.method(JMod.PUBLIC, getCm().ref(List.class).narrow(entity()), "findAll") ;
		md.body()._return(JExpr.ref("repository").invoke("findAll")) ;
		
		md = cls.method(JMod.PUBLIC, getCm().VOID, "save") ;
		JVar p = md.param(entity(), "p") ;
		md.body().add(JExpr.ref("repository").invoke("save").arg(p)) ;
		
		md = cls.method(JMod.PUBLIC, entity(), "get") ;
		p = md.param(String.class, "p") ;
		md.body()._return(JExpr.ref("repository").invoke("findById").arg(p).invoke("get")) ;
		
		md = cls.method(JMod.PUBLIC, getCm().VOID, "delete") ;
		p = md.param(String.class, "p") ;
		md.body().add(JExpr.ref("repository").invoke("deleteById").arg(p)) ;
		
	}

	public abstract AbstractJType entity();

	@Override
	public void buildDefaultMethods(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void extends_(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void implements_(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

}
