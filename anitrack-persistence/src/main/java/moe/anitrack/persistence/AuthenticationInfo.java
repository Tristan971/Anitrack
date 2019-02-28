package moe.anitrack.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "authentication_info")
public class AuthenticationInfo {

    @Id
    @Column(name = "service_name")
    private String relatingService = "unset_service";

    @Column(length = 2048, name = "authentication_data")
    @Convert(converter = JacksonMapToDatabaseConverter.class)
    private Map<String, String> authenticationData = new HashMap<>();

    public AuthenticationInfo() {
    }

    public Map<String, String> getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(Map<String, String> authenticationData) {
        this.authenticationData = authenticationData;
    }

    public String getRelatingService() {
        return relatingService;
    }

    public void setRelatingService(String relatingService) {
        this.relatingService = relatingService;
    }

}
