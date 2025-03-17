package no.ntnu.oving5.ovinga5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Represents a deck of different cards.
 */
public class DeckOfCards {

  private final char[] suit = {'S','H','D','C'};
  private final int[] rank = {1,2,3,4,5,6,7,8,9,10,11,12,13};
  private ArrayList<PlayingCard> fullDeck;
  private ArrayList<PlayingCard> playedCards = new ArrayList<>();

  /**
   * Constructor of class. Initializes the deck of cards by adding a full set of 52 cards.
   */
  public DeckOfCards(){
    fullDeck = new ArrayList<>();

    for(char suit : suit){
      for(int rank : rank){
        PlayingCard playingCard = new PlayingCard(suit,rank);
        fullDeck.add(playingCard);
      }
    }
  }

  /**
   * Method that deals out a hand of cards.
   * It randomly selects cards from the fullDeck, ensures no card is dealt more than once,
   * and adds it to the dealt hand until the specified number of cards is dealt.
   *
   * @param n the number of cards to deal.
   * @return a collection of PlayingCard objects representing the dealt hand.
   * @throws IllegalArgumentException if the number of cards to deal is less than 1 or greater than 52.
   */
  public Collection<PlayingCard> dealHand(int n){
    if(n < 1 || n > 52){
      throw new IllegalArgumentException("Can not excel past 52 or under 1");
    }
    Random random = new Random();
    Collection<PlayingCard> dealHand = new ArrayList<>();

    while(dealHand.size() < n){
      if(fullDeck.isEmpty()){
        break;
      }
      int cardIndex = random.nextInt(fullDeck.size());
      PlayingCard card = fullDeck.get(cardIndex);

      if(!playedCards.contains(card)){
        dealHand.add(card);
        playedCards.add(card);
        fullDeck.remove(card);
      }
    }
    return dealHand;
  }

  /**
   * Method to check and return all the hearts cards present in the given hand.
   *
   * @param cardCollection collection of cards to check.
   * @return a string representation of the cards that are hearts.
   */
  public String checkHearts(Collection<PlayingCard> cardCollection){
    Collection<PlayingCard> hearts = cardCollection.stream()
        .filter(card -> card.getSuit() == 'H')
        .toList();
    return hearts.toString();
  }

  /**
   * Method to check if the given hand contains a flush
   *
   * @param cardCollection the collection of cards to check.
   * @return true if the hand is a flush, otherwise false.
   */
  public boolean checkFlush(Collection<PlayingCard> cardCollection) {
    // AI-assisted: check for a flush (5 consecutive face values: 1, 2, 3, 4, 5)
    Map<Integer,Long> faceCount = cardCollection.stream()
        .collect(Collectors.groupingBy(PlayingCard::getFace, Collectors.counting()));

    return faceCount.getOrDefault(1,0L) == 1 &&
        faceCount.getOrDefault(2,0L) == 1 &&
        faceCount.getOrDefault(3,0L) == 1 &&
        faceCount.getOrDefault(4,0L) == 1 &&
        faceCount.getOrDefault(5,0L) == 1;
  }

  /**
   * Method to check if a hand contains five cards from the same suit.
   *
   * @param cardCollection the collection of cards to check.
   * @return true if the hand contains five cards of the same suit, otherwise false.
   */
  public boolean checkQueenOfCard(Collection<PlayingCard> cardCollection){
    int hearts = 0;
    int spades = 0;
    int diamond = 0;
    int clubs = 0;
    for(PlayingCard card : cardCollection){
      if(card.getSuit() == 'H'){
        hearts++;
      } if(card.getSuit() == 'S'){
        spades++;
      } if(card.getSuit() == 'D'){
        diamond++;
      } if(card.getSuit() == 'C'){
        clubs++;
      } if(hearts == 5 || spades == 5 || diamond == 5 || clubs == 5){
        return true;
      }
    } return false;
  }

  /**
   * Method to calculate the sum of the face values of the cards in the given hand.
   *
   * @param cardCollection the collection of cards to sum.
   * @return a string representation of the sum of the face values of the cards in the collection.
   */
  public String stringSumFace(Collection<PlayingCard> cardCollection){
    int sumFace = 0;
    for(PlayingCard card : cardCollection){
      sumFace += card.getFace();
    }
    return Integer.toString(sumFace);
  }

  /**
   * Getter method to retrieve the full deck of cards.
   *
   * @return an ArrayList containing all the PlayingCard objects in the deck.
   */
  public ArrayList<PlayingCard> getFullDeck(){
    return this.fullDeck;
  }
}
