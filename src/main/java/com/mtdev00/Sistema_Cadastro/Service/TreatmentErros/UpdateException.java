package com.mtdev00.Sistema_Cadastro.Service.TreatmentErros;

import java.util.List;

public class UpdateException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private List<FieldMessage> fieldMessages;

    public UpdateException(String msg) {
        super(msg);
    }

    public UpdateException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UpdateException(List<FieldMessage> fieldMessages) {
        super("Ocorreu um erro de importação duplicada.");
        this.fieldMessages = fieldMessages;
    }

    public List<FieldMessage> getFieldMessages() {
        return fieldMessages;
    }
}
