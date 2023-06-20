package edu.hm.hafner.java2.schein;

import edu.hm.hafner.util.Generated;

/**
 * Base class for postal codes that have integer values within a specific range.
 *
 * @author Ullrich Hafner
 */
public abstract class AbstractPostalCode implements PostalCode {
    private final Country country;
    private final int code;

    /**
     * Creates a new instance of {@link AbstractPostalCode}.
     *
     * @param country
     *         the country of the postal code
     * @param code
     *         the actual postal code value
     * @param min
     *         minimum value for postal codes
     * @param max
     *         maximum value for postal codes
     */
    protected AbstractPostalCode(final Country country, final int code, final int min, final int max) {
        this.country = country;
        this.code = code;

        if (code < min || code > max) {
            throw new IllegalArgumentException(
                    String.format("Postleitzahl %d ist nicht gültig, muss im Bereich [%d, %d] sein!", code, min, max));
        }
    }

    @Override
    public final Country getCountry() {
        return country;
    }

    @Override
    public final String getCountryIsoCode() {
        return country.getIsoCode();
    }

    @Override
    public final int getCode() {
        return code;
    }

    /**
     * Returns the postal code (long form). The long form displays both the value of the {@code code} and
     * the country code. E.g., "DE-85567", "CH-8006".
     *
     * @return the postal code (long form)
     */
    @Override
    public String getLongForm() {
        return String.format("%s-%s", getCountryIsoCode(), getShortForm());
    }

    @Override @Generated
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractPostalCode that = (AbstractPostalCode) o;

        if (code != that.code) {
            return false;
        }
        return country.equals(that.country);
    }

    @Override @Generated
    public int hashCode() {
        int result = country.hashCode();
        result = 31 * result + code;
        return result;
    }

    @Override
    public String toString() {
        return getLongForm();
    }
}
