package edu.hm.hafner.java2.schein;

/**
 * A factory to create postal codes for different countries.
 *
 * @author Ullrich Hafner
 */
public class PostalCodeFactory {
    /**
     * Creates a new {@link PostalCode} instance from the specified code (given in its String representation).
     *
     * @param code the code to create a {@link PostalCode} from
     * @return the {@link PostalCode}
     * @throws IllegalArgumentException if the specified code cannot be converted to a {@link PostalCode}
     */
    public static PostalCode createPostalCodeFrom(final String code) {
        if (code.startsWith("DE-")) {
            return new GermanyPostalCode(getPlainCode(code));
        }
        if (code.startsWith("CH-")) {
            return new SwitzerlandPostalCode(getPlainCode(code));
        }
        throw new IllegalArgumentException(String.format("Kann aus dem String %s keine Postleitzahl erzeugen", code));
    }

    private static int getPlainCode(final String code) {
        var cityCode = code.substring(code.indexOf('-') + 1);
        try {
            return Integer.parseInt(cityCode);
        }
        catch (NumberFormatException exception) {
            throw new IllegalArgumentException(String.format("Kann aus dem String %s keine Postleitzahl erzeugen", code));
        }
    }
}
