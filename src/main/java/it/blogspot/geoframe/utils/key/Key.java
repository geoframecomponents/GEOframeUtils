/*
 * GNU GPL v3 License
 *
 * Copyright 2015 AboutHydrology (Riccardo Rigon)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.blogspot.geoframe.utils.key;

import it.blogspot.geoframe.utils.key.pairingFunctions.CantorPairing;
import it.blogspot.geoframe.utils.key.pairingFunctions.PairingFunction;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import net.jcip.annotations.Immutable;

import java.util.List;

/**
 * Key used as ID for the nodes of the tree
 * <p>
 * The Key class is <em>ThreadSafe</em> because
 * <em>Immutable</em>. The key is stored in hexadecimal format.
 * Converters <strong>hexToDecimal</strong> and
 * <strong>decimalToHex</strong> have been implemented as private
 * methods, thus the user can construct the object providing:
 * <ul>
 * <li>a <tt>Key</tt> object;</li>
 * <li>an <em>hexadecimal</em> String;</li>
 * <li>a <em>decimal</em> double value.
 * </ul>
 * <p>
 * The <tt>Key</tt> works as key value for <code>Map</code>
 * objects. The methods <code>equals(Object obj)</code> and
 * <code>hashCode()</code> have been implemented using the library
 * <A HREF="http://commons.apache.org/proper/commons-lang/download_lang.cgi">Apache Commons Lang</A>
 * </p>
 *
 * @author sidereus, francesco.serafin.3@gmail.com
 * @version 0.1, 11/08/2015
 * @since 0.3.1
 */
@Immutable
public class Key {

    private final String hexKey; //!< the hexadecimal key
    private PairingFunction pairingFunction;

    /**
     * Constructor from a <em>decimal</em> double value
     *
     * @param decimalKey The input value in decimal double format
     */
    public Key(final long decimalKey) {

        validateLongKey(decimalKey); // precondition
        this.hexKey = decimalToHex(decimalKey);

    }

    /**
     * @param hexKey Hexadecimal key
     */
    public Key(final String hexKey) {

        validateStringKey(hexKey); // precondition
        this.hexKey = hexKey;

    }

    /**
     * @param key the key
     */
    public Key(final Key key) {

        validateKey(key); // precondition
        this.hexKey = key.getString();

    }

    public Key(final long a, final long b) {

        validateLongKey(a);
        validateLongKey(b);

        pairingFunction = new CantorPairing();
        long result = pairingFunction.computePair(a, b);

        this.hexKey = decimalToHex(result);
    }

    /**
     * Getter method key in <strong>hexadecimal</strong> format
     *
     * @return the key in hexadecimal format as <code>String</code> object
     */
    public String getString() {
        return hexKey;
    }

    /**
     * Getter method for key in <strong>decimal</strong> format
     *
     * @return the key in decimal format as <tt>Double</tt> object
     */
    public long getInteger() {
        return hexToDecimal();
    }

    public List<Integer> getUnpair() {
        if (pairingFunction != null) return pairingFunction.computeUnpair(hexToDecimal());
        else {
            String message = "Key has NOT been construct over pairing function";
            throw new NullPointerException(message);
        }
    }

    /**
     * Compute if the key is odd or even
     * <p>
     * The computation is done following these steps:
     * <ol>
     * <li>dividing the decimal format of the key by 2;</li>
     * <li>converting the result from <tt>Double</tt> to
     * <tt>String</tt>;</li>
     * <li>parsing the <tt>String</tt> through regular expression,
     * splitting <strong>integer</strong> part and
     * <strong>decimal</strong> part and saving them in an array of
     * <tt>String</tt>;</li>
     * <li>comparing the <strong>decimal</strong> part with 0.</li>
     * </ol>
     *
     * @return TRUE if the key is even, FALSE if the key is odd
     */
    public boolean isEven() {

        Double division = ((double) hexToDecimal()) / 2;
        String tmpString = division.toString();
        String[] result = tmpString.split("\\.");

        return (result[1].compareTo("0") == 0) ? true : false;

    }

