package com.nogroup.booster.flows;

import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.JAnnotationUse;
import com.helger.jcodemodel.JCodeModelException;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JVar;
import com.nogroup.booster.builders.ClassBuilder;
import com.nogroup.booster.builders.classBuilders.cvrtrs.DateConverterBuilder;
import com.nogroup.booster.builders.classBuilders.cvrtrs.EmbeddConverterBuilder;
import com.nogroup.booster.builders.classBuilders.cvrtrs.EmbeddedCollectionConverterBuilder;
import com.nogroup.booster.builders.classBuilders.embeddeds.EmbeddedBuilder;
import com.nogroup.booster.builders.classBuilders.embeddeds.EmbeddedCollectionBuilder;
import com.nogroup.booster.builders.classBuilders.entities.EntityBuilder;
import com.nogroup.booster.builders.classBuilders.factors.FactorBuilder;
import com.nogroup.booster.builders.classBuilders.factors.FactorCollectionBuilder;
import com.nogroup.booster.builders.classBuilders.sprngbt.AbstractControllerBuilder;
import com.nogroup.booster.builders.classBuilders.sprngbt.AbstractRepositoryBuilder;
import com.nogroup.booster.builders.classBuilders.sprngbt.AbstractServiceBuilder;
import com.nogroup.booster.codeModel.BoosterUtils;
import com.nogroup.booster.descriptors.maven.Attribute;
import com.nogroup.booster.descriptors.maven.Attribute_;
import com.nogroup.booster.descriptors.maven.Embedded;
import com.nogroup.booster.descriptors.maven.Entity;
import com.nogroup.booster.descriptors.maven.Factor;
import com.nogroup.booster.work.annotations.Message;
import com.nogroup.booster.work.annotations.Order;

public interface SpringBootPool extends MavenPool{

	@Order(10)
	@Message("Pre-Building BaseIdentifierGenerator Class")
	public default void buildBaseIdentifierGeneratorClass() throws JCodeModelException {
		new ClassBuilder(cm()) {
			
			@Override
			public String packageName() {
				return descriptors().getGroupId() + ".backend.data.utils";
			}
			
			@Override
			public String name() {
				return "BaseIdentifierGenerator";
			}
			
			@Override
			public int modifiers() {
				return JMod.PUBLIC;
			}
			
			@Override
			public void extends_(JDefinedClass cls) {
				cls._extends(UUIDGenerator.class) ;
			}
			
			@Override
			public void buildDefaultMethods(JDefinedClass cls) {
				JMethod md = cls.method(JMod.PUBLIC, Serializable.class, "generate") ;
				md.annotate(Override.class) ;
				JVar session = md.param(SharedSessionContractImplementor.class, "session") ;
				JVar obj = md.param(Object.class, "obj") ;
				md._throws(HibernateException.class) ;
				
				md.javadoc().add("Generate a custom ID for the new entity") ;
				JVar uuid = md.body().decl(
					JMod.FINAL,getCm().ref(String.class), 
					"uuid",
					JExpr._super().invoke("generate").arg(session).arg(obj).invoke("toString")
				) ;
				JVar longTimeRandom = md.body().decl(
					JMod.FINAL,getCm().LONG, 
					"longTimeRandom",
					getCm().ref(System.class).staticInvoke("nanoTime").plus(
						getCm().ref(System.class).staticInvoke("currentTimeMillis")
					).plus(
						JExpr._new(getCm().ref(Random.class)).invoke("nextLong")
					).plus(
						getCm().ref(Objects.class).staticInvoke("hash").arg(obj)
					)
				) ;
				JVar timeHex = md.body().decl(
					JMod.FINAL,getCm().ref(String.class), 
					"timeHex",
					getCm().ref(Long.class).staticInvoke("toHexString").arg(longTimeRandom)
				) ;
				md.body()._return(
					getCm().ref(StringUtils.class).staticInvoke("substring")
						.arg(timeHex)
						.arg(JExpr.ref("NUMBER_OF_CHARS_IN_ID_PART")
						).plus(
						getCm().ref(StringUtils.class).staticInvoke("substring")
						.arg(uuid)
						.arg(JExpr.ref("NUMBER_OF_CHARS_IN_ID_PART")
						)
					)
				) ;
			}

			@Override
			public void buildAttributes(JDefinedClass cls) {
				cls.field(JMod.PRIVATE_FINAL|JMod.STATIC, getCm().INT, "NUMBER_OF_CHARS_IN_ID_PART",JExpr.lit(-5)) ;
			}
			
			@Override
			public String archetype() {
				return "Entity Utils";
			}

		}.prebuild();
	}
	
	@Order(11)
	@Message("Pre-Building Factor Classes")
	public default void a_9994() throws JCodeModelException {
		
		for(final Factor tmp : descriptors().getData().getFactors()) {
			new FactorBuilder(cm()) {
				
				@Override
				public String name() {
					return tmp.getName() + "_F";
				}
				
				@Override
				public Collection<String> constants() {
					return tmp.getValues();
				}

			}.prebuild();
		}
	}
	
	@Order(12)
	@Message("Pre-Building Factor Collection Classes")
	public default void a_9995() throws JCodeModelException {
		
		for(final Factor tmp : descriptors().getData().getFactors()) {
			new FactorCollectionBuilder(cm()) {
				
				@Override
				public String name() {
					return tmp.getName() + "_FC";
				}
				
				@Override
				public String boundName() {
					return tmp.getName() + "_F";
				}
			}.prebuild();
		}
	}
	
	@Order(13)
	@Message("Pre-Building Embedded Classes")
	public default void a_9996() throws JCodeModelException {
		for(final Embedded tmp : descriptors().getData().getPojos().getEmbeddeds()) {
			new EmbeddedBuilder(cm()) {
				
				@Override
				public String name() {
					return tmp.getName() + "_S";
				}

				@Override
				public void buildAttributes(JDefinedClass cls) {
					for(Attribute tmp2 : tmp.getAttributes()) {
						cls.field(
							JMod.PRIVATE, 
							cm().resolve(tmp2.getDataType()), 
							tmp2.getName()
						) ;
					}
				}

				@Override
				public void extends_(JDefinedClass cls) {
					if(!BoosterUtils.isEmpty("" + tmp.getSuper())) {
						cls._extends(getCm().resolve("" + tmp.getSuper()).boxify()) ;
					}
					
				}
			}.prebuild();
		}
	}
	
	@Order(14)
	@Message("Pre-Building Embedded Collection Classes")
	public default void a_9997() throws JCodeModelException {
		for(final Embedded tmp : descriptors().getData().getPojos().getEmbeddeds()) {
			new EmbeddedCollectionBuilder(cm()) {
				
				@Override
				public String name() {
					return tmp.getName() + "_SC";
				}
				
				@Override
				public String boundName() {
					return tmp.getName() + "_S";
				}
			}.prebuild();
		}
	}
	
	@Order(14)
	@Message("Pre-Building Converter Classes")
	public default void a_9998() throws JCodeModelException {
		for(final Embedded tmp : descriptors().getData().getPojos().getEmbeddeds()) {
			new EmbeddConverterBuilder(cm()) {
				
				@Override
				public String name() {
					return tmp.getName() + "_SCvrtr";
				}
				
				@Override
				public AbstractJClass target() {
					return getCm().resolve(tmp.getName() + "_S").boxify();
				}
			}.prebuild();
			
			new EmbeddedCollectionConverterBuilder(cm()) {
				
				@Override
				public String name() {
					return tmp.getName() + "_SCCvrtr";
				}
				
				@Override
				public AbstractJClass target() {
					return getCm().resolve(tmp.getName() + "_SC").boxify();
				}
			}.prebuild();
		}
		
		for(final Factor tmp : descriptors().getData().getFactors()) {
			new EmbeddedCollectionConverterBuilder(cm()) {
				
				@Override
				public String name() {
					return tmp.getName() + "_FCCvrtr";
				}
				
				@Override
				public AbstractJClass target() {
					return getCm().resolve(tmp.getName() + "_FC").boxify();
				}
			}.prebuild();
		}
	}
	
	@Order(15)
	@Message("Pre-Building Shared Converter Classes")
	public default void a_9999() throws JCodeModelException {
		new DateConverterBuilder(cm()) {

			@Override
			public String name() {
				return "LongDateConverter";
			}

			@Override
			protected String format() {
				return "yyyy-MM-dd HH:mm:ss";
			}
		}.prebuild();

		new DateConverterBuilder(cm()) {

			@Override
			public String name() {
				return "ShortDateConverter";
			}

			@Override
			protected String format() {
				return "yyyy-MM-dd";
			}
		}.prebuild();

		new EmbeddConverterBuilder(cm()) {

			@Override
			public String name() {
				return "HashMapConverter";
			}

			@Override
			public AbstractJClass target() {
				return getCm().ref(HashMap.class);
			}

			@Override
			public String packageName() {
				return getCm().rootPkg + ".backend.data.converters.others";
			}
		}.prebuild();

	}
	
	@Order(16)
	@Message("Pre-Building AbstractEntity Class")
	public default void a_9999_0() throws JCodeModelException, IOException {
		
		new EntityBuilder(cm()) {

			@Override
			public String name() {
				return "AbstractEntity";
			}

			@Override
			public void buildAttributes(JDefinedClass cls) {
				JFieldVar fd = cls.field(JMod.PRIVATE, cm()._ref(String.class), "id");
				fd.annotate(Id.class);
				JAnnotationUse ann = fd.annotate(GeneratedValue.class);
				ann.param("generator", "custom-generator") ;
				ann.param("strategy",getCm().ref(GenerationType.class).staticRef("IDENTITY")) ;
				ann = fd.annotate(GenericGenerator.class);
				ann.param("name","custom-generator");
				ann.param("strategy",descriptors().getGroupId() + ".data.utils.BaseIdentifierGenerator");
				
				fd = cls.field(JMod.PRIVATE, cm().INT, "version");
				fd.annotate(Version.class);
				fd.annotate(Column.class) ;

				fd = cls.field(JMod.PRIVATE, cm().ref(HashMap.class).narrow(String.class).narrow(Object.class), "data",
						JExpr._new(cm().ref(HashMap.class).narrow(String.class).narrow(Object.class)));
				ann = fd.annotate(Convert.class);
				ann.param("converter", cm().resolve("HashMapConverter"));
				
				fd = cls.field(JMod.PROTECTED, cm().ref(Instant.class), "createdAt");
				fd.annotate(CreationTimestamp.class) ;
				ann = fd.annotate(Column.class) ;
				ann.param("name","created_at") ;
				ann.param("updatable",JExpr.FALSE) ;
				ann.param("nullable",JExpr.FALSE) ;

				fd = cls.field(JMod.PROTECTED, cm().ref(Instant.class), "modifiedAt");
				fd.annotate(UpdateTimestamp.class) ;
				ann = fd.annotate(Column.class) ;
				ann.param("name","modified_at") ;
				ann.param("updatable",JExpr.FALSE) ;
				ann.param("nullable",JExpr.FALSE) ;
			}

			public void extends_(JDefinedClass cls) {

			};

			@Override
			public void buildMethods(JDefinedClass cls) {
			};

			@Override
			public void buildDefaultMethods(JDefinedClass cls) {
				for (JFieldVar tmp : cls.fields().values()) {
					createGetter(tmp, cls);
				}

				JFieldVar fd = cls.fields().get("data");
				JMethod m = cls.method(JMod.PUBLIC, cm().VOID, "clear");
				m.javadoc().add("Data Delegate Method.");
				m.body().add(fd.invoke("clear"));

				m = cls.method(JMod.PUBLIC, cm().VOID, "put");
				m.param(cm()._ref(String.class), "v1");
				m.param(cm()._ref(Object.class), "v2");
				m.javadoc().add("Data Delegate Method.");
				m.body().directStatement("data.put(v1,v2);");

				m = cls.method(JMod.PUBLIC, Object.class, "fetch");
				m.param(cm()._ref(String.class), "v1");
				m.javadoc().add("Data Delegate Method.");
				m.body().directStatement("return data.get(v1);");

				m = cls.method(JMod.PUBLIC, Object.class, "remove");
				m.param(cm()._ref(Object.class), "v1");
				m.javadoc().add("Data Delegate Method.");
				m.body().directStatement("return data.remove(v1);");

				m = cls.method(JMod.PUBLIC, cm().BOOLEAN, "containsKey");
				m.param(cm()._ref(String.class), "v1");
				m.javadoc().add("Data Delegate Method.");
				m.body().directStatement("return data.containsKey(v1);");

				m = cls.method(JMod.PUBLIC, cm().ref(Set.class).narrow(String.class), "keySet");
				m.javadoc().add("Data Delegate Method.");
				m.body().directStatement("return data.keySet();");

				m = cls.method(JMod.PUBLIC, cm().ref(Collection.class).narrow(Object.class), "values");
				m.javadoc().add("Data Delegate Method.");
				m.body().directStatement("return data.values();");

				m = cls.method(JMod.PUBLIC, getCm().BOOLEAN, "isNew");
				m.javadoc().add("Is Entity New ?");
				m.body().directStatement("return getId()==null;");
				
				m = cls.method(JMod.PUBLIC, getCm().ref(String.class), "toString");
				m.annotate(Override.class) ;
				m.javadoc().add("Custom toString method");
				m.body().directStatement("return id;");
				
				
			};
			
			@Override
			public void annotate(JDefinedClass cls) {
				cls.annotate(MappedSuperclass.class);
			};
		}.prebuild();
	}
	
	@Order(17)
	@Message("Pre-Building Entity Classes")
	public default void a_9999_1() throws JCodeModelException {
		
		for (Entity tmp : descriptors().getData().getEntities()) {
			new EntityBuilder(cm()) {

				@Override
				public String name() {
					return tmp.getName() + "_E";
				}

				@Override
				public void buildAttributes(JDefinedClass cls) {
					
					for (Attribute_ tmp2 : tmp.getAttributes()) {
						
						JFieldVar fd = field(JMod.PRIVATE, getCm().resolve(tmp2.getDataType()), tmp2.getName()) ;
						if(tmp2.getDataType().endsWith("_E")) {
							
						}else if(tmp2.getDataType().endsWith("_S")) {
							fd.annotate(Column.class).param("name",camelCaseToUnderScore(tmp2.getName()).toUpperCase()) ;
							JAnnotationUse ann = fd.annotate(Convert.class) ;
							ann.param("converter",resolve("" + tmp2.getDataType() + "Cvrtr").boxify().dotclass()) ;
						}else if(tmp2.getDataType().endsWith("_F")) {
							fd.annotate(Enumerated.class).param("value", getCm().ref(EnumType.class).staticRef("STRING")) ;
							fd.annotate(Column.class).param("name",camelCaseToUnderScore(tmp2.getName()).toUpperCase()) ;
						}else if(tmp2.getDataType().endsWith("_SC")) {
							fd.annotate(Column.class).param("name",camelCaseToUnderScore(tmp2.getName()).toUpperCase()) ;
							JAnnotationUse ann = fd.annotate(Convert.class) ;
							ann.param("converter",resolve("" + tmp2.getDataType() + "Cvrtr").boxify().dotclass()) ;
						}else if(tmp2.getDataType().endsWith("_FC")) {
							fd.annotate(Column.class).param("name",camelCaseToUnderScore(tmp2.getName()).toUpperCase()) ;
							JAnnotationUse ann = fd.annotate(Convert.class) ;
							ann.param("converter",resolve("" + tmp2.getDataType() + "Cvrtr").boxify().dotclass()) ;
						}else {
							fd.annotate(Column.class).param("name",camelCaseToUnderScore(tmp2.getName()).toUpperCase()) ;
						}
						
					}
				}
				
				

				
			}.prebuild();
		}
	}
	
	@Order(18)
	@Message("Pre-Building Repositories")
	public default void a_9999_3() throws JCodeModelException {
		for(Entity tmp : descriptors().getData().getEntities()) {
			new AbstractRepositoryBuilder(cm()) {
				
				@Override
				public String name() {
					return tmp.getName() + "_Repository";
				}
				
				@Override
				public AbstractJType entity() {
					return getCm().resolve(tmp.getName() + "_E");
				}
			}.prebuild();
		}
	}
	
	@Order(19)
	@Message("Pre-Building Services")
	public default void a_9999_4() throws JCodeModelException {
		for(Entity tmp : descriptors().getData().getEntities()) {
			new AbstractServiceBuilder(cm()) {

				@Override
				public String name() {
					return tmp.getName() + "_Service";
				}

				@Override
				public AbstractJType repository() {
					return getCm().resolve(tmp.getName() + "_Repository");
				}

				@Override
				public AbstractJType entity() {
					return getCm().resolve(tmp.getName() + "_E");
				}
				
			}.prebuild() ;
		}
	}
	
	@Order(20)
	@Message("Pre-Building Controllers")
	public default void a_9999_5() throws JCodeModelException {
		for(Entity tmp : descriptors().getData().getEntities()) {
			new AbstractControllerBuilder(cm()) {
				
				@Override
				public String name() {
					return tmp.getName() + "_Controller";
				}
				
				@Override
				public AbstractJType service() {
					return getCm().resolve(tmp.getName() + "_Service");
				}
				
				@Override
				public AbstractJType entity() {
					return getCm().resolve(tmp.getName() + "_E");
				}
			}.prebuild();
		}
	}
}
