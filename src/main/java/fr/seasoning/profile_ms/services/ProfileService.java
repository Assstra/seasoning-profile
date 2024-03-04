package fr.seasoning.profile_ms.services;

import fr.seasoning.profile_ms.entities.Profile;
import fr.seasoning.profile_ms.repository.ProfileRepository;

import java.util.Optional;

public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Optional<Profile> getProfileBySsoid(String ssoid) {
        // Query the profile by ssoid
        return this.profileRepository.findBySsoid(ssoid);
    }

    public Profile deleteProfileBySsoid(String ssoid) {
        return this.profileRepository.deleteBySsoid(ssoid);
    }
}
