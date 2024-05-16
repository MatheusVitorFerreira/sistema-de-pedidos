package edu.mtdev00.sistemapedido.resource;

import edu.mtdev00.sistemapedido.dto.OrderDTO;
import edu.mtdev00.sistemapedido.service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class BoletoResource {

    private final BoletoService boletoService;

    @Autowired
    public BoletoResource(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @PostMapping("/gerar-boleto")
    public ResponseEntity<String> gerarBoleto(@RequestBody OrderDTO orderDTO) {
        byte[] boletoBytes = boletoService.gerarBoleto(orderDTO);

        String base64EncodedBoleto = Base64.getEncoder().encodeToString(boletoBytes);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_PDF)
                .body(base64EncodedBoleto);
    }
}
