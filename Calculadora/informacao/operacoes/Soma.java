package informacao.operacoes;

import informacao.Operacao;

public class Soma extends Operacao {
    public Soma(double valorA, double valorB) {
        super(valorA, valorB, "+");
    }
    @Override
    public double calcular() {
        return getValorA() + getValorB();
    }
}