package model;

import java.net.http.HttpResponse;

public class UserLogin {
    BackendConnect backendConnect = new BackendConnect();

    private final String url = backendConnect.getUrlBase() + "login";
    private final HttpService httpService = new HttpService();


    public int accessUser(String email, String pass) {
        try {
            String json = String.format(
                    "{\"email\":\"%s\", \"password\":\"%s\"}",
                    email, pass
            );

            HttpResponse<String> response = httpService.postJson(url, json);

            return response.statusCode();
        } catch (Exception e) {
            e.printStackTrace();
            return 500;
        }
    }
}
