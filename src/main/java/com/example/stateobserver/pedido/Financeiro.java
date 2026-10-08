package com.example.stateobserver.pedido;

import com.example.stateobserver.observer.Observador;

public class Financeiro implements Observador {

    private String ultimaNotificacao;

    public void atualizar(String mensagem) {
        this.ultimaNotificacao = mensagem;
    }

    public String getUltimaNotificacao() {
        return ultimaNotificacao;
    }
}