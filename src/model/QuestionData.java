package model;

import java.net.http.HttpResponse;

public class QuestionData {
    BackendConnect backendConnect = new BackendConnect();
    private final HttpService httpService = new HttpService();

    public Double getNumFailedQuestions(String email) {
        try {
            String url = backendConnect.getUrlBase() + "errorQuestion?email=" + email;
            HttpResponse<String> response = httpService.get(url);

            if (response.statusCode() == 200) {
                String body = response.body();
                if (body == null || body.isEmpty()) return null;
                return Double.parseDouble(body);
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}