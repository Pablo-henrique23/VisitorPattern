package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VisitorRelatorio implements VisitorAtendimento {
    private final List<String> linhas = new ArrayList<>();

    @Override
    public void visitar(Consulta consulta) {
        linhas.add(String.format(Locale.ROOT,
                "Consulta - %s - R$ %.2f", consulta.getPaciente(), consulta.getValor()));
    }

    @Override
    public void visitar(Exame exame) {
        linhas.add(String.format(Locale.ROOT,
                "Exame (%s) - %s - R$ %.2f",
                exame.getTipo(), exame.getPaciente(), exame.getValor()));
    }

    public List<String> getLinhas() {
        return List.copyOf(linhas);
    }
}
