package com.example.tuanle.chatapplication.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    public static boolean isValidUser(final String username){
        Pattern pattern;
        Matcher matcher;

        final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{8,}$";
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);

        return matcher.matches();
    }

    public static boolean isValidPassword(final String pwd){
        Pattern pattern;
        Matcher matcher;

        final String PWD_PATTERN = "^(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{4,8}$";
        pattern = Pattern.compile(PWD_PATTERN);
        matcher = pattern.matcher(pwd);

        return matcher.matches();
    }

    public static boolean isValidCategories(final String cat_name, final String cat_des){
        Pattern catname_pattern;
        Pattern catdes_pattern;
        Matcher catdes_matcher;
        Matcher catname_matcher;

        final String CAT_PATTERN = "[A-Za-z]{4,}";
        final String DES_PATTERN = "[a-z0-9]{10,}";
        catname_pattern = Pattern.compile(CAT_PATTERN);
        catname_matcher = catname_pattern.matcher(cat_name);
        catdes_pattern = Pattern.compile(DES_PATTERN);
        catdes_matcher = catdes_pattern.matcher(cat_des);

        if (catname_matcher.matches() && catdes_matcher.matches())
            return true;
        else
            return false;
    }
}
