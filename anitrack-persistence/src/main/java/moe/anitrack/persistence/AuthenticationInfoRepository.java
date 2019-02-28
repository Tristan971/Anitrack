package moe.anitrack.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationInfoRepository extends JpaRepository<AuthenticationInfo, String> {

}
