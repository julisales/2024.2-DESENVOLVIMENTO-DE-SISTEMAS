package objetos;

public class ItemLEC {
    private double quantidadePedida;
    private double consumoAnual;
    private double custoPedidoCompra;
    private double custoMaterialArmazenado;
    private double precoUnitario;

    public ItemLEC(double quantidadePedida, double consumoAnual, double custoPedidoCompra, double custoMaterialArmazenado, double precoUnitario) {
        this.quantidadePedida = quantidadePedida;
        this.consumoAnual = consumoAnual;
        this.custoPedidoCompra = custoPedidoCompra;
        this.custoMaterialArmazenado = custoMaterialArmazenado;
        this.precoUnitario = precoUnitario;
    }

    public double calcularNumPedidosAnuais() {
        return consumoAnual / quantidadePedida;
    }

    public double calcularEstoqueMedio() {
        return (quantidadePedida / 2) * precoUnitario;
    }

    public double calcularCustoPosse() {
        return calcularEstoqueMedio() * custoMaterialArmazenado;
    }

    public double calcularCustoPedido() {
        return calcularNumPedidosAnuais() * custoPedidoCompra;
    }

    public double calcularCustoTotal() {
        return calcularCustoPosse() + calcularCustoPedido();
    }

    public double calcularLEC() {
        return Math.sqrt((2 * consumoAnual * custoPedidoCompra) / (custoMaterialArmazenado * precoUnitario));
    }

    public static void exibirResultadosLEC(ItemLEC itemLEC) {
        System.out.println("\nResultados do Cálculo LEC:");
        System.out.printf("Quantidade Pedida: %.2f%n", itemLEC.quantidadePedida);
        System.out.printf("Número de Pedidos Anuais: %.2f%n", itemLEC.calcularNumPedidosAnuais());
        System.out.printf("Estoque Médio: %.2f%n", itemLEC.calcularEstoqueMedio());
        System.out.printf("Custo de Posse: %.2f%n", itemLEC.calcularCustoPosse());
        System.out.printf("Custo do Pedido: %.2f%n", itemLEC.calcularCustoPedido());
        System.out.printf("Custo Total: %.2f%n", itemLEC.calcularCustoTotal());
        System.out.printf("LEC: %.2f%n", itemLEC.calcularLEC());
    }
}
