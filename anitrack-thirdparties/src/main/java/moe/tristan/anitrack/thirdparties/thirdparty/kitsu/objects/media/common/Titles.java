package moe.tristan.anitrack.thirdparties.thirdparty.kitsu.objects.media.common;

import java.util.Optional;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Immutable
@JsonSerialize
public interface Titles {

    @JsonProperty("en")
    Optional<String> getEnglishEnglish();

    @JsonProperty("en_jp")
    String getEnglishJapanese();

    @JsonProperty("ja_jp")
    Optional<String> getJapaneseJapanese();

}
