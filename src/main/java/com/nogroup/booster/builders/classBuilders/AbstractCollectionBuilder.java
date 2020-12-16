package com.nogroup.booster.builders.classBuilders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JForEach;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JVar;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class AbstractCollectionBuilder extends AttributeLessClassBuilder{

	public AbstractCollectionBuilder(CodeModel zm) {
		super(zm);
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
	public void buildConstants(JDefinedClass cls) {
		// NOTHING TO ADD HERE
	}

	@Override
	public void extends_(JDefinedClass cls) {
		cls._extends(getCm().ref(ArrayList.class).narrow(bound())) ;
	}

	public abstract AbstractJClass bound() ;

	@Override
	public void implements_(JDefinedClass cls) {
		cls._implements(Serializable.class) ;
	}

	public void createFilterByMethod(JDefinedClass cl,JDefinedClass type, JFieldVar var) {
		JMethod filter = cl.method(JMod.PUBLIC, cl, "filterBy" + capitalize(var.name())) ;
		JVar p = filter.param(var.type(), "val") ;
		JVar tmps = filter.body().decl(cl, "tmps",JExpr._new(cl)) ;
		JForEach fr = filter.body().forEach(type, "tmp",JExpr._this()) ;
		fr.body()._if(fr.var().invoke("get"+capitalize(var.name())).invoke("equals").arg(p))._then().add(tmps.invoke("add").arg(fr.var())) ;
		filter.body()._return(tmps) ;
	}
	
	public static void createFindByMethod(JDefinedClass cl,JDefinedClass type, JFieldVar var) {
		JMethod filter = cl.method(JMod.PUBLIC, type, "findBy" + capitalize(var.name())) ;
		JVar p = filter.param(var.type(), "val") ;
		
		filter.body()._return(
			JExpr._this().invoke(
				"filterBy" + capitalize(
					var.name()
				)
			).arg(p).invoke("getFirst")
		) ;
	}
	
	public void createClusterByMethod(JDefinedClass cl,JDefinedClass type, JFieldVar var) {
		if(var.type() == null) {
			return ;
		}
		JMethod filter = cl.method(JMod.PUBLIC, getCm().ref(Map.class).narrow(var.type()).narrow(cl), "clusterBy" + capitalize(var.name())) ;
		
		
		JVar tmps = filter.body().decl(getCm().ref(Map.class).narrow(var.type()).narrow(cl), "tmps",
			JExpr._new(getCm().ref(HashMap.class).narrow(var.type()).narrow(cl))) ;
		JForEach fr = filter.body().forEach(var.type(), "tmp", JExpr.invoke("projectOver" + capitalize(var.name()) )) ;
		fr.body().add(tmps.invoke("put").arg(fr.var()).arg(JExpr._this().invoke("filterBy" + capitalize(var.name())).arg(fr.var())
				
		));
		filter.body()._return(tmps) ;
	}
	
	public void createProjectOverMethodMethod(JDefinedClass cl,JDefinedClass type, JFieldVar var) {
		if(var.type() == null) {
			return ;
		}
		if(var.type().boxify()._package().name().contains("singular")) {
			AbstractJClass ccol = getCm().resolve("" + var.type().name() + "C").boxify() ;
			JMethod filter = cl.method(JMod.PUBLIC,ccol, "projectOver" + capitalize(var.name())) ;
			JVar tmps = filter.body().decl(ccol, "tmps",
				JExpr._new(ccol).arg(
					JExpr._new(getCm().ref(HashSet.class).narrow(var.type())).arg(JExpr._this().invoke("select" + capitalize(var.name())))
				)
			) ;
			filter.body()._return(tmps) ;
		}else {
			JMethod filter = cl.method(JMod.PUBLIC, getCm().ref(List.class).narrow(var.type()), "projectOver" + capitalize(var.name())) ;
			JVar tmps = filter.body().decl(getCm().ref(List.class).narrow(var.type()), "tmps",
				JExpr._new(getCm().ref(ArrayList.class).narrow(var.type())).arg(
					JExpr._new(getCm().ref(HashSet.class).narrow(var.type())).arg(JExpr._this().invoke("select" + capitalize(var.name())))
				)
			) ;
			
			filter.body()._return(tmps) ;
			
		}
	}
	public void createSelectMethod(JDefinedClass cl,JDefinedClass type, JFieldVar var) {
		if(var.type() == null) {
			return ;
		}
		String pkg = var.type().boxify()._package().name() ;
		
		if(pkg.contains("singular")) {
			AbstractJClass colc = getCm().resolve("" + var.type().name() + "C").boxify() ;
			System.out.println(var.type());
			JMethod filter = cl.method(JMod.PUBLIC, colc, "select" + capitalize(var.name())) ;
			JVar tmps = filter.body().decl(colc, "tmps",
				JExpr._new(colc)
			) ;
			JForEach fr = filter.body().forEach(type, "tmp",JExpr._this()) ;
			fr.body().add(tmps.invoke("add").arg(fr.var().invoke("get" + capitalize(var.name())))) ;
			filter.body()._return(tmps) ;
		}else {
			JMethod filter = cl.method(JMod.PUBLIC, getCm().ref(List.class).narrow(var.type()), "select" + capitalize(var.name())) ;
			JVar tmps = filter.body().decl(getCm().ref(List.class).narrow(var.type()), "tmps",
				JExpr._new(getCm().ref(ArrayList.class).narrow(var.type()))
			) ;
			JForEach fr = filter.body().forEach(type, "tmp",JExpr._this()) ;
			fr.body().add(tmps.invoke("add").arg(fr.var().invoke("get" + capitalize(var.name())))) ;
			filter.body()._return(tmps) ;
		}
		
	}
}
