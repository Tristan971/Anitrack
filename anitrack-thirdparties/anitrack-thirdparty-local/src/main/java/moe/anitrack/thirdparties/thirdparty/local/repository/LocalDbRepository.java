package moe.anitrack.thirdparties.thirdparty.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moe.anitrack.thirdparties.thirdparty.local.model.LocalSimpleMedia;

public interface LocalDbRepository extends JpaRepository<LocalSimpleMedia, String> {

}
