package com.wordz.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewGameTest {
    private static final Player PLAYER = new Player();
    @Mock
    private GameRepository gameRepository;
    @Mock
    private WordRepository wordRepository ;
    @Mock
    private RandomNumbers randomNumbers ;
    @InjectMocks
    private Wordz wordz;

    @Test
    void startsNewGame() {
        givenWordToSelect("ARISE");

        wordz.newGame(PLAYER);

        Game game = getGameInRepository();

        assertThat(game.getWord()).isEqualTo("ARISE");
        assertThat(game.getAttemptNumber()).isZero();
        assertThat(game.getPlayer()).isSameAs(PLAYER);
    }

    // Mock randomNumbers.next and wordRepository.fetchWordByNumber
    private void givenWordToSelect(String wordToSelect) {
        int wordNumber = 2;

        when(randomNumbers.next(anyInt()))
                .thenReturn(wordNumber);

        when(wordRepository
                .fetchWordByNumber(wordNumber))
                .thenReturn(wordToSelect);
    }

//  Verify that a Game object was created and saved to the gameRepository mock object,
//  and then to capture and return that Game object for further assertions.
    private Game getGameInRepository() {
        // Create an ArgumentCaptor for the Game.class type
        // Used to capture method arguments for inspection.
        // This is useful when you want to make assertions about the object that was passed
        // to a mocked method, especially when you don't have a direct reference to that object.
        var gameArgument = ArgumentCaptor.forClass(Game.class);

//      Assert that the create method of the gameRepository mock was called.
//      gameArgument.capture(): It tells Mockito to capture the argument that was passed
//      to the create method. The captured argument is stored within the gameArgument captor.
        verify(gameRepository)
                .create(gameArgument.capture());
        // Retrieve the actual Game object that was captured in the previous step.
        var game = gameArgument.getValue();
        // Return actual Game object
        return game;
    }

}
