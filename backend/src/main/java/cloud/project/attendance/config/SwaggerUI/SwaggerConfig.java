package cloud.project.attendance.config.SwaggerUI;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Attendance Systemt")
                        .version("1.0.0")
                        .description("API for Attendance Systemt")
                        .contact(new Contact()
                                .name("Lê Minh Tân")
                                .url("https://github.com/letan-165/attendance-system"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}