    /**
     * Indicates wheter some other object is <em>equal to</em> this one
     *
     * @param obj The reference object with which to compare
     * @return TRUE if this object is the same as the object argument, FALSE otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Key)) return false;
        if (obj == this) return true;

        Key rhs = (Key) obj;
        return new EqualsBuilder().append(hexKey, rhs.hexKey).isEquals();

    }

    /**
     * Returns a hash code value for the object.
     * <p>
     * This method is supported for the benefit of hash tables such
     * as those provided by <code>HashMap</code>
     * </p>
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 3).append(hexKey).toHashCode();
    }

    /**
     * <strong>hexadecimal</strong> to <strong>decimal</strong> format
     * <p>
     * The algorithm has been modified from the original version in
     * <A HREF=
     * "http://introcs.cs.princeton.edu/java/31datatype/Hex2Decimal.java.html">
     * Introduction to Programming in Java</A>, in order to work
     * with <code>double</code> data type
     * </p>
     *
     * @return The decimal value in double
     */
    private long hexToDecimal() {

        final String DIGITS = "0123456789ABCDEF";
        final String HEXKEY = hexKey.toUpperCase();
        long decimalVal = 0;

        for (int i = 0; i < HEXKEY.length(); i++) {
            char c = HEXKEY.charAt(i); // parses char by char
            int d = DIGITS.indexOf(c); // checking the corresponding digit
            decimalVal = 16 * decimalVal + d; // conversion
        }

        return decimalVal;

    }

    /**
     * <strong>decimal</strong> to <strong>hexadecimal</strong> format
     * <p>
     * The algorithm has been modified from the original version in
     * <A HREF=
     * "http://introcs.cs.princeton.edu/java/31datatype/Hex2Decimal.java.html">
     * Introduction to Programming in Java</A>, in order to work
     * with <code>double</code> data type
     * </p>
     *
     * @param decimalVal The decimal value to convert in hexadecimal
     * @return The hexadecimal format of the input value
     */
    private String decimalToHex(double decimalVal) {

        final String DIGITS = "0123456789abcdef";

        if (decimalVal == 0.0) return "0";

        String hexadecimal = "";
        while (Math.floor(decimalVal) > 0) {
            int digit = (int) decimalVal % 16;
            hexadecimal = DIGITS.charAt(digit) + hexadecimal;
            decimalVal = decimalVal / 16;
        }

        return hexadecimal;

    }

    /**
     * <strong>Precondition</strong> to validate input hexadecimal string
     *
     * @param hexKey The input hexadecimal string
     * @throws NumberFormatException if the input string contains characters that are not
     *                               hexadecimal symbols
     */
    private void validateStringKey(final String hexKey) {

        final String DIGITS = "0123456789ABCDEF";
        final String HEXKEY = hexKey.toUpperCase();

        for (int i = 0; i < HEXKEY.length(); i++) {
            char c = HEXKEY.charAt(i);
            int d = DIGITS.indexOf(c);

            if (d < 0) {
                String message = "String '" + hexKey;
                message += "' cannot be converted in";
                message += "hexadecimal format.\n";
                message += c + " is not an hex symbol";
                throw new NumberFormatException(message);
            }

        }

    }

    /**
     * <strong>Precondition</strong> to validate the input Key
     *
     * @param key The input key
     * @throws NullPointerException if the input key is <code>null</code>
     */
    private void validateKey(final Key key) {

        if (key == null)
            throw new NullPointerException("The input key is null");

    }

    /**
     * <strong>Precondition</strong> to validate the input decimal value
     *
     * @param longKey The decimal key in input
     * @throws IllegalArgumentException if the decimal value is negative
     */
    private void validateLongKey(final long longKey) {

        if (longKey < 0) {
            String message = "Negative key - " + longKey;
            message += " - are not accepted";
            throw new IllegalArgumentException(message);
        }

    }

}
