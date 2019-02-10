package moe.anitrack.thirdparties.common;

import java.util.Map;

import moe.anitrack.thirdparties.common.model.authentication.post.AuthenticationResult;
import moe.anitrack.thirdparties.common.model.authentication.pre.RequiredAuthenticationInformation;

public interface ThirdpartyAuthenticationService {

    RequiredAuthenticationInformation getRequiredAuthenticationInformation();

    /**
     * @param authenticationValues The values from the fields given by {@link #getRequiredAuthenticationInformation()} with keys
     *                             as the name of the fields and values as the values entered by users
     *
     * @return Some user-readable version of their name (username, email, id... whatever is recognizable for the success notification)
     */
    AuthenticationResult authenticateWith(final Map<String, String> authenticationValues);

}
