package com.example.stateobserver.pedido;

public class Pedido {

    private PedidoEstado estado;

    public Pedido() {
        this.estado = new PedidoEstadoPendente();
    }

    public void pagar() {
        estado.pagar(this);
    }

    public void enviar() {
        estado.enviar(this);
    }

    public void entregar() {
        estado.entregar(this);
    }

    public void cancelar() {
        estado.cancelar(this);
    }

    public void setEstado(PedidoEstado estado) {
        this.estado = estado;
    }

    public PedidoEstado getEstado() {
        return estado;
    }
}