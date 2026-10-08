package com.example.stateobserver.pedido;

public class PedidoEstadoEnviado extends PedidoEstado {

    public void entregar(Pedido pedido) {
        pedido.setEstado(new PedidoEstadoEntregue());
    }
}