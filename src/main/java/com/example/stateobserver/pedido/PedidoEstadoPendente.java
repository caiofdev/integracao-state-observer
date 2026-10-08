package com.example.stateobserver.pedido;

public class PedidoEstadoPendente extends PedidoEstado {

    public void pagar(Pedido pedido) {
        pedido.setEstado(new PedidoEstadoPago());
    }

    public void cancelar(Pedido pedido) {
        pedido.setEstado(new PedidoEstadoCancelado());
    }
}