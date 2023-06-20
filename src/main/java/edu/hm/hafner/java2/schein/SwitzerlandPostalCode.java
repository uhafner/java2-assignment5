package edu.hm.hafner.java2.schein;

/**
 * A postal code of Switzerland.
 */
public class SwitzerlandPostalCode extends AbstractPostalCode {
    /**
     * Creates a new {@link SwitzerlandPostalCode}.
     *
     * @param code
     *         the code to use
     */
    public SwitzerlandPostalCode(final int code) {
        super(Country.SWITZERLAND, code, 1_000, 10_000 - 1);
    }

    @Override
    public String getShortForm() {
        return String.valueOf(getCode());
    }
}
