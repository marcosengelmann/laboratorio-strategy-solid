import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcessadorDeVendas {

    private final EstrategiaFrete estrategiaFrete;

    public ProcessadorDeVendas(EstrategiaFrete estrategiaFrete) {
        this.estrategiaFrete = estrategiaFrete;
    }

    public void processar(Cliente cliente, List<Item> itens) {
        System.out.println("Iniciando processamento...");

        if (!cliente.isDocumentoValido()) {
            System.out.println("Erro: Documento invalido.");
            return;
        }

        double subtotal = calcularSubtotal(itens);
        double impostos = calcularImpostos(itens);

        double valorFrete = this.estrategiaFrete.calcularFrete(cliente);

        double totalFinal = subtotal + impostos + valorFrete;

        imprimirRecibo(
            cliente,
            subtotal,
            impostos,
            valorFrete,
            totalFinal
        );
    }

    private double calcularSubtotal(List<Item> itens) {
        double total = 0;

        for (Item item : itens) {
            total += item.getSubtotal();
        }

        return total;
    }

    private double calcularImpostos(List<Item> itens) {
        double impostoTotal = 0;

        for (Item item : itens) {
            if (item.getValor() > 100) {
                impostoTotal += item.getSubtotal() * 0.15;
            } else {
                impostoTotal += item.getSubtotal() * 0.05;
            }
        }

        return impostoTotal;
    }

    private void imprimirRecibo(
        Cliente cliente,
        double subtotal,
        double impostos,
        double frete,
        double totalFinal
    ) {
        System.out.println("=====================================");
        System.out.println("RECIBO DE VENDA");
        System.out.println("=====================================");
        System.out.println("Data: " + new Date());
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Documento: " + cliente.getDocumento());
        System.out.println("-------------------------------------");
        System.out.println("Subtotal Itens: R$ " + subtotal);
        System.out.println("Total Impostos: R$ " + impostos);
        System.out.println("Frete: R$ " + frete);
        System.out.println("-------------------------------------");
        System.out.println("TOTAL A PAGAR: R$ " + totalFinal);
        System.out.println("=====================================");
    }

    public static void main(String[] args) {

        Cliente clienteVip =
            new Cliente(
                "Joao Silva",
                "12345678901",
                1,
                "SP"
            );

        Cliente clienteSudeste =
            new Cliente(
                "Maria Souza",
                "98765432100",
                2,
                "SP"
            );

        Cliente clienteNacional =
            new Cliente(
                "Carlos Oliveira",
                "11122233344",
                2,
                "PR"
            );

        List<Item> itens = new ArrayList<>();

        itens.add(new Item(50.0, 2));
        itens.add(new Item(150.0, 1));
        itens.add(new Item(30.0, 3));

        ProcessadorDeVendas processadorVip =
            new ProcessadorDeVendas(
                new FreteVipStrategy()
            );

        ProcessadorDeVendas processadorSudeste =
            new ProcessadorDeVendas(
                new FreteSudesteStrategy()
            );

        ProcessadorDeVendas processadorNacional =
            new ProcessadorDeVendas(
                new FreteNacionalStrategy()
            );

        System.out.println("\n===== CLIENTE VIP =====");
        processadorVip.processar(clienteVip, itens);

        System.out.println("\n===== CLIENTE SUDESTE =====");
        processadorSudeste.processar(clienteSudeste, itens);

        System.out.println("\n===== CLIENTE NACIONAL =====");
        processadorNacional.processar(clienteNacional, itens);
    }
}