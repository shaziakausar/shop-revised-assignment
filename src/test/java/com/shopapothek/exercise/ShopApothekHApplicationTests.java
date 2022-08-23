package com.shopapothek.exercise;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShopApothekHApplicationTests {

    @LocalServerPort
    private int port;

    @Test
    void contextLoads() {
    }

    @Test
    void whenGetRepositoriesByCorrectDateFormat_thenSuccess() throws IOException {
        //arrange
        String date = "2022-01-13";
        HttpUriRequest request = new HttpGet( "http://localhost:" + port + "/repository/date/" + date );

        //act
        HttpResponse response = HttpClientBuilder.create().build().execute( request );
        String stringRes = EntityUtils.toString(response.getEntity());

        //assert
        assertThat(stringRes != null).isTrue();
    }

    @Test
    void whenGetRepositoryByInvalidCount_thenBadRequest() throws IOException, JSONException {
        //arrange
        String count = "I";
        HttpUriRequest request = new HttpGet( "http://localhost:" + port + "/repository/" + count );

        //act
        HttpResponse response = HttpClientBuilder.create().build().execute( request );
        String stringRes = EntityUtils.toString(response.getEntity());

        //assert
        JSONObject jsonObject = new JSONObject(stringRes);
        String status = jsonObject.getString("status");
        assertThat(status.equals("400")).isTrue();
    }

}
