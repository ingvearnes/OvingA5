package no.ntnu.oving5.ovinga5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class DeckOfCardsTest {

  private DeckOfCards deckOfCards;

  @BeforeEach
  public void setup() {
    deckOfCards = new DeckOfCards();
  }

  @Test
  public void testDeckInitialization() {
    // Check if the deck has 52 cards initially
    assertEquals(52, deckOfCards.getFullDeck().size(), "The deck should contain 52 cards.");
  }

  @Test
  public void testDealHandValid() {
    // Deal a hand of 5 cards and check if the hand has the correct size
    Collection<PlayingCard> hand = deckOfCards.dealHand(5);
    assertEquals(5, hand.size(), "The hand should contain 5 cards.");
  }

  @Test
  public void testDealHandInvalid() {
    // Try to deal an invalid number of cards (0 cards)
    assertThrows(IllegalArgumentException.class, () -> deckOfCards.dealHand(0), "Exception should be thrown for invalid hand size.");
    // Try to deal more than 52 cards
    assertThrows(IllegalArgumentException.class, () -> deckOfCards.dealHand(53), "Exception should be thrown for hand size greater than 52.");
  }

  @Test
  public void testCheckHearts() {
    // Deal a hand and check if it correctly identifies hearts
    Collection<PlayingCard> hand = deckOfCards.dealHand(5);
    String hearts = deckOfCards.checkHearts(hand);
    assertTrue(hearts.contains("H"), "The hand should contain hearts.");
  }

  @Test
  public void testCheckFlush() {
    // Deal a hand of 5 cards with different suits
    Collection<PlayingCard> hand = deckOfCards.dealHand(5);
    assertFalse(deckOfCards.checkFlush(hand), "The hand should not be a flush.");
  }

  @Test
  public void testCheckQueenOfCard() {
    // Deal a hand of 5 cards and check if there is a 'Queen' of a single suit
    Collection<PlayingCard> hand = deckOfCards.dealHand(5);
    assertFalse(deckOfCards.checkQueenOfCard(hand), "The hand should not contain five cards of the same suit.");
  }

  @Test
  public void testStringSumFace() {
    // Deal a hand and calculate the sum of the face values
    Collection<PlayingCard> hand = deckOfCards.dealHand(5);
    String sum = deckOfCards.stringSumFace(hand);
    assertNotNull(sum, "The sum of the face values should not be null.");
    try {
      Integer.parseInt(sum); // Check if the sum is a valid integer
    } catch (NumberFormatException e) {
      fail("The sum of face values should be a valid integer.");
    }
  }

  @Test
  public void testDealHandExhaustDeck() {
    // Deal all cards one by one and check that the deck eventually runs out of cards
    int totalCardsDealt = 0;
    while (deckOfCards.getFullDeck().size() > 0) {
      Collection<PlayingCard> hand = deckOfCards.dealHand(1);
      totalCardsDealt += hand.size();
    }
    assertEquals(52, totalCardsDealt, "All 52 cards should be dealt.");
  }
}
