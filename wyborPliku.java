import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


public class wyborPliku extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wybór pliku");

        FileChooser fileChooser = new FileChooser();

        Button button = new Button("Wybierz plik");
        button.setOnAction(e -> {
            java.io.File selectedFile = fileChooser.showOpenDialog(primaryStage);
        });

        VBox vBox = new VBox(button);
        Scene scene = new Scene(vBox, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
