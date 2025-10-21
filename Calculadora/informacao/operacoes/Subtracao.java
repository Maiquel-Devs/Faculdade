package informacao.operacoes;

import informacao.Operacao;

public class Subtracao extends Operacao {
    public Subtracao(double valorA, double valorB) {
        super(valorA, valorB, "-");
    }
    @Override
    public double calcular() {
        return getValorA() - getValorB();
    }
}