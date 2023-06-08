package edu.hm.hafner.java2.schein;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests the class {@link GermanyPostalCode}.
 *
 * @author Ullrich Hafner
 */
@DisplayName("Aufgabe 1a - GermanyPostalCodeTest")
class GermanyPostalCodeTest {
    private static final int GRAFING = 85_567;

    @DisplayName("Aufgabe 1a - GermanyPostalCodeTest - getLongForm")
    @Test
    void shouldHaveValidLongForm() {
        PostalCode grafing = (PostalCode) new GermanyPostalCode(GRAFING);

        assertThat(grafing).hasToString("DE-85567");
        assertThat(grafing.getLongForm()).isEqualTo("DE-85567");
    }

    @DisplayName("Aufgabe 1a - GermanyPostalCodeTest - getShortForm")
    @Test
    void shouldHaveValidShortForm() {
        PostalCode grafing = (PostalCode) new GermanyPostalCode(GRAFING);

        assertThat(grafing.getShortForm()).isEqualTo("85567");
    }

    @DisplayName("Aufgabe 1a - GermanyPostalCodeTest - Führende Null")
    @Test
    void shouldHaveValidLongFormLeadingZero() {
        PostalCode dresden = (PostalCode) new GermanyPostalCode(1_067);

        assertThat(dresden.getShortForm()).isEqualTo("01067");
        assertThat(dresden.getLongForm()).isEqualTo("DE-01067");
    }

    @DisplayName("Aufgabe 1a - GermanyPostalCodeTest - Validierung")
    @Test
    void shouldThrowExceptionIfCityIsNotFound() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new GermanyPostalCode(999)
        ).withMessageContaining("999");
        assertThatIllegalArgumentException().isThrownBy(
                () -> new GermanyPostalCode(100_000)
        ).withMessageContaining("100000");
    }
}
