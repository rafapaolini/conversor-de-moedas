package br.com.seuusuario;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    private static final String API_KEY = "ca9e249b6da4332330ef12d6"; // sua chave

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("****************************");
        System.out.println("Seja bem-vindo/a ao Conversor de Moeda =]");
        System.out.println("****************************");

        while (true) {
            System.out.println("Escolha uma opcao:");
            System.out.println("1) USD -> BRL");
            System.out.println("2) BRL -> USD");
            System.out.println("3) EUR -> BRL");
            System.out.println("4) BRL -> EUR");
            System.out.println("5) ARS -> USD");
            System.out.println("6) COP -> BRL");
            System.out.println("7) Sair");
            System.out.print("Opcao: ");
            int opcao = scanner.nextInt();

            if (opcao == 7) {
                System.out.println("Saindo...");
                break;
            }

            System.out.print("Digite o valor que deseja converter: ");
            double valor = scanner.nextDouble();

            String deMoeda;
            String paraMoeda;

            switch (opcao) {
                case 1 -> { deMoeda = "USD"; paraMoeda = "BRL"; }
                case 2 -> { deMoeda = "BRL"; paraMoeda = "USD"; }
                case 3 -> { deMoeda = "EUR"; paraMoeda = "BRL"; }
                case 4 -> { deMoeda = "BRL"; paraMoeda = "EUR"; }
                case 5 -> { deMoeda = "ARS"; paraMoeda = "USD"; }
                case 6 -> { deMoeda = "COP"; paraMoeda = "BRL"; }
                default -> {
                    System.out.println("Opcao invalida!");
                    continue;
                }
            }

            double taxa = buscarTaxaCambio(deMoeda, paraMoeda);
            if (taxa == 0) {
                System.out.println("Erro ao buscar a taxa de câmbio.");
                continue;
            }

            double convertido = valor * taxa;

            System.out.printf("Valor %.2f [%s] corresponde ao valor final de =>>> %.2f [%s]\n",
                    valor, deMoeda, convertido, paraMoeda);
        }

        // Mantém a janela do console aberta até o usuário apertar Enter
        System.out.println("\nPressione ENTER para sair...");
        new Scanner(System.in).nextLine();
    }

    private static double buscarTaxaCambio(String de, String para) {
        try {
            String urlString = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + de;

            // Criando cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Criando requisição
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            // Enviando requisição e recebendo resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Erro na requisição: " + response.statusCode());
                return 0;
            }

            // Parseando JSON da resposta
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject rates = json.getAsJsonObject("conversion_rates");

            return rates.get(para).getAsDouble();

        } catch (Exception e) {
            System.err.println("Erro ao buscar taxa de câmbio: " + e.getMessage());
            return 0;
        }
    }

}