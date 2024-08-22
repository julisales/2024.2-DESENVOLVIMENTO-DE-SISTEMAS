package objetos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MaterialABC {
    private String nome;
    private double precoUnitario;
    private int consumoAnualUnidades;
    private String classificacao;

    public MaterialABC(String nome, double precoUnitario, int consumoAnualUnidades) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.consumoAnualUnidades = consumoAnualUnidades;
        this.classificacao = "";
    }

    public double calcularValorConsumoAnual() {
        return precoUnitario * consumoAnualUnidades;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public static void classificarMateriais(ArrayList<MaterialABC> materiais) {
        double valorTotalConsumoAnual = materiais.stream()
                .mapToDouble(MaterialABC::calcularValorConsumoAnual)
                .sum();

        // Ordena os materiais por valor de consumo anual em ordem decrescente
        Collections.sort(materiais, new Comparator<MaterialABC>() {
            @Override
            public int compare(MaterialABC m1, MaterialABC m2) {
                return Double.compare(m2.calcularValorConsumoAnual(), m1.calcularValorConsumoAnual());
            }
        });

        double valorAcumulado = 0;
        double porcentagemAcumulada = 0;
        double limiteA = 70;
        double limiteB = 95;

        for (int i = 0; i < materiais.size(); i++) {
            MaterialABC material = materiais.get(i);
            valorAcumulado += material.calcularValorConsumoAnual();
            porcentagemAcumulada = (valorAcumulado / valorTotalConsumoAnual) * 100;

            if (Math.abs(porcentagemAcumulada - limiteA) < Math.abs(porcentagemAcumulada - limiteB)) {
                material.setClassificacao("A");
            } else if (Math.abs(porcentagemAcumulada - limiteB) < Math.abs(porcentagemAcumulada - 100)) {
                material.setClassificacao("B");
            } else {
                material.setClassificacao("C");
            }
        }
    }

    public static void exibirResultados(ArrayList<MaterialABC> materiais) {
        System.out.println("\nResultados da Classificação ABC:");
        double valorTotalConsumoAnual = materiais.stream()
                .mapToDouble(MaterialABC::calcularValorConsumoAnual)
                .sum();
        double valorAcumulado = 0;
        for (MaterialABC material : materiais) {
            valorAcumulado += material.calcularValorConsumoAnual();
            double porcentagemAcumulada = (valorAcumulado / valorTotalConsumoAnual) * 100;
            System.out.println("Nome: " + material.nome);
            System.out.println("Preço Unitário: " + String.format("%.2f", material.precoUnitario));
            System.out.println("Consumo Anual: " + material.consumoAnualUnidades);
            System.out.println("Valor Consumo Anual: " + String.format("%.2f", material.calcularValorConsumoAnual()));
            System.out.println("Valor Consumo Anual Acumulado: " + String.format("%.2f, valorAcumulado"));
            System.out.println("% sobre o valor do consumo anual: " + String.format("%.2f", porcentagemAcumulada));
            System.out.println("Classificação: " + material.getClassificacao());
            System.out.println("------------------------------------------------");
        }
    }
}


