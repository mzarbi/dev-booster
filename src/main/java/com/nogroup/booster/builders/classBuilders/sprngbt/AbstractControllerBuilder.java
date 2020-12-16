package com.nogroup.booster.builders.classBuilders.sprngbt;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.EClassType;
import com.helger.jcodemodel.JAnnotationUse;
import com.helger.jcodemodel.JCatchBlock;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JTryBlock;
import com.helger.jcodemodel.JVar;
import com.nogroup.booster.builders.AbstractClassBuilder;
import com.nogroup.booster.codeModel.CodeModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public abstract class AbstractControllerBuilder extends AbstractClassBuilder {

	public AbstractControllerBuilder(CodeModel zm) {
		super(zm);
	}

	@Override
	public String archetype() {
		return "Controller";
	}
	
	@Override
	public String packageName() {
		return getCm().rootPkg + ".backend.controllers";
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
	public void annotate(JDefinedClass cls) {
		cls.annotate(RestController.class) ;
		JAnnotationUse ann = cls.annotate(RequestMapping.class) ;
		ann.param("/" + cls.name().split("_")[0].toLowerCase() + "_") ;
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
		JFieldVar fd = cls.field(JMod.PRIVATE, service(), "service") ;
		fd.annotate(Autowired.class) ;
	}

	public abstract AbstractJType service();

	@Override
	public void buildMethods(JDefinedClass cls) {
		
		
		JMethod md = cls.method(JMod.PUBLIC, getCm().ref(List.class).narrow(entity()), "list") ;
		md.annotate(GetMapping.class).param("/" + entity().name().replace("_E", "").toLowerCase()) ;
		md.body()._return(JExpr.ref("service").invoke("findAll")) ;
		md.javadoc().add("RESTful API methods for Retrieval operations") ;
		
		//
		md = cls.method(JMod.PUBLIC, getCm().ref(ResponseEntity.class).narrow(entity()), "get") ;
		md.annotate(GetMapping.class).param("/" + entity().name().replace("_E", "").toLowerCase()+"/{id}") ;
		JVar p = md.param(String.class, "id") ;
		p.annotate(PathVariable.class) ;
		
		JTryBlock tr = md.body()._try() ;
		tr.body().decl(entity(), "obj",JExpr.ref("service").invoke("get").arg(p)) ;
		tr.body()._return(JExpr._new(getCm().ref(ResponseEntity.class).narrow(entity())).arg(JExpr.ref("obj")).arg(
			getCm().ref(HttpStatus.class).staticRef("OK")
		)) ;
		tr._catch(getCm().ref(NoSuchElementException.class)).body()._return(
			JExpr._new(getCm().ref(ResponseEntity.class).narrow(entity())).arg(
					getCm().ref(HttpStatus.class).staticRef("NOT_FOUND")
				)
		) ;
		md.javadoc().add("RESTful API methods for Retrieval operations") ;
		
		//
		md = cls.method(JMod.PUBLIC, getCm().VOID, "add") ;
		md.annotate(Operation.class).param("summary","Create a new " +  entity().name().replace("_E", "").toLowerCase()) ;
		JAnnotationUse ann = md.annotate(ApiResponse.class) ;
		ann.param("responseCode","201") ;
		ann.param("description",entity().name().replace("_E", "").toLowerCase() + " is created") ;
		ann = md.annotate(PostMapping.class).param("/" + entity().name().replace("_E", "").toLowerCase()) ;
		md.param(entity(), "obj").annotate(RequestBody.class) ;
		md.body().add(JExpr.ref("service").invoke("save").arg(JExpr.ref("obj"))) ;
		md.javadoc().add("RESTful API method for Save operation") ;
		
		/*@Operation(summary = "Crate a new order")
		@ApiResponse(responseCode = "201", description = "Order is created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDto.class))})
		@PostMapping(consumes = APPLICATION_JSON_VALUE)
		*/
		
		
		//
		md = cls.method(JMod.PUBLIC, getCm().ref(ResponseEntity.class).narrowAny(), "update") ;
		md.annotate(PutMapping.class).param("/" + entity().name().replace("_E", "").toLowerCase() + "/{id}") ;
		md.param(entity(), "obj").annotate(RequestBody.class) ;
		md.param(String.class, "id").annotate(PathVariable.class) ;
		tr = md.body()._try() ;
		tr.body().decl(entity(), "existProduct",JExpr.ref("service").invoke("get").arg(p)) ;
		tr.body().add(JExpr.ref("service").invoke("save").arg(JExpr.ref("obj"))) ;
		tr.body()._return(
			JExpr._new(getCm().ref(ResponseEntity.class).narrowEmpty()).arg(getCm().ref(HttpStatus.class).staticRef("OK"))
		) ;
		JCatchBlock ctch = tr._catch(getCm().ref(NoSuchElementException.class)) ;
		ctch.param("e") ;
		ctch.body()._return(
				JExpr._new(getCm().ref(ResponseEntity.class).narrowEmpty()).arg(getCm().ref(HttpStatus.class).staticRef("NOT_FOUND"))
		) ;
		md.javadoc().add("RESTful API method for Update operation") ;
		
		md = cls.method(JMod.PUBLIC, getCm().VOID, "delete") ;
		md.annotate(DeleteMapping.class).param("/" + entity().name().replace("_E", "").toLowerCase() + "/{id}") ;
		md.param(String.class, "id").annotate(PathVariable.class) ;
		md.body().add(JExpr.ref("service").invoke("delete").arg(JExpr.ref("id"))) ;
		md.javadoc().add("RESTful API method for Delete operation") ;

	}

	public abstract AbstractJType entity();

	@Override
	public void buildDefaultMethods(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void extends_(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void implements_(JDefinedClass cls) {
		// TODO Auto-generated method stub

	}

}
