package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 */
public class Binary {
    private String number = "0"; // string containing the binary value '0' or '1'

    /**
     * A constructor that generates a binary object.
     *
     * @param number a String of the binary values. It should contain only zeros or
     *               ones with any length and order. Otherwise, the value of "0" will
     *               be stored. Trailing zeros will be excluded, and an empty string
     *               will be considered as zero.
     */
    public Binary(String number) {
        for (int i = 0; i < number.length(); i++) {
            // check each character if it's not 0 or 1
            char ch = number.charAt(i);
            if (ch != '0' && ch != '1') {
                number = "0"; // if not store "0" and end the function
                return;
            }
        }
        // remove any trailing zeros
        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg) != '0')
                break;
        }
        // beg has the index of the first non-zero digit in the number
        this.number = number.substring(beg); // exclude the trailing zeros if any
        // uncomment the following code

        if (this.number.equals("")) { // replace empty strings with a single zero
            this.number = "0";
        }

    }

    /**
     * Return the binary value of the variable
     *
     * @return the binary value in a string format.
     */
    public String getValue() {
        return this.number;
    }

    /**
     * Adding two binary variables. For more information, visit
     * <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
     *
     * @param num1 The first addend object
     * @param num2 The second addend object
     * @return A binary variable with a value of <i>num1+num2</i>.
     */
    public static Binary add(Binary num1, Binary num2) {
        // the index of the first digit of each number
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        // initial variable
        int carry = 0;
        String num3 = ""; // the binary value of the sum
        while (ind1 >= 0 || ind2 >= 0 || carry != 0) // loop until all digits are processed
        {
            int sum = carry; // previous carry
            if (ind1 >= 0) { // if num1 has a digit to add
                sum += (num1.number.charAt(ind1) == '1') ? 1 : 0; // convert the digit to int and add it to sum
                ind1--; // update ind1
            }
            if (ind2 >= 0) { // if num2 has a digit to add
                sum += (num2.number.charAt(ind2) == '1') ? 1 : 0; // convert the digit to int and add it to sum
                ind2--; // update ind2
            }
            carry = sum / 2; // the new carry
            sum = sum % 2; // the resultant digit
            num3 = ((sum == 0) ? "0" : "1") + num3; // convert sum to string and append it to num3
        }
        Binary result = new Binary(num3); // create a binary object with the calculated value.
        return result;
    }

    /**
     * Bitwise logical OR of two binary variables.
     *
     * @param other The second binary variable
     * @return A binary variable with a value of <i>this OR other</i>.
     */
    public Binary or(Binary other) {
        StringBuilder result = new StringBuilder();
        int maxLength = Math.max(this.number.length(), other.number.length());

        // Ensure equal length by padding with leading zeros
        String paddedNum1 = padWithZeros(this.number, maxLength);
        String paddedNum2 = padWithZeros(other.number, maxLength);

        for (int i = 0; i < maxLength; i++) {
            char bit1 = paddedNum1.charAt(i);
            char bit2 = paddedNum2.charAt(i);
            result.append((bit1 == '1' || bit2 == '1') ? '1' : '0');
        }

        return new Binary(result.toString());
    }

    /**
     * Bitwise logical AND of two binary variables.
     *
     * @param other The second binary variable
     * @return A binary variable with a value of <i>this AND other</i>.
     */
    public Binary and(Binary other) {
        StringBuilder result = new StringBuilder();
        int maxLength = Math.max(this.number.length(), other.number.length());

        // Ensure equal length by padding with leading zeros
        String paddedNum1 = padWithZeros(this.number, maxLength);
        String paddedNum2 = padWithZeros(other.number, maxLength);

        for (int i = 0; i < maxLength; i++) {
            char bit1 = paddedNum1.charAt(i);
            char bit2 = paddedNum2.charAt(i);
            result.append((bit1 == '1' && bit2 == '1') ? '1' : '0');
        }

        return new Binary(result.toString());
    }

    /**
     * Multiply two binary variables.
     *
     * @param other The second binary variable
     * @return A binary variable with a value of <i>this * other</i>.
     */
    public Binary multiply(Binary other) {
        Binary result = new Binary("0");
        String currentNumber = this.number; // Create a local variable to store the current binary number

        for (int i = 0; i < other.number.length(); i++) {
            char bit = other.number.charAt(i);
            if (bit == '1') {
                result = add(result, new Binary(currentNumber));
            }
            currentNumber = currentNumber.substring(0, currentNumber.length() - 1) + "0";
        }

        return result;
    }

    /**
     * Helper method to pad a binary number with leading zeros to match a specified
     * length.
     *
     * @param binaryNumber The binary number to pad
     * @param length       The desired length of the padded binary number
     * @return The padded binary number
     */
    private static String padWithZeros(String binaryNumber, int length) {
        StringBuilder paddedBinary = new StringBuilder(binaryNumber);
        while (paddedBinary.length() < length) {
            paddedBinary.insert(0, '0');
        }
        return paddedBinary.toString();
    }
}
