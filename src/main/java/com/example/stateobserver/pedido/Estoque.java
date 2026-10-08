package com.example.stateobserver.pedido;

import com.example.stateobserver.observer.Observador;

import java.util.ArrayList;
import java.util.List;

public class Estoque implements Observador {

    private final List<String> historicoNotificacoes = new ArrayList<>();

    public void atualizar(String mensagem) {
        historicoNotificacoes.add(mensagem);
    }

    public List<String> getHistoricoNotificacoes() {
        return historicoNotificacoes;
    }
}