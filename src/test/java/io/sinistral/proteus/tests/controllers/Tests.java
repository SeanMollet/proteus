/**
 * 
 */
package io.sinistral.proteus.tests.controllers;

import static io.sinistral.proteus.server.ServerResponse.response;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import com.google.inject.Singleton;
import com.jsoniter.output.JsonStream;

import io.sinistral.proteus.server.ServerRequest;
import io.sinistral.proteus.server.ServerResponse;
import io.sinistral.proteus.tests.models.TestUser;
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
@Tag(name="tests")
@Path("/tests")
@Produces((MediaType.APPLICATION_JSON)) 
@Consumes((MediaType.MEDIA_TYPE_WILDCARD)) 
@Singleton
public class Tests
{
	 private static final ByteBuffer buffer;
	  static {
	    String message = "Hello, World!";
	    byte[] messageBytes = message.getBytes(java.nio.charset.StandardCharsets.US_ASCII);
	    buffer = ByteBuffer.allocateDirect(messageBytes.length);
	    buffer.put(messageBytes);
	    buffer.flip();
	  }
	  
	@GET
	@Path("/exchange/json/serialize")
	@Operation(summary = "Json serialization endpoint",   method = "GET" )
	public void exchangeJsonSerialize(HttpServerExchange exchange)
	{ 
		response( JsonStream.serialize(ImmutableMap.of("message", "Hello, World!")) ).applicationJson().send(exchange);
	}
	
	@GET
	@Path("/exchange/json/serializeToBytes")
	@Operation(summary = "Json serialization with bytes endpoint",   method = "GET" )
	public void exchangeJsonSerializeToBytes(HttpServerExchange exchange)
	{ 
		response( JsonStream.serializeToBytes(ImmutableMap.of("message", "Hello, World!")) ).applicationJson().send(exchange);
	}
	

	
	@GET 
	@Path("/exchange/user/json")
	@Operation(summary = "User serialization endpoint",   method = "GET", responses = { @ApiResponse(
	                                                                                                 content = @Content(mediaType = "application/json",
	                                                                                                 schema = @Schema(implementation = TestUser.class)) ) } )
	public void exchangeUserJson(HttpServerExchange exchange)
	{  
		response( new TestUser(123L) ).applicationJson().send(exchange); 
	}
	
	@GET 
	@Path("/exchange/user/xml")
	@Produces((MediaType.APPLICATION_XML))
	@Operation(summary = "User serialization endpoint",   method = "GET", responses = { @ApiResponse(
	                                                                                                 content = @Content(mediaType=MediaType.APPLICATION_XML,
	                                                                                                 schema = @Schema(implementation = TestUser.class)) ) } )
	public void exchangeUserXml(HttpServerExchange exchange)
	{  
		response( new TestUser(123L) ).applicationXml().send(exchange); 
	}

	@GET
	@Path("/response/user/json")
	@Operation(summary = "User serialization endpoint",   method = "GET",  responses={@ApiResponse(description="The user",content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TestUser.class)))} )
	public ServerResponse<TestUser> responseUserJson(ServerRequest request)
	{ 
 		TestUser user = new TestUser(123L);
		 
		return response( user ).applicationJson(); 
	}
	
	@GET
	@Path("/response/user/xml")
	@Produces((MediaType.APPLICATION_XML))
	@Operation(summary = "User serialization endpoint",   method = "GET", responses={@ApiResponse(description="The user",content = @Content(mediaType = MediaType.APPLICATION_XML, schema = @Schema(implementation = TestUser.class)))} )
	public ServerResponse<TestUser> responseUserXml(ServerRequest request)
	{ 
 		TestUser user = new TestUser(123L);
		 
		return response( user ).applicationXml(); 
	}
	
	
	@GET
	@Path("/exchange/plaintext")
	@Produces((MediaType.TEXT_PLAIN)) 
	@Operation(summary = "Plaintext endpoint",   method = "GET" )
	public void exchangePlaintext(HttpServerExchange exchange)
	{ 
		response("Hello, World!").textPlain().send(exchange);

	}
	
	@GET
	@Path("/exchange/plaintext2")
	@Produces((MediaType.TEXT_PLAIN)) 
	@Operation(summary = "Plaintext endpoint 2",   method = "GET" )
	public void exchangePlaintext2(HttpServerExchange exchange)
	{ 
		exchange.getResponseHeaders().put(io.undertow.util.Headers.CONTENT_TYPE, "text/plain");
	    exchange.getResponseSender().send(buffer.duplicate());
	}
	
	@GET
	@Path("/response/plaintext")
	@Produces((MediaType.TEXT_PLAIN)) 
	@Operation(summary = "Plaintext endpoint",   method = "GET" )
	public ServerResponse<ByteBuffer> responsePlaintext(ServerRequest request)
	{ 
		return response("Hello, World!").textPlain();

	}
	
	@GET
	@Path("/response/future/map")
	@Operation(summary = "Future map endpoint",   method = "GET" )
	public CompletableFuture<ServerResponse<ImmutableMap<String,String>>> responseFutureMap()
	{ 
		return CompletableFuture.completedFuture(response( ImmutableMap.of("message", "success") ).applicationJson());
	}
	
	@POST
	@Path("/response/file/path")
	@Produces(MediaType.APPLICATION_OCTET_STREAM) 
 	@Consumes("*/*")
	@Operation(summary = "Upload file path endpoint",   method = "POST" )
	public ServerResponse<ByteBuffer> responseUploadFilePath(ServerRequest request, @FormParam("file") java.nio.file.Path file ) throws Exception
	{  
		return response(ByteBuffer.wrap(Files.toByteArray(file.toFile()))).applicationOctetStream(); 
	}
	
	@POST
	@Path("/response/json/echo")
	@Produces(MediaType.APPLICATION_OCTET_STREAM) 
 	@Consumes("*/*")
	@Operation(summary = "Echo json endpoint",   method = "POST", responses={@ApiResponse(description="The user",content = @Content(schema = @Schema(implementation = TestUser.class)))} )
	public ServerResponse<TestUser> responseEchoJson(ServerRequest request, @FormParam("user") TestUser user ) throws Exception
	{  
		return response(user).applicationJson();
	}
	
	@POST
	@Path("/response/file/bytebuffer")
	@Produces(MediaType.APPLICATION_OCTET_STREAM) 
 	@Consumes("*/*")
	@Operation(summary = "Upload file path endpoint",   method = "POST" )
	public ServerResponse<ByteBuffer> responseUploadByteBuffer(ServerRequest request, @FormParam("file") ByteBuffer file ) throws Exception
	{ 
		 
		return response(file).applicationOctetStream();
		 

	}
	
	@GET
	@Path("/response/future/user")
	@Operation(summary = "Future user endpoint",   method = "GET" )
	public CompletableFuture<ServerResponse<TestUser>> responseFutureUser()
	{ 
		return CompletableFuture.completedFuture(response( new TestUser(123L) ).applicationJson() );
	}
	
	@GET
	@Path("/response/parameters/complex/{pathLong}")
	@Operation(summary = "Complex parameters", method = "GET")
	public ServerResponse<Map<String,Object>> complexParameters(
	                    final ServerRequest serverRequest, 
	                    @PathParam("pathLong") final Long pathLong, 
	                    @QueryParam("optionalQueryString")  Optional<String> optionalQueryString, 
	                    @QueryParam("optionalQueryLong")  Optional<Long> optionalQueryLong, 
	                    @QueryParam("optionalQueryDate") @Parameter( schema=@Schema(type="string",format="date-time",implementation=OffsetDateTime.class))  Optional<OffsetDateTime>  optionalQueryDate, 
	                    @QueryParam("optionalQueryUUID") Optional<UUID> optionalQueryUUID, 
	                    @HeaderParam("optionalHeaderUUID") Optional<UUID> optionalHeaderUUID,
	                    @QueryParam("optionalQueryEnum") Optional<TestUser.UserType> optionalQueryEnum,
	                    @HeaderParam("optionalHeaderString") Optional<String> optionalHeaderString,
	                    @QueryParam("queryUUID") UUID queryUUID,  
	                    @HeaderParam("headerString") String headerString,
 	                    @QueryParam("queryEnum") TestUser.UserType queryEnum, 
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
		return response(responseMap).applicationJson(); 
	}
}
