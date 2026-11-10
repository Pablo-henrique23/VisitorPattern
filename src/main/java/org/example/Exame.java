package org.example;

import java.util.Objects;

public class Exame implements Atendimento {
    private final String paciente;
    private final String tipo;
    private final double valor;

    public Exame(String paciente, String tipo, double valor) {
        if (paciente == null || paciente.isBlank()) {
            throw new IllegalArgumentException("Paciente obrigatorio");
        }
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo de exame obrigatorio");
        }
        if (!Double.isFinite(valor) || valor < 0) {
            throw new IllegalArgumentException("Valor invalido");
        }
        this.paciente = paciente;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getPaciente() {
        return paciente;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public void aceitar(VisitorAtendimento visitante) {
        Objects.requireNonNull(visitante).visitar(this);
    }
}
