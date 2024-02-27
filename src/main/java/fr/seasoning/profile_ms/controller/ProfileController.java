package fr.seasoning.profile_ms.controller;

import fr.seasoning.profile_ms.entities.Profile;
import fr.seasoning.profile_ms.repository.ProfileRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    private final ProfileRepository profileRepository;

    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping("/profile")
    public Iterable<Profile> findAllEmployees() {
        return this.profileRepository.findAll();
    }

    @PostMapping("/profile")
    public Profile addOneEmployee(@RequestBody Profile profile) {
            return this.profileRepository.save(profile);
    }
}
