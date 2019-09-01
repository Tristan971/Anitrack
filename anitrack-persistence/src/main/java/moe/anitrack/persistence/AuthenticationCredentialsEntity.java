package moe.anitrack.persistence;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import moe.anitrack.thirdparties.common.model.authentication.AuthenticationResult;


@Entity(name = "authentication_info")
public class AuthenticationCredentialsEntity {

    @Id
    @Column(name = "service_name")
    private String service;

    @Column(name = "authentication_data", length = 8096)
    @Convert(converter = AuthenticationResultConverter.class)
    private AuthenticationResult authenticationData;

    public AuthenticationCredentialsEntity() {
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public AuthenticationResult getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(AuthenticationResult authenticationData) {
        this.authenticationData = authenticationData;
    }

}
