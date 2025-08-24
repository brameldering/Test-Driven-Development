package com.wordz.domain;

public class WordSelection {
    private final WordRepository repository;
    private final RandomNumbers randomNumbers;

    public WordSelection(WordRepository repository, RandomNumbers randomNumbers) {
        this.repository = repository;
        this.randomNumbers = randomNumbers;
    }

    public String chooseRandomWord() {
        try {
            int wordNumber = randomNumbers.next(repository.highestWordNumber());
            return repository.fetchWordByNumber(wordNumber);
        } catch (WordRepositoryException wre) {
            throw new WordSelectionException("Could not select word", wre);
        }
    }

}
