package org.example;

public class VisitorCustoTotal implements VisitorAtendimento {
    private double total;

    @Override
    public void visitar(Consulta consulta) {
        total += consulta.getValor();
    }

    @Override
    public void visitar(Exame exame) {
        total += exame.getValor();
    }

    public double getTotal() {
        return total;
    }
}
