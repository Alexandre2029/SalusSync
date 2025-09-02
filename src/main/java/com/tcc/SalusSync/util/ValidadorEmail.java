package com.tcc.SalusSync.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class ValidadorEmail {

    public static boolean validarEmail(String email){
        String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }
}
