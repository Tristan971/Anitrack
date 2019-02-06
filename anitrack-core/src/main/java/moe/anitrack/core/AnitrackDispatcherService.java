package moe.anitrack.core;

import java.util.List;

import org.springframework.stereotype.Component;

import moe.anitrack.thirdparties.common.ThirdpartyService;

@Component
public class AnitrackDispatcherService {

    private final List<ThirdpartyService> thirdpartyServices;

    public AnitrackDispatcherService(List<ThirdpartyService> thirdpartyServices) {
        this.thirdpartyServices = thirdpartyServices;
    }

}
