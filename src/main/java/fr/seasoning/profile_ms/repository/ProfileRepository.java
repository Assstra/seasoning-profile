package fr.seasoning.profile_ms.repository;

import fr.seasoning.profile_ms.entities.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProfileRepository extends CrudRepository<Profile, UUID> {}
