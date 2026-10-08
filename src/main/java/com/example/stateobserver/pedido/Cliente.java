package com.example.stateobserver.pedido;

import com.example.stateobserver.observer.Observador;

public class Cliente implements Observador {

    private final String nome;
    private String ultimaNotificacao;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public void atualizar(String mensagem) {
        this.ultimaNotificacao = mensagem;
    }

    public String getUltimaNotificacao() {
        return ultimaNotificacao;
    }

    public String getNome() {
        return nome;
    }
}