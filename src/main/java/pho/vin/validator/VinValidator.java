package pho.vin.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VinValidator {
    private static final List<String> TRANSLITERATION_CHARACTERS = Arrays.stream(new String[]{
            "A", "B", "C", "D", "E", "F", "G", "H",
            "J", "K", "L", "M", "N", "P", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z", "1",
            "2", "3", "4", "5", "6", "7", "8", "9",
            "0"
    }).toList();

    private static final List<Integer> TRANSLITERATION_VALUES = Arrays.stream(new Integer[]{
            1, 2, 3, 4, 5, 6, 7, 8,
            1, 2, 3, 4, 5, 7, 9, 2,
            3, 4, 5, 6, 7, 8, 9, 1,
            2, 3, 4, 5, 6, 7, 8, 9,
            0
    }).toList();

    private static final Map<String, Integer> TRANSLISTERATIONS = IntStream
            .range(0, TRANSLITERATION_CHARACTERS.size())
            .boxed()
            .collect(Collectors.toMap(TRANSLITERATION_CHARACTERS::get, TRANSLITERATION_VALUES::get));

    private static final List<Integer> WEIGHTS = Arrays.stream(new Integer[]{
                    8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2})
            .toList();

    private static final int VIN_DIGIT_INDEX = 8;

    private static final int DIVISOR = 11;

    private static final String DEFAULT_DIGIT = "X";

    public static boolean checkDigitVin(String vin) {
        char[] vinCharArray = vin.toCharArray();
        List<Integer> vinList = new ArrayList<>();

        for (char c : vinCharArray) {
            vinList.add(TRANSLISTERATIONS.get(String.valueOf(c)));
        }

        vinList.set(VIN_DIGIT_INDEX, 0);

        int sum = IntStream.range(0, WEIGHTS.size()).map(x -> vinList.get(x) * WEIGHTS.get(x)).sum();

        int modulus = sum % DIVISOR;
        String modulusCheck = (modulus == 10) ? DEFAULT_DIGIT : String.valueOf(modulus);

        return vin.substring(8, 9).equals(modulusCheck);
    }
}
