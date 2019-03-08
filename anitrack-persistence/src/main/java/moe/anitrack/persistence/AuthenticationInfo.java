package moe.anitrack.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import moe.anitrack.thirdparties.common.model.authentication.AuthenticationField;

@Entity(name = "authentication_info")
public class AuthenticationInfo {

    @Id
    @Column(name = "service_name")
    private String relatingService = "unset_service";

    @Column(length = 2048, name = "authentication_data")
    @Convert(converter = JacksonMapToDatabaseConverter.class)
    private Map<AuthenticationField, String> authenticationData = new HashMap<>();

    public AuthenticationInfo() {
    }

    public Map<AuthenticationField, String> getAuthenticationData() {
        return authenticationData;
    }

    public String getRelatingService() {
        return relatingService;
    }

    public void setRelatingService(String relatingService) {
        this.relatingService = relatingService;
    }

    public void setAuthenticationData(Map<AuthenticationField, String> authenticationData) {
        this.authenticationData = authenticationData;
    }

}
