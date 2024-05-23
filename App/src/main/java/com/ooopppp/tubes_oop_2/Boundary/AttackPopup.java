package com.ooopppp.tubes_oop_2.Boundary;

import com.ooopppp.tubes_oop_2.Entity.Card;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AttackPopup extends Stage {
    public AttackPopup(MainView parent) {
        initModality(Modality.APPLICATION_MODAL); // This popup must be closed before you can interact with the owner again
        initStyle(StageStyle.UNDECORATED); // Remove window decorations

        // Load the GIF
        Image gif = new Image(getClass().getResourceAsStream("/com/ooopppp/tubes_oop_2/img/BearAttack.gif"));
        ImageView imageView = new ImageView(gif);


        // Text field below the image
        Label label = new Label("Wah, ada serangan beruang!");
        label.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: 700;-fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: red");

        // Create a layout and add the ImageView, label, and Button to it
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(imageView, label);
        layout.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-border-color: #FFEFC8; -fx-border-width: 5px; -fx-background-color: #E99C1E");

        Scene scene = new Scene(layout, 600, 500); // Adjusted size
        scene.setFill(Color.TRANSPARENT);

        // Load CSS
        URL css = getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        } else {
            System.out.println("CSS Resource not found.");
        }

        // Set the scene
        setScene(scene);
        this.initStyle(StageStyle.TRANSPARENT);

        // Automatically close the popup after 10 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> close());
            }
        }, 3000);
    }

    public static void showView(MainView parent){
        AttackPopup attackPopup = new AttackPopup(parent);
        attackPopup.showAndWait();
    }
}
