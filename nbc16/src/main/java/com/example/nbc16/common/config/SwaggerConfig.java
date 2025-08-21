package com.example.nbc16.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		final String schemeName = "bearerAuth";
		return new OpenAPI()
			.info(new Info().title("nbc_16 API").version("v1"))
			.addSecurityItem(new SecurityRequirement().addList(schemeName))
			.components(new Components().addSecuritySchemes(schemeName,
				new SecurityScheme()
					.name(schemeName)
					.type(SecurityScheme.Type.HTTP)
					.scheme("bearer")
					.bearerFormat("JWT") // 표시 용도(옵션)
			));
	}
}
