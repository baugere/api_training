package fr.esiea.ex4A.controller;

import fr.esiea.ex4A.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class ApiController {

    private final ArrayList<User> userList = new ArrayList<>();

    @PostMapping("/api/inscription")
    public ResponseEntity<?> addUser(@RequestBody Map<String, String> request){
        User user = new User(request.get("userEmail"), request.get("userName"), request.get("userTweeter"), request.get("userCountry"), request.get("userSex"), request.get("userSexPref"));
        userList.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
