package com.wordz;

import java.util.ArrayList;
import java.util.List;

public class Score {

    private final String correctWord;
    private final List<Letter> results = new ArrayList<>();
    private int position;

    public Score (String correctWord) {
        this.correctWord = correctWord;
    }

    public Letter letter(int position) {
        return results.get(position);
    }

    public void assess(String attempt) {
        for (char current: attempt.toCharArray()) {
            results.add(scoreFor(current));
            position++;
        }
    }

    private boolean isCorrectLetter (char currentLetter) {
        return correctWord.charAt(position) == currentLetter;
    }

    private boolean occursInWord(char current) {
        return correctWord.contains(String.valueOf(current));
    }

    private Letter scoreFor (char current) {
        if (isCorrectLetter(current)) {
            return Letter.CORRECT;
        }
        if (occursInWord(current)) {
            return Letter.PART_CORRECT;
        }
        return Letter.INCORRECT;
    }
}
