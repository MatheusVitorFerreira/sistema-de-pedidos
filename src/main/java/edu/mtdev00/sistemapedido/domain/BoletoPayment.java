package edu.mtdev00.sistemapedido.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonTypeName("boletoPayment")
public class BoletoPayment extends Payment {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dueDate;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date paymentDate;

	private String pagadorNome;
	private String pagadorDocumento;
	private String valor;
	private String idBoleto;

}
