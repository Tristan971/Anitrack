package moe.anitrack.core.persistence.configuration;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationInfoRepository extends JpaRepository<AuthenticationInfo, String> {

}
