package model.services;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GoogleMapsService {
    private static final String API_KEY = "AIzaSyCjXyfN5ZmbKBc3fBthBe0Btbe1aU2dU2E";
    private static final String ORIGEM = "13343-060, Indaiatuba-SP, Brazil"; // Ajuste para seu CD

    public static String calcularDistancia(String destino) {
        try {
            // Codifica os endereços para formato de URL
            String origemEncoded = URLEncoder.encode(ORIGEM, StandardCharsets.UTF_8);
            String destinoEncoded = URLEncoder.encode(destino, StandardCharsets.UTF_8);

            String urlString = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
                    + origemEncoded + "&destinations=" + destinoEncoded + "&mode=driving&units=metric&key=" + API_KEY;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();

            // Processando a resposta JSON
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            if (jsonObject.has("rows") && jsonObject.getAsJsonArray("rows").size() > 0) {
                JsonObject elemento = jsonObject.getAsJsonArray("rows").get(0)
                        .getAsJsonObject().getAsJsonArray("elements").get(0)
                        .getAsJsonObject();

                if (elemento.has("distance") && elemento.has("duration")) {
                    String distancia = elemento.getAsJsonObject("distance").get("text").getAsString();
                    String duracao = elemento.getAsJsonObject("duration").get("text").getAsString();
                    return "Distância: " + distancia + ", Tempo estimado: " + duracao;
                }
            }
            return "Não foi possível calcular a distância.";
        } catch (Exception e) {
            return "Erro ao calcular distância: " + e.getMessage();
        }
    }
}
