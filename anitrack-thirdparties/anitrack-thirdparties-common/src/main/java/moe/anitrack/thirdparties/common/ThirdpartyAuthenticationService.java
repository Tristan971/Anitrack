package moe.anitrack.thirdparties.common;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import moe.anitrack.thirdparties.common.model.authentication.AuthenticationField;


public interface ThirdpartyAuthenticationService<T> {

    String getName();

    List<AuthenticationField> getAuthenticationFields();

    CompletableFuture<T> tryAuthenticate(final Map<AuthenticationField, String> submittedValues);

    void authenticateWithSavedCredentials(T savedCredentials);

}
