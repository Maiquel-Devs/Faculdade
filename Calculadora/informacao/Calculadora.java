package informacao;

// Importação das Operações
import informacao.operacoes.Soma;
import informacao.operacoes.Subtracao;
import informacao.operacoes.Multiplicacao;
import informacao.operacoes.Divisao;
import informacao.operacoes.Potencia;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculadora {
    private final Historico historico;

    public Calculadora() {
        this.historico = new Historico();
    }

    public void executarOperacao(int tipo, Scanner scanner) {
        Operacao operacao;
        double valorA, valorB;

        String msgValorA = "Digite o primeiro valor: ";
        String msgValorB = "Digite o segundo valor: ";

        if (tipo == 5) {
            msgValorA = "Digite a Base: ";
            msgValorB = "Digite o Expoente: ";
        }

        try {
            System.out.print(msgValorA);
            valorA = scanner.nextDouble();

            System.out.print(msgValorB);
            valorB = scanner.nextDouble();

            
            operacao = switch (tipo) {
                case 1 -> new Soma(valorA, valorB);
                case 2 -> new Subtracao(valorA, valorB);
                case 3 -> new Multiplicacao(valorA, valorB);
                case 4 -> {
                    if (valorB == 0) throw new ArithmeticException("Divisão por zero não é permitida.");
                    yield new Divisao(valorA, valorB); // 'yield' retorna o valor
                }
                case 5 -> new Potencia(valorA, valorB);
                default -> throw new IllegalArgumentException("Opção de operação inválida.");
            };
            
            
            historico.adicionarOperacao(operacao);
            System.out.printf("\n[RESULTADO] %s%n", operacao.getExpressaoCompleta());

        } catch (InputMismatchException e) {
            System.out.println("\n[ERRO] Entrada inválida. Por favor, digite apenas números.");
            scanner.nextLine();
        } catch (ArithmeticException e) {
            System.out.println("\n[ERRO MATEMÁTICO] " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("[ERRO] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n[ERRO] Ocorreu um erro: " + e.getMessage());
        }
    }

    public Historico getHistorico() {
        return historico;
    }
}