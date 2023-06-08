package edu.hm.hafner.java2.schein;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests the class {@link SwitzerlandPostalCode}.
 *
 * @author Ullrich Hafner
 */
@DisplayName("Aufgabe 1b - SwitzerlandPostalCodeTest")
class SwitzerlandPostalCodeTest {
    @DisplayName("Aufgabe 1b - SwitzerlandPostalCodeTest - getLongForm")
    @Test
    void shouldHaveValidLongForm() {
        PostalCode zurich = (PostalCode) new SwitzerlandPostalCode(8_005);

        assertThat(zurich).hasToString("CH-8005");
        assertThat(zurich.getLongForm()).isEqualTo("CH-8005");
    }

    @DisplayName("Aufgabe 1b - SwitzerlandPostalCodeTest - getShortForm")
    @Test
    void shouldHaveValidShortForm() {
        PostalCode zurich = (PostalCode) new SwitzerlandPostalCode(8_005);

        assertThat(zurich.getShortForm()).isEqualTo("8005");
    }

    @DisplayName("Aufgabe 1b - SwitzerlandPostalCodeTest - Validierung")
    @Test
    void shouldThrowExceptionIfCityIsNotFound() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new SwitzerlandPostalCode(999)
        ).withMessageContaining("999");
        assertThatIllegalArgumentException().isThrownBy(
                () -> new SwitzerlandPostalCode(10_000)
        ).withMessageContaining("10000");
    }
}
