package edu.hm.hafner.java2.schein;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.core.importer.Location;
import com.tngtech.archunit.lang.ArchRule;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Verifies that inheritance is used correctly.
 *
 * @author Ullrich Hafner
 */
@DisplayName("Aufgabe 1c - Vererbungstests")
class PostalCodeTest {
    private static final JavaClasses AUFGABE3_CLASSES
            = new ClassFileImporter(List.of(new DoNotIncludeTests(), new DoNotIncludeEnum())).importPackages(PostalCode.class.getPackageName());
    private static final Class<?>[] POSTAL_CODES = {GermanyPostalCode.class, SwitzerlandPostalCode.class};

    @Test
    @DisplayName("Aufgabe 1c - Postal Codes sollten nicht direkt das Interface implementieren")
    void shouldNotDirectlyImplementPostalCode() {
        for (Class<?> postalCodeClass : POSTAL_CODES) {
            JavaClass postalCodeInterface = AUFGABE3_CLASSES.get(PostalCode.class);
            JavaClass postalCode = AUFGABE3_CLASSES.get(postalCodeClass);

            assertThat(postalCode.getInterfaces()).as("%s sollten PostalCode nicht direkt implementieren", postalCode).doesNotContain(postalCodeInterface);
            assertThat(postalCode.getAllClassesSelfIsAssignableTo()).contains(postalCodeInterface);
        }
    }

    @DisplayName("Aufgabe 1c - Sollte eine Basisklasse ungleich Object haben")
    @ValueSource(classes = {GermanyPostalCode.class, SwitzerlandPostalCode.class})
    @ParameterizedTest(name = "[{index}] Postleitzahl = {0}")
    void shouldHaveParentClassDifferentFromObject(final Class<?> postalCodeClass) {
        JavaClass postalCode = AUFGABE3_CLASSES.get(postalCodeClass);

        assertThat(postalCode.getSuperclass().isPresent()).isTrue();
        assertThat(postalCode.getSuperclass().get().getName()).as("%s hat keine neue Basisklasse", postalCode).isNotEqualTo(Object.class.getName());
    }

    @DisplayName("Aufgabe 1c - Methoden sollten in der Basisklasse implementiert sein")
    @ValueSource(classes = {GermanyPostalCode.class, SwitzerlandPostalCode.class})
    @ParameterizedTest(name = "[{index}] Postleitzahl = {0}")
    void shouldHaveMethodsInBaseClass(final Class<?> postalCodeClass) {
        JavaClass postalCode = AUFGABE3_CLASSES.get(postalCodeClass);

        assertThat(postalCode.getMethods().stream().map(JavaMethod::getName))
                .doesNotContain("getCode", "getLongForm", "equals");
        assertThat(postalCode.getSuperclass().get().toErasure().getMethods().stream().map(JavaMethod::getName))
                .contains("getCode", "getLongForm", "equals");
    }

    @DisplayName("Aufgabe 1c - Sollte nur private Fields haben")
    @ValueSource(classes = {GermanyPostalCode.class, SwitzerlandPostalCode.class})
    @ParameterizedTest(name = "[{index}] Postleitzahl = {0}")
    void shouldHavePrivateFields(final Class<?> postalCodeClass) {
        ArchRule privateFieldsRule = fields()
                .should().bePrivate()
                .andShould().beFinal().allowEmptyShould(true);

        privateFieldsRule.check(AUFGABE3_CLASSES);
    }

    @DisplayName("Aufgabe 1c - Equals")
    @Test
    void shouldVerifyEquals() {
        EqualsVerifier.simple().suppress(Warning.NULL_FIELDS).forClasses(Arrays.asList(POSTAL_CODES)).verify();
    }

    static final class DoNotIncludeEnum implements ImportOption {
        @Override
        public boolean includes(final Location location) {
            return !location.contains(Country.class.getSimpleName());
        }
    }

}
