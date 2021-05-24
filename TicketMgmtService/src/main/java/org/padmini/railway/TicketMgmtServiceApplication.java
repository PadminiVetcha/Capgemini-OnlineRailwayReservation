package org.padmini.railway;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import com.google.common.base.Predicate;
import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEmailTools
public class TicketMgmtServiceApplication
{
	public static void main(String[] args) {
		SpringApplication.run(TicketMgmtServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	    return builder.build();
	}
	
	 private ApiKey apiKey() { return new
	 ApiKey("jwtToken","Authorization","header"); }
	 
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
	}
	  
	private List<SecurityReference> defaultAuth() {
		final AuthorizationScope authScope=new AuthorizationScope("global", "accessEverything");
		final AuthorizationScope[] authScopes=new AuthorizationScope[]{authScope};
		return Collections.singletonList(new SecurityReference("APIKey",authScopes));
	}

	@Bean
	public Docket swaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.paths((Predicate<String>) PathSelectors.any())
					.apis((Predicate<RequestHandler>) RequestHandlerSelectors.basePackage("org.padmini.railway"))
					.build()
					.apiInfo(apiDetails())
					.securitySchemes(Arrays.asList(apiKey()))
					.useDefaultResponseMessages(false)
					.securityContexts(Collections.singletonList(securityContext()));
		
	}
	
	private ApiInfo apiDetails()
	{
		return new ApiInfo(
				"Ticket Management API Documentation",
				"API for Train Mgmt Microservice",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Padmini Vetcha", "http://padminivetcha.com", "padmini@gmail.com"),
				"API Licence",
				"http://padminivetcha.com",
				Collections.emptyList());
	}
}