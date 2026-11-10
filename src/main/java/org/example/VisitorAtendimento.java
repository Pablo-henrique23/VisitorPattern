package org.example;

public interface VisitorAtendimento {
    void visitar(Consulta consulta);
    void visitar(Exame exame);
}
