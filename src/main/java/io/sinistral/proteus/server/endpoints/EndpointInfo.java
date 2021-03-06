/**
 * 
 */
package io.sinistral.proteus.server.endpoints;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import io.undertow.util.HttpString;

/**
 * @author jbauer
 *
 */
public class EndpointInfo implements Comparable<EndpointInfo>
{
	private HttpString method;
	private String pathTemplate;
	private String consumes = "*/*";
	private String produces = "*/*";
	private String controllerMethod = "*";
	private String controllerName = "_";

	
	private EndpointInfo(Builder builder)
	{
		this.method = builder.method;
		this.pathTemplate = builder.pathTemplate;
		this.consumes = builder.consumes;
		this.produces = builder.produces;
		this.controllerMethod = builder.controllerMethod;
		this.controllerName = builder.controllerName;
	}
	 
	public EndpointInfo()
	{
		
	}

	/**
	 * @return the method
	 */
	public HttpString getMethod()
	{
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(HttpString method)
	{
		this.method = method;
	}

	/**
	 * @return the pathTemplate
	 */
	public String getPathTemplate()
	{
		return pathTemplate;
	}

	/**
	 * @param pathTemplate the pathTemplate to set
	 */
	public void setPathTemplate(String pathTemplate)
	{
		this.pathTemplate = pathTemplate;
	}

	/**
	 * @return the consumes
	 */
	public String getConsumes()
	{
		return consumes;
	}

	/**
	 * @param consumes the consumes to set
	 */
	public void setConsumes(String consumes)
	{
		this.consumes = consumes;
	}

	/**
	 * @return the produces
	 */
	public String getProduces()
	{
		return produces;
	}

	/**
	 * @param produces the produces to set
	 */
	public void setProduces(String produces)
	{
		this.produces = produces;
	}

	/**
	 * @return the controllerMethod
	 */
	public String getControllerMethod()
	{
		return controllerMethod;
	}

	/**
	 * @param controllerMethod the controllerMethod to set
	 */
	public void setControllerMethod(String controllerMethod)
	{
		this.controllerMethod = controllerMethod;
	}

	/**
	 * @return the controllerName
	 */
	public String getControllerName()
	{
		return controllerName;
	}

	/**
	 * @param controllerName the controllerName to set
	 */
	public void setControllerName(String controllerName)
	{
		this.controllerName = controllerName;
		if(this.controllerName == null)
		{
			this.controllerName = "";
		}
	}
	
	
	public int hashCode()
	{
	     return new HashCodeBuilder(17, 37).
	    	       append(controllerName).
	    	       append(controllerMethod).
	    	       append(consumes).
	    	       append(produces).
	    	       append(method).
	    	       append(pathTemplate).
	    	       toHashCode();
	}
	
	public int compareTo(EndpointInfo other) {

	     return new CompareToBuilder()
  	       .append(this.pathTemplate, other.pathTemplate)
	       .append(this.controllerName, other.controllerName)
	       .append(this.controllerMethod, other.controllerMethod)
	       .append(this.method, other.method)

	       .toComparison();
	   }
	
 

	@Override
	public String toString()
	{
		return String.format("\t%-8s %-40s %-26s %-26s %s", this.method, this.pathTemplate, "[" + this.consumes + "]", "[" + this.produces+ "]", "("+this.controllerName+"."+this.controllerMethod+ ")");
	}

	/**
	 * Creates builder to build {@link EndpointInfo}.
	 * @return created builder
	 */
	
	public static Builder builder()
	{
		return new Builder();
	}

 
	/**
	 * Builder to build {@link EndpointInfo}.
	 */
	
	public static final class Builder
	{
		private HttpString method;
		private String pathTemplate;
		private String consumes = "*/*";
		private String produces = "*/*";
		private String controllerMethod = "_";
		private String controllerName = "_";

		private Builder()
		{
		}

		public Builder withMethod(HttpString method)
		{
			this.method = method;
			return this;
		}

		public Builder withPathTemplate(String pathTemplate)
		{
			this.pathTemplate = pathTemplate;
			return this;
		}

		public Builder withConsumes(String consumes)
		{
			this.consumes = consumes;
			return this;
		}

		public Builder withProduces(String produces)
		{
			this.produces = produces;
			return this;
		}

		public Builder withControllerMethod(String controllerMethod)
		{
			this.controllerMethod = controllerMethod;
			return this;
		}

		public Builder withControllerName(String controllerName)
		{
			this.controllerName = controllerName;
			return this;
		}

		public EndpointInfo build()
		{
			return new EndpointInfo(this);
		}
	}

	 
	
}
