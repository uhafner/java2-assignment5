package edu.hm.hafner.java2.schein;

/**
 * A country in the world.
 *
 * @author Ullrich Hafner
 */
public enum Country {
    /** Germany. */
    GERMANY("DE", "Germany"),
    /** Switzerland. */
    SWITZERLAND("CH", "Switzerland");

    private final String isoCode;
    private final String name;

    Country(final String isoCode, final String name) {
        this.isoCode = isoCode;
        this.name = name;
    }

    /**
     * Returns the 2-letter ISO code of this country.
     *
     * @return the country
     */
    public String getIsoCode() {
        return isoCode;
    }

    /**
     * Returns the name of this country.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
