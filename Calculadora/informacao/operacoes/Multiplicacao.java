package informacao.operacoes;

import informacao.Operacao;

public class Multiplicacao extends Operacao {
    public Multiplicacao(double valorA, double valorB) {
        super(valorA, valorB, "*");
    }
    @Override
    public double calcular() {
        return getValorA() * getValorB();
    }
}