/**
 * 
 */
package io.sinistral.proteus.controllers;

 
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Singleton;
import com.jsoniter.output.JsonStream;

import io.sinistral.proteus.models.User;
import io.sinistral.proteus.server.ServerRequest;
import io.sinistral.proteus.server.ServerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.undertow.server.HttpServerExchange;


/**
 * @author jbauer
 *
 */


@Tag(name = "Examples")
@Path("/examples")
@Produces((MediaType.APPLICATION_JSON)) 
@Consumes((MediaType.WILDCARD)) 
@Singleton 
public class Examples
{  
	  
	public static class Fortune
	{
		
	}
	
	public static class World
	{
		
	}
	
	@GET
	@Path("/plaintext")
	@Produces((MediaType.TEXT_PLAIN)) 
	@Operation(summary="Plaintext endpoint",   method = "GET",  responses={@ApiResponse(description="The text")}  )
	public void plaintext(HttpServerExchange exchange)
	{ 
		ServerResponse.response("Hello, World!").contentType(MediaType.TEXT_PLAIN).send(exchange);
	}
	
	
	@GET
	@Path("/json") 
	@Operation(summary="Json serialization endpoint",   method = "GET", responses={@ApiResponse(description="The json message")} )
	public void json(HttpServerExchange exchange)
	{ 
		ServerResponse.response( JsonStream.serializeToBytes(ImmutableMap.of("message", "Hello, World!")) ).applicationJson().send(exchange);
	}
	
	@GET
	@Path("/echo")
	@Produces((MediaType.TEXT_PLAIN)) 
	@Operation(summary="Echo a message",   method = "GET", responses={@ApiResponse(description="The message")} )
	public ServerResponse<ByteBuffer> echo(String message)
	{ 
		return ServerResponse.response(message).contentType(MediaType.TEXT_PLAIN);
	}
 
	 
 
	
	@GET
	@Path("/complex/{pathLong}")
	@Operation(summary = "Complex parameters", method = "GET", responses={@ApiResponse(description="The map",content = @Content(schema = @Schema(implementation = Map.class)))})
	public ServerResponse<Map<String,Object>> complexParameters(
	                    final ServerRequest serverRequest, 
	                    @PathParam("pathLong") final Long pathLong, 
	                    @QueryParam("optionalQueryString")  Optional<String> optionalQueryString, 
	                    @QueryParam("optionalQueryLong")  Optional<Long> optionalQueryLong, 
	                    @QueryParam("optionalQueryDate") @Parameter(schema=@Schema(format="date")) Optional<OffsetDateTime>  optionalQueryDate, 
	                    @QueryParam("optionalQueryUUID") Optional<UUID> optionalQueryUUID, 
	                    @HeaderParam("optionalHeaderUUID") Optional<UUID> optionalHeaderUUID,
	                    @QueryParam("optionalQueryEnum") Optional<User.UserType> optionalQueryEnum,
	                    @HeaderParam("optionalHeaderString") Optional<String> optionalHeaderString,
	                    @QueryParam("queryUUID") UUID queryUUID,  
	                    @HeaderParam("headerString") String headerString,
 	                    @QueryParam("queryEnum") User.UserType queryEnum, 
	                    @QueryParam("queryIntegerList")    List<Integer>  queryIntegerList, 
	                    @QueryParam("queryLong")   Long  queryLong
 	                    
	                    )
	{
 			
		Map<String,Object> responseMap = new HashMap<>();
		
		responseMap.put("optionalQueryString", optionalQueryString.orElse(null));
		responseMap.put("optionalQueryLong", optionalQueryLong.orElse(null));
	 	responseMap.put("optionalQueryDate", optionalQueryDate.map(OffsetDateTime::toString).orElse(null));
		responseMap.put("optionalQueryUUID", optionalQueryUUID.map(UUID::toString).orElse(null));
		responseMap.put("optionalHeaderUUID", optionalHeaderUUID.map(UUID::toString).orElse(null));
		responseMap.put("optionalHeaderString", optionalHeaderString.orElse(null));
		responseMap.put("optionalQueryEnum", optionalQueryEnum.orElse(null));
		responseMap.put("queryEnum", queryEnum);
		responseMap.put("queryUUID", queryUUID.toString()); 
		responseMap.put("queryLong", queryLong);
		responseMap.put("pathLong", pathLong);
		responseMap.put("headerString", headerString); 
		responseMap.put("queryIntegerList", queryIntegerList); 
		return ServerResponse.response(responseMap).applicationJson(); 
	}
}
