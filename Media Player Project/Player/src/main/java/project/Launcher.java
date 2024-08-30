package project;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent baseNode= FXMLLoader.load(this.getClass().getResource("/ui/mainNode.fxml"));

        Scene scene=new Scene(baseNode);

        stage.setTitle("Player");

        //Setting the window fullscreen if double clicked
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent doubleClicked) {
                if(doubleClicked.getClickCount()==2){
                    stage.setFullScreen(true);
                }
            }
        });
        stage.centerOnScreen();
        stage.setScene(scene);

        stage.show();

    }
}
