package ch.benoitschopfer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.List;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@Configuration
@EnableSwagger2
public class OpenAPIDocumentationConfig {
  private static final String BASIC_AUTH = "basicAuth";
  private static final String BEARER_AUTH = "Bearer";

  ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("Contacts API")
      .description("This is the Contact API for Open Web Technology Hiring Test.")
      .license("Apache 2.0")
      .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
      .termsOfServiceUrl("")
      .version("1.0.2")
      .contact(new Contact("Benoît Schopfer", "", "mails@benoitschopfer.ch"))
      .build();
  }

  @Bean
  public Docket customImplementation(ServletContext servletContext, @Value("${openapi.Contacts API.base-path:/api}") String basePath) {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("ch.benoitschopfer"))
      .build()
      .pathProvider(new BasePathAwareRelativePathProvider(servletContext, basePath))
      .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
      .directModelSubstitute(java.time.OffsetDateTime.class, java.util.Date.class)
      .apiInfo(apiInfo())
      .securitySchemes(securitySchemes())
      .securityContexts(List.of(securityContext()))
      .useDefaultResponseMessages(false);
  }

  private List<SecurityScheme> securitySchemes() {
    return List.of(new BasicAuth(BASIC_AUTH), new ApiKey(BEARER_AUTH, "Authorization", "header"));
  }

  private SecurityContext securityContext() {
    return SecurityContext
      .builder()
      .securityReferences(List.of(basicAuthReference(), bearerAuthReference()))
      .forPaths(PathSelectors.ant("/api/**"))
      .build();
  }

  private SecurityReference basicAuthReference() {
    return new SecurityReference(BASIC_AUTH, new AuthorizationScope[0]);
  }

  private SecurityReference bearerAuthReference() {
    return new SecurityReference(BEARER_AUTH, new AuthorizationScope[0]);
  }

  class BasePathAwareRelativePathProvider extends RelativePathProvider {
    private String basePath;

    public BasePathAwareRelativePathProvider(ServletContext servletContext, String basePath) {
      super(servletContext);
      this.basePath = basePath;
    }

    @Override
    protected String applicationPath() {
      return Paths.removeAdjacentForwardSlashes(UriComponentsBuilder.fromPath(super.applicationPath()).path(basePath).build().toString());
    }

    @Override
    public String getOperationPath(String operationPath) {
      UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
      return Paths.removeAdjacentForwardSlashes(
        uriComponentsBuilder.path(operationPath.replaceFirst("^" + basePath, "")).build().toString());
    }
  }

}
