package com.sadat.theo.controller;

import com.sadat.theo.util.OWM;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherController implements Initializable {

    private final static Logger LOGGER = Logger.getLogger(WeatherController.class.getName());

    @FXML
    private Label temperatureLabel, statusLabel, humidityLabel, windLabel, cityLabel;
    @FXML
    private ImageView iconView;
    private JSONObject main, wind;
    private JSONArray weather;
    private Image icon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                getFeed();
                LOGGER.log(Level.INFO, "Updated...");
            }
        };

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.MINUTES);
    }

    @FXML
    public void exitAction(ActionEvent event) {

        System.exit(0);
        Platform.exit();
    }

    private void getFeed() {

        Task task = new Task() {

            @Override
            protected void succeeded() {

                temperatureLabel.setText(main.get("temp") + "°c");
                humidityLabel.setText(main.get("humidity") + "%");
                windLabel.setText(wind.get("speed") + " kmph");
                statusLabel.setText(String.valueOf(weather.getJSONObject(0).get("description")));
                cityLabel.setText("Dhaka, BD");
                iconView.setImage(icon);
            }

            @Override
            protected Void call() {

                JSONObject object = new JSONObject(OWM.getForecast());
                main = object.getJSONObject("main");
                wind = object.getJSONObject("wind");
                weather = object.getJSONArray("weather");
                icon = OWM.fetchIcon("http://openweathermap.org/img/wn/" + weather.getJSONObject(0).get("icon") + "@2x.png");
                getCurrentTime();
                
                return null;
            }
        };

        Thread t = new Thread(task);
        t.start();
    }
    
    private void getCurrentTime(){
    
        
    }

}
