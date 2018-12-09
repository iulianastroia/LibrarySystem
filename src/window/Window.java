package window;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Window {
    static public void showWindow(Label windowLabel, Image windowIcon) {
        BorderPane borderPane = new BorderPane();
        windowLabel.setWrapText(true);

        //Make ok button
        Button okButton = new Button("OK!");
        HBox hBox = new HBox();
        hBox.getStylesheets().add("/appearance/ButtonsAppearance.css");
        hBox.getChildren().add(okButton);
        hBox.setAlignment(Pos.CENTER);


        borderPane.setBottom(hBox);
        borderPane.setCenter(windowLabel);

        // New scene
        Scene windowScene = new Scene(borderPane, 200, 150);
        windowLabel.prefHeightProperty().bind(windowScene.heightProperty());


        // New window (Stage)
        Stage windowStage = new Stage();
//        windowStage.setScene(windowScene);

        windowScene.getStylesheets().add("/appearance/StageAppearance.css");
        windowStage.setScene(windowScene);


        okButton.setOnAction(e -> windowStage.close());

        //Adding the icon to the window
        windowStage.getIcons().add(windowIcon);
        windowStage.showAndWait();
    }


}
