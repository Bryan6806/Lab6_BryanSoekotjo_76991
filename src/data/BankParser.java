package data;

import model.Bank;

import java.util.ArrayList;
import java.util.List;

public class BankParser {

    private static final int SHORT_NUMBER_COLUMN = 0;
    private static final int NAME_COLUMN = 1;

    public List<Bank> parse(String text) {
        List<Bank> result = new ArrayList<>();

        for (String line : text.split("\\R")) {
            String[] columns = line.split("\t", -1);
            if (columns.length <= NAME_COLUMN) {
                continue;
            }

            String shortNumber = columns[SHORT_NUMBER_COLUMN].trim();
            String name = columns[NAME_COLUMN].trim();

            if (shortNumber.matches("\\d{3}") && !name.isEmpty()) {
                result.add(new Bank(shortNumber, name));
            }
        }
        return result;
    }
}