package edu.hm.hafner.java2.schein;

/**
 * A postal code of Germany.
 */
public class GermanyPostalCode extends AbstractPostalCode {
    /**
     * Creates a new {@link GermanyPostalCode}.
     *
     * @param code
     *         the code to use
     */
    public GermanyPostalCode(final int code) {
        super(Country.GERMANY, code, 1001, 100_000 - 1);
    }

    @Override
    public String getShortForm() {
        return String.format("%05d", getCode());
    }
}
