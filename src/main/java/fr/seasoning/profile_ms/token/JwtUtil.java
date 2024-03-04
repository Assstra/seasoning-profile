package fr.seasoning.profile_ms.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

import static io.jsonwebtoken.security.Keys.*;

public class JwtUtil {

    // Parse and extract information from a JWT token
    public static Claims parseJwtToken(String jwtToken) {
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhgfeKGabwL5+V3rQ1PSEfp//nwY8w5UMknXJIMSrozXtdamSON3Q+g5kvEaN4dCqpYdTy8f1zVbiTDytXwHC4N5Sie6EA5oOfrmPiXuiqMqhTyRvVzEze/+PCiiaDF32COv5XUN/QXiMdnu986b65twAONnbIHmscJS7cGfptguquJNn6xjG3EtxD2k/4rL9eYglmajHSbBXPmp6JSE3tD0xS+hrpjOS8UstbOHmSTSlCFYwUPKMpgcz77FbuJmX7Zz2SMXkvAbIAntDlpSM1N3b4FcUZ/qR6lFA2RLxooRmCrg95Av1/DgJ1mW9LWUTPsn3CBdaxEKSA7X2unj1mQIDAQAB";
        try {
            String cleanedToken = extractTokenFromAuthorizationHeader(jwtToken);
//            // Decode the cleaned token from Base64 URL encoding
//            byte[] decodedBytes = Base64.getUrlDecoder().decode(tok);
//            String decodedToken = new String(decodedBytes);

            // Parse the RSA public key from the provided string
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey rsaPublicKey = keyFactory.generatePublic(keySpec);

            // Parse the JWT token with the provided RSA public key
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(rsaPublicKey)
                    .build()
                    .parseClaimsJws(cleanedToken)
                    .getBody();

            return claims;
        } catch (Exception e) {
            // Handle other parsing errors
            e.printStackTrace();
            return null;
        }
    }

    public static String extractTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return null; // Invalid or missing Authorization header
        }

        // Split the Authorization header into parts and retrieve the token
        String[] parts = authorizationHeader.split("\\s+");
        if (parts.length != 2) {
            return null; // Invalid Authorization header format
        }

        return parts[1]; // Return the token part
    }
}
