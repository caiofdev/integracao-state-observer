package com.example.stateobserver.observer;

public interface Observavel {

    void adicionarObservador(Observador observador);

    void removerObservador(Observador observador);

    void notificarObservadores(String mensagem);
}
