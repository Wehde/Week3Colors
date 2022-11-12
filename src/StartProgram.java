/**
 * @author Kilian Wehde - kewehde
 * CIS175 - Fall 2022
 * Sep 10, 2022
 */

import java.util.List;
import java.util.Scanner;

import controller.HexColorHelper;
import model.HexColor;

public class StartProgram {

	static Scanner in = new Scanner(System.in);
	static HexColorHelper help = new HexColorHelper();
	
	private static void addColor() {
		boolean valid;
		HexColor hexColor = new HexColor();
		do {
			System.out.print("Enter a hex color code: ");
			String hex = in.nextLine().toUpperCase();
			try {
				hexColor.setHexColor(hex);
				List<HexColor> allColors = help.getAllColors();
				if(allColors.contains(hexColor)) {
					System.out.println(hex + " is already in the database. Unable to add.");
				} else {
					help.insertColor(hexColor);
				}
				valid = true;
			} catch (IllegalArgumentException ex){
				System.out.println(ex.getMessage());
				valid = false;
			}
		} while(!valid);
		
	}
	
	private static void deleteColor() {
		boolean valid;
		HexColor hexColor = new HexColor();
		do {
			System.out.print("Enter the hex color code to delete: ");
			String hex = in.nextLine().toUpperCase();
			try {
				hexColor.setHexColor(hex);
				List<HexColor> allColors = help.getAllColors();
				if (allColors.contains(hexColor)) {
					help.deleteColor(hexColor);
				} else {
					System.out.println(hex + " is not found in the database. Unable to delete.");
				}
				valid = true;
			} catch (IllegalArgumentException ex){
				System.out.println(ex.getMessage());
				valid = false;
			}
		} while(!valid);
	}
	
	public static void editColor() {
		boolean valid;
		HexColor hexColor = new HexColor();
		HexColor newColor = new HexColor();
		do {
			System.out.print("Enter the hex color code to edit: ");
			String hex = in.nextLine().toUpperCase();
			try {
				hexColor.setHexColor(hex);
				List<HexColor> allColors = help.getAllColors();
				if (allColors.contains(hexColor)) {
					System.out.print("Enter the new color code:");
					String newHex = in.nextLine().toUpperCase();
					newColor.setHexColor(newHex);
					help.updateColor(hexColor, newColor);
				} else {
					System.out.println(hex + " is not found in the database. Unable to edit.");
				}
				valid = true;
			} catch (IllegalArgumentException ex){
				System.out.println(ex.getMessage());
				valid = false;
			}
		} while(!valid);
	}
	
	private static void viewAllColors() {
		List<HexColor> allColors = help.getAllColors();
		System.out.println("COLOR  :  GRAY");
		for (HexColor color : allColors) {
			System.out.println(color.print());
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private static void viewSomeColors() {
		boolean repeat = true;
		while(repeat) {
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Color");
			System.out.println("2 : Search by Grayscale");
			int choice = in.nextInt();
			in.nextLine();
			if (choice == 1) {
				repeat = false;
				System.out.print("Enter the hex color code: ");
				String hex = in.nextLine().toUpperCase();
				boolean valid;
				HexColor hexColor = new HexColor();
				do {
					try {
						hexColor.setHexColor(hex);
						List<HexColor> allColors = help.getAllColors();
						if (allColors.contains(hexColor)) {
							List<HexColor> grayColors = help.searchForItemByHexGray(hexColor.getHexGray());
							System.out.println("COLOR  :  GRAY");
							for (HexColor color : grayColors) {
								System.out.println(color.print());
							}
						} else {
							System.out.println(hex + " is not found in the database. Unable to display colors with same gray value.");
						}
						valid = true;
					} catch (IllegalArgumentException ex){
						System.out.println(ex.getMessage());
						valid = false;
					}
				} while(!valid);
			} else if (choice == 2) {
				repeat = false;
				System.out.print("Enter the hex color code: ");
				String hex = in.nextLine().toUpperCase();
				boolean valid;
				do {
					try {
						List<HexColor> allGrays = help.getAllGrays();
						if (allGrays.contains(hex)) {
							List<HexColor> grayColors = help.searchForItemByHexGray(hex);
							for (HexColor color : grayColors) {
								System.out.println(color.print());
							}
						} else {
							System.out.println(hex + " is not found in the database. Unable to display colors with this gray value.");
						}
						valid = true;
					} catch (IllegalArgumentException ex){
						System.out.println(ex.getMessage());
						valid = false;
					}
				} while(!valid);
			} else {
				System.out.println("Command not found. Please try again.");
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();
	}
	
	public static void runMenu() {
		boolean repeat = true;
		System.out.println("--- Welcome to the Hex Grayscale Database ---");
		while(repeat) {
			System.out.println("*  Please choose an option:");
			System.out.println("*  1 -- Add a color");
			System.out.println("*  2 -- Edit a color");
			System.out.println("*  3 -- Delete a color");
			System.out.println("*  4 -- View all colors");
			System.out.println("*  5 -- View all colors with same strength");
			System.out.println("*  6 -- Exit");
			System.out.print("*  Your selection: ");
			
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addColor();
			} else if (selection == 2) {
				editColor();
			} else if (selection == 3) {
				deleteColor();
			} else if (selection == 4) {
				viewAllColors();
			} else if (selection == 5) {
				viewSomeColors();
			} else if (selection == 6){
				help.cleanUp();
				System.out.println("   Goodbye!   ");
				repeat = false;
			} else if (selection == 7){
				HexColorHelper.addAllColors();
				help.cleanUp();
				System.out.println("   All Colors Added!   ");
				repeat = false;
			} else {
				System.out.println("Command not found. Please try again.");
			}
		}
	}

}
