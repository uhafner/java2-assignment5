package edu.hm.hafner.java2.schein;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests the class {@link PostalCodeFactory}.
 */
@DisplayName("Aufgabe 1d - PostalCodeFactory")
class PostalCodeFactoryTest {
    @DisplayName("Aufgabe 1d - PostalCodeFactory - Germany")
    @Test
    void shouldConvertPostalCodesGermany() {
        GermanyPostalCode grafing = new GermanyPostalCode(85_567);
        assertThat(PostalCodeFactory.createPostalCodeFrom("DE-85567")).isEqualTo(grafing);
        assertThat(PostalCodeFactory.createPostalCodeFrom("DE-08297")).isEqualTo(new GermanyPostalCode(8_297));

        assertThat(PostalCodeFactory.createPostalCodeFrom("CH-2066")).isNotEqualTo(new GermanyPostalCode(2_066));
    }

    @DisplayName("Aufgabe 1d - PostalCodeFactory - Switzerland")
    @Test
    void shouldConvertPostalCodesSwitzerland() {
        assertThat(PostalCodeFactory.createPostalCodeFrom("CH-8297")).isEqualTo(new SwitzerlandPostalCode(8_297));

        assertThat(PostalCodeFactory.createPostalCodeFrom("CH-8297")).isNotEqualTo(new GermanyPostalCode(8_297));
    }

    @DisplayName("Aufgabe 1d - PostalCodeFactory - Exception: Invalid Short Form")
    @Test
    void shouldThrowExceptionIfShortFormIsNotSupportedOrMissing() {
        assertThatIllegalArgumentException().isThrownBy(() -> PostalCodeFactory.createPostalCodeFrom("AU-8297"))
                .withMessageContaining("AU-8297");
        assertThatIllegalArgumentException().isThrownBy(() -> PostalCodeFactory.createPostalCodeFrom("8297"))
                .withMessageContaining("8297");
    }

    @DisplayName("Aufgabe 1d - PostalCodeFactory - Exception: Invalid Format")
    @Test
    void shouldThrowExceptionIfFormatIsInvalid() {
        assertThatIllegalArgumentException().isThrownBy(() -> PostalCodeFactory.createPostalCodeFrom("DE-DE-85567"))
                .withMessageContaining("DE-DE-85567");
        assertThatIllegalArgumentException().isThrownBy(() -> PostalCodeFactory.createPostalCodeFrom("85567-DE"))
                .withMessageContaining("85567-DE");
    }

    @DisplayName("Aufgabe 1d - PostalCodeFactory - Exception: Invalid Number")
    @Test
    void shouldThrowExceptionIfShortFormIsNotSupported() {
        assertThatIllegalArgumentException().isThrownBy(() -> PostalCodeFactory.createPostalCodeFrom("DE-00011"))
                .withMessageContaining("11");
        assertThatIllegalArgumentException().isThrownBy(() -> PostalCodeFactory.createPostalCodeFrom("DE-85.567"))
                .withMessageContaining("DE-85.567");
    }
}
