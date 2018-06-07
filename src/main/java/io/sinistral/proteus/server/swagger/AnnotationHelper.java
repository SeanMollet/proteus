/**
 * 
 */
package io.sinistral.proteus.server.swagger;

import java.lang.annotation.Annotation;

import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;


/**
 * @author jbauer
 */
public class AnnotationHelper
{
	public static FormParam createFormParam(java.lang.reflect.Parameter parameter)
	{

		return new FormParam()
		{

			@Override
			public String value()
			{
				return parameter.getName();
			}

			@Override
			public Class<? extends Annotation> annotationType()
			{
				return FormParam.class;
			}

		};
	}

	public static QueryParam createQueryParam(java.lang.reflect.Parameter parameter)
	{

		return new QueryParam()
		{

			@Override
			public String value()
			{
				return parameter.getName();
			}

			@Override
			public Class<? extends Annotation> annotationType()
			{
				return QueryParam.class;
			}
		};
	}

	public static PathParam createPathParam(java.lang.reflect.Parameter parameter)
	{

		return new PathParam()
		{

			@Override
			public String value()
			{
				return parameter.getName();
			}

			@Override
			public Class<? extends Annotation> annotationType()
			{
				return PathParam.class;
			}
		};
	}

	public static Parameter createApiParam(java.lang.reflect.Parameter parameter)
	{

		return new Parameter()
		{

			@Override
			public Class<? extends Annotation> annotationType()
			{
				// TODO Auto-generated method stub
				return Parameter.class;
			}

			@Override
			public String name()
			{
				return parameter.getName();
			}

		  
			@Override
			public boolean required()
			{
				return !parameter.getParameterizedType().getTypeName().contains("java.util.Optional");
			} 

			@Override
			public boolean hidden()
			{
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String example()
			{
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ExampleObject[] examples()
			{
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ParameterIn in()
			{
				// TODO Auto-generated method stub
				return null;
			}
 

			@Override
			public boolean allowEmptyValue()
			{
				// TODO Auto-generated method stub
				return false;
			}

			/* (non-Javadoc)
			 * @see io.swagger.v3.oas.annotations.Parameter#description()
			 */
			@Override
			public String description()
			{
				// TODO Auto-generated method stub
				return null;
			}

			/* (non-Javadoc)
			 * @see io.swagger.v3.oas.annotations.Parameter#deprecated()
			 */
			@Override
			public boolean deprecated()
			{
				// TODO Auto-generated method stub
				return false;
			}

			/* (non-Javadoc)
			 * @see io.swagger.v3.oas.annotations.Parameter#style()
			 */
			@Override
			public ParameterStyle style()
			{
				// TODO Auto-generated method stub
				return null;
			}

			/* (non-Javadoc)
			 * @see io.swagger.v3.oas.annotations.Parameter#explode()
			 */
			@Override
			public Explode explode()
			{
				// TODO Auto-generated method stub
				return null;
			}

			/* (non-Javadoc)
			 * @see io.swagger.v3.oas.annotations.Parameter#allowReserved()
			 */
			@Override
			public boolean allowReserved()
			{
				// TODO Auto-generated method stub
				return false;
			}

			/* (non-Javadoc)
			 * @see io.swagger.v3.oas.annotations.Parameter#schema()
			 */
			@Override
			public Schema schema()
			{
				return new Schema(){

					@Override
					public Class<? extends Annotation> annotationType()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public Class<?> implementation()
					{ 
						if(parameter.getType().getTypeName().contains("java.util.List"))
						{
							return null;
						}
						
						return parameter.getType();
					}

					@Override
					public Class<?> not()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public Class<?>[] oneOf()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public Class<?>[] anyOf()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public Class<?>[] allOf()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public String name()
					{
						return parameter.getType().getSimpleName();
					}

					@Override
					public String title()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public double multipleOf()
					{
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public String maximum()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public boolean exclusiveMaximum()
					{
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public String minimum()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public boolean exclusiveMinimum()
					{
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public int maxLength()
					{
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int minLength()
					{
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public String pattern()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public int maxProperties()
					{
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int minProperties()
					{
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public String[] requiredProperties()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public boolean required()
					{
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public String description()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public String format()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public String ref()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public boolean nullable()
					{
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public boolean readOnly()
					{
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public boolean writeOnly()
					{
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public AccessMode accessMode()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public String example()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public ExternalDocumentation externalDocs()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public boolean deprecated()
					{
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public String type()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public String[] allowableValues()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public String defaultValue()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public String discriminatorProperty()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public DiscriminatorMapping[] discriminatorMapping()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public boolean hidden()
					{
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public Class<?>[] subTypes()
					{
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public Extension[] extensions()
					{
						// TODO Auto-generated method stub
						return null;
					}
					
				};
			}

			/* (non-Javadoc)
			 * @see io.swagger.v3.oas.annotations.Parameter#array()
			 */
			@Override
			public ArraySchema array()
			{
				// TODO Auto-generated method stub
				return null;
			}

			/* (non-Javadoc)
			 * @see io.swagger.v3.oas.annotations.Parameter#content()
			 */
			@Override
			public Content[] content()
			{
				// TODO Auto-generated method stub
				return null;
			}

			/* (non-Javadoc)
			 * @see io.swagger.v3.oas.annotations.Parameter#extensions()
			 */
			@Override
			public Extension[] extensions()
			{
				// TODO Auto-generated method stub
				return null;
			}

		 

		};
	}
}
