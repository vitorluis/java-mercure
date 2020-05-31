package com.vitorvillar.mercure;

import com.vitorvillar.mercure.exceptions.HubNotFoundException;
import com.vitorvillar.mercure.exceptions.PublishRejectedException;
import com.vitorvillar.mercure.exceptions.UnauthorizedPublisherException;
import com.vitorvillar.mercure.http.Client;
import com.vitorvillar.mercure.http.exceptions.ForbiddenException;
import com.vitorvillar.mercure.http.exceptions.NotFoundException;
import com.vitorvillar.mercure.http.exceptions.UnauthorizedException;

import java.util.HashMap;
import java.util.UUID;

public class Publisher {

    private Client httpClient;

    public Publisher(String mercureHub, String mercureToken) {
        this.httpClient = new Client(mercureHub, mercureToken);
    }

    public Message publish(Message message) throws UnauthorizedPublisherException, PublishRejectedException,
            HubNotFoundException {
        var parameters = new HashMap<String, String>();
        parameters.put("data", message.getData());
        parameters.put("topic", message.getTopic());
        parameters.put("id", message.getId().toString());

        if (message.isPrivate()) {
            parameters.put("private", "on");
        }

        if (message.getType() != null && message.getType().isEmpty() == false) {
            parameters.put("type", message.getType());
        }

        try {
            var messageId = this.httpClient.sendRequest(parameters);
            message.setId(UUID.fromString(messageId));

            return message;
        } catch (UnauthorizedException e) {
            throw new UnauthorizedPublisherException(e.getMessage());
        } catch (ForbiddenException e) {
            throw new PublishRejectedException(e.getMessage());
        } catch (NotFoundException e) {
            throw new HubNotFoundException();
        }
    }
}
