package com.slemma.test.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by turov on 10.05.2017.
 */
public class HttpServiceImpl implements HttpService {

    @Override
    public String sendGet(String url) {
        URL obj = null;
        try {
            obj = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection con = null;

        try {
            assert obj != null;
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

        } catch (IOException e) {
            e.printStackTrace();
        }

        assert con != null;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {


            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            return response.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
