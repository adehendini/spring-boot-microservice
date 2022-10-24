package ade.hendini;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Produk Service", version = "1.0", description = "Produk Service"))
public class ProdukServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdukServiceApplication.class, args);
	}

}
