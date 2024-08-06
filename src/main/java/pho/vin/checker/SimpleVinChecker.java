package pho.vin.checker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleVinChecker {

    private static final Pattern SIMPLE_VIN_PATTERN = Pattern.compile("^[A-HJ-NPR-Z0-9]{17}$");

    public static boolean simpleCheck(String vin) {
        Matcher matcher = SIMPLE_VIN_PATTERN.matcher(vin);
        return matcher.matches();
    }

}
