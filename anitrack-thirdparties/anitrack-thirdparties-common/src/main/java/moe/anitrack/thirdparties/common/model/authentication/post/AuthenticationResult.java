package moe.anitrack.thirdparties.common.model.authentication.post;

import java.util.Optional;

import org.immutables.value.Value.Immutable;

@Immutable
public interface AuthenticationResult {

    boolean isSuccessful();

    Optional<String> getLoggedInAs();

    Optional<String> getError();

}
