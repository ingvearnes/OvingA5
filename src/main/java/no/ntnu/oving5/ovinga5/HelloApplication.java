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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
        Scene scene = new Scene(root,900,600, Color.WHITE);

        //Background
        Rectangle background = new Rectangle(600,400);
        background.setX(50);
        background.setY(50);
        background.setFill(Color.GRAY);
        root.getChildren().add(background);


        //Card-deck
        Text cardDeckText = new Text();
        cardDeckText.setX(225);
        cardDeckText.setY(200);
        cardDeckText.setFont(Font.font("Verdana",25));
        cardDeckText.setFill(Color.WHITE);
        root.getChildren().add(cardDeckText);

        Button buttonDeal = new Button("Deal cards!");
        buttonDeal.setLayoutX(700);
        buttonDeal.setLayoutY(200);
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
        analFlush.setX(100);
        analFlush.setY(500);
        analFlush.setFont(Font.font("Verdana",20));
        analFlush.setFill(Color.BLACK);
        root.getChildren().add(analFlush);

        Text analQueen = new Text();
        analQueen.setX(300);
        analQueen.setY(500);
        analQueen.setFont(Font.font("Verdana",20));
        analQueen.setFill(Color.BLACK);
        root.getChildren().add(analQueen);

        Text analHearts = new Text();
        analHearts.setX(100);
        analHearts.setY(530);
        analHearts.setFont(Font.font("Verdana",20));
        analHearts.setFill(Color.BLACK);
        root.getChildren().add(analHearts);

        Text analSumFace = new Text();
        analSumFace.setX(300);
        analSumFace.setY(530);
        analSumFace.setFont(Font.font("Verdana",20));
        analSumFace.setFill(Color.BLACK);
        root.getChildren().add(analSumFace);

        Button analyzeButton = new Button("Analyze the hand");
        analyzeButton.setLayoutX(700);
        analyzeButton.setLayoutY(250);
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