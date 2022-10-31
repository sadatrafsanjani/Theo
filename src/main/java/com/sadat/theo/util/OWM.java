package com.sadat.theo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OWM {
    
    private final static Logger LOGGER = Logger.getLogger(OWM.class.getName());
    
    //DHAKA: 1185241
    
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=Dhaka,BD&units=metric&appid=8a0755663de1f6a70015fe6e34dc1bba";
    
    private final static OkHttpClient CLIENT = new OkHttpClient();
    
    public static String getForecast(){
    
        Request request = new Request.Builder()
                .cacheControl(new CacheControl.Builder().noCache().build())
                .url(URL).build();
        String feed = "";

        try {

            Response response = CLIENT.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            feed = response.body().string();

        } catch (IOException e) {
            
            LOGGER.log(Level.SEVERE, e.getMessage());
        }

        return feed;
        
    }
    
    public static Image fetchIcon(String url) {

        Request request = new Request.Builder()
                .cacheControl(new CacheControl.Builder().noCache().build())
                .url(url).build();

        Image image = null;

        try {
            Response response = CLIENT.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            } else {
                InputStream inputStream = response.body().byteStream();
                image = ImageProcessing.processImage(new Image(inputStream, 512, 512, true, true));
                
            }

        } catch (IOException e) {
            
            LOGGER.log(Level.SEVERE, e.getMessage());
        }

        return image;
    }
    
}
