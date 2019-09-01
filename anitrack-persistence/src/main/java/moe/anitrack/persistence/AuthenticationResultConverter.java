package moe.anitrack.persistence;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import moe.anitrack.thirdparties.common.model.authentication.AuthenticationResult;

public class AuthenticationResultConverter implements AttributeConverter<AuthenticationResult, String> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(AuthenticationResult attribute) {
        try {
            return OBJECT_MAPPER.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthenticationResult convertToEntityAttribute(String dbData) {
        try {
            return OBJECT_MAPPER.readValue(dbData, AuthenticationResult.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
