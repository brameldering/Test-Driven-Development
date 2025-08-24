package com.wordz.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class WordSelectionTest {

    private static final int HIGHEST_WORD_NUMBER = 3;
    private static final int WORD_NUMBER_SHINE = 2;

    @Mock
    private WordRepository repository;

    @Mock
    private RandomNumbers randomNumbers;

    @Test
    void selectsWordAtRandom() {
        when(repository.highestWordNumber()).thenReturn(HIGHEST_WORD_NUMBER);
        when(randomNumbers.next(HIGHEST_WORD_NUMBER)).thenReturn(WORD_NUMBER_SHINE);
        when(repository.fetchWordByNumber(WORD_NUMBER_SHINE)).thenReturn("SHINE");

        var selection = new WordSelection(repository, randomNumbers);

        var actual = selection.chooseRandomWord();

        assertThat(actual).isEqualTo("SHINE");
    }
}
