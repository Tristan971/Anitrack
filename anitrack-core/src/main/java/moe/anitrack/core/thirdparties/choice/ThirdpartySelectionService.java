package moe.anitrack.core.thirdparties.choice;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyService;
import moe.anitrack.thirdparties.common.model.authentication.pre.AuthenticationField;

@Component
public class ThirdpartySelectionService {

    private final AtomicReference<ThirdpartyChoice> serviceChoice = new AtomicReference<>();

    public void tryAuthenticateWith(final ThirdpartyService service, final Map<AuthenticationField, String>)

    public boolean isCurrentlySet() {
        return serviceChoice.get() != null;
    }

    public void setCurrentServiceChoice(final ThirdpartyChoice serviceChoice) {
        this.serviceChoice.set(serviceChoice);
    }

}
