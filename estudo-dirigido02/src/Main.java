import objetos.ItemLEC;
import objetos.MaterialABC;
import objetos.MaterialXYZ;

import java.util.*;

import static objetos.MaterialABC.classificarMateriais;
import static objetos.MaterialABC.exibirResultados;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean continuar = true;
        int opcao;
        while (continuar) {
            System.out.println("\nEscolha o cálculo que deseja realizar: ");
            System.out.println("1 - Cálculo de B, C, D, E e LEC");
            System.out.println("2 - Cáculo de ABC");
            System.out.println("3 - Cálculo de XYZ");
            System.out.println("4 - Sair");

            System.out.print("\nDigite a opção desejada: ");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    calcularLEC(scan);
                    break;
                case 2:
                    calcularABC(scan);
                    break;
                case 3:
                    calcularXYZ(scan);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    public static void calcularLEC(Scanner scan) {
        System.out.print("Quantidade pedida: ");
        double quantidadePedida = scan.nextInt();
        System.out.print("Consumo Anual (CA): ");
        double consumoAnual = scan.nextDouble();
        System.out.print("Custo do Pedido Compra (CC): ");
        double custoPedidoCompra = scan.nextDouble();
        System.out.print("Custo de Material Armazenado (CPA): ");
        double custoMaterialArmazenado = scan.nextDouble();
        System.out.print("Preço Unitário (PU): ");
        double precoUnitario = scan.nextDouble();

        ItemLEC itemLEC = new ItemLEC(quantidadePedida, consumoAnual, custoPedidoCompra, custoMaterialArmazenado, precoUnitario);

        ItemLEC.exibirResultadosLEC(itemLEC);
    }

    private static void calcularABC(Scanner scan) {
        ArrayList<MaterialABC> materiais = new ArrayList<>();
        boolean continuarAdicionando = true;

        while (continuarAdicionando) {
            System.out.print("\nDigite o nome do material: ");
            String nome = scan.next() + scan.nextLine();

            System.out.print("Digite o preço unitário do material: ");
            double precoUnitario = scan.nextDouble();

            System.out.print("Digite o consumo anual em unidades do material: ");
            int consumoAnualUnidades = scan.nextInt();

            materiais.add(new MaterialABC(nome, precoUnitario, consumoAnualUnidades));

            System.out.print("Deseja adicionar outro material? (s/n): ");
            String resposta = scan.next() + scan.nextLine();
            if (!resposta.equalsIgnoreCase("s")) {
                continuarAdicionando = false;
            }
        }

        classificarMateriais(materiais);
        exibirResultados(materiais);
    }

    public static void calcularXYZ(Scanner scan) {
        ArrayList<MaterialXYZ> materiais = new ArrayList<>();
        boolean adicionarMais = true;

        while (adicionarMais) {
            System.out.print("Digite o nome do material: ");
            String nomeMaterial = scan.next() + scan.nextLine();

            String importanciaEquipe = obterRespostaValida(scan, "O material é importante para a equipe? (s/n): ");
            String linhaDeFrente = obterRespostaValida(scan, "O equipamento é de linha de frente? (s/n): ");
            String existeSimilar = obterRespostaValida(scan, "Existe similar? (s/n): ");

            materiais.add(new MaterialXYZ(nomeMaterial, importanciaEquipe, linhaDeFrente, existeSimilar));

            System.out.print("\nDeseja adicionar mais materiais? (s/n): ");
            String resposta = scan.nextLine().toLowerCase();

            if (resposta.equals("n")) {
                adicionarMais = false;
            }
        }

        System.out.println("\nResultados da Classificação XYZ:");
        for (MaterialXYZ material : materiais) {
            System.out.println("Material: " + material.getNome());
            System.out.println("Classificacao: " + material.getClassificacao());
            System.out.println("Descricao: " + material.getDescricao());
            System.out.println("---------------------------------------------------------------------------------------------------------");
        }
    }

    private static String obterRespostaValida(Scanner scan, String mensagem) {
        String resposta;
        do {
            System.out.print(mensagem);
            resposta = scan.nextLine().toLowerCase();
            if (!resposta.equals("s") && !resposta.equals("n")) {
                System.out.println("Resposta inválida. Por favor, digite 's' para sim ou 'n' para não.");
            }
        } while (!resposta.equals("s") && !resposta.equals("n"));
        return resposta;
    }
}
