package informacao.operacoes;

import informacao.Operacao;

public class Potencia extends Operacao {
    public Potencia(double base, double expoente) {
        super(base, expoente, "^");
    }
    @Override
    public double calcular() {
        return Math.pow(getValorA(), getValorB());
    }
}