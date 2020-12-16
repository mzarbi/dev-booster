package com.nogroup.booster.builders.classBuilders.embeddeds;

import java.util.Collection;

import com.helger.jcodemodel.JConditional;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JVar;
import com.nogroup.booster.builders.classBuilders.AbstractCollectionBuilder;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class EmbeddedCollectionBuilder extends AbstractCollectionBuilder {

	public EmbeddedCollectionBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public JDefinedClass bound() {
		return (JDefinedClass) getCm().resolve(boundName()).boxify();
	}

	public abstract String boundName();

	@Override
	public String archetype() {
		return "Embedded Collection";
	}

	@Override
	public String packageName() {
		return getCm().rootPkg + ".backend.data.embedded.collection";
	}

	@Override
	public void annotate(JDefinedClass cls) {
		// NOTHING TO ADD HERE
	}

	@Override
	public void javadoc(JDefinedClass cls) {
		// NOTHING TO ADD HERE
	}

	@Override
	public void buildMethods(JDefinedClass cls) {
		
		cls.constructor(JMod.PUBLIC) ;
		
		JMethod cnstr = cls.constructor(JMod.PUBLIC) ;
		JVar vls = cnstr.param(ref(Collection.class).narrow(bound()), "vals") ;
		cnstr.body().add(JExpr.invoke("addAll").arg(vls)) ;
		
		JMethod md = cls.method(JMod.PUBLIC, bound(), "getFirst") ;
		JConditional iff = md.body()._if(
			JExpr.invoke("size").eq(JExpr.lit(0))
		) ;
		
		iff._then()._return(JExpr._null()) ;
		iff._else()._return(JExpr.invoke("get").arg(0)) ;
		
		for(JFieldVar tmp2 : bound().fields().values()) {
			createFilterByMethod(cls,bound(), tmp2);
			createFindByMethod(cls, bound(), tmp2);
			createSelectMethod(cls, bound(), tmp2);
			createProjectOverMethodMethod(cls, bound(), tmp2);
			createClusterByMethod(cls, bound(), tmp2);
		}
	}

	@Override
	public void buildDefaultMethods(JDefinedClass cls) {
		// NOTHING TO ADD HERE
	}

}
