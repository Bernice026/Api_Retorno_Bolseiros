package ao.bolseiro.api.bolseiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
@EnableSwagger2
public class ApiBolseiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBolseiroApplication.class, args);
	}

}
