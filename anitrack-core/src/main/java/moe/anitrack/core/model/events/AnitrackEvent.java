package moe.anitrack.core.model.events;

import org.springframework.context.ApplicationEvent;

public abstract class AnitrackEvent extends ApplicationEvent {

    public AnitrackEvent(Object source) {
        super(source);
    }

}
