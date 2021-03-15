package com.pauloporto.acmeap.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @AllArgsConstructor
public class ExceptionResponse {

    private Date timestamp;
    private String mensagem;
    private String detalhes;
}
