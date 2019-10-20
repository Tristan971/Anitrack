package moe.anitrack.thirdparty.common.model;

import moe.tristan.anitrack.commons.Media;

public interface ThirdpartyClient {

	void markWatched(Media media);

	void authenticate();

}
