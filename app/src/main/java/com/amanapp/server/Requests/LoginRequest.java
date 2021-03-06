package com.amanapp.server.Requests;

import com.amanapp.application.core.exceptions.InvalidNumberOfQueriesException;
import com.amanapp.application.core.exceptions.RequiredQueryException;
import com.amanapp.server.RetrofitClient;

import java.util.Map;

/**
 * Created by Abdullah ALT on 11/15/2016.
 */
class LoginRequest extends ServerRequest {

    @Override
    protected void checkQuery(Map<String, String> query) {
        if (query.size() != 2) {
            throw new InvalidNumberOfQueriesException("sendRequest::LoginRequest accepts 2 parameters " +
                    " in the order [email, password]");
        } else if (!query.containsKey("email") || !query.containsKey("password")) {
            throw new RequiredQueryException();
        }
    }

    @Override
    void initRequest(Map<String, String> query) {
        AmanRequests requests = RetrofitClient.getClient().create(AmanRequests.class);
        call = requests.login(query.get("email"), query.get("password"));
    }
}
