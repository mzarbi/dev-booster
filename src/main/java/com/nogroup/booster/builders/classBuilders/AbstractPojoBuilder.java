package com.nogroup.booster.builders.classBuilders;

import java.io.Serializable;

import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JVar;
import com.nogroup.booster.builders.AbstractClassBuilder;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class AbstractPojoBuilder extends AbstractClassBuilder {

	public AbstractPojoBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public EClassType type() {
		return EClassType.CLASS;
	}

	@Override
	public void buildConstants(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildDefaultMethods(JDefinedClass cls) {
		JMethod m = cls.constructor(JMod.PUBLIC) ;
		m.javadoc().add("Empty Constructor.");
		
		m = cls.constructor(JMod.PUBLIC) ;
		m.javadoc().add("Plain Constructor.");
		int counter = 0 ;
		for(JFieldVar tmp : cls.fields().values()) {
			JVar p = m.param(tmp.type(), "val"+counter) ;
			m.body().assign(JExpr.refthis(tmp), p) ;
			counter++;
			createGetter(tmp,cls);
			createSetter(tmp,cls);
			createWither(tmp,cls);
		}
		buildDefaultPojoMethods(cls) ;
	}

	@Override
	public void implements_(JDefinedClass cls) {
		cls._implements(Serializable.class) ;
		pojoImplements(cls) ;
	}
	
	
	
	
	public abstract void buildDefaultPojoMethods(JDefinedClass cls);
	public abstract void pojoImplements(JDefinedClass cls);

}
