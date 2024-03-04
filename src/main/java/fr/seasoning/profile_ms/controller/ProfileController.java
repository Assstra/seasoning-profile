package fr.seasoning.profile_ms.controller;

import fr.seasoning.profile_ms.entities.Profile;
import fr.seasoning.profile_ms.repository.ProfileRepository;
import fr.seasoning.profile_ms.token.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static fr.seasoning.profile_ms.token.JwtUtil.*;

@RestController
public class ProfileController {

    private final ProfileRepository profileRepository;

    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping("/profile/me")
    public Optional<Profile> findOneEmployee(@RequestHeader("Authorization") String authorizationHeader) {
        Claims claims = parseJwtToken(authorizationHeader);
        return this.profileRepository.findBySsoid(claims.getSubject());
    }

    @PostMapping("/profile")
    public Profile addOneEmployee(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Profile profile) {
        Claims claims = parseJwtToken(authorizationHeader);
        String userId = claims.getSubject();
        Random r = new Random();
        // Associate user SSOID with the profile entity
        profile.setSsoid(userId);
        profile.setName((String) claims.get("given_name"));
        profile.setSurname((String) claims.get("family_name"));
        profile.setEmail((String) claims.get("email"));
        profile.setId(r.nextInt());
        return this.profileRepository.save(profile);
    }
    @PatchMapping("/profile")
    public Profile updateProfile(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Profile updatedProfile) {
        Claims claims = parseJwtToken(authorizationHeader);
        String userId = claims.getSubject();
        
        Optional<Profile> optionalProfile = this.profileRepository.findBySsoid(userId);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            // Update the profile fields
            profile.setGenre(updatedProfile.getGenre());
            profile.setAddress(updatedProfile.getAddress());
            profile.setBirthdate(updatedProfile.getBirthdate());
            profile.setBiography(updatedProfile.getBiography());
            profile.setPhone(updatedProfile.getPhone());
            profile.setExperiences(updatedProfile.getExperiences());
            profile.setReferences(updatedProfile.getReferences());

            return this.profileRepository.save(profile);
        } else {
            // Handle profile not found error
            throw new RuntimeException("Profile not found");
        }
    }
    @DeleteMapping("/profile")
    public Profile deleteProfile(@RequestHeader("Authorization") String authorizationHeader) {
        Claims claims = parseJwtToken(authorizationHeader);
        String userId = claims.getSubject();

        Optional<Profile> optionalProfile = this.profileRepository.findBySsoid(userId);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            // Update the profile fields
            return this.profileRepository.deleteBySsoid(profile.getSsoid());
        } else {
            // Handle profile not found error
            throw new RuntimeException("Profile not found");
        }
    }
}
