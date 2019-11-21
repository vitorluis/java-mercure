package com.vitorvillar.mercure;

import com.vitorvillar.mercure.exceptions.PublishRejectedException;
import com.vitorvillar.mercure.exceptions.UnauthorizedPublisherException;
import com.vitorvillar.mercure.http.Client;
import com.vitorvillar.mercure.http.exceptions.ForbiddenException;
import com.vitorvillar.mercure.http.exceptions.UnauthorizedException;

import java.util.HashMap;

public class Publisher {

    private Client httpClient;

    public Publisher(String mercureHub, String mercureToken) {
        this.httpClient = new Client(mercureHub, mercureToken);
    }

    public String publish(Message message) throws UnauthorizedPublisherException, PublishRejectedException {
        var parameters = new HashMap<String, String>();
        parameters.put("data", message.getData());
        parameters.put("topic", message.getTopic());

        try {
            return this.httpClient.sendRequest(parameters);
        } catch (UnauthorizedException e) {
            throw new UnauthorizedPublisherException(e.getMessage());
        } catch (ForbiddenException e) {
            throw new PublishRejectedException(e.getMessage());
        }
    }
}
