package com.nogroup.booster.builders.classBuilders;

import java.util.Collection;
import java.util.List;

import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JForEach;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JVar;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class AbstractEnumBuilder extends AttributeLessClassBuilder {

	public AbstractEnumBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public EClassType type() {
		return EClassType.ENUM;
	}

	@Override
	public void buildConstants(JDefinedClass cls) {
		for(String tmp : constants()) {
			cls.enumConstant(tmp) ;
		}
	}

	public abstract Collection<String> constants() ;

	@Override
	public void extends_(JDefinedClass cls) {

	}

	@Override
	public void buildDefaultMethods(JDefinedClass cls) {
		createLookUpMethod(cls);
	}
	private void createLookUpMethod(JDefinedClass cls) {
		JMethod m = cls.method(JMod.PUBLIC, cls, "lookup") ;
		JVar p = m.param(String.class, "val") ;
		m.body()._return(cls.staticInvoke("valueOf").arg(p)) ;
		
		m = cls.method(JMod.PUBLIC, refList(String.class), "list") ;
		JVar ls = m.body().decl(refList(String.class), "ls", JExpr._new(refArrayList(String.class))) ;
		JForEach fr = m.body().forEach(cls, "tmp", cls.staticInvoke("values"));
		
		fr.body().add(ls.invoke("add").arg(fr.var())) ;
		m.body()._return(ls);
	}
	
}
