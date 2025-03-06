package no.ntnu.oving5.ovinga5;

import java.util.ArrayList;
import java.util.Collection;
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

  public boolean checkCards(Collection<PlayingCard> cardCollection){
    int flush1 = 0;
    int flush2 = 0;
    int flush3 = 0;
    int flush4 = 0;
    int flush5 = 0;
    for(PlayingCard cardFlush : cardCollection){
      if(cardFlush.getFace() == 1){
        flush1++;
      }
      if(cardFlush.getFace() == 2){
        flush2++;
      }
      if(cardFlush.getFace() == 3){
        flush3++;
      }
      if(cardFlush.getFace() == 4){
        flush4++;
      }
      if(cardFlush.getFace() == 5){
        flush5++;
      }
      if(flush1 == 1 && flush2 == 1 && flush3 == 1 && flush4 == 1 && flush5 == 1){
        return true;
      }
    }

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

  public ArrayList<PlayingCard> getFullDeck(){
    return this.fullDeck;
  }

}
