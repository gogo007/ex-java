package macadambumper;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import macadambumper.controller.Playground;
import macadambumper.model.Player;

/**
 *
 * @author User
 */
public class Game extends Application {

    private Player itsPlayer;
    private AnchorPane root;
    private Scene scene;

    public Game(){

    }
    
    public void startGame(){
        
    }
    
    public boolean isFinished(){
        return false;
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/macadambumper/view/MacadamView.fxml"));
        scene = new Scene(root,1024,680);
        scene.getRoot().requestFocus();
        primaryStage.setTitle("Macadam Bumper");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/macadambumper/images/bille.png"));
        primaryStage.show();
    }
    @Override
    public void stop(){
        Playground.stopTimer();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        launch(args);
    }
    
}
