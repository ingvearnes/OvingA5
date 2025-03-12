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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private DeckOfCards deckOfCards = new DeckOfCards();
    private String flush;
    private String queen;
    private String hearts;
    private String sumCard;

    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root,900,600, Color.LIGHTBLUE);
        stage.setTitle("Cardgame");

        //Background
        Rectangle background = new Rectangle(600,400);
        background.setX(50);
        background.setY(50);
        background.setFill(Color.BLACK);
        root.getChildren().add(background);


        //Card-deck
        Text cardDeckText = new Text("Cards Coming...");
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
        Text textAnalFlush = new Text("Flush: ");
        textAnalFlush.setX(40);
        textAnalFlush.setY(500);
        textAnalFlush.setFont(Font.font("Verdana",15));
        textAnalFlush.setFill(Color.BLACK);
        root.getChildren().add(textAnalFlush);
        Text analFlush = new Text("Flush");
        analFlush.setX(100);
        analFlush.setY(500);
        analFlush.setFont(Font.font("Verdana",15));
        analFlush.setFill(Color.BLACK);
        root.getChildren().add(analFlush);

        Text textAnalQueen = new Text("Queen of Cards:");
        textAnalQueen.setX(300);
        textAnalQueen.setY(500);
        textAnalQueen.setFont(Font.font("Verdana",15));
        textAnalQueen.setFill(Color.BLACK);
        root.getChildren().add(textAnalQueen);
        Text analQueen = new Text("Queen of Cards");
        analQueen.setX(430);
        analQueen.setY(500);
        analQueen.setFont(Font.font("Verdana",15));
        analQueen.setFill(Color.BLACK);
        root.getChildren().add(analQueen);

        Text textAnalHearts = new Text("Hearts:");
        textAnalHearts.setX(40);
        textAnalHearts.setY(530);
        textAnalHearts.setFont(Font.font("Verdana",15));
        textAnalHearts.setFill(Color.BLACK);
        root.getChildren().add(textAnalHearts);
        Text analHearts = new Text("Hearts");
        analHearts.setX(100);
        analHearts.setY(530);
        analHearts.setFont(Font.font("Verdana",15));
        analHearts.setFill(Color.BLACK);
        root.getChildren().add(analHearts);

        Text textAnalSumFace = new Text("Sum of faces: ");
        textAnalSumFace.setX(300);
        textAnalSumFace.setY(530);
        textAnalSumFace.setFont(Font.font("Verdana",15));
        textAnalSumFace.setFill(Color.BLACK);
        root.getChildren().add(textAnalSumFace);
        Text analSumFace = new Text("Sum");
        analSumFace.setX(415);
        analSumFace.setY(530);
        analSumFace.setFont(Font.font("Verdana",15));
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