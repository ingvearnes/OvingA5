package no.ntnu.oving5.ovinga5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class DeckOfCards {

  private final char[] suit = {'S','H','D','C'};
  private final int[] rank = {1,2,3,4,5,6,7,8,9,10,11,12,13};
  private ArrayList<PlayingCard> fullDeck;
  private ArrayList<PlayingCard> playedCards = new ArrayList<>();

  /**
   * Constructor of class. Puts in all Cards into fullDeck.
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
   * Method that deals out the card-hand.
   * @param n Amount of cards that is to be given out
   * @return the dealhand of cards
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
   *
   * @param cardCollection
   * @return
   */
  public String checkHearts(Collection<PlayingCard> cardCollection){
    Collection<PlayingCard> hearts = cardCollection.stream()
        .filter(card -> card.getSuit() == 'H')
        .toList();
    return hearts.toString();
  }

  public boolean checkFlush(Collection<PlayingCard> cardCollection) {
    //AI-based
    Map<Integer,Long> faceCount = cardCollection.stream()
        .collect(Collectors.groupingBy(PlayingCard::getFace, Collectors.counting()));

    return faceCount.getOrDefault(1,0L) == 1 &&
           faceCount.getOrDefault(2,0L) == 1 &&
           faceCount.getOrDefault(3,0L) == 1 &&
           faceCount.getOrDefault(4,0L) == 1 &&
           faceCount.getOrDefault(5,0L) == 1;
  }


  public boolean checkQueenOfCard(Collection<PlayingCard> cardCollection){
    int hearts = 0;
    int spades = 0;
    int diamond = 0;
    int clubs = 0;
    for(PlayingCard card : cardCollection){
      if(card.getSuit() == 'H'){
        hearts++;
      }
      if(card.getSuit() == 'S'){
        spades++;
      }
      if(card.getSuit() == 'D'){
        diamond++;
      }
      if(card.getSuit() == 'C'){
        clubs++;
      }
      if(hearts == 5 || spades == 5 || diamond == 5 || clubs == 5){
        return true;
      }
    }
    return false;
  }

  public String stringSumFace(Collection<PlayingCard> cardCollection){
    int sumFace = 0;
    for(PlayingCard card : cardCollection){
      sumFace += card.getFace();
    }
    return Integer.toString(sumFace);
  }

  public ArrayList<PlayingCard> getFullDeck(){
    return this.fullDeck;
  }

}
