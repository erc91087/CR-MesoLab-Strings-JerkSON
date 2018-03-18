package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
    public Item parseStringIntoItem(String rawItem) throws ItemParseException {
        int count = 0;
        String strPrice = "\\d.\\d\\d";
        Double price = Double.valueOf(strPrice);
        String type = "(T|t)..(E|e)";
        String expiration = "(E|e)........(N|n)";

        ArrayList<String> temp = parseRawDataIntoStringArray(rawItem);
      //  ArrayList<Item> myItem = new ArrayList<Item>();

        for (int i = 0; i < temp.size(); i++) {
            if (findKeyValuePairsInRawItemData(temp.get(i)).equals(null)) {
                count++;
                throw new ItemParseException();
            }
            else if (findKeyValuePairsInRawItemData(temp.get(i)).size() == 4) {
                for (int j = 0; j < temp.size(); j++) {

                }
            }
        }
        return null;
    }



}
