package com.wordz.domain;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

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

    public boolean allCorrect() {
        var totalCorrect = results.stream()
                .filter(letter -> letter == Letter.CORRECT)
                .count();

        return totalCorrect == results.size();
    }

    public List<Letter> letters() {
        return unmodifiableList(results);
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
