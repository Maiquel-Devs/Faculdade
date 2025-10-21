package informacao;

import java.util.Objects;

public abstract class Operacao {
    private double valorA;
    private double valorB;
    private double resultado;
    private final String simbolo;
    private final boolean isUnaria;

    // Construtor binário
    public Operacao(double valorA, double valorB, String simbolo) {
        this.valorA = valorA;
        this.valorB = valorB;
        this.simbolo = simbolo;
        this.isUnaria = false;
        this.resultado = 0.0;
    }

    // Construtor unário
    public Operacao(double valorA, String simbolo) {
        this.valorA = valorA;
        this.valorB = 0;
        this.simbolo = Objects.requireNonNullElse(simbolo, "FUNC");
        this.isUnaria = true;
        this.resultado = 0.0;
    }

    public abstract double calcular();

    public String getExpressaoCompleta() {
        this.resultado = this.calcular();
        
        if (isUnaria) {
             return String.format("%s(%.2f) = %.2f", this.simbolo, this.valorA, this.resultado);
        } else {
            return String.format("%.2f %s %.2f = %.2f", this.valorA, this.simbolo, this.valorB, this.resultado);
        }
    }
    
    protected double getValorA() { return valorA; }
    protected double getValorB() { return valorB; }
    public double getResultado() { return resultado; }
}