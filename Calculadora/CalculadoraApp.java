import informacao.Calculadora;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculadoraApp {
    
    private static final Calculadora calculadora = new Calculadora();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;

        System.out.println("--- CALCULADORA ORIENTADA A OBJETOS (TERMINAL) ---");

        while (opcao != 0) {
            exibirMenu();
            
            try {
                if (scanner.hasNextInt()) {
                    opcao = scanner.nextInt();
                } else {
                    System.out.println("\n[ERRO] Opção inválida. Digite um número do menu.");
                    scanner.next(); 
                    continue;
                }

                boolean shouldContinue = processarOpcao(opcao);
                if (!shouldContinue) {
                    opcao = 0;
                }
                
            } catch (Exception e) {
                System.out.println("\n[ERRO FATAL] Ocorreu um erro inesperado: " + e.getMessage());
                break;
            }
        }
        System.out.println("Calculadora encerrada. Até logo!");
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("  -- OPERAÇÕES BINÁRIAS --");
        System.out.println("  1 - Soma (+) ");
        System.out.println("  2 - Subtração (-)");
        System.out.println("  3 - Multiplicação (*)");
        System.out.println("  4 - Divisão (/)");
        System.out.println("  5 - Potência (x^y)");
        System.out.println("  -- OUTROS --");
        System.out.println("  6 - Ver Histórico");
        System.out.println("  7 - Limpar Histórico");
        System.out.println("  0 - Sair");
        System.out.print("Sua opção: ");
    }
    
    private static boolean perguntarContinuacao() {
        System.out.println("\n--- PRÓXIMA AÇÃO ---");
        System.out.println("1 - Realizar outra ação (Voltar ao menu)");
        System.out.println("0 - Sair da Calculadora");
        System.out.print("Sua escolha: ");
        
        try {
            if (scanner.hasNextInt()) {
                int escolha = scanner.nextInt();
                return escolha != 0;
            } else {
                scanner.next();
                System.out.println("\n[ERRO] Escolha inválida. Retornando ao menu principal.");
                return true;
            }
        } catch (InputMismatchException e) {
             System.out.println("\n[ERRO] Escolha inválida. Retornando ao menu principal.");
             scanner.nextLine();
             return true;
        }
    }


    private static boolean processarOpcao(int opcao) {
        
        if (opcao >= 1 && opcao <= 5) {
            calculadora.executarOperacao(opcao, scanner);
            return perguntarContinuacao();
            
        } else {
            switch (opcao) {
                case 6:
                    calculadora.getHistorico().exibirHistorico();
                    return perguntarContinuacao();
                    
                case 7:
                    calculadora.getHistorico().limparHistorico();
                    return true; 
                    
                case 0:
                    return false;
                    
                default:
                    System.out.println("\n[ERRO] Opção de menu inválida. Retornando ao menu.");
                    return true; 
            }
        }
    }
}