package com.example.stateobserver.pedido;

public abstract class PedidoEstado {

    public void pagar(Pedido pedido) {
        operacaoInvalida();
    }

    public void enviar(Pedido pedido) {
        operacaoInvalida();
    }

    public void entregar(Pedido pedido) {
        operacaoInvalida();
    }

    public void cancelar(Pedido pedido) {
        operacaoInvalida();
    }

    private void operacaoInvalida() {
        throw new IllegalStateException("Operação não permitida no estado atual do pedido");
    }
}