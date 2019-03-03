package moe.anitrack.core.thirdparties.choice;

import org.immutables.value.Value.Immutable;

import moe.anitrack.base.util.ValueObjectStyle;
import moe.anitrack.persistence.AuthenticationInfo;
import moe.anitrack.thirdparties.common.ThirdpartyService;

@Immutable
@ValueObjectStyle
public abstract class AbstractThirdpartyChoice {

    public abstract ThirdpartyService getService();

    public abstract AuthenticationInfo getAuthentication();

}
