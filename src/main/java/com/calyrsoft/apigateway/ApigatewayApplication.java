package com.calyrsoft.apigateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ApigatewayApplication {


	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}


	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route( p -> p
						.path("/all")
						.uri("http://ms-product:9090"))
				.route( p -> p
						.path("/todosmisproductos")
						.filters( f-> f.rewritePath("/todosmisproductos", "/all"))
						.uri("http://ms-product:9090"))
				.route(p -> p
						.path("/get")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri("http://httpbin.org:80"))
				.route("create_producto", r -> r.path("/")
						.and()
						.method("POST")
						.uri("http://ms-product:9090"))
				.route("create_producto_version2", r -> r.path("/crear")
						.and()
						.method("POST")
						.filters(f->f.rewritePath("/crear","/"))
						.uri("http://ms-product:9090"))
				.build();

	}


}
