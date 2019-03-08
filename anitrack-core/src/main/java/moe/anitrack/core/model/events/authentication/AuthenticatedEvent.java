package moe.anitrack.core.model.events.authentication;

import moe.anitrack.core.model.events.AnitrackEvent;
import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.model.authentication.AuthenticationResult;

public class AuthenticatedEvent extends AnitrackEvent {

    private final ThirdpartyService service;
    private final AuthenticationResult authenticationResult;

    public AuthenticatedEvent(Object source, ThirdpartyService service, AuthenticationResult authenticationResult) {
        super(source);
        this.service = service;
        this.authenticationResult = authenticationResult;
    }

    public ThirdpartyService getService() {
        return service;
    }

    public AuthenticationResult getAuthenticationResult() {
        return authenticationResult;
    }

}
