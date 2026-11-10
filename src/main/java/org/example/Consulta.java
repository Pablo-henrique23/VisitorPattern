package org.example;

import java.util.Objects;

public class Consulta implements Atendimento {
    private final String paciente;
    private final double valor;

    public Consulta(String paciente, double valor) {
        if (paciente == null || paciente.isBlank()) {
            throw new IllegalArgumentException("Paciente obrigatorio");
        }
        if (!Double.isFinite(valor) || valor < 0) {
            throw new IllegalArgumentException("Valor invalido");
        }
        this.paciente = paciente;
        this.valor = valor;
    }

    public String getPaciente() {
        return paciente;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public void aceitar(VisitorAtendimento visitante) {
        Objects.requireNonNull(visitante).visitar(this);
    }
}
