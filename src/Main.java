import data.BankParser;
import data.NbpBankSource;
import logic.BankLookup;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter bank code (3 digits): ");
        String code = scanner.nextLine().trim();

        if (!code.matches("\\d{3}")) {
            System.out.println("Please enter exactly 3 digits.");
            return;
        }

        BankLookup lookup = new BankLookup(new NbpBankSource(), new BankParser());

        try {
            List<String> names = lookup.findBankNames(code);

            if (names.isEmpty()) {
                System.out.println("Bank not found.");
                return;
            }

            System.out.println("Bank number: " + code);
            names.forEach(name -> System.out.println("Bank name: " + name));

            if (names.size() > 1) {
                System.out.println("Several banks match this prefix.");
            }
        } catch (IOException e) {
            System.out.println("Could not get data from NBP: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Download was interrupted.");
        }
    }
}