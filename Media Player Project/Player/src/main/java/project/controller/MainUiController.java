package project.controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;

public class MainUiController {
    @FXML
    public MediaView mediaView;

    @FXML
    public Slider volumeSlider;

    @FXML
    public Slider timelineSlider;

    private MediaPlayer mediaPlayer;

    private String filePath;

    @FXML
    public void openFileBtnClicked(ActionEvent actionEvent) {

        FileChooser fileChooser=new FileChooser();
        FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Select a File (*.mp4)","*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file=fileChooser.showOpenDialog(null);
        filePath=file.toURI().toString();

        if(filePath!=null){
            Media media=new Media(filePath);
            mediaPlayer=new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);

            //Setting the total duration of media file selected
            mediaPlayer.setOnReady(()->{
                Duration totalDuration=mediaPlayer.getTotalDuration();
                timelineSlider.setMax(totalDuration.toSeconds());
            });


            //Setting media size to window dimensions
            DoubleProperty width=mediaView.fitWidthProperty();
            DoubleProperty height=mediaView.fitHeightProperty();

            width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));

            //Adding volume slider functionality
            volumeSlider.setValue(mediaPlayer.getVolume()*100);
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volumeSlider.getValue()/100);
                }
            });

            //Timeline slider
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
                    timelineSlider.setValue(newValue.toSeconds());
                }
            });

            //Seek to the correct position when the timeline slider is clicked
            timelineSlider.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(Duration.seconds(timelineSlider.getValue()));
                }
            });

            mediaPlayer.play();
        }
    }

    //Assigning actions
    @FXML
    public void playBtnClicked(ActionEvent actionEvent) {
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }

    @FXML
    public void pauseBtnClicked(ActionEvent actionEvent) {
        mediaPlayer.pause();
    }

    @FXML
    public void stopBtnClicked(ActionEvent actionEvent) {
        mediaPlayer.stop();
    }

    @FXML
    public void slowerVideoBtnClicked(ActionEvent actionEvent) {
        mediaPlayer.setRate(0.5);
    }

    @FXML
    public void slowVideoBtnClicked(ActionEvent actionEvent) {
        mediaPlayer.setRate(0.75);
    }

    @FXML
    public void fastVideoBtnClicked(ActionEvent actionEvent) {
        mediaPlayer.setRate(1.5);
    }

    @FXML
    public void fasterVideoBtnClicked(ActionEvent actionEvent) {
        mediaPlayer.setRate(2);
    }

    @FXML
    public void exitBtnClicked(ActionEvent actionEvent) {
        System.exit(0);
    }
}
