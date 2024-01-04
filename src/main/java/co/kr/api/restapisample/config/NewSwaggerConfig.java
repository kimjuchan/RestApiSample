package co.kr.api.restapisample.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info= @Info(title= " test", description = "spring boot" , version= "v1.0.0")

)
@Configuration
@RequiredArgsConstructor
public class NewSwaggerConfig {

    @Bean
    public GroupedOpenApi customTestOpenAPI(){
        String[] path = {"/users/**", "/admin/**"};

        return GroupedOpenApi.builder()
                        .group("일반 사용자와 관리자를 위한 User 도메인에 대한 API")
                        .pathsToMatch(path)
                        .build();
    }
}
