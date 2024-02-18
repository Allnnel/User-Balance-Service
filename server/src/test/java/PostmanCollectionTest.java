import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class PostmanCollectionTest {
    @Test
    public void testPostmanCollection() throws IOException {
        String collectionUrl = "https://dev-booking.agilecyber.com/api/user/auth/mail-sign-in";

        HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(collectionUrl); // Используем метод POST

        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(200, statusCode);

        // Выводим тело ответа на экран
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder responseBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBody.append(line);
        }
        System.out.println("Response Body: " + responseBody.toString());
    }

}
