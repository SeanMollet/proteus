/**
 * 
 */
package io.sinistral.proteus.server.swagger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JavaType;

import io.swagger.v3.jaxrs2.ResolvedParameter;
import io.swagger.v3.jaxrs2.ext.OpenAPIExtension;
import io.swagger.v3.oas.models.Components;

/**
 * @author jbauer
 *
 */
public class ServerParameterExtension extends io.swagger.v3.jaxrs2.DefaultParameterExtension
{

	public ServerParameterExtension()
	{
 		super();

	}
 
	@Override
	  public ResolvedParameter extractParameters(List<Annotation> annotations,
	                                               Type type,
	                                               Set<Type> typesToSkip,
	                                               Components components,
	                                               javax.ws.rs.Consumes classConsumes,
	                                               javax.ws.rs.Consumes methodConsumes,
	                                               boolean includeRequestBody,
	                                               JsonView jsonViewAnnotation,
	                                               Iterator<OpenAPIExtension> chain)
	{
 
		if(type.getTypeName().contains("java.nio.ByteBuffer") || type.getTypeName().contains("java.nio.file.Path"))
	      {
	      	type = java.io.File.class;
 
	      } 
		 
		ResolvedParameter parameter =  super.extractParameters(annotations, type, typesToSkip, components, classConsumes, methodConsumes, includeRequestBody, jsonViewAnnotation, chain);
	  
		return parameter;
	}

 
	@Override
	protected boolean shouldIgnoreType(Type type, Set<Type> typesToSkip)
	{ 
	 
		if( type.getTypeName().contains("io.sinistral.proteus.server.ServerRequest") 
				|| type.getTypeName().contains("HttpServerExchange") 
				|| type.getTypeName().contains("HttpHandler") 
				|| type.getTypeName().contains("io.sinistral.proteus.server.ServerResponse") 
				|| type.getTypeName().contains("io.undertow.server.session")
				)
		{
			return true;
		} 
		
		return super.shouldIgnoreType(type, typesToSkip);
	}

 
	@Override
	protected JavaType constructType(Type type)
	{ 
		
		if(type.getTypeName().contains("java.nio.ByteBuffer") || type.getTypeName().contains("java.nio.file.Path"))
	      {
	      	type = java.io.File.class; 

	      }
	 
		return super.constructType(type);

	}

}
