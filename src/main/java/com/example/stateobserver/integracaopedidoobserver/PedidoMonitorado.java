package com.example.stateobserver.integracaopedidoobserver;

import com.example.stateobserver.pedido.Pedido;
import com.example.stateobserver.pedido.PedidoEstado;

import com.example.stateobserver.observer.Observador;
import com.example.stateobserver.observer.Observavel;

import java.util.ArrayList;
import java.util.List;

public class PedidoMonitorado extends Pedido implements Observavel {

    private final List<Observador> observadores = new ArrayList<>();

    public void adicionarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores(String mensagem) {
        for (Observador observador : observadores) {
            observador.atualizar(mensagem);
        }
    }

    @Override
    public void setEstado(PedidoEstado novoEstado) {
        String estadoAnterior = nomeDoEstado(getEstado());
        super.setEstado(novoEstado);
        String estadoAtual = nomeDoEstado(novoEstado);

        notificarObservadores("Pedido mudou de " + estadoAnterior + " para " + estadoAtual);
    }

    private String nomeDoEstado(PedidoEstado estado) {
        if (estado == null) {
            return "Nenhum";
        }
        return estado.getClass().getSimpleName().replace("PedidoEstado", "");
    }
}