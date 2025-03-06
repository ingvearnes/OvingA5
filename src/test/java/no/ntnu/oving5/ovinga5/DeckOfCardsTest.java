package no.ntnu.oving5.ovinga5;

import static org.junit.jupiter.api.Assertions.*;

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
    System.out.println(deckOfCards.dealHand(9));
  }
}