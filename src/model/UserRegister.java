package model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class UserRegister {

    private final String url = "http://localhost:8080/create";

    public boolean createUser(String name, String surname, String email, String pass, String formation) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String json = String.format(
                    "{\"firstName\":\"%s\", \"surName\":\"%s\", \"email\":\"%s\", \"password\":\"%s\", \"study\":\"%s\"}",
                    name, surname, email, pass, formation
            );
            System.out.println(json);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            System.out.println("Usuario registrado con éxito");
            System.out.println("Código de respuesta: " + response.statusCode());
            System.out.println("Cuerpo de respuesta: " + response.body());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
