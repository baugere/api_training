package fr.esiea.ex4A.repository;

import fr.esiea.ex4A.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Repository {

    private final List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;

    }
}
