package com.nogroup.booster.builders.classBuilders.cvrtrs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JTryBlock;
import com.helger.jcodemodel.JVar;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class EmbeddConverterBuilder extends AbstractConverterBuilder {

	public EmbeddConverterBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public void initConvertToEntityAttribute(JDefinedClass cla, AbstractJClass target) {
		JMethod m = cla.method(JMod.PUBLIC, target, "convertToEntityAttribute") ;
		JVar p = m.param(String.class, "value") ;
		JVar mapper = m.body().decl(getCm().ref(ObjectMapper.class), "mapper", JExpr._new(getCm().ref(ObjectMapper.class))) ;
		JTryBlock tr = m.body()._try();
		tr.body()._return(mapper.invoke("readValue").arg(p).arg(target.dotclass())) ;
		tr._catch(getCm().ref(Exception.class)) ;
		m.body()._return(JExpr._null()) ;
	}

	@Override
	public void initConvertToDatabaseColumn(JDefinedClass cla, AbstractJClass tp) {
		JMethod m = cla.method(JMod.PUBLIC, String.class, "convertToDatabaseColumn") ;
		JVar p = m.param(tp, "value") ;
		JVar mapper = m.body().decl(getCm().ref(ObjectMapper.class), "mapper", JExpr._new(getCm().ref(ObjectMapper.class))) ;
		JTryBlock tr = m.body()._try();
		tr.body()._return(mapper.invoke("writeValueAsString").arg(p)) ;
		tr._catch(getCm().ref(JsonProcessingException.class)) ;
		m.body()._return(JExpr.lit("")) ;
	}

	@Override
	public String packageName() {
		return getCm().rootPkg + ".backend.data.converters.singular";
	}

}
