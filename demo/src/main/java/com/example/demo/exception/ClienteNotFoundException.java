package com.example.demo.exception;

public class ClienteNotFoundException extends Throwable {
    public ClienteNotFoundException(Long id) {
            super("Produto não encontrado: " + id);
    }
}
