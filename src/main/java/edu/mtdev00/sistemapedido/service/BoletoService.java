package edu.mtdev00.sistemapedido.service;

import edu.mtdev00.sistemapedido.domain.BoletoPayment;
import edu.mtdev00.sistemapedido.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

	@Value("${boletosimples.api.url}")
	private String apiUrl;

	@Value("${boletosimples.api.token}")
	private String apiToken;

	private final RestTemplate restTemplate;

	public BoletoService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void fillPagamentoComBoleto(BoletoPayment pagtB, Date InstanceOrder) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(InstanceOrder);
		cal.add(Calendar.DAY_OF_MONTH, 30);
		pagtB.setDueDate(cal.getTime());
	}

	public byte[] gerarBoleto(OrderDTO orderDTO) {
		String endpoint = apiUrl + "/boletos";

		BoletoPayment boletoPayment = new BoletoPayment();
		boletoPayment.setPagadorNome(orderDTO.getClientName());
		boletoPayment.setPagadorDocumento(orderDTO.getCpfCNPJ());
		boletoPayment.setValor(String.valueOf(orderDTO.getAmountTotal()));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(apiToken);

		HttpEntity<BoletoPayment> entity = new HttpEntity<>(boletoPayment, headers);

		ResponseEntity<byte[]> response = restTemplate.postForEntity(endpoint, entity, byte[].class);

		if (response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		} else {
			throw new RuntimeException("Falha ao gerar boleto. Status: " + response.getStatusCodeValue());
		}
	}
}
