package com.mtdev00.Sistema_Cadastro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtdev00.Sistema_Cadastro.Domain.BoletoPayment;
import com.mtdev00.Sistema_Cadastro.Domain.CardPayment;
import com.mtdev00.Sistema_Cadastro.Service.OrderService;
@Configuration
public class PaymentConfigJackson {
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(CardPayment.class); 
				objectMapper.registerSubtypes(BoletoPayment.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
	@Bean
	public OrderService orderService() {
	  return new OrderService(); 
	}

}
