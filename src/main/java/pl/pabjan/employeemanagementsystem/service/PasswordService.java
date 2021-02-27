package pl.pabjan.employeemanagementsystem.service;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordService {

    public static String getGeneratedCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = getCombinedChars(upperCaseLetters, lowerCaseLetters, numbers, specialChar, totalChars);
        List<Character> pwdChars = getPwdChars(combinedChars);
        Collections.shuffle(pwdChars);
        return getToString(pwdChars);
    }

    private static String getCombinedChars(String upperCaseLetters, String lowerCaseLetters, String numbers, String specialChar, String totalChars) {
        return upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);
    }

    private static String getToString(List<Character> pwdChars) {
        return pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private static List<Character> getPwdChars(String combinedChars) {
        return combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }
}
