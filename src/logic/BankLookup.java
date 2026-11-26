package logic;

import data.BankParser;
import data.NbpBankSource;
import model.Bank;

import java.io.IOException;
import java.util.List;

public class BankLookup {
    private final NbpBankSource source;
    private final BankParser parser;

    public BankLookup(NbpBankSource source, BankParser parser) {
        this.source = source;
        this.parser = parser;
    }

    public List<String> findBankNames(String code)
            throws IOException, InterruptedException {

        if (code == null || !code.matches("\\d{3}")) {
            throw new IllegalArgumentException("The code must be exactly 3 digits.");
        }

        List<Bank> banks = parser.parse(source.download());
        if (banks.isEmpty()) {
            throw new IOException("The NBP file contains no bank records.");
        }

        return banks.stream()
                .filter(bank -> bank.hasShortNumber(code))
                .map(Bank::name)
                .distinct()
                .toList();
    }
}