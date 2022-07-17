package ca.georgiancollege.comp1011m2022ice8;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class  Main extends Application {
    @Override
    public void start(Stage stage) throws IOException
    {
        // serialization
        Vector2D vector = new Vector2D(0, 10.0f, 20.0f);
        Gson gson = new GsonBuilder().create();
        String vectorString = gson.toJson(vector);
        System.out.println(vectorString);

        // deserialization
        Vector2D vector2 = gson.fromJson(vectorString, Vector2D.class);
        System.out.println(vector2);


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("file-chooser.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Vector2D File I/O with JSON");
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}   