/**
 * @author Kilian Wehde - kewehde
 * CIS175 - Fall 2022
 * Sep 10, 2022
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "colors")
public class HexColor{
	//---------------------------------------Variables---------------------------------------
	@Id
	@Column(name = "ColorHex")
	private String hexColor;
	@Column(name = "GrayHex")
	private String hexGray;
	@Transient
	private int r, g, b;
	
	//----------------------------------Getters and Setters----------------------------------
	public String getHexColor() {
		return hexColor;
	}
	public void setHexColor(String hexColor) {
		if (hexColor.length() == 6) {
			this.hexColor = hexColor;
		}
		else if (hexColor.length() == 0) {
			throw new IllegalArgumentException("The color field is empty");
		}
		else {
			throw new IllegalArgumentException(hexColor + " is not a valid color. There should be 6 characters");
		}
		parseHexColor(hexColor);
		int y = (int)((r * Convert.CONVERT_R) + (g * Convert.CONVERT_G) + (b * Convert.CONVERT_B) + Convert.ROUND);
        this.hexGray = ("" + Convert.base10ToHex(y / Convert.BASE_16) + Convert.base10ToHex(y % Convert.BASE_16)).repeat(3);
	}
	public String getHexGray() {
		return hexGray;
	}
	public int getR() {
		return r;
	}
	private void setR(int r) {
		if (r < 0) {
			throw new IllegalArgumentException(hexColor + " is not a valid color");
		}
		else {
			this.r = r;
		}
	}
	public int getG() {
		return g;
	}
	private void setG(int g) {
		if (g < 0) {
			throw new IllegalArgumentException(hexColor + " is not a valid color");
		}
		else {
			this.g = g;
		}
	}
	public int getB() {
		return b;
	}
	private void setB(int b) {
		if (b < 0) {
			throw new IllegalArgumentException(hexColor + " is not a valid color");
		}
		else {
			this.b = b;
		}
	}
	
	//--------------------------------------Constructors-------------------------------------
	/**
	 * Default no-arg constructor
	 */
	public HexColor() {
	}
	/**
	 * Constructor that takes one argument
	 * @param hexColor String of a color in hex.
	 */
	public HexColor(String hexColor) {
		setHexColor(hexColor);
	}

	
	//----------------------------------------Methods----------------------------------------
	/**
	 * Parses the hexColor string and sets the RGB values in the class
	 * @param hexColor
	 */
	public void parseHexColor(String hexColor) {
		int[] colorArray = new int[hexColor.length()];
		for(int i = 0; i < hexColor.length(); i++) {
			colorArray[i] = Convert.hexToBase10(hexColor.charAt(i));
		}
        setR(colorArray[0] * Convert.BASE_16 + colorArray[1]);
        setG(colorArray[2] * Convert.BASE_16 + colorArray[3]);
        setB(colorArray[4] * Convert.BASE_16 + colorArray[5]);
	}
	
	/**
	 * Provides string for command line viewing.
	 * @return hexColor : hexGray
	 */
	public String print() {
		return this.hexColor + " : " + this.hexGray;
	}
	
	@Override
	  public boolean equals(Object obj) {
	    if (obj == this)
	      return true; // Same object in memory
	    if (!(obj instanceof HexColor))
	      return false; // Not even the same class
	    final HexColor other = (HexColor) obj;
	    return this.hexColor.equals(other.getHexColor());
	  } 
}