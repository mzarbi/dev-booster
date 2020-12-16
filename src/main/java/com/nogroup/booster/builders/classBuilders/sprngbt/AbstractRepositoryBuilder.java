package com.nogroup.booster.builders.classBuilders.sprngbt;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JMod;
import com.nogroup.booster.builders.AbstractClassBuilder;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class AbstractRepositoryBuilder extends AbstractClassBuilder {

	public AbstractRepositoryBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public String archetype() {
		return "Repository";
	}

	@Override
	public String packageName() {
		return getCm().rootPkg + ".backend.repositories";
	}

	@Override
	public EClassType type() {
		return EClassType.INTERFACE;
	}

	@Override
	public int modifiers() {
		return JMod.PUBLIC;
	}

	@Override
	public void annotate(JDefinedClass cls) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildMethods(JDefinedClass cls) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildDefaultMethods(JDefinedClass cls) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extends_(JDefinedClass cls) {
		cls._extends(getCm().ref(JpaRepository.class).narrow(entity()).narrow(getCm().ref(String.class))) ;
	}

	public abstract AbstractJType entity();

	@Override
	public void implements_(JDefinedClass cls) {
		// TODO Auto-generated method stub
		
	}

}
