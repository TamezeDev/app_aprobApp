package model;

import java.net.http.HttpResponse;

public class TestData {
    BackendConnect backendConnect = new BackendConnect();
    private final HttpService httpService = new HttpService();

    public Double getAverageTest(String email) {
        try {
            String url = backendConnect.getUrlBase() + "testAverage?email=" + email;
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
    public Double getPassedTest(String email) {
        try {
            String url = backendConnect.getUrlBase() + "testPassed?email=" + email;
            HttpResponse<String> response = httpService.get(url);

            System.out.println(response);
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
