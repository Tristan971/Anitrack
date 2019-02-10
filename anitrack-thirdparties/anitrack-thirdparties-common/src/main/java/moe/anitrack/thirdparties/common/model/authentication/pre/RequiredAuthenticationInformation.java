package moe.anitrack.thirdparties.common.model.authentication.pre;

import java.util.List;

import org.immutables.value.Value.Immutable;

@Immutable
public interface RequiredAuthenticationInformation {

    /**
     * @return A list of the authentication fields requested by your 3rd-party API to manage authentication.
     */
    List<AuthenticationField> getRequiredFieldValues();

}
