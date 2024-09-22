package se.fluff.aptusviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.util.Properties;
import java.util.TimeZone;

public class AptusViewerApplication extends Application {

    public static final Properties settings = new Properties();

    @Override
    public void start(Stage stage) throws IOException {

        TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");
        TimeZone.setDefault(utcTimeZone);

        FXMLLoader fxmlLoader = new FXMLLoader(AptusViewerApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 880, 600);
        stage.setTitle("AptusViewer");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        try(InputStream is = new FileInputStream("aptusviewer.properties")) {
            settings.load(is);
        } catch (IOException e) {
            System.err.println("Could not find/read aptusviewer.properties");
            throw new RuntimeException(e);
        }

        Security.setProperty("jdk.tls.disabledAlgorithms", "");
        launch();
    }
}