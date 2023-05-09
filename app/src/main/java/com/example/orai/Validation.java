package com.example.orai;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final String USER_NAME_PATTERN = "^[A-Za-z]{3,20}$";
    private static final String USER_PASSWORD_PATTERN = "^[A-Za-z-.!@]{5,20}$";
    private static final String USER_NAME_REGISTRATION_PATTERN = "^[A-za-z]{3,20}$";
    private static final String USER_PASSWORD_REGISTRATION_PATTERN = "^[A-za-z0-9.!@_]{5,20}$";
    private static final String USER_EMAIL_REGISTRATION_PATTERN = "^[A-za-z0-9@._-]{10,50}$";


    public static boolean isUserNameValid(String userName) {
        //sukuriamas šablonas pagal eilutėje aprašytas taisykles
        Pattern pattern = Pattern.compile(USER_NAME_PATTERN);
        //patikrina ar vartotjo įvesti duomenys atitinka regex/šabloną
        Matcher matcher = pattern.matcher(userName);
        return matcher.find();

    }

    public static boolean isUserPasswordValid (String userPassword){
        Pattern pattern = Pattern.compile(USER_PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(userPassword);
        return  matcher.find();

    }

    public static boolean isUserRegistrationNameValid (String username){
        Pattern pattern  = Pattern.compile(USER_NAME_REGISTRATION_PATTERN);
        Matcher matcher = pattern.matcher(username);
        return matcher.find();

    }

    public static boolean isUserRegistrationPasswordValid(String userPassword){
        Pattern pattern = Pattern.compile(USER_PASSWORD_REGISTRATION_PATTERN);
        Matcher matcher = pattern.matcher(userPassword);
        return matcher.find();

    }


    public static boolean isUserRegistrationEmailValid(String userEmail){
        Pattern pattern = Pattern.compile(USER_EMAIL_REGISTRATION_PATTERN);
        Matcher matcher = pattern.matcher(userEmail);
        return matcher.find();
    }

}

