package com.mtdev00.Sistema_Cadastro.Resourche;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.mtdev00.Sistema_Cadastro.Domain.BoletoPayment;

@Service
public class BoletoService {
	public void fillPagamentoComBoleto(BoletoPayment pagtB, Date InstanceOrder) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(InstanceOrder);
		cal.add(Calendar.DAY_OF_MONTH, 30);
		pagtB.setDueDate(cal.getTime());
	}

}
