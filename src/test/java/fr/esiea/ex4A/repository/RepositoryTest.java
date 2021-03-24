package fr.esiea.ex4A.repository;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    @Test
    void repository_get_users(){
        Repository repository = new Repository();

        assertThat(repository.getUserList()).isInstanceOf(List.class);
    }

}
