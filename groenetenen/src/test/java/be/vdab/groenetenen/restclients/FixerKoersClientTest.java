package be.vdab.groenetenen.restclients;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FixerKoersClientTest {
    private final FixerKoersClient client;

    FixerKoersClientTest(FixerKoersClient client) {
        this.client = client;
    }

    @Test
    void deKoersMoetPositiefZijn() {
        assertThat(client.getDollarKoers()).isGreaterThan(BigDecimal.ZERO);
    }
}