/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package slack;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 *
 * @author chris
 */
public class OpenRequest {
    private static HttpClient client = HttpClient.newHttpClient();
    // é aqui que você coloca a URL do webhook
    private static final String URL = "https://hooks.slack.com/services/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";

    public static void sendMessage(JSONObject content) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(URL))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(content.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.printf("Status: %s", response.statusCode());
        System.out.printf("Response: %s", response.body());
    }

    public void enviarMensagem(String mensagem) throws IOException, InterruptedException {
        JSONObject json = new JSONObject();
        json.put("text", mensagem);
        OpenRequest.sendMessage(json);
    }
}
