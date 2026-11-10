package org.example;

import org.junit.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class VisitorHospitalarTest {

    @Test
    void somaCustosDeConsultasEExames() {
        List<Atendimento> atendimentos = List.of(
                new Consulta("Ana", 150),
                new Exame("Ana", "Raio-X", 80),
                new Consulta("Paulo", 200)
        );
        VisitorCustoTotal visitante = new VisitorCustoTotal();
        atendimentos.forEach(a -> a.aceitar(visitante));
        assertEquals(430.0, visitante.getTotal(), 0.001);
    }

    @Test
    void visitanteIniciaComTotalZero() {
        assertEquals(0.0, new VisitorCustoTotal().getTotal());
    }

    @Test
    void consultaExecutaVisitaDoTipoCorreto() {
        VisitorRelatorio visitante = new VisitorRelatorio();
        new Consulta("Maria", 120).aceitar(visitante);
        assertEquals(List.of("Consulta - Maria - R$ 120.00"), visitante.getLinhas());
    }

    @Test
    void exameExecutaVisitaDoTipoCorreto() {
        VisitorRelatorio visitante = new VisitorRelatorio();
        new Exame("Joao", "Hemograma", 60).aceitar(visitante);
        assertEquals(List.of("Exame (Hemograma) - Joao - R$ 60.00"), visitante.getLinhas());
    }

    @Test
    void diferentesVisitantesPodemOperarNosMesmosObjetos() {
        List<Atendimento> atendimentos = List.of(new Consulta("Ana", 100), new Exame("Ana", "Sangue", 50));
        VisitorCustoTotal custo = new VisitorCustoTotal();
        VisitorRelatorio relatorio = new VisitorRelatorio();
        atendimentos.forEach(a -> {
            a.aceitar(custo);
            a.aceitar(relatorio);
        });
        assertEquals(150, custo.getTotal(), 0.001);
        assertEquals(2, relatorio.getLinhas().size());
    }

    @Test
    void relatorioNaoPermiteAlteracaoExterna() {
        VisitorRelatorio visitante = new VisitorRelatorio();
        new Consulta("Ana", 10).aceitar(visitante);
        assertThrows(UnsupportedOperationException.class, () -> visitante.getLinhas().add("Outro"));
    }

    @Test
    void visitanteNuloERejeitado() {
        assertThrows(NullPointerException.class, () -> new Consulta("Ana", 10).aceitar(null));
        assertThrows(NullPointerException.class, () -> new Exame("Ana", "Raio-X", 10).aceitar(null));
    }

    @Test
    void valoresInvalidosSaoRejeitados() {
        assertThrows(IllegalArgumentException.class, () -> new Consulta("Ana", -10));
        assertThrows(IllegalArgumentException.class, () -> new Exame("Ana", "Raio-X", Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> new Consulta(" ", 10));
        assertThrows(IllegalArgumentException.class, () -> new Exame("Ana", " ", 10));
    }
}
