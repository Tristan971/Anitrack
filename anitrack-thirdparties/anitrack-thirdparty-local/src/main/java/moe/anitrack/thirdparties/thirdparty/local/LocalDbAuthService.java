package moe.anitrack.thirdparties.thirdparty.local;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyAuthenticationService;
import moe.anitrack.thirdparties.common.model.authentication.post.AuthenticationResult;
import moe.anitrack.thirdparties.common.model.authentication.post.ImmutableAuthenticationResult;
import moe.anitrack.thirdparties.common.model.authentication.pre.AuthenticationField;

@Component
public class LocalDbAuthService implements ThirdpartyAuthenticationService {

    @Override
    public List<AuthenticationField> getAuthenticationFields() {
        return Collections.emptyList();
    }

    @Override
    public AuthenticationResult authenticateWith(Map<String, String> authenticationValues) {
        return ImmutableAuthenticationResult
                .builder()
                .isSuccessful(true)
                .build();
    }

}
