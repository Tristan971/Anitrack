package moe.anitrack.thirdparties.common;

import java.util.List;
import java.util.Map;

import moe.anitrack.thirdparties.common.model.authentication.post.AuthenticationResult;
import moe.anitrack.thirdparties.common.model.authentication.pre.AuthenticationField;

public interface ThirdpartyAuthenticationService {

    List<AuthenticationField> getAuthenticationFields();

    /**
     * @param authenticationValues The values from the fields given by {@link #getAuthenticationFields()} with keys
     *                             as the name of the fields and values as the values entered by users
     *
     * @return Some user-readable version of their name (username, email, id... whatever is recognizable for the success notification)
     */
    AuthenticationResult authenticateWith(final Map<AuthenticationField, String> authenticationValues);

}
