package com.nogroup.booster.builders.classBuilders.cvrtrs;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JTryBlock;
import com.helger.jcodemodel.JVar;
import com.nogroup.booster.codeModel.CodeModel;

public abstract class DateConverterBuilder extends AbstractConverterBuilder{

	public DateConverterBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public void initConvertToEntityAttribute(JDefinedClass cla, AbstractJClass target) {
		JMethod m = cla.method(JMod.PUBLIC, Date.class, "convertToEntityAttribute") ;
		JVar p = m.param(String.class, "value") ;
		JVar format = m.body().decl(getCm().ref(SimpleDateFormat.class), "formatter",JExpr._new(getCm().ref(SimpleDateFormat.class)).arg(JExpr.lit(format()))) ;

		JTryBlock tr = m.body()._try() ;
		tr.body()._return(format.invoke("parse").arg(p)) ;
		tr._catch(getCm().ref(Exception.class)) ;
		m.body()._return(JExpr._null()) ;
	}

	@Override
	public void initConvertToDatabaseColumn(JDefinedClass cla, AbstractJClass target) {
		JMethod m = cla.method(JMod.PUBLIC, String.class, "convertToDatabaseColumn") ;
		JVar format = m.body().decl(getCm().ref(SimpleDateFormat.class), "formatter",JExpr._new(getCm().ref(SimpleDateFormat.class)).arg(JExpr.lit(format()))) ;

		JVar p = m.param(Date.class, "value") ;

		m.body()._return(format.invoke("format").arg(p)) ;
	}

	protected abstract String format();

	@Override
	public AbstractJClass target() {
		return getCm().ref(Date.class);
	}

	@Override
	public String packageName() {
		return getCm().rootPkg + ".backend.data.converters.others";
	}

}
