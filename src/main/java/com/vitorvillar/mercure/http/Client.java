package com.vitorvillar.mercure.http;


import com.vitorvillar.mercure.http.exceptions.ForbiddenException;
import com.vitorvillar.mercure.http.exceptions.UnauthorizedException;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Client {

    private HttpClient httpClient;
    private String url;
    private String authorizationToken;

    public Client(String url, String authorizationToken) {
        this.httpClient = HttpClients.createDefault();
        this.url = url;
        this.authorizationToken = authorizationToken;

    }

    public String sendRequest(Map<String, String> postData) throws UnauthorizedException, ForbiddenException {
        var responseContent = "";
        var request = new HttpPost(this.url);
        request.addHeader("Authorization", "Bearer " + this.authorizationToken);
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");

        List<NameValuePair> parameters = new ArrayList<>();

        for (var entry : postData.entrySet()) {
            parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        try {
            request.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));

            var response = this.httpClient.execute(request);
            if (response.getEntity() != null) {
                responseContent = EntityUtils.toString(response.getEntity());
            }
            switch (response.getStatusLine().getStatusCode()) {
                case HttpStatus.SC_UNAUTHORIZED:
                    throw new UnauthorizedException(responseContent);
                case HttpStatus.SC_FORBIDDEN:
                    throw new ForbiddenException(responseContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseContent;
    }
}
