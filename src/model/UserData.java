package model;

import java.net.http.HttpResponse;

public class UserData {
    BackendConnect backendConnect = new BackendConnect();
    private final HttpService httpService = new HttpService();

    public String getUserData(String email) {
        try {
            String url = backendConnect.getUrlBase() + "user?email=" + email; // email como query param
            HttpResponse<String> response = httpService.get(url);

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
