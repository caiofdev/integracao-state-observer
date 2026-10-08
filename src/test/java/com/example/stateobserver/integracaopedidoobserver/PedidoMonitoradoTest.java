package com.example.stateobserver.integracaopedidoobserver;

import org.junit.jupiter.api.Test;

import com.example.stateobserver.pedido.Cliente;
import com.example.stateobserver.pedido.Estoque;
import com.example.stateobserver.pedido.Financeiro;

import static org.junit.jupiter.api.Assertions.*;

class PedidoMonitoradoTest {

    @Test
    public void deveNotificarClienteQuandoPedidoForPago() {
        PedidoMonitorado pedido = new PedidoMonitorado();
        Cliente cliente = new Cliente("Ana Costa");
        pedido.adicionarObservador(cliente);

        pedido.pagar();

        assertEquals("Pedido mudou de Pendente para Pago", cliente.getUltimaNotificacao());
    }

    @Test
    public void deveNotificarTodosOsObservadoresCadastrados() {
        PedidoMonitorado pedido = new PedidoMonitorado();
        Cliente cliente = new Cliente("Ana Costa");
        Estoque estoque = new Estoque();
        Financeiro financeiro = new Financeiro();

        pedido.adicionarObservador(cliente);
        pedido.adicionarObservador(estoque);
        pedido.adicionarObservador(financeiro);

        pedido.pagar();

        String mensagemEsperada = "Pedido mudou de Pendente para Pago";
        assertEquals(mensagemEsperada, cliente.getUltimaNotificacao());
        assertEquals(mensagemEsperada, financeiro.getUltimaNotificacao());
        assertTrue(estoque.getHistoricoNotificacoes().contains(mensagemEsperada));
    }

    @Test
    public void deveNotificarCadaTransicaoDoFluxoCompletoDoPedido() {
        PedidoMonitorado pedido = new PedidoMonitorado();
        Estoque estoque = new Estoque();
        pedido.adicionarObservador(estoque);

        pedido.pagar();     // Pendente -> Pago
        pedido.enviar();    // Pago -> Enviado
        pedido.entregar();  // Enviado -> Entregue

        assertEquals(3, estoque.getHistoricoNotificacoes().size());
        assertEquals("Pedido mudou de Pendente para Pago", estoque.getHistoricoNotificacoes().get(0));
        assertEquals("Pedido mudou de Pago para Enviado", estoque.getHistoricoNotificacoes().get(1));
        assertEquals("Pedido mudou de Enviado para Entregue", estoque.getHistoricoNotificacoes().get(2));
    }

    @Test
    public void naoDeveNotificarObservadorRemovido() {
        PedidoMonitorado pedido = new PedidoMonitorado();
        Cliente cliente = new Cliente("Ana Costa");

        pedido.adicionarObservador(cliente);
        pedido.removerObservador(cliente);
        pedido.pagar();

        assertNull(cliente.getUltimaNotificacao());
    }

    @Test
    public void transicaoInvalidaNaoDeveNotificarNinguem() {
        PedidoMonitorado pedido = new PedidoMonitorado();
        Estoque estoque = new Estoque();
        pedido.adicionarObservador(estoque);

        assertThrows(IllegalStateException.class, pedido::entregar);
        assertTrue(estoque.getHistoricoNotificacoes().isEmpty());
    }

    @Test
    public void pedidoCanceladoNaoGeraMaisNotificacoesAposCancelado() {
        PedidoMonitorado pedido = new PedidoMonitorado();
        Estoque estoque = new Estoque();
        pedido.adicionarObservador(estoque);

        pedido.cancelar();
        assertEquals(1, estoque.getHistoricoNotificacoes().size());

        assertThrows(IllegalStateException.class, pedido::pagar);
        assertEquals(1, estoque.getHistoricoNotificacoes().size());
    }
}