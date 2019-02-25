package moe.anitrack.core.thirdparties;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyService;

@Component
public class CurrentThirdpartySystem {

    private final AtomicReference<ThirdpartyService> currentService = new AtomicReference<>();

    public boolean isCurrentlySet() {
        return currentService.get() != null;
    }

    public void setCurrentService(final ThirdpartyService service) {
        this.currentService.set(service);
    }

}