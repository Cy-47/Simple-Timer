package top.udcr.timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;


import java.net.URL;
import java.util.ResourceBundle;

public class StopwatchController implements Initializable {
    @FXML
    private Label time;

    @FXML
    private Button startButton;

    long thisTime, lastTime, recordedTime = 0;
    boolean recording = false;

    @FXML
    protected void onStartButtonClick() {
        recording = !recording;
        if (recording) startButton.setText("暂停");
        else startButton.setText("开始");
        thisTime = lastTime = System.currentTimeMillis();
    }

    @FXML
    protected void onClearButtonClick() {
        recordedTime = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(-1);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), (ActionEvent) ->
        {
            if (recording) {
                thisTime = System.currentTimeMillis();
                recordedTime += thisTime - lastTime;
                lastTime = thisTime;
            }
            time.setText(String.format("%02d:%02d:%02d.%03d",
                    recordedTime / 3600000,
                    (recordedTime / 60000) % 60,
                    (recordedTime / 1000) % 60,
                    recordedTime % 1000)
            );
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}