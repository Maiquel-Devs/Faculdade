package informacao.operacoes;

import informacao.Operacao;

public class Divisao extends Operacao {
    public Divisao(double valorA, double valorB) {
        super(valorA, valorB, "/");
    }
    @Override
    public double calcular() {
        return getValorA() / getValorB();
    }
}