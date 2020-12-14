package stepDefs.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomEmails {
    public static String generateEmail() {

        return RandomStringUtils.random(10, true, false);
    }
}
