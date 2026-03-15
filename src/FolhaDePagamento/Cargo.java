package FolhaDePagamento;

import java.util.Scanner;

public class Cargo {

    private String nome;
    private double salarioBase;
    private Scanner sc = new Scanner(System.in);

    public Cargo(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public String getNome() {
        return nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }


    public Cargo escolherCargo() {

        int opcao;
        double salarioPersonalizado = 0;


        System.out.println("""
                Escolha o cargo:\
                
                1 - Auxiliar\
                
                2 - Encarregado\
                
                3 - Gerente\
                
                4 - Personalizado""");

        while (true) {
            opcao = sc.nextInt();

            if (opcao < 1 || opcao > 4) {
                System.out.println("Opção Inválida.");
            } else {
                break;
            }
        }

        if (opcao == 4) {
            System.out.print("Informe o salário do cargo personalizado: ");
            salarioPersonalizado = sc.nextDouble();
        }

        return switch (opcao) {
            case 1 -> new Cargo("Auxiliar", 1621);
            case 2 -> new Cargo("Encarregado", 2500);
            case 3 -> new Cargo("Gerente", 5000);
            case 4 -> new Cargo("Personalizado", salarioPersonalizado);
            default -> new Cargo("Auxiliar", 1621);
        };
    }
}