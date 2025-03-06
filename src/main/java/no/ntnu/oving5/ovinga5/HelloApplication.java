package no.ntnu.oving5.ovinga5;

/*
Easiest way to add github for mac:
1. Gå på github, lag ny repository UTEN readMe eller noen annet (så tomt som mulig)
2. På lokalt prosjekts filplassering i terminal: git init -b main/master
3. git add .; git commit -m "tekst"
4. git remote add origin URL
5. Sjekk om det virket med; git remote -v
6. git push -u origin main
 */

import java.util.Collection;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private DeckOfCards deckOfCards = new DeckOfCards();
    private String flush;
    private String queen;
    String hearts;
    private String sumCard;

    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root,900,600, Color.BEIGE);

        //Card-deck
        Text cardDeckText = new Text();
        cardDeckText.setX(400);
        cardDeckText.setY(100);
        cardDeckText.setFont(Font.font("Verdana",25));
        cardDeckText.setFill(Color.PURPLE);
        root.getChildren().add(cardDeckText);

        Button buttonDeal = new Button("Deal cards!");
        buttonDeal.setLayoutX(400);
        buttonDeal.setLayoutY(300);
        buttonDeal.setOnAction(event -> {
            Collection<PlayingCard> playingCards = deckOfCards.dealHand(5);
            String dealtCards = FXdealHand(playingCards);
            this.flush = FXcheckFlush(playingCards);
            this.queen = FXcheckQueenOfCard(playingCards);
            this.hearts = deckOfCards.checkHearts(playingCards);
            this.sumCard = deckOfCards.stringSumFace(playingCards);
            cardDeckText.setText(dealtCards);
        });
        root.getChildren().add(buttonDeal);


        //analasys
        Text analFlush = new Text();
        analFlush.setX(400);
        analFlush.setY(150);
        analFlush.setFont(Font.font("Verdana",25));
        analFlush.setFill(Color.PURPLE);
        root.getChildren().add(analFlush);

        Text analQueen = new Text();
        analQueen.setX(400);
        analQueen.setY(200);
        analQueen.setFont(Font.font("Verdana",25));
        analQueen.setFill(Color.PURPLE);
        root.getChildren().add(analQueen);

        Text analHearts = new Text();
        analHearts.setX(400);
        analHearts.setY(250);
        analHearts.setFont(Font.font("Verdana",25));
        analHearts.setFill(Color.PURPLE);
        root.getChildren().add(analHearts);

        Text analSumFace = new Text();
        analSumFace.setX(400);
        analSumFace.setY(300);
        analSumFace.setFont(Font.font("Verdana",25));
        analSumFace.setFill(Color.PURPLE);
        root.getChildren().add(analSumFace);

        Button analyzeButton = new Button("Analyze the hand");
        analyzeButton.setLayoutX(400);
        analyzeButton.setLayoutY(350);
        analyzeButton.setOnAction(event -> {
            analFlush.setText(this.flush);
            analQueen.setText(this.queen);
            analHearts.setText(this.hearts);
            analSumFace.setText(this.sumCard);
        });
        root.getChildren().add(analyzeButton);


        stage.setScene(scene);
        stage.show();
    }

    public String FXdealHand(Collection<PlayingCard> playingCards){
        StringBuilder text = new StringBuilder(); //ikke initialize noe med NULL, da får du nullpointerexeption

        for(PlayingCard card : playingCards){
            text.append(card.getAsString()).append(" ");
        }
        return text.toString();
    }
    public String FXcheckFlush(Collection<PlayingCard> playingCards){
        if(deckOfCards.checkFlush(playingCards)){
            return "Flush";
        }
        return "No flush";
    }
    public String FXcheckQueenOfCard(Collection<PlayingCard> playingCards){
        if(deckOfCards.checkQueenOfCard(playingCards)){
            return "Queen of something";
        }
        return "No Queen";
    }

    public static void main(String[] args) {
        launch();
    }
}