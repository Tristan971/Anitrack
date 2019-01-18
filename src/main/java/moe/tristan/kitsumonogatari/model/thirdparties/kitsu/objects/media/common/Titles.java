package moe.tristan.kitsumonogatari.model.thirdparties.kitsu.objects.media.common;

import java.util.Optional;

import org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.annotation.JsonProperty;

import moe.tristan.kitsumonogatari.util.ApiStyle;

@Immutable
@ApiStyle
public interface Titles {

    @JsonProperty("en")
    Optional<String> getEnglishEnglish();

    @JsonProperty("en_jp")
    String getEnglishJapanese();

    @JsonProperty("ja_jp")
    Optional<String> getJapaneseJapanese();

}
