package edu.hm.hafner.java2.schein;

/**
 * A postal code of a city, region, building, etc.
 *
 * @author Ullrich Hafner
 */
public interface PostalCode {
    /**
     * Returns the country this postal code belongs to. I.e., for the city of Munich this would be
     * {@link Country#GERMANY}. For the city of Zurich it would be {@link Country#SWITZERLAND}.
     *
     * @return the country
     */
    Country getCountry();

    /**
     * Returns the 2-letter ISO code of the country this postal code belongs to. I.e., for the city of Munich this would
     * be "DE".
     *
     * @return the country
     */
    String getCountryIsoCode();

    /**
     * Returns the actual postal code value as an integer. I.e., for the city of Munich this would be 80335. For the
     * city of Dresden it would be 1099.
     *
     * @return the country code
     */
    int getCode();

    /**
     * Returns the printable postal code (short form) as a String. The short form displays the value of the {@code code}
     * and does not contain the country code. I.e., for the city of Munich this would be 80335. For the city of Dresden
     * it would be 01099.
     *
     * @return the postal code (short form)
     */
    String getShortForm();

    /**
     * Returns the printable postal code (long form). The long form displays both the value of the {@code code} and the
     * country code. I.e., for the city of Munich this would be DE-80335. For the city of Zurich it would be CH-8001.
     *
     * @return the postal code (long form)
     */
    String getLongForm();
}
