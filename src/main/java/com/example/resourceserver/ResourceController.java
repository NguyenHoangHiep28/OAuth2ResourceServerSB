package com.example.resourceserver;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/resource")
public class ResourceController {
    private final String PROFILE_SCOPE_KEY = "view_basic_info";
    private final String AVATAR_SCOPE_KEY = "view_photo";
    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserInfo(
            @PathVariable(name = "id") Integer userId,
            @RequestHeader(name = "Authorization") String authHeadertoken
    ) {
        DecodedJWT decodedJWT = verifyToken(authHeadertoken);
        if (decodedJWT == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean hasAuthorization = checkScope(decodedJWT, PROFILE_SCOPE_KEY);
        if (!hasAuthorization){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = null;
        for (User u : Resource.getUserList()) {
            if (u.getId().equals(userId)) {
                user = u;
                break;
            }
        }
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(path = "/avatar/{id}", method = RequestMethod.GET)
    public ResponseEntity<Avatar> getUserAvatar(
            @PathVariable(name = "id") Integer userId,
            @RequestHeader(name = "Authorization") String authHeadertoken) {
        DecodedJWT decodedJWT = verifyToken(authHeadertoken);
        if (decodedJWT == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean hasAuthorization = checkScope(decodedJWT, AVATAR_SCOPE_KEY);
        if (!hasAuthorization){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Avatar avatar = null;
        for (Avatar a : Resource.getAvatarList()) {
            if (a.getUserId().equals(userId)) {
                avatar = a;
                break;
            }
        }
        if (avatar != null) {
            return ResponseEntity.ok(avatar);
        }
        return ResponseEntity.notFound().build();
    }

    private DecodedJWT verifyToken(String authHeadertoken){
        String token = authHeadertoken.replace("Bearer", "").trim();
        try {
            DecodedJWT decodedJWT = JwtUtil.getDecodedJwt(token);
            String username = decodedJWT.getClaim("username").asString();
            for (User user:
                 Resource.getUserList()) {
                if (user.getUsername().equals(username)){
                    return decodedJWT;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    private boolean checkScope(DecodedJWT decodedJWT,String key){
        String[] scopes = decodedJWT.getClaim("scopes").asArray(String.class);
        return Arrays.asList(scopes).contains(key);
    }
}
