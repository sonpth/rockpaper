package code.resmed.rockpaper.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {  
	private static final String APP_ID ="rockpaper";
	
    @Bean
    public Docket v2Api() {
        return new Docket(DocumentationType.SWAGGER_2)
        	.securitySchemes(Arrays.asList(basicAuth()))
        	.securityContexts(securityContexts())
        	.select()
        	.apis(RequestHandlerSelectors.basePackage("code.resmed.rockpaper"))
            .paths(PathSelectors.any())
            .build()         
            .pathMapping("/") 
            .produces(new HashSet<String>(Arrays.asList(new String[]{MediaType.APPLICATION_JSON_VALUE})))
        ;
    }
    
	private BasicAuth basicAuth() {		
		return new BasicAuth(APP_ID);
	}
    
	//https://stackoverflow.com/questions/54648227/how-fix-security-scope-definition-global-could-not-be-resolved-in-springfox
	private List<SecurityContext> securityContexts() {
		return Arrays.asList(SecurityContext.builder().securityReferences(defaultAuth()).build());
	}
	
	private List<SecurityReference> defaultAuth() {
		return Arrays.asList(new SecurityReference(APP_ID, new AuthorizationScope[]{}));
	}
}