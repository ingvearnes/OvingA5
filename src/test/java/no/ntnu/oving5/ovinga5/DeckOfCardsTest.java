package no.ntnu.oving5.ovinga5;

import java.util.Collection;
import org.junit.jupiter.api.Test;

class DeckOfCardsTest {

  @Test
  void getFullDeck() {
    DeckOfCards deckOfCards = new DeckOfCards();
    System.out.println(deckOfCards.getFullDeck());
  }

  @Test
  void checkDealHand(){
    DeckOfCards deckOfCards = new DeckOfCards();
    Collection<PlayingCard> deck = deckOfCards.dealHand(5);
    System.out.println(deck);
    System.out.println(deckOfCards.checkCards(deck));
  }

  @Test
  void checkCheckCards(){
    DeckOfCards deckOfCards = new DeckOfCards();
    System.out.println(deckOfCards.getFullDeck());
    System.out.println(deckOfCards.checkCards(deckOfCards.getFullDeck()));
  }


}