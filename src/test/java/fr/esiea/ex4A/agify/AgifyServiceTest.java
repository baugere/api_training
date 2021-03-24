package fr.esiea.ex4A.agify;

import fr.esiea.ex4A.agify.mock.AgifyServiceMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class AgifyServiceTest {
    @Mock
    private final AgifyService agifyService;

    AgifyServiceTest() {
        this.agifyService = new AgifyService(AgifyServiceMock.getAgifyClientMock());
    }

    @Test
    void getAgeOfJeanFr() throws IOException {
        assertThat(agifyService.getAge("jean", "FR")).isEqualTo(68);
    }
}
