package fr.esiea.ex4A.controller;

import fr.esiea.ex4A.agify.AgifyService;
import fr.esiea.ex4A.entity.Match;
import fr.esiea.ex4A.entity.User;
import fr.esiea.ex4A.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {

    private final Repository repository;
    private final AgifyService agifyService;

    public ApiController(Repository repository, AgifyService agifyService) {
        this.repository = repository;
        this.agifyService = agifyService;
    }

    @PostMapping("/api/inscription")
    public ResponseEntity<?> addUser(@RequestBody Map<String, String> request) throws IOException {
        String userName = request.get("userName");
        String userCountry = request.get("userCountry");
        Integer userAge = agifyService.getAge(userName, userCountry);

        User user = new User(request.get("userEmail"), userName, request.get("userTweeter"), userCountry, request.get("userSex"), request.get("userSexPref"), userAge);
        repository.getUserList().add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/api/matches")
    public ResponseEntity<?> getMatches(@RequestParam("userName") String userName, @RequestParam("userCountry") String userCountry) {
        User requestUser = null;
        for (User user : repository.getUserList()) {if (user.getUserName().equalsIgnoreCase(userName) && user.getUserCountry().equalsIgnoreCase(userCountry)) { requestUser = user;break;}}
        if (requestUser == null) return new ResponseEntity<>(new ArrayList<User>(), HttpStatus.OK);
        List<Match> matches = new ArrayList<>();
        for (User user : repository.getUserList()) {
            Integer ageDiff = Math.abs(user.getUserAge() - requestUser.getUserAge());
            if (!requestUser.equals(user) && requestUser.getUserSexPref().equalsIgnoreCase(user.getUserSex()) && requestUser.getUserSex().equalsIgnoreCase(user.getUserSexPref()) && ageDiff <= 4){ matches.add(new Match(user.getUserName(), user.getUserTweeter()));}
        }return new ResponseEntity<>(matches, HttpStatus.OK);
    }
}
