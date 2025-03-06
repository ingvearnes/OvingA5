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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}