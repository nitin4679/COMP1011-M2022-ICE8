package ca.georgiancollege.comp1011m2022ice8;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;

public class FileChooserController
{
    private String activeFilePath = "vector2d.txt";
    @FXML
    private BorderPane borderPane;

    @FXML
    private Button OpenFileButton;
    @FXML
    private Button SaveFileButton;

    @FXML
    private TextArea textArea;


    private void SaveToFile()
    {
        try(Formatter output = new Formatter(activeFilePath) )
        {
            ArrayList<Vector2D> vector2DData = DBManager.Instance().readVectorTable();

            vector2DData.sort(null);


            for (var vector2d : vector2DData)
            {
                // serialize our Vector2D data
                output.format("%.1f %.1f%n", vector2d.getX(), vector2d.getY());
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void SaveJSONDataToFile()
    {
        Gson gson = new Gson();

        try
                (
                        Formatter output = new Formatter(activeFilePath)
                )
        {
            ArrayList<Vector2D> vectors = DBManager.Instance().readVectorTable();

            vectors.sort(null);

            System.out.println(gson.toJson(vectors.toArray()));

            output.format("%s", gson.toJson(vectors.toArray()));

        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void ReadFromFile()
    {
        try(Scanner input = new Scanner(Paths.get(activeFilePath)))
        {
            while(input.hasNext())
            {
                System.out.printf("(%.1f, %.1f)%n", input.nextFloat(), input.nextFloat());
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void ReadJSONDataFromFile()
    {
        Gson gson = new Gson();

        try
                (
                        Scanner input = new Scanner(Paths.get(activeFilePath))
                )
        {
            // Create an empty container of vectors
            ArrayList<Vector2D> vectors = new ArrayList<Vector2D>();

            // Deserialization
            Type ArrayListOfTypeVector2D = new TypeToken<ArrayList<Vector2D>>(){}.getType();

            while(input.hasNext())
            {
                vectors = gson.fromJson(input.next(), ArrayListOfTypeVector2D);
            }

            // test
            for (var vector: vectors)
            {
                System.out.println(vector);
            }

        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void ShowFileDialog(FileDialogType type)
    {
        // configure dialog (modal window) allowing selection of a file
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"),
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("All Files (*.*)", "*.*")
        );


        // display files in folder from which the app was launched
        fileChooser.setInitialDirectory(new File("."));

        // display the FileChooser Dialog
        File file = null;

        switch (type)
        {
            case OPEN:
                fileChooser.setTitle("Open File");
                file = fileChooser.showOpenDialog(borderPane.getScene().getWindow());
                // process selected Path or display a message
                if(file != null)
                {
                    activeFilePath = file.getName();
                    //ReadFromFile();
                    ReadJSONDataFromFile();
                }

                else
                {
                    textArea.setText("Open File");
                }
                break;
            case SAVE:
                fileChooser.setTitle("Save File");
                fileChooser.setInitialFileName("vector2d.json");
                file = fileChooser.showSaveDialog(borderPane.getScene().getWindow());
                // process selected Path or display a message
                if(file != null)
                {
                    activeFilePath = file.getName();
                    //SaveToFile();
                    SaveJSONDataToFile();
                }

                else
                {
                    textArea.setText("Save File");
                }
                break;
        }

    }

    @FXML
    void OpenFileButtonClicked(ActionEvent event)
    {
        ShowFileDialog(FileDialogType.OPEN);
    }

    @FXML
    void SaveFileButtonClicked(ActionEvent event)
    {
        ShowFileDialog(FileDialogType.SAVE);
    }
}
