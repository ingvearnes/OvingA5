package no.ntnu.oving5.ovinga5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayingCardTest {

  @Test
  public void testValidPlayingCardCreation() {
    PlayingCard card = new PlayingCard('H', 7);  // Heart 7
    assertNotNull(card);
    assertEquals('H', card.getSuit());
    assertEquals(7, card.getFace());
    assertEquals("7_of_hearts.png", card.generateCardImage('H', 7));
  }

  @Test
  public void testAceCard() {
    PlayingCard card = new PlayingCard('S', 1);  // Spade Ace
    assertNotNull(card);
    assertEquals('S', card.getSuit());
    assertEquals(1, card.getFace());
    assertEquals("ace_of_spades.png", card.generateCardImage('S', 1));
  }

  @Test
  public void testJackCard() {
    PlayingCard card = new PlayingCard('D', 11);  // Diamond Jack
    assertNotNull(card);
    assertEquals('D', card.getSuit());
    assertEquals(11, card.getFace());
    assertEquals("jack_of_diamonds.png", card.generateCardImage('D', 11));
  }

  @Test
  public void testQueenCard() {
    PlayingCard card = new PlayingCard('C', 12);  // Club Queen
    assertNotNull(card);
    assertEquals('C', card.getSuit());
    assertEquals(12, card.getFace());
    assertEquals("queen_of_clubs.png", card.generateCardImage('C', 12));
  }

  @Test
  public void testKingCard() {
    PlayingCard card = new PlayingCard('H', 13);  // Heart King
    assertNotNull(card);
    assertEquals('H', card.getSuit());
    assertEquals(13, card.getFace());
    assertEquals("king_of_hearts.png", card.generateCardImage('H', 13));
  }

  @Test
  public void testCardToString() {
    PlayingCard card = new PlayingCard('S', 5);  // Spade 5
    assertEquals("S5", card.toString());
  }

  @Test
  public void testCardEquality() {
    PlayingCard card1 = new PlayingCard('D', 9);
    PlayingCard card2 = new PlayingCard('D', 9);
    assertTrue(card1.equals(card2));
  }

  @Test
  public void testCardHashCode() {
    PlayingCard card1 = new PlayingCard('D', 9);
    PlayingCard card2 = new PlayingCard('D', 9);
    assertEquals(card1.hashCode(), card2.hashCode());
  }


  @Test
  public void testInvalidSuit() {
    assertThrows(IllegalArgumentException.class, () -> {
      new PlayingCard('X', 5);  // Invalid suit 'X'
    });
  }

  @Test
  public void testInvalidFaceLow() {
    assertThrows(IllegalArgumentException.class, () -> {
      new PlayingCard('S', 0);  // Invalid face value 0
    });
  }

  @Test
  public void testInvalidFaceHigh() {
    assertThrows(IllegalArgumentException.class, () -> {
      new PlayingCard('H', 14);  // Invalid face value 14
    });
  }

  @Test
  public void testInvalidSuitAndFace() {
    assertThrows(IllegalArgumentException.class, () -> {
      new PlayingCard('X', 14);  // Invalid suit 'X' and face 14
    });
  }

  @Test
  public void testNullCardEquality() {
    PlayingCard card1 = new PlayingCard('C', 7);
    assertFalse(card1.equals(null));  // Null comparison should return false
  }

  @Test
  public void testDifferentCardsNotEqual() {
    PlayingCard card1 = new PlayingCard('S', 5);
    PlayingCard card2 = new PlayingCard('H', 5);
    assertFalse(card1.equals(card2));  // Different suits should not be equal
  }
}