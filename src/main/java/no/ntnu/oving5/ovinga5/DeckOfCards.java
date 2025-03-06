package no.ntnu.oving5.ovinga5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class DeckOfCards {

  private final char[] suit = {'S','H','D','C'};
  private final int[] rank = {1,2,3,4,5,6,7,8,9,10,11,12,13};
  private ArrayList<PlayingCard> fullDeck;

  public DeckOfCards(){
    fullDeck = new ArrayList<>();

    for(char suit : suit){
      for(int rank : rank){
        PlayingCard playingCard = new PlayingCard(suit,rank);
        fullDeck.add(playingCard);
      }
    }
  }

  public Collection<PlayingCard> dealHand(int n){
    if(n < 1 || n > 52){
      System.out.println("Amount of cards can not excel 52");
      return new ArrayList<>();
    }

    Collection<PlayingCard> cardCollection = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < n; i++) {
      int randInt = random.nextInt(52); //random integer from range
      cardCollection.add(fullDeck.get(randInt));
    }
    return cardCollection;
  }

  public ArrayList<PlayingCard> getFullDeck(){
    return this.fullDeck;
  }

}
