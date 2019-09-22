package moe.anitrack.thirdparties.thirdparty.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moe.anitrack.thirdparties.thirdparty.local.model.LocalMediaEntity;

public interface LocalDatabaseRepository extends JpaRepository<LocalMediaEntity, String> {

}
