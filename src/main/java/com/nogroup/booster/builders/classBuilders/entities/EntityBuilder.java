package com.nogroup.booster.builders.classBuilders.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.JAnnotationUse;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JNarrowedClass;
import com.helger.jcodemodel.JVar;
import com.nogroup.booster.builders.classBuilders.AbstractPojoBuilder;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class EntityBuilder extends AbstractPojoBuilder {

	public EntityBuilder(CodeModel zm) {
		super(zm);
	}
	
	@Override
	public void extends_(JDefinedClass cls) {
		cls._extends(getCm().directClass("AbstractEntity")) ;
	}

	@Override
	public void buildDefaultPojoMethods(JDefinedClass cls) {

	}

	@Override
	public void pojoImplements(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public String archetype() {
		return "Entity";
	}

	@Override
	public String packageName() {
		return getCm().rootPkg + ".backend.data.entities.singular";
	}

	@Override
	public int modifiers() {
		return JMod.PUBLIC;
	}

	@Override
	public void annotate(JDefinedClass cls) {
		cls.annotate(Entity.class) ;
		JAnnotationUse ann = cls.annotate(Table.class);
		ann.param("name",JExpr.lit(cls.name().toUpperCase().replace("_E", "") + "_"));
	}

	@Override
	public void javadoc(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildMethods(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void buildDelegatedMethods(JDefinedClass cls) {
		super.buildDelegatedMethods(cls);
		
		for(JFieldVar tmp : cls.fields().values()) {
			if(tmp.type() instanceof JNarrowedClass) {
				List<? extends AbstractJClass> params = ((JNarrowedClass)tmp.type()).getTypeParameters() ;
				if(params.size() == 1) {
					AbstractJClass tp = ((JNarrowedClass)tmp.type()).getTypeParameters().get(0) ;
					
					JMethod md = cls.method(JMod.PUBLIC, getCm().BOOLEAN, "add") ;
					JVar e = md.param(tp, "e") ;
					md.body()._return(tmp.invoke("add").arg(e)) ;
					
					md = cls.method(JMod.PUBLIC, getCm().BOOLEAN, "addAll") ;
					e = md.param(getCm().ref(Collection.class).narrow(tp), "e") ;
					md.body()._return(tmp.invoke("addAll").arg(e)) ;
					
					md = cls.method(JMod.PUBLIC, getCm().BOOLEAN, "remove") ;
					e = md.param(tp, "e") ;
					md.body()._return(tmp.invoke("remove").arg(e)) ;
					
					md = cls.method(JMod.PUBLIC, getCm().VOID, "clear") ;
					md.body().add(tmp.invoke("clear")) ;
				}
			}
			
			if(tmp.type().name().endsWith("C")) {
				String name = tmp.type().name().substring(0,tmp.type().name().length() -1) ;
				AbstractJType tp = getCm().resolve(name) ;
				JMethod md = cls.method(JMod.PUBLIC, getCm().BOOLEAN, "add" + capitalize(tmp.name())) ;
				JVar e = md.param(tp, "e") ;
				md.body()._return(tmp.invoke("add").arg(e)) ;
				
				md = cls.method(JMod.PUBLIC, getCm().BOOLEAN, "addAll" + capitalize(tmp.name())) ;
				e = md.param(getCm().ref(Collection.class).narrow(tp), "e") ;
				md.body()._return(tmp.invoke("addAll").arg(e)) ;
				
				md = cls.method(JMod.PUBLIC, getCm().BOOLEAN, "remove"  + capitalize(tmp.name())) ;
				e = md.param(tp, "e") ;
				md.body()._return(tmp.invoke("remove").arg(e)) ;
				
				md = cls.method(JMod.PUBLIC, getCm().VOID, "clear"  + capitalize(tmp.name())) ;
				md.body().add(tmp.invoke("clear")) ;
			}
		}
		/*public boolean add(ProjectDetail_E e) {
			return projectDetails.add(e);
		}

		public boolean remove(ProjectDetail_E o) {
			return projectDetails.remove(o);
		}

		public boolean addAll(Collection<? extends ProjectDetail_E> c) {
			return projectDetails.addAll(c);
		}

		public void clear() {
			projectDetails.clear();
		}*/
	}

}
