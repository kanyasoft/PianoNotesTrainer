package com.kanyasoft.pianonotestrainer;
import java.util.Random;

public class NoteGenerator {
    private static final String[] NOTES = {"C", "D", "E", "F", "G", "A", "B"};

    public static String generateRandomNote() {
        Random rand = new Random();
        int index = rand.nextInt(NOTES.length);
        return NOTES[index];
    }
}
