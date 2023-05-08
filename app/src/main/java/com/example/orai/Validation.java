package com.example.orai;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final String USER_NAME_PATTERN = "^[A-Za-z]{3,20}$";
    private static final String USER_PASSWORD_PATTERN = "^[A-Za-z-.!@]{5,20}$";


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
}

//TODO::1. LoginActivity paspaudus mygtuką login - pereiti į SearchActivity (reikės sukurti naują Activity).
// Pereiti tik, jeigu praeina validaciją.
// 2. Prisijungimo lange atlikti šių laukelių validaciją:
// Prisijungimo vardo:- nuo 3 iki 20 symbolių;
// - mažosios ir didžiosios raidės;- jokių specialių symbolių;
// Slaptažodžio:- nuo 5 iki 20 symbolių;- mažosios ir didžiosios raidės;
// - skaičiai;- specialūs symboliai- .!@_
// 3. Jeigu validacija sėkminga, išvesti pranešimą vartotojui su jo įvestu prisijungimo vardu ir slaptažodžiu.
