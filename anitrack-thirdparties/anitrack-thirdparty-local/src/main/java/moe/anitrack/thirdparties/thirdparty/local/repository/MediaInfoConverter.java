package moe.anitrack.thirdparties.thirdparty.local.repository;

import java.io.IOException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import moe.anitrack.thirdparties.common.model.media.ImmutableMediaInfo;

@Converter(autoApply = true)
public class MediaInfoConverter implements AttributeConverter<ImmutableMediaInfo, String> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ImmutableMediaInfo attribute) {
        try {
            return OBJECT_MAPPER.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImmutableMediaInfo convertToEntityAttribute(String dbData) {
        try {
            return OBJECT_MAPPER.readValue(dbData, ImmutableMediaInfo.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
