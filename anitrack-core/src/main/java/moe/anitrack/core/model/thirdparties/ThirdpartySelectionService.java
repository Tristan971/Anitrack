package moe.anitrack.core.model.thirdparties;

import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import moe.anitrack.core.model.events.authentication.AuthenticatedEvent;
import moe.anitrack.persistence.AuthenticationCredentialsEntity;
import moe.anitrack.persistence.AuthenticationInfoRepository;
import moe.anitrack.thirdparties.common.ThirdpartyService;

@Component
public class ThirdpartySelectionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdpartySelectionService.class);

    private final AuthenticationInfoRepository authenticationInfoRepository;

    private final AtomicReference<ThirdpartyService> currentService = new AtomicReference<>();

    @Autowired
    public ThirdpartySelectionService(AuthenticationInfoRepository authenticationInfoRepository) {
        this.authenticationInfoRepository = authenticationInfoRepository;
    }

    public boolean isCurrentlySet() {
        return currentService.get() != null;
    }

    public ThirdpartyService getCurrentService() {
        return currentService.get();
    }

    @EventListener(AuthenticatedEvent.class)
    public void markAuthenticatedWith(AuthenticatedEvent authenticatedEvent) {
        AuthenticationCredentialsEntity authenticationCredentialsEntity = new AuthenticationCredentialsEntity();
        authenticationCredentialsEntity.setService(authenticatedEvent.getService().uniqueName());
        authenticationCredentialsEntity.setAuthenticationData(authenticatedEvent.getAuthenticationResult());
        currentService.set(authenticatedEvent.getService());
        authenticationInfoRepository.save(authenticationCredentialsEntity);
    }

}
