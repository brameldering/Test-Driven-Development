package com.wordz.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
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

    @BeforeEach
    void beforeEachTest() {
        when(repository.highestWordNumber()).thenReturn(HIGHEST_WORD_NUMBER);
//        when(repository.fetchWordByNumber(WORD_NUMBER_SHINE)).thenReturn("SHINE");
        when(repository.fetchWordByNumber(anyInt())).thenReturn("SHINE");
    }

    @Test
    void selectsWordAtRandom() {
        when(randomNumbers.next(HIGHEST_WORD_NUMBER)).thenReturn(WORD_NUMBER_SHINE);

        var selection = new WordSelection(repository, randomNumbers);

        var actual = selection.chooseRandomWord();

        assertThat(actual).isEqualTo("SHINE");
    }
}
