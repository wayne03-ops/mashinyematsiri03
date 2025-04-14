/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject5;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author MaxgeoLA
 */
public class Mavenproject5 {
private String registeredUsername;
    private String registeredPassword;
    private String registeredPhoneNumber;
    private String firstName;
    private String lastName;

    public Mavenproject5(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return Pattern.matches(pattern, password);
    }

    public boolean checkCellPhoneNumber(String phoneNumber) {
        String pattern = "^\\+27\\d{9}$";
        return Pattern.matches(pattern, phoneNumber);
    }

    public String registerUser(String username, String password, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted. It must contain an underscore and be no more than five characters.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted. It must have at least 8 characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number is incorrectly formatted. It must start with +27 and have 9 digits after.";
        }

        this.registeredUsername = username;
        this.registeredPassword = password;
        this.registeredPhoneNumber = phoneNumber;
        return "User successfully registered.";
    }

    public boolean loginUser(String username, String password) {
        return username.equals(this.registeredUsername) && password.equals(this.registeredPassword);
    }

    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect. Please try again.";
        }
    }

    public String displayUserDetails() {
        return "First Name: " + firstName + "\n"
             + "Last Name: " + lastName + "\n"
             + "Username: " + registeredUsername + "\n"
             + "Password: " + registeredPassword + "\n"
             + "Cell Number: " + registeredPhoneNumber;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User Details Input
        System.out.println("Enter your first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name:");
        String lastName = scanner.nextLine();

        UserLoginAppInteractive loginApp = new UserLoginAppInteractive(firstName, lastName);

        System.out.println("Enter your username (must contain '_' and be ≤5 characters):");
        String username = scanner.nextLine();

        System.out.println("Enter your password (min 8 chars, 1 uppercase, number, special char):");
        String password = scanner.nextLine();

        System.out.println("Enter your cell phone number (format: +27831112222):");
        String phoneNumber = scanner.nextLine();

        // Register User
        String registrationMessage = loginApp.registerUser(username, password, phoneNumber);
        System.out.println("\n" + registrationMessage);

        if (registrationMessage.equals("User successfully registered.")) {
            System.out.println("\nUser Details:");
            System.out.println(loginApp.displayUserDetails());

            // Login
            System.out.println("\n--- Login ---");
            System.out.println("Enter your username:");
            String loginUsername = scanner.nextLine();

            System.out.println("Enter your password:");
            String loginPassword = scanner.nextLine();

            boolean isLoggedIn = loginApp.loginUser(loginUsername, loginPassword);
            System.out.println(loginApp.returnLoginStatus(isLoggedIn));
        }

        scanner.close();
    }
}   
    