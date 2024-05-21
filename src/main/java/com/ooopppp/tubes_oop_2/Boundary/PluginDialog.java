    package com.ooopppp.tubes_oop_2.Boundary;

    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.control.TextField;
    import javafx.scene.layout.GridPane;
    import javafx.scene.layout.HBox;
    import javafx.scene.layout.VBox;
    import javafx.stage.Modality;
    import javafx.stage.Stage;
    import javafx.stage.StageStyle;
    import javafx.stage.FileChooser;
    import java.io.*;


    public class PluginDialog {
        private Stage dialogStage;

        public PluginDialog(Stage owner) {
            this.dialogStage = new Stage();
            initializeDialog(owner);
            VBox dialogVBox = new VBox(0);
            dialogVBox.setPadding(new Insets(10));
            dialogVBox.setStyle("-fx-background-color: #FFEFC8; -fx-font-family: 'Courier'; -fx-border-color: #AA6039; -fx-border-width: 15; -fx-border-style: solid; -fx-border-radius: 15; -fx-background-radius: 25;");
            setupComponents(dialogVBox);

            Scene dialogScene = new Scene(dialogVBox, 720, 450);
            dialogScene.getStylesheets().add(getClass().getResource("/com/ooopppp/tubes_oop_2/css/style.css").toExternalForm());
            dialogScene.setFill(null);
            dialogStage.setScene(dialogScene);
        }

        public static void showPluginDialog(Stage owner) {
            PluginDialog pluginLoaderDialog = new PluginDialog(owner);
            pluginLoaderDialog.showDialog();
        }

        private void initializeDialog(Stage owner) {
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(owner);
            dialogStage.initStyle(StageStyle.TRANSPARENT);
            dialogStage.setTitle("Plugin");
        }

        private void setupComponents(VBox dialogVBox) {
            Button closeButton = new Button("X");
            closeButton.getStyleClass().add("x-symbol");
            closeButton.setOnAction(event -> dialogStage.close());

            closeButton.setOnMousePressed(event -> {
                closeButton.setTranslateY(1);
            });

            closeButton.setOnMouseReleased(event -> {
                closeButton.setTranslateY(0);
            });

            VBox closecontainer = new VBox(closeButton);
            closecontainer.setAlignment(Pos.TOP_RIGHT);

            Label titleLabel = new Label("Plugin");
            titleLabel.setStyle("-fx-font-family: 'Courier'; -fx-font-size: 40; -fx-text-fill: #000000; -fx-font-weight: 900;");
            HBox titleBox = new HBox(titleLabel);
            titleBox.setAlignment(Pos.TOP_CENTER);
            titleBox.setPadding(new Insets(0, 0, 50, 0));

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(30);
            grid.setPadding(new Insets(0, 0, 0, 90));

            Label folderLabel = new Label("File plugin   :   No File Uploaded");
            folderLabel.setMinSize(430, 45);
            folderLabel.setMaxSize(430, 45);
            folderLabel.setStyle("-fx-font-family: 'Courier';-fx-text-fill: #000000; -fx-font-size: 20; -fx-font-weight: 900;");

            Button chooseFileButton = new Button("Choose File");
            chooseFileButton.setMaxSize(150, 45);
            chooseFileButton.setMinSize(150, 45);
            chooseFileButton.setStyle("-fx-font-family: 'Courier';-fx-text-fill: #000000; -fx-background-color: #DBE056; -fx-font-size: 20; -fx-background-radius: 15; -fx-font-weight: 900;");
            chooseFileButton.setOnAction(e -> handleChoose(folderLabel));

            grid.add(chooseFileButton, 1, 0);
            grid.add(folderLabel, 2, 0);

            Label feedbackLabel = new Label();
            HBox feedbackBox = new HBox(feedbackLabel);
            feedbackBox.setAlignment(Pos.CENTER);

            Button uploadButton = new Button("Upload");
            uploadButton.setStyle("-fx-font-family: 'Courier'; -fx-text-fill: #DBE056; -fx-background-color: #5B311C; -fx-font-size: 30; -fx-background-radius: 15; -fx-font-weight: 900;");
            uploadButton.setMaxSize(170, 65);
            uploadButton.setMinSize(170, 65);
            HBox uploadButtonBox = new HBox(uploadButton);
            uploadButtonBox.setAlignment(Pos.CENTER);
            uploadButtonBox.setPadding(new Insets(50, 0, 25, 0));

            dialogVBox.getChildren().addAll(closecontainer, titleBox, grid, uploadButtonBox, feedbackBox);
        }

        private void handleChoose(Label folderLabel){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File selectedFile = fileChooser.showOpenDialog(dialogStage);
            if (selectedFile != null) {
                folderLabel.setText("File plugin   :   " + selectedFile.getName());
                System.out.println("File selected: " + selectedFile.getAbsolutePath());
            }
        }

        public void showDialog() {
            dialogStage.showAndWait();
        }
    }
