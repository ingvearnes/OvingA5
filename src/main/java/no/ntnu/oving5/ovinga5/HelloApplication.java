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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.util.Duration;

public class HelloApplication extends Application {

    private DeckOfCards deckOfCards = new DeckOfCards();
    private String flush;
    private String queen;
    private String hearts;
    private String sumCard;

    /**
     * Method that sets up and runs the javaFX
     */
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
        Button buttonDeal = new Button("Deal cards!");
        buttonDeal.setLayoutX(700);
        buttonDeal.setLayoutY(200);
        buttonDeal.setOnAction(event -> {
            Collection<PlayingCard> playingCards = deckOfCards.dealHand(5);
            List<String> pathCard = FXdealHand(playingCards);

            this.flush = FXcheckFlush(playingCards);
            this.queen = FXcheckQueenOfCard(playingCards);
            this.hearts = deckOfCards.checkHearts(playingCards);
            this.sumCard = deckOfCards.stringSumFace(playingCards);

            for(int i = 0; i < pathCard.size(); i++){
                String imagePath = pathCard.get(i);

                //Image
                Image image = new Image(imagePath);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(70);
                imageView.setFitHeight(100);
                imageView.setX((200*i-(100*i))+100);
                imageView.setY(200);
                root.getChildren().add(imageView);

                //Animation
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(imageView);
                transition.setDuration(Duration.seconds(1));
                transition.setFromX(-600);
                transition.setToX(20);
                transition.play();
            }
        });
        root.getChildren().add(buttonDeal);


        //analysis
        Text textAnalysisFlush = new Text("Flush: ");
        textAnalysisFlush.setX(40);
        textAnalysisFlush.setY(500);
        textAnalysisFlush.setFont(Font.font("Verdana",15));
        textAnalysisFlush.setFill(Color.BLACK);
        root.getChildren().add(textAnalysisFlush);
        Text analysisFlush = new Text("Flush");
        analysisFlush.setX(100);
        analysisFlush.setY(500);
        analysisFlush.setFont(Font.font("Verdana",15));
        analysisFlush.setFill(Color.BLACK);
        root.getChildren().add(analysisFlush);

        Text textAnalysisQueen = new Text("Queen of Cards:");
        textAnalysisQueen.setX(300);
        textAnalysisQueen.setY(500);
        textAnalysisQueen.setFont(Font.font("Verdana",15));
        textAnalysisQueen.setFill(Color.BLACK);
        root.getChildren().add(textAnalysisQueen);
        Text analysisQueen = new Text("Queen of Cards");
        analysisQueen.setX(430);
        analysisQueen.setY(500);
        analysisQueen.setFont(Font.font("Verdana",15));
        analysisQueen.setFill(Color.BLACK);
        root.getChildren().add(analysisQueen);

        Text textAnalysisHearts = new Text("Hearts:");
        textAnalysisHearts.setX(40);
        textAnalysisHearts.setY(530);
        textAnalysisHearts.setFont(Font.font("Verdana",15));
        textAnalysisHearts.setFill(Color.BLACK);
        root.getChildren().add(textAnalysisHearts);
        Text analysisHearts = new Text("Hearts");
        analysisHearts.setX(100);
        analysisHearts.setY(530);
        analysisHearts.setFont(Font.font("Verdana",15));
        analysisHearts.setFill(Color.BLACK);
        root.getChildren().add(analysisHearts);

        Text textAnalysisSumFace = new Text("Sum of faces: ");
        textAnalysisSumFace.setX(300);
        textAnalysisSumFace.setY(530);
        textAnalysisSumFace.setFont(Font.font("Verdana",15));
        textAnalysisSumFace.setFill(Color.BLACK);
        root.getChildren().add(textAnalysisSumFace);
        Text analysisSumFace = new Text("Sum");
        analysisSumFace.setX(415);
        analysisSumFace.setY(530);
        analysisSumFace.setFont(Font.font("Verdana",15));
        analysisSumFace.setFill(Color.BLACK);
        root.getChildren().add(analysisSumFace);

        Button analyzeButton = new Button("Analyze the hand");
        analyzeButton.setLayoutX(700);
        analyzeButton.setLayoutY(250);
        analyzeButton.setOnAction(event -> {
            analysisFlush.setText(this.flush);
            analysisQueen.setText(this.queen);
            analysisHearts.setText(this.hearts);
            analysisSumFace.setText(this.sumCard);
        });
        root.getChildren().add(analyzeButton);


        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that takes in a collection of cards and generates the path of the associated
     * .png from the generateCardImage() method. Must use for-loop later to get each path.
     * @param playingCards collection of playing cards
     * @return list of filepath of card-png
     */
    public List<String> FXdealHand(Collection<PlayingCard> playingCards){
        List<String> paths = new ArrayList<>(); //ikke initialize noe med NULL, da får du nullpointerexeption

        for(PlayingCard card : playingCards){
            String imagePath = card.generateCardImage(card.getSuit(), card.getFace());
            paths.add(imagePath);
        }
        return paths;
    }


    /**
     * Method that checks if the cards collectively gives flush, i.e is 1 to 5.
     * @param playingCards Collection of playing cards
     * @return string for flush or not flush
     */
    public String FXcheckFlush(Collection<PlayingCard> playingCards){
        if(deckOfCards.checkFlush(playingCards)){
            return "Flush";
        }
        return "No flush";
    }

    /**
     * Method that checks if the cards gives queen of something. I.e. 5 of one of the picture
     * cards.
     * @param playingCards collection of playing cards.
     * @return string for queen or no queen of something.
     */
    public String FXcheckQueenOfCard(Collection<PlayingCard> playingCards){
        if(deckOfCards.checkQueenOfCard(playingCards)){
            return "Queen of something";
        }
        return "No Queen";
    }

    /**
     * Main method to start javaFX.
     */
    public static void main(String[] args) {
        launch();
    }
}