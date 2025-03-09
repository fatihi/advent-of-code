package de.fatih_inan.days;

import de.fatih_inan.Day;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 implements Day {
    @Override
    public String part1(String input) {
        int counter = 0;

        while (true) {
            String hash = getMd5Hash(input + counter);
            if (hash.startsWith("00000")) {
                return String.valueOf(counter);
            }
            counter++;
        }
    }

    private String getMd5Hash(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(s.getBytes());
            return String.format("%032X", new BigInteger(1, digest));
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    @Override
    public String part2(String input) {
        int counter = 0;

        while (true) {
            String hash = getMd5Hash(input + counter);
            if (hash.startsWith("000000")) {
                return String.valueOf(counter);
            }
            counter++;
        }
    }
}
