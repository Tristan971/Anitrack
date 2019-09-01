package moe.anitrack.thirdparties.thirdparty.local;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyAuthenticationService;
import moe.anitrack.thirdparties.common.model.authentication.AuthenticationField;

@Component
public class LocalDbAuthService implements ThirdpartyAuthenticationService<Void> {

    @Override
    public String getName() {
        return "local";
    }

    @Override
    public List<AuthenticationField> getAuthenticationFields() {
        return Collections.emptyList();
    }

    @Override
    public CompletableFuture<Void> tryAuthenticate(Map<AuthenticationField, String> submittedValues) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void onFoundSavedCredentials(Void savedCredentials) {
    }

}
