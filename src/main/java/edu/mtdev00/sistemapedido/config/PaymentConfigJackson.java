package edu.mtdev00.sistemapedido.config;

import edu.mtdev00.sistemapedido.service.OrderService;
import edu.mtdev00.sistemapedido.domain.BoletoPayment;
import edu.mtdev00.sistemapedido.domain.CardPayment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	public OrderService paymentOrderService() {
	    return new OrderService();
	}

}
