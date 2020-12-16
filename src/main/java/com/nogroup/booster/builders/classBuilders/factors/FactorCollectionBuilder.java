package com.nogroup.booster.builders.classBuilders.factors;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JVar;
import com.nogroup.booster.builders.classBuilders.AbstractCollectionBuilder;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class FactorCollectionBuilder extends AbstractCollectionBuilder {

	public FactorCollectionBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public JDefinedClass bound() {
		return (JDefinedClass) getCm().resolve(boundName()).boxify();
	}

	public abstract String boundName();

	@Override
	public String archetype() {
		return "Factor Collection";
	}

	@Override
	public String packageName() {
		return getCm().rootPkg + ".backend.data.factors.collection";
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
		createProjectionMethod(cls,bound()) ;
		createComplementaryMethod(cls,bound()) ;
	}
	
	public void createProjectionMethod(JDefinedClass cl, JDefinedClass factor) {
		JMethod prjM = cl.method(JMod.PUBLIC, cl, "projection") ;
		prjM.javadoc().add("Returns a list with no redundancy") ;
		JBlock prjMBd = prjM.body() ;
		JVar tmps = prjMBd.decl(cl, "tmps",JExpr._new(cl)) ;
		prjMBd.add(tmps.invoke("addAll").arg(JExpr._new(getCm().ref(HashSet.class).narrow(factor)).arg(JExpr._this()))) ;
		
		prjMBd._return(tmps) ;
	}
	
	public void createComplementaryMethod(JDefinedClass cl, JDefinedClass factor) {
		JMethod cmplmntrM = cl.method(JMod.PUBLIC, cl, "complementary") ;
		cmplmntrM.javadoc().add("Returns the missing values from this list") ;
		JBlock cmplmntrBd = cmplmntrM.body() ;
		JVar tmps = cmplmntrBd.decl(cl, "tmps",JExpr._new(cl)) ;
		JVar cv = cmplmntrBd.decl(getCm().ref(Set.class).narrow(factor), "currentValues",
			JExpr._new(getCm().ref(HashSet.class).narrow(factor)).arg(JExpr._this())
		) ;
		JVar av = cmplmntrBd.decl(
				getCm().ref(
				Set.class
			).narrow(
				factor
			),
			"allValues",
			JExpr._new(
					getCm().ref(
					HashSet.class
				).narrow(
					factor
				)
			).arg(
					getCm().ref(
					Arrays.class
				).staticInvoke(
					"asList"
				).arg(
					factor.staticInvoke(
						"values"
					)
				)
			)
		) ;
		cmplmntrBd.add(av.invoke("removeAll").arg(cv)) ;
		cmplmntrBd.add(tmps.invoke("addAll").arg(av)) ;

		cmplmntrBd._return(tmps) ;
	}

	@Override
	public void buildDefaultMethods(JDefinedClass cls) {
		// NOTHING TO ADD HERE
	}

}
