package com.sadat.theo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {

    private final static Logger LOGGER = Logger.getLogger(MainApp.class.getName());

    @Override
    public void start(Stage stage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene-1.fxml"));
            Scene scene = new Scene(root);
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            double x = bounds.getMinX() + (bounds.getWidth() - scene.getWidth() - 270);
            double y = bounds.getMinY() + (bounds.getHeight() - scene.getHeight() - 720);
            stage.setX(x);
            stage.setY(y);
            stage.getIcons().add(new Image("/img/logo.png"));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {

            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
