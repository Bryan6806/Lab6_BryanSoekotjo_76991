package model;

public class Bank {
    private final String shortNumber;
    private final String name;

    public Bank(String shortNumber, String name) {
        this.shortNumber = shortNumber;
        this.name = name;
    }

    public String shortNumber() {
        return shortNumber;
    }

    public String name() {
        return name;
    }

    public boolean hasShortNumber(String code) {
        return shortNumber.equals(code);
    }
}