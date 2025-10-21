package informacao;

import java.util.ArrayList;
import java.util.List;

public class Historico {
    private final List<Operacao> registros;

    public Historico() {
        this.registros = new ArrayList<>();
    }

    public void adicionarOperacao(Operacao op) {
        this.registros.add(op);
    }

    public void exibirHistorico() {
        if (registros.isEmpty()) {
            System.out.println("\n[INFO] Histórico vazio.");
            return;
        }

        System.out.println("\n--- HISTÓRICO DE OPERAÇÕES ---");
        for (int i = 0; i < registros.size(); i++) {
            System.out.printf("[%d] %s%n", (i + 1), registros.get(i).getExpressaoCompleta());
        }
        System.out.println("-----------------------------");
    }

    public void limparHistorico() {
        this.registros.clear();
        System.out.println("\n[INFO] Histórico limpo com sucesso!");
    }
}