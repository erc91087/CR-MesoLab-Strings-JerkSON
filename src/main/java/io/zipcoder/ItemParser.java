package io.zipcoder;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {


    // 1
    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString) {
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }

    // 2
    public ArrayList<String> parseRawDataIntoStringArray(String rawData) {
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern, rawData);
        return response;
    }

    // 3
    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem) {
        String stringPattern = "[@|^|*|%|!|;]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern, rawItem);
        return response;
    }

    // 4
    public Item parseStringIntoItem(String rawItem) throws ItemParseException { // need addtnl.. methods to check info

        String name = checkName(rawItem);
        Double price = Double.valueOf(checkPrice(rawItem));
        String type = checkType(rawItem);
        String expiration = checkExpiration(rawItem);


        return new Item(name, price, type, expiration);
    }

    public String checkName(String input) throws ItemParseException {
        Pattern patternName = Pattern.compile("([Nn]..[Ee]:)(\\w+)");
        Matcher matcherName = patternName.matcher(input);

        if (matcherName.find())         // find() will search substrings
            return matcherName.group(2).toLowerCase();
        else throw new ItemParseException();
    }

    public String checkPrice(String input) throws ItemParseException {
        Pattern patternPrice = Pattern.compile("([Pp]...[Ee]:)(\\d\\.\\d{2})");
        Matcher matcherPrice = patternPrice.matcher(input);

        if (matcherPrice.find())
            return matcherPrice.group(2);
        else throw new ItemParseException();
    }

    public String checkType(String input) throws ItemParseException {
        Pattern patternType = Pattern.compile("([Tt]..[Ee]:)(\\w+)");
        Matcher matcherType = patternType.matcher(input);

        if (matcherType.find())
            return matcherType.group(2).toLowerCase();
        else throw new ItemParseException();
    }

    public String checkExpiration(String input) throws ItemParseException {
        Pattern patternExpiration = Pattern.compile("([Ee]........[Nn]:)(\\d\\/\\d{2}\\/\\d{4})");
        Matcher matcherExpiration = patternExpiration.matcher(input);

        if (matcherExpiration.find())
            return matcherExpiration.group(2);
        else throw new ItemParseException();
    }

}
