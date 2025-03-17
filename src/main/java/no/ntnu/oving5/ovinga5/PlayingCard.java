package no.ntnu.oving5.ovinga5;

/**
 * Represents a playing card, with a face between 1 and 13.
 * The card is also one of 4 suits: Spades, Hearts, Diamonds, and Clubs.
 */
public class PlayingCard {

  private final char suit; // 'S'=spade, 'H'=heart, 'D'=diamonds, 'C'=clubs
  private final int face; // a number between 1 and 13
  private final String pathCard;

  /**
   * Creates an instance of a PlayingCard with a given suit and face.
   *
   * @param suit The suit of the card, as a single character H, D, C or S-
   * @param face The face value of the card, an integer between 1 and 13.
   * @throws IllegalArgumentException if suit or face have invalid values.
   */
  public PlayingCard(char suit, int face) {
    if (suit != 'H' && suit != 'D' && suit != 'C' && suit != 'S') {
      throw new IllegalArgumentException("Parameter suit must be one of H, D, C or S");
    }

    if (face < 1 || face > 13) {
      throw new IllegalArgumentException("Parameter face must be a number between 1 to 13");
    }

    this.suit = suit;
    this.face = face;
    this.pathCard = generateCardImage(suit, face);
  }

  /**
   * Generates the file path for the image representing the card.
   *
   * @param suit The suit of the card
   * @param face The face value of the card (1 to 13).
   * @return File path for the image of the card as a string.
   */
  public String generateCardImage(char suit, int face) {
    String suitString = "";
    String faceString = "";

    //SUIT
    if (suit == 'H') {
      suitString = "hearts";
    } else if (suit == 'D') {
      suitString = "diamonds";
    } else if (suit == 'C') {
      suitString = "clubs";
    } else if (suit == 'S') {
      suitString = "spades";
    }

    //FACE
    if (face == 11) {
      faceString = "jack";
    } else if (face == 12) {
      faceString = "queen";
    } else if (face == 13) {
      faceString = "king";
    } else if (face == 1) {
      faceString = "ace";
    } else {
      faceString = Integer.toString(face);
    }

    return faceString + "_of_" + suitString + ".png";
  }

  /**
   * Returns a string representation of the card
   *
   * @return the suit and face of the card as a string.
   */
  public String getAsString() {
    return String.format("%s%s", suit, face);
  }

  /**
   * Returns the suit of the card.
   * 'S' for Spades, 'H' for Hearts, 'D' for Diamonds, 'C' for Clubs.
   *
   * @return the suit of the card as a character.
   */
  public char getSuit() {
    return suit;
  }

  /**
   * Returns the face value of the card.
   * An integer between 1 and 13
   *
   * @return the face value of the card as an integer.
   */
  public int getFace() {
    return face;
  }

  /**
   * Compares this card with another object for equality.
   *
   * @param o the object to compare card with.
   * @return true if the cards are the same suit and face, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlayingCard otherCard = (PlayingCard) o;
    return getSuit() == otherCard.getSuit() && getFace() == otherCard.getFace();
  }

  /**
   * Returns a hash code value for the card based on its suit and face value.
   *
   * @return a hash code value for the card.
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 31 * hash + getSuit();
    hash = 31 * hash + getFace();
    return hash;
  }

  /**
   * The toString method, to override the point-adresse
   *
   * @return the string representation of the card.
   */
  @Override
  public String toString() {
    return this.suit + "" + this.face;
  }
}
