/**
 * @author Kilian Wehde - kewehde
 * CIS175 - Fall 2022
 * Sep 10, 2022
 */
package model;

public class Convert {
	//Global Variables
	public static final double CONVERT_R = 0.299;
    public static final double CONVERT_G = 0.587;
    public static final double CONVERT_B = 0.114;
    public static final double ROUND = 0.5;
    public static final int BASE_16 = 16;
	
	/**
     * Converts an integer to its corresponding hex character
     * @param base10 integer between 0-16
     * @return A character with the hex value of the integer provided. Will be 'R' if invalid
     */
	public static char base10ToHex (int base10){
        return switch (base10) {
            case 0 -> '0';
            case 1 -> '1';
            case 2 -> '2';
            case 3 -> '3';
            case 4 -> '4';
            case 5 -> '5';
            case 6 -> '6';
            case 7 -> '7';
            case 8 -> '8';
            case 9 -> '9';
            case 10 -> 'A';
            case 11 -> 'B';
            case 12 -> 'C';
            case 13 -> 'D';
            case 14 -> 'E';
            case 15 -> 'F';
            default -> 'R';
        };
    }
    /**
     * Converts a hex character to its base 10 value
     * @param hex character of the hex value
     * @return An integer with the base10 equivalent to the hex character provided. Will be -1000 if invalid.
     */
	public static int hexToBase10 (char hex){
        final int ERROR = -1000;
        return switch (hex) {
            case '0' -> 0;
            case '1' -> 1;
            case '2' -> 2;
            case '3' -> 3;
            case '4' -> 4;
            case '5' -> 5;
            case '6' -> 6;
            case '7' -> 7;
            case '8' -> 8;
            case '9' -> 9;
            case 'A' -> 10;
            case 'B' -> 11;
            case 'C' -> 12;
            case 'D' -> 13;
            case 'E' -> 14;
            case 'F' -> 15;
            default -> ERROR;
        };
	}
}
