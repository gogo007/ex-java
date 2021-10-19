package macadambumper.controller;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import macadambumper.Game;
import macadambumper.model.*;

/**
 *
 * @author User
 */
public class Playground implements Initializable{

    private Game itsGame;
    private int itsIdPlayground;
    private ArrayList<Shape> gameElements;
    private ArrayList<PhysicalElement> gameClassElements;
    private SimpleIntegerProperty ballsCounter;
    private SimpleIntegerProperty scoreCounter;
    private boolean isInGame = false;
    private Player player1;
    private Alert alertHelp;
    ArrayList<Polygon> lPoly = new ArrayList();
    int k = 0; //For Timer
    static Timer timerLight;
    static TimerTask loopLight;
    int timerCounter;

    Image bumperGif = new Image("/macadambumper/images/bumper3.gif");
    Image bumperBase = new Image("/macadambumper/images/bumper.png");
    Image trianglegifCenterLeftStorm = new Image("/macadambumper/images/gifCenterLeftStorm.gif");
    Image triangleCenterLeftStormBase = new Image("/macadambumper/images/centerLeftStorm.png");
    Image trianglegifCenterRightStorm = new Image("/macadambumper/images/gifCenterRightStorm.gif");
    Image triangleCenterRightStormBase = new Image("/macadambumper/images/centerRightStorm.png");
    Image trianglegifLeftStorm = new Image("/macadambumper/images/gifLeftStorm.gif");
    Image triangleLeftStormBase = new Image("/macadambumper/images/leftStorm.png");
    Image trianglegifTopStorm = new Image("/macadambumper/images/gifTopStorm.gif");
    Image triangleTopStormBase = new Image("/macadambumper/images/topStorm.png");

    private Media mediaIntro;
    private MediaPlayer mediaPlayer;
    private Media mediaLauncher;
    private MediaPlayer mediaPlayerLauncher;
    private Media mediaInGame;
    private MediaPlayer mediaPlayerInGame;
    private Media mediaBumper;
    private MediaPlayer mediaPlayerBumper;
    private Media mediaFlipperDroit;
    private MediaPlayer mediaPlayerFlipperDroit;
    private Media mediaFlipperGauche;
    private MediaPlayer mediaPlayerFlipperGauche;

    //PhysicalElement
    private Ball itsBall;
    private Flipper itsLeftFlipper;
    private Flipper itsRightFlipper;
    private Flipper itsMidLFlipper;
    private Flipper itsMidRFlipper;
    private Edge itsYLimit;
    private Edge itsRedge;
    private Edge itsLedge;
    private Edge itsTedge;
    private Edge itsDedge;
    private Edge itsLaunchEdge;
    private Edge itsTopRBorderV;
    private Edge itsTopRBorderH;
    private Edge itsFlipBorder;
    private Edge itsBorderFlip;
    private Edge itsBotLauncherEdge;
    private Edge itsBotDiagoLauncherEdge;
    private Edge itsMidLauncherEdge;
    private Edge itsTopDiagoLauncherEdge;
    private Edge itsLBotEdge;
    private Edge itsLTopDiagoEdge;
    private Edge itsLDiagoEdge;
    private Edge itsLpl1;
    private Edge itsLpl2;
    private Edge itsLpl3;
    private Edge itsRpl1;
    private Edge itsRpl2;
    private Edge itsRpl3;
    private Edge itsBpl1;
    private Edge itsBpl2;
    private Edge itsBpl3;
    private Edge itsTpl1;
    private Edge itsTpl2;
    private Edge itsTpl3;
    private Edge itsNul1;
    private Edge itsNul2;
    private Bumper itsTopBumper;
    private Bumper itsTopLBumper;
    private Bumper itsTopRBumper;
    private Bumper itsMidLBumper;
    private Bumper itsMidRBumper;
    private Launcher itsLauncher;

    //Graphic Element
    @FXML    private AnchorPane anchorP;

    //LABELS
    @FXML    private Label lBalls;
    @FXML    private Label lScore1;
    @FXML    private Label lHighscore;
    @FXML    private Label lName;

    //RECTANGLES
    @FXML    private Rectangle lFlipper;
    @FXML    private Rectangle rFlipper;
    @FXML    private Rectangle launcher;
    @FXML    private Rectangle midLFlipper;
    @FXML    private Rectangle midRFlipper;
    @FXML    private Rectangle rectSpikes;

    //LINES
    @FXML    private Line yLimit;
    @FXML    private Line rEdge;
    @FXML    private Line tEdge;
    @FXML    private Line dEdge;
    @FXML    private Line lEdge;
    @FXML    private Line launchEdge;
    @FXML    private Line topRBorderV;
    @FXML    private Line topRBorderH;
    @FXML    private Line flipBorder;
    @FXML    private Line borderFlip;
    @FXML    private Line botLauncherEdge;
    @FXML    private Line botDiagoLauncherEdge;
    @FXML    private Line midLauncherEdge;
    @FXML    private Line topDiagoLauncherEdge;
    @FXML    private Line lBotEdge;
    @FXML    private Line lTopDiagoEdge;
    @FXML    private Line lDiagoEdge;
    @FXML    private Line lpl1;
    @FXML    private Line lpl2;
    @FXML    private Line lpl3;
    @FXML    private Line rpl1;
    @FXML    private Line rpl2;
    @FXML    private Line rpl3;
    @FXML    private Line bpl1;
    @FXML    private Line bpl2;
    @FXML    private Line bpl3;
    @FXML    private Line tpl1;
    @FXML    private Line tpl2;
    @FXML    private Line tpl3;
    @FXML    private Line nul1;
    @FXML    private Line nul2;

    //CIRCLES
    @FXML    private Circle ballP;
    @FXML    private Circle topBumper;
    @FXML    private Circle topLBumper;
    @FXML    private Circle topRBumper;
    @FXML    private Circle midLBumper;
    @FXML    private Circle midRBumper;
    @FXML    private Circle lpc1;
    @FXML    private Circle lpc2;
    @FXML    private Circle lpc3;
    @FXML    private Circle rpc1;
    @FXML    private Circle rpc2;
    @FXML    private Circle rpc3;
    @FXML    private Circle bpc1;
    @FXML    private Circle bpc2;
    @FXML    private Circle bpc3;
    @FXML    private Circle tpc1;
    @FXML    private Circle tpc2;
    @FXML    private Circle tpc3;
    @FXML    private Circle nuc1;
    @FXML    private Circle nuc2;
    @FXML    private Circle nuc3;
    @FXML    private Circle cTopLauncher;

    //POLYGONS FOR GIFS
    @FXML    private Polygon t1;
    @FXML    private Polygon t2;
    @FXML    private Polygon t3;
    @FXML    private Polygon t4;
    @FXML    private Polygon t5;
    @FXML    private Polygon t6;
    @FXML    private Polygon t7;
    @FXML    private Polygon t8;
    @FXML    private Polygon t9;
    @FXML    private Polygon t10;
    @FXML    private Polygon t11;
    @FXML    private Polygon t12;
    @FXML    private Polygon t13;
    @FXML    private Polygon p1;
    @FXML    private Polygon p2;
    @FXML    private Polygon p3;
    @FXML    private Polygon p4;

    @FXML    private ProgressBar progressBar;
    @FXML    private Slider slider;
    @FXML    private Button helpButton;
    @FXML    private RadioButton buttonEN;
    @FXML    private RadioButton buttonFR;
    ToggleGroup group = new ToggleGroup();


    static Timer itsTimer = new Timer();
    TimerTask gameloop;
    double soundM = 0.05; //for slider

    @FXML
    private void showAlertWithHeaderText(){
        this.alertHelp = new Alert(Alert.AlertType.INFORMATION);
        alertHelp.setTitle("Help");
        alertHelp.setHeaderText("How to play :");
        ((Stage)alertHelp.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/macadambumper/images/help.png"));
        if(buttonEN.isSelected()){
            alertHelp.setContentText("Welcome to the Macadam Bumper !\n" +
                    "The goal is simple : You have 3 balls to get the highest score you can by hitting the bumpers and the flippers with the ball !\n\n" +
                    "A collision with a bumper adds 300 points to your score and a collision with an other polygon adds 100 points. \n\n" +
                    "To play, you have to press the down-arrow key of your keyboard to launch the ball. You have to use the left and right arrows to make flippers go up.\n\n" +
                    "This is it, enjoy the game !");
            alertHelp.showAndWait();
        }
        else{
            alertHelp.setContentText("Bienvenue dans le Macadam Bumper !\n" +
                    "Le but est simple : vous avez 3 balles pour atteindre le meilleur score, vous pouvez rebondir sur les Bumpers et les flippers avec la balle !\n\n" +
                    "Une collision avec un Bumper ajoute 300 points à votre score et une collision avec un polygone ajoute 100 points \n\n" +
                    "Pour jouer, vous devez presser la flèche vers le bas de votre clavier pour lancer la ball. Vous devez utiliser les flèches droites et gauches pour faire lever les flippers\n\n" +
                    "Maintenant, profitez du jeu !");
            alertHelp.showAndWait();
        }
    }

    @FXML
    private void keyPressed(KeyEvent ke){
        double aDirX = itsBall.getDirX();
        double aDirY = itsBall.getDirY();

        if (ke.getCode() == KeyCode.LEFT){
            itsLeftFlipper.setIsUp(true);
            itsMidRFlipper.setIsUp(true);
            mediaPlayerFlipperGauche.seek(Duration.millis(0));
            mediaPlayerFlipperGauche.play();
        }

        if (ke.getCode() == KeyCode.RIGHT){
            itsRightFlipper.setIsUp(true);
            itsMidLFlipper.setIsUp(true);
            mediaPlayerFlipperDroit.seek(Duration.millis(0));
            mediaPlayerFlipperDroit.play();
        }

        if (ke.getCode() == KeyCode.DOWN){
            itsLauncher.setRising(false);
            mediaPlayer.stop();
            mediaPlayerLauncher.play();
            if(itsLauncher.getPower() > 240){
                itsLauncher.setRising(true);
            }
            try{
                    timerLight.schedule(loopLight,5,150);
            }
            catch(IllegalStateException e){
                //System.err.println("Timer déjà lancé");
            }
            
        }
        
        if (ke.getCode() == KeyCode.M){
            double sound = slider.getValue();
            
            if(sound != 0){
                soundM = sound;
                slider.setValue(0);
            }
            else {
                slider.setValue(soundM);
            }
        }
    }

    @FXML
    private void keyReleased(KeyEvent ke){
        if (ke.getCode() == KeyCode.LEFT){
            itsLeftFlipper.setIsUp(false);
            itsMidRFlipper.setIsUp(false);
        }
        if (ke.getCode() == KeyCode.RIGHT){
            itsRightFlipper.setIsUp(false);
            itsMidLFlipper.setIsUp(false);
        }
        if (ke.getCode() == KeyCode.DOWN){
            itsLauncher.setRising(true);
            mediaPlayerLauncher.stop();
            mediaPlayerInGame.setAutoPlay(true);
        }
    }
    
    @FXML
    public void mouseClicked(MouseEvent mouseEvent){
        anchorP.setFocusTraversable(true);
        anchorP.requestFocus();
    }

    private void movePivot(Node node, double x, double y){
        node.getTransforms().add(new Translate(-x, -y));
        node.setTranslateX(x);
        node.setTranslateY(y);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        this.gameElements = new ArrayList();
        this.gameClassElements = new ArrayList();
        this.itsBall = new Ball(1002, 550, ballP);
        
        //Add image to spikes in Y Limit
        Image spikes = new Image("/macadambumper/images/Spikes.png", false);
        rectSpikes.setFill(new ImagePattern(spikes));

        this.player1 = new Player(new SimpleStringProperty("Riton"), lScore1);
        lName.textProperty().bind(player1.getName());

        slider.setFocusTraversable(false);
        slider.setValue(0.05);

        try {
            this.mediaIntro = new Media(Game.class.getResource("music/Mini_Intro.mp3").toURI().toString());
            this.mediaLauncher = new Media(Game.class.getResource("music/LancementPartie.mp3").toURI().toString());
            this.mediaInGame = new Media(Game.class.getResource("music/musiqueInGame.mp3").toURI().toString());
            this.mediaBumper = new Media(Game.class.getResource("music/CollisionBumper.mp3").toURI().toString());
            this.mediaFlipperDroit = new Media(Game.class.getResource("music/rflipper.mp3").toURI().toString());
            this.mediaFlipperGauche = new Media(Game.class.getResource("music/lflipper.mp3").toURI().toString());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        this.mediaPlayer = new MediaPlayer(mediaIntro);
        mediaPlayer.setVolume(slider.getValue());
        mediaPlayer.setAutoPlay(true);

        this.mediaPlayerLauncher = new MediaPlayer(mediaLauncher);
        mediaPlayerLauncher.setVolume(slider.getValue());

        this.mediaPlayerInGame = new MediaPlayer(mediaInGame);
        mediaPlayerInGame.setVolume(slider.getValue());

        this.mediaPlayerBumper = new MediaPlayer(mediaBumper);
        mediaPlayerBumper.setVolume(slider.getValue());

        this.mediaPlayerFlipperDroit = new MediaPlayer(mediaFlipperDroit);
        mediaPlayerFlipperDroit.setVolume(slider.getValue());
        mediaPlayerFlipperDroit.setBalance(1);

        this.mediaPlayerFlipperGauche = new MediaPlayer(mediaFlipperGauche);
        mediaPlayerFlipperGauche.setVolume(slider.getValue());
        mediaPlayerFlipperGauche.setBalance(-1);

        this.itsLeftFlipper = new Flipper(lFlipper.getX(), lFlipper.getY(), true, lFlipper);
        this.itsRightFlipper = new Flipper(rFlipper.getX(), rFlipper.getY(), false, rFlipper);
        this.itsMidLFlipper = new Flipper(midLFlipper.getX(), midLFlipper.getY(), false ,midLFlipper);
        this.itsMidRFlipper = new Flipper(midRFlipper.getX(), midRFlipper.getY(), true, midRFlipper);

        this.itsLauncher = new Launcher(launcher.getLayoutX(),launcher.getLayoutY(), launcher);

        this.itsDedge = new Edge(dEdge);
        this.itsTedge = new Edge(tEdge);
        this.itsLedge = new Edge(lEdge);
        this.itsLaunchEdge = new Edge(launchEdge);
        this.itsYLimit = new Edge(yLimit);
        this.itsRedge = new Edge(rEdge);

        this.itsTopRBorderV = new Edge(topRBorderV);
        this.itsTopRBorderH = new Edge(topRBorderH);
        this.itsFlipBorder = new Edge(flipBorder);
        this.itsBorderFlip = new Edge(borderFlip);
        this.itsBotLauncherEdge = new Edge(botLauncherEdge);
        this.itsBotDiagoLauncherEdge = new Edge(botDiagoLauncherEdge);
        this.itsMidLauncherEdge = new Edge(midLauncherEdge);
        this.itsTopDiagoLauncherEdge = new Edge(topDiagoLauncherEdge);
        this.itsLBotEdge = new Edge(lBotEdge);
        this.itsLTopDiagoEdge = new Edge(lTopDiagoEdge);
        this.itsLDiagoEdge = new Edge(lDiagoEdge);
        this.itsLpl1 = new Edge(lpl1);
        this.itsLpl2 = new Edge(lpl2);
        this.itsLpl3 = new Edge(lpl3);
        this.itsRpl1 = new Edge(rpl1);
        this.itsRpl2 = new Edge(rpl2);
        this.itsRpl3 = new Edge(rpl3);
        this.itsBpl1 = new Edge(bpl1);
        this.itsBpl2 = new Edge(bpl2);
        this.itsBpl3 = new Edge(bpl3);
        this.itsTpl1 = new Edge(tpl1);
        this.itsTpl2 = new Edge(tpl2);
        this.itsTpl3 = new Edge(tpl3);
        this.itsNul1 = new Edge(nul1);
        this.itsNul2 = new Edge(nul2);

        this.itsTopBumper = new Bumper(topBumper.getLayoutX(), topBumper.getLayoutY(), topBumper);
        this.itsTopLBumper = new Bumper(topLBumper.getLayoutX(), topLBumper.getLayoutY(), topLBumper);
        this.itsTopRBumper = new Bumper(topRBumper.getLayoutX(), topRBumper.getLayoutY(), topRBumper);
        this.itsMidLBumper = new Bumper(midLBumper.getLayoutX(), midLBumper.getLayoutY(), midLBumper);
        this.itsMidRBumper = new Bumper(midRBumper.getLayoutX(), midRBumper.getLayoutY(), midRBumper);

        this.progressBar.setProgress(0.5);
        this.progressBar.progressProperty().bind(this.itsLauncher.getItsPowerProgress());

        this.ballsCounter = new SimpleIntegerProperty(3);
        this.lBalls.textProperty().bind(ballsCounter.asString());

        this.scoreCounter = new SimpleIntegerProperty(0);
        this.lScore1.textProperty().bind(scoreCounter.asString());

        movePivot(this.lFlipper, -lFlipper.getWidth() / 2 + lFlipper.getHeight() / 2, 0);
        lFlipper.setRotate(30);
        movePivot(this.rFlipper, rFlipper.getWidth() / 2 - rFlipper.getHeight() / 2, 0);
        rFlipper.setRotate(-30);

        movePivot(this.midLFlipper, midLFlipper.getWidth() / 2 - midLFlipper.getHeight() / 2, 0);
        midLFlipper.setRotate(-30);
        movePivot(this.midRFlipper, -midRFlipper.getWidth() / 2 + midRFlipper.getHeight() / 2, 0);
        midRFlipper.setRotate(30);

        IntegerProperty highscore = new SimpleIntegerProperty(0);
        lHighscore.textProperty().bind(highscore.asString());

        //GRAPHICAL ELEMENTS ADDING
        gameElements.add(lFlipper);
        gameElements.add(rFlipper);
        gameElements.add(midLFlipper);
        gameElements.add(midRFlipper);
        gameElements.add(launcher);

        gameElements.add(yLimit);
        gameElements.add(rEdge);
        gameElements.add(tEdge);
        gameElements.add(dEdge);
        gameElements.add(lEdge);
        gameElements.add(launchEdge);
        gameElements.add(topRBorderV);
        gameElements.add(topRBorderH);
        gameElements.add(flipBorder);
        gameElements.add(borderFlip);
        gameElements.add(botLauncherEdge);
        gameElements.add(botDiagoLauncherEdge);
        gameElements.add(midLauncherEdge);
        gameElements.add(topDiagoLauncherEdge);
        gameElements.add(lBotEdge);
        gameElements.add(lTopDiagoEdge);
        gameElements.add(lDiagoEdge);
        gameElements.add(lpl1);
        gameElements.add(lpl2);
        gameElements.add(lpl3);
        gameElements.add(rpl1);
        gameElements.add(rpl2);
        gameElements.add(rpl3);
        gameElements.add(bpl1);
        gameElements.add(bpl2);
        gameElements.add(bpl3);
        gameElements.add(tpl1);
        gameElements.add(tpl2);
        gameElements.add(tpl3);
        gameElements.add(nul1);
        gameElements.add(nul2);

        gameElements.add(topBumper);
        gameElements.add(topLBumper);
        gameElements.add(topRBumper);
        gameElements.add(midLBumper);
        gameElements.add(midRBumper);
        gameElements.add(lpc1);
        gameElements.add(lpc2);
        gameElements.add(lpc3);
        gameElements.add(rpc1);
        gameElements.add(rpc2);
        gameElements.add(rpc3);
        gameElements.add(bpc1);
        gameElements.add(bpc2);
        gameElements.add(bpc3);
        gameElements.add(tpc1);
        gameElements.add(tpc2);
        gameElements.add(tpc3);
        gameElements.add(nuc1);
        gameElements.add(nuc2);
        gameElements.add(nuc3);
        gameElements.add(cTopLauncher);



        //PHYSICAL ELEMENTS ADDING
        gameClassElements.add(itsLeftFlipper);
        gameClassElements.add(itsRightFlipper);
        gameClassElements.add(itsMidLFlipper);
        gameClassElements.add(itsMidRFlipper);

        gameClassElements.add(itsYLimit);
        gameClassElements.add(itsRedge);
        gameClassElements.add(itsLedge);
        gameClassElements.add(itsTedge);
        gameClassElements.add(itsDedge);
        gameClassElements.add(itsLaunchEdge);
        gameClassElements.add(itsLauncher);
        gameClassElements.add(itsTopRBorderV);
        gameClassElements.add(itsTopRBorderH);
        gameClassElements.add(itsFlipBorder);
        gameClassElements.add(itsBorderFlip);
        gameClassElements.add(itsBotLauncherEdge);
        gameClassElements.add(itsBotDiagoLauncherEdge);
        gameClassElements.add(itsMidLauncherEdge);
        gameClassElements.add(itsTopDiagoLauncherEdge);
        gameClassElements.add(itsLBotEdge);
        gameClassElements.add(itsLTopDiagoEdge);
        gameClassElements.add(itsLDiagoEdge);
        gameClassElements.add(itsLpl1);
        gameClassElements.add(itsLpl2);
        gameClassElements.add(itsLpl3);
        gameClassElements.add(itsRpl1);
        gameClassElements.add(itsRpl2);
        gameClassElements.add(itsRpl3);
        gameClassElements.add(itsBpl1);
        gameClassElements.add(itsBpl2);
        gameClassElements.add(itsBpl3);
        gameClassElements.add(itsTpl1);
        gameClassElements.add(itsTpl2);
        gameClassElements.add(itsTpl3);
        gameClassElements.add(itsNul1);
        gameClassElements.add(itsNul2);

        gameClassElements.add(itsTopBumper);
        gameClassElements.add(itsTopLBumper);
        gameClassElements.add(itsTopRBumper);
        gameClassElements.add(itsMidLBumper);
        gameClassElements.add(itsMidRBumper);

        topBumper.setFill(new ImagePattern(bumperBase));
        topLBumper.setFill(new ImagePattern(bumperBase));
        topRBumper.setFill(new ImagePattern(bumperBase));
        midLBumper.setFill(new ImagePattern(bumperBase));
        midRBumper.setFill(new ImagePattern(bumperBase));

        topBumper.setFill(new ImagePattern(bumperBase));
        topLBumper.setFill(new ImagePattern(bumperBase));
        topRBumper.setFill(new ImagePattern(bumperBase));
        midLBumper.setFill(new ImagePattern(bumperBase));
        midRBumper.setFill(new ImagePattern(bumperBase));
        
        ImagePattern gifBumper = new ImagePattern(bumperGif);
        ImagePattern baseBumper = new ImagePattern(bumperBase);
        ImagePattern stormLeftCenterGif = new ImagePattern(trianglegifCenterLeftStorm);
        ImagePattern stormLeftCenterBase = new ImagePattern(triangleCenterLeftStormBase);
        ImagePattern stormRightCenterGif = new ImagePattern(trianglegifCenterRightStorm);
        ImagePattern stormRightCenterBase = new ImagePattern(triangleCenterRightStormBase);
        ImagePattern StormLeftGif = new ImagePattern(trianglegifLeftStorm);
        ImagePattern StormLeftBase = new ImagePattern(triangleLeftStormBase);
        ImagePattern StormTopGif = new ImagePattern(trianglegifTopStorm);
        ImagePattern StormTopBase = new ImagePattern(triangleTopStormBase);

        //Polygon List

        lPoly.add(t1);
        lPoly.add(t2);
        lPoly.add(t3);
        lPoly.add(t4);
        lPoly.add(t5);
        lPoly.add(t6);
        lPoly.add(t7);
        lPoly.add(t8);
        lPoly.add(t9);
        lPoly.add(t10);
        lPoly.add(t11);
        lPoly.add(t12);
        lPoly.add(t13);

        slider.setShowTickLabels(false);
        slider.setShowTickMarks(false);
        buttonEN.setToggleGroup(group);
        buttonFR.setToggleGroup(group);
        
        
        slider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number previous, Number now){
                now.doubleValue();
                mediaPlayer.setVolume(slider.getValue());
                mediaPlayerLauncher.setVolume(slider.getValue());
                mediaPlayerInGame.setVolume(slider.getValue());
                mediaPlayerBumper.setVolume(slider.getValue());
                mediaPlayerFlipperDroit.setVolume(slider.getValue());
                mediaPlayerFlipperGauche.setVolume(slider.getValue());
            }
        });

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showAlertWithHeaderText();
            }
        });

        //Create TimerTask of frequency play
        gameloop = new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(() -> {
                    
                    itsBall.draw();
                    if (ballP.getLayoutY() + ballP.getRadius() + 1 <= yLimit.getStartY()){
                        for (int i = 0; i < gameElements.size(); i++){
                            Shape intersectBallElements = Shape.intersect(gameElements.get(i), ballP);

                            if (intersectBallElements.getBoundsInLocal().getWidth() != -1 || intersectBallElements.getBoundsInLocal().getHeight() != -1){
                                switch (gameElements.get(i).getClass().getSimpleName()){
                                    case "Line":
                                        if (gameElements.get(i).getId().equals("bpl3")){
                                            Timeline timeline = new Timeline(
                                                new KeyFrame(Duration.ZERO, new KeyValue(p3.fillProperty(), StormLeftGif)),
                                                new KeyFrame(Duration.millis(300), new KeyValue(p3.fillProperty(), StormLeftBase))
                                                );
                                            timeline.play();
                                            scoreCounter.set(scoreCounter.get() + 100);
                                        }
                                        if (gameElements.get(i).getId().equals("tpl1") || gameElements.get(i).getId().equals("tpl2") || gameElements.get(i).getId().equals("tpl3")){
                                            Timeline timeline = new Timeline(
                                                new KeyFrame(Duration.ZERO, new KeyValue(p4.fillProperty(), StormTopGif)),
                                                new KeyFrame(Duration.millis(300), new KeyValue(p4.fillProperty(), StormTopBase))
                                                );
                                            timeline.play();
                                            scoreCounter.set(scoreCounter.get() + 100);
                                        }
                                        if (gameElements.get(i).getId().equals("lpl1") || gameElements.get(i).getId().equals("lpl2") || gameElements.get(i).getId().equals("lpl3")){
                                            Timeline timeline = new Timeline(
                                                new KeyFrame(Duration.ZERO, new KeyValue(p1.fillProperty(), stormLeftCenterGif)),
                                                new KeyFrame(Duration.millis(300), new KeyValue(p1.fillProperty(), stormLeftCenterBase))
                                                );
                                            timeline.play();
                                            scoreCounter.set(scoreCounter.get() + 100);
                                        }
                                        if (gameElements.get(i).getId().equals("rpl1") || gameElements.get(i).getId().equals("rpl2") || gameElements.get(i).getId().equals("rpl3")){
                                            Timeline timeline = new Timeline(
                                                new KeyFrame(Duration.ZERO, new KeyValue(p2.fillProperty(), stormRightCenterGif)),
                                                new KeyFrame(Duration.millis(300), new KeyValue(p2.fillProperty(), stormRightCenterBase))
                                                );
                                            timeline.play();
                                            scoreCounter.set(scoreCounter.get() + 100);
                                        }
                                        for (int j = 0; j < gameClassElements.size(); j++){
                                            if ((gameClassElements.get(j).getItsShape()).equals(gameElements.get(i))){
                                                checkCollisionEdges(intersectBallElements, (Edge) gameClassElements.get(j));
                                            }
                                        }
                                        break;
                                    case "Rectangle":
                                        if (gameElements.get(i).getId().equals("launcher")){
                                            itsBall.setDirX(0);
                                            checkCollisionLauncher();
                                        }
                                        else{
                                            for (int j = 0; j < gameClassElements.size(); j++){
                                                if ((gameClassElements.get(j).getItsShape()).equals(gameElements.get(i))){
                                                    checkCollisionFlipper(intersectBallElements, (Flipper) gameClassElements.get(j));

                                                }
                                            }
                                        }
                                        break;
                                    case "Circle":
                                            boolean isFlipper = false;
                                        for (int j = 0; j < gameClassElements.size(); j++){
                                            if ((gameClassElements.get(j).getItsShape()).equals(gameElements.get(i))){
                                                isFlipper = true;
                                                checkCollisionBumper(intersectBallElements, (Bumper) gameClassElements.get(j));
                                                scoreCounter.set(scoreCounter.get() + 300);
                                                mediaPlayerBumper.seek(Duration.millis(0));
                                                mediaPlayerBumper.play();
                                                Timeline timeline = new Timeline(
                                                        new KeyFrame(Duration.ZERO, new KeyValue(gameElements.get(i).fillProperty(), gifBumper)),
                                                        new KeyFrame(Duration.millis(400), new KeyValue(gameElements.get(i).fillProperty(), baseBumper))
                                                );
                                                timeline.play();
                                            }                                            
                                        }
                                                if(!isFlipper){
                                                    checkCollisionBumper(intersectBallElements, new Bumper(gameElements.get(i).getLayoutX(),gameElements.get(i).getLayoutY(),(Circle)gameElements.get(i)));
                                                }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                    //DEFEAT
                    else if (ballP.getLayoutX() + ballP.getRadius() <= yLimit.getEndX()){
                        System.out.println("-1 balle");
                        ballsCounter.set(ballsCounter.get()-1);
                        if (ballsCounter.getValue() <= 0){
                            System.out.println("PERDU");
                            System.exit(0);
                        }
                        else{
                            isInGame = false;
                            itsBall.setSpeed(0);
                            itsBall.setDirX(0);
                            itsBall.setDirY(0);
                            itsBall.setX(1002);
                            itsBall.setY(550);
                        }
                    }
                    itsLauncher.draw();
                    itsRightFlipper.draw();
                    itsLeftFlipper.draw();
                    itsMidLFlipper.draw();
                    itsMidRFlipper.draw();
                });
            }
        };
        itsTimer.schedule(gameloop, 1, 2);


        //Polygons animation timer
        timerLight = new Timer();
        loopLight = new TimerTask(){

            @Override
            public void run() {
                Platform.runLater(() -> {
                    if(k + 4 == lPoly.size()){
                        lPoly.get(k - 1).setOpacity(0.2f);
                        lPoly.get(k).setOpacity(0.2f);
                        lPoly.get(k + 1).setOpacity(0.2f);
                        lPoly.get(k + 2).setOpacity(0.2f);
                        lPoly.get(k + 3).setOpacity(0.2f);

                        k = 0;
                        timerCounter++;
                    }
                    if(timerCounter == 4){
                        timerLight.cancel();
                    }
                    else if(k == 0){
                        lPoly.get(k).setOpacity(0.8f);
                        lPoly.get(k +1).setOpacity(0.8f);
                        lPoly.get(k + 2).setOpacity(0.8f);
                        lPoly.get(k + 3).setOpacity(0.8f);
                        lPoly.get(k + 4).setOpacity(0.8f);
                    }
                    else{
                        lPoly.get(k - 1).setOpacity(0.2f);
                        lPoly.get(k).setOpacity(0.8f);
                        lPoly.get(k + 1).setOpacity(0.8f);
                        lPoly.get(k + 2).setOpacity(0.8f);
                        lPoly.get(k + 3).setOpacity(0.8f);
                        lPoly.get(k + 4).setOpacity(0.8f);
                    }
                    k++;
                });
            }
        };
    }

    public void checkCollisionLauncher(){
        itsLauncher.setDirY(0);
        if (itsLauncher.getRising()){
            itsLauncher.throwBall(itsBall);
        }
        else{
            itsBall.setY(itsLauncher.getY() - itsBall.getRadius());
        }
        if (itsBall.getX() < 1024 && itsBall.getX() > 982 && itsBall.getDirY() > 0){
            itsBall.setDirY(0);
            itsBall.setX(1002);
            itsBall.setY(550);
        }
    }

    public void checkCollisionBumper(Shape aShapeCollision, Bumper aPhysicalBumper){
        Circle theBumper = (Circle) aPhysicalBumper.getItsShape();
        double oldX = itsBall.getX();
        double oldY = itsBall.getY();
        double oldDirX = itsBall.getDirX();
        double oldDirY = itsBall.getDirY();
            //System.out.println("oldDirXBump: "+oldDirX);
            //System.out.println("oldDirYBump: "+oldDirY);

        //If elements are close
        if (itsBall.getX() + itsBall.getRadius() + theBumper.getRadius() > theBumper.getLayoutX() && itsBall.getX() < theBumper.getLayoutX() + itsBall.getRadius() + theBumper.getRadius() && itsBall.getY() + itsBall.getRadius() + theBumper.getRadius() > theBumper.getRadius() && itsBall.getY() < theBumper.getLayoutY() + itsBall.getRadius() + theBumper.getRadius()){
            //BALL 1 = bumper BALL 2 = ball
            double dx = itsBall.getX() - theBumper.getLayoutX();
            double dy = itsBall.getY() - theBumper.getLayoutY();
            double distance = Math.sqrt(dx*dx + dy*dy);

            //Reflection angle cos and sin
            double axisAngleCollision = Math.atan2(dy,dx);
            double cos = Math.cos(axisAngleCollision);
            double sin = Math.sin(axisAngleCollision);

            //Rotate velocity
            double vx1 = 0;
            double vx2 = itsBall.getDirX()*cos + itsBall.getDirY()*sin;
            double vy2 = itsBall.getDirY()*cos - itsBall.getDirX()*sin;

            //Resolve 1D case
            double vx2final = ((itsBall.getRadius()/2 - theBumper.getRadius()/2) * vx2 + 2 * theBumper.getRadius()/2 * vx1) / (theBumper.getRadius()/2 + itsBall.getRadius()/2);

            vx2 = vx2final;

            if(oldX > theBumper.getLayoutX()){
                itsBall.setX(itsBall.getX() + aShapeCollision.getBoundsInLocal().getWidth());
            }
            else{
                itsBall.setX(itsBall.getX() - aShapeCollision.getBoundsInLocal().getWidth());
            }
            if(oldY > theBumper.getLayoutY()){
                itsBall.setY(itsBall.getY() + aShapeCollision.getBoundsInLocal().getWidth());
            }
            else{
                itsBall.setY(itsBall.getY() - aShapeCollision.getBoundsInLocal().getWidth());
            }

            //Rotate velocity back
            itsBall.setDirX(vx2*cos - vy2*sin);
            itsBall.setDirY(-(vy2*cos + vx2-sin));
            double egaliseur = 0;
            if (aPhysicalBumper.getBumperPower() == 1.5){
                egaliseur = calcEqualizer(1.5, 1.5, itsBall.getDirX(), itsBall.getDirY());
            }
            else if (aPhysicalBumper.getBumperPower() == 1.0){
                egaliseur = calcEqualizer(oldDirX, oldDirY, itsBall.getDirX(), itsBall.getDirY());
            }
            itsBall.setDirX(egaliseur * itsBall.getDirX());
            itsBall.setDirY(egaliseur * itsBall.getDirY());
        }
    }


    public void checkCollisionEdges(Shape aShapeCollision, Edge anEdge){
        Line theLine = (Line) anEdge.getItsShape();
        //Replacing
        //High horizontal collision
        if ((theLine.getStartY() == theLine.getEndY()) && (itsBall.getY() - itsBall.getRadius()) <= theLine.getEndY()+3){
            itsBall.setY(itsBall.getY() + aShapeCollision.getBoundsInLocal().getHeight() + 1);
            itsBall.setDirY(-itsBall.getDirY());
        }//Right vertical collision
        else if ((theLine.getStartX() == theLine.getEndX()) && (itsBall.getX() + itsBall.getRadius()) < theLine.getEndX()){
            itsBall.setX(itsBall.getX() - aShapeCollision.getBoundsInLocal().getWidth() - 1);
            itsBall.setDirX(-itsBall.getDirX());
        }//Left vertical collision
        else if ((theLine.getStartX() == theLine.getEndX()) && (itsBall.getX() - itsBall.getRadius()) > theLine.getEndX()){
            itsBall.setX(itsBall.getX() + aShapeCollision.getBoundsInLocal().getWidth() + 1);
            itsBall.setDirX(-itsBall.getDirX());
        } 
        else{
            double oldDirX = itsBall.getDirX();
            double oldDirY = itsBall.getDirY();
            if(Math.abs(theLine.getStartX()-theLine.getEndX()) != Math.abs(theLine.getStartY()-theLine.getEndY())){
                if (anEdge.getAngleLine() > 90){
                    double resDirX = Math.cos(anEdge.getAngleLine()) + Math.sin(anEdge.getAngleLine());
                    double resDirY = -(Math.sin(anEdge.getAngleLine())) + Math.cos(anEdge.getAngleLine());
                    itsBall.setDirX(resDirX);
                    itsBall.setDirY(-resDirY);
                }
                else{
                    itsBall.rotateDirX(oldDirX, oldDirY, anEdge.getAngleLine());
                    itsBall.rotateDirY(oldDirX, oldDirY, anEdge.getAngleLine());
                }
                Double egaliseur = calcEqualizer(oldDirX, oldDirY, itsBall.getDirX(), itsBall.getDirY());
                itsBall.setDirX((-itsBall.getDirX()) * egaliseur);
                itsBall.setDirY((-itsBall.getDirY()) * egaliseur);
            }
            else{
                double oldX = itsBall.getDirX();
                itsBall.setDirX(itsBall.getDirY());
                if(anEdge.getAngleLine() < 0){
                    itsBall.setDirY(-oldX);
                }
                else{
                    itsBall.setDirY(oldX);
                }
            }
        }
    }

    public void checkCollisionFlipper(Shape aShapeCollision, Flipper aFlipper){
        Rectangle anElement = (Rectangle) aFlipper.getItsShape();
        //If there is superposition
        //A                                                                                                                                              
        Double topLeftPointX = aFlipper.calcPivotX(anElement.getLayoutX(), anElement.getTranslateX(), anElement.getLayoutY(), anElement.getTranslateY(), anElement.getRotate());
        Double topLeftPointY = aFlipper.calcPivotY(anElement.getLayoutX(), anElement.getTranslateX(), anElement.getLayoutY(), anElement.getTranslateY(), anElement.getRotate());
        //D
        Double botLeftPointX = aFlipper.calcPivotX(anElement.getLayoutX(), anElement.getTranslateX(), anElement.getLayoutY() + anElement.getHeight(), anElement.getTranslateY(), anElement.getRotate());
        Double botLeftPointY = aFlipper.calcPivotY(anElement.getLayoutX(), anElement.getTranslateX(), anElement.getLayoutY() + anElement.getHeight(), anElement.getTranslateY(), anElement.getRotate());
        //B
        Double topRightPointX = aFlipper.calcPivotX(anElement.getLayoutX() + anElement.getWidth(), anElement.getTranslateX(), anElement.getLayoutY(), anElement.getTranslateY(), anElement.getRotate());
        Double topRightPointY = aFlipper.calcPivotY(anElement.getLayoutX() + anElement.getWidth(), anElement.getTranslateX(), anElement.getLayoutY(), anElement.getTranslateY(), anElement.getRotate());
        //C
        Double botRightPointX = aFlipper.calcPivotX(anElement.getLayoutX() + anElement.getWidth(), anElement.getTranslateX(), anElement.getLayoutY() + anElement.getHeight(), anElement.getTranslateY(), anElement.getRotate());
        Double botRightPointY = aFlipper.calcPivotY(anElement.getLayoutX() + anElement.getWidth(), anElement.getTranslateX(), anElement.getLayoutY() + anElement.getHeight(), anElement.getTranslateY(), anElement.getRotate());

        double oldDirX = itsBall.getDirX();
        double oldDirY = itsBall.getDirY();

        if (aFlipper.isIsGoingUp()){

            itsBall.setY(itsBall.getY() - aShapeCollision.getBoundsInLocal().getHeight());

            if(anElement.getTranslateX()<0){
                itsBall.setDirX(Math.cos(anElement.getRotate() + 90));
                itsBall.setDirY(Math.sin(anElement.getRotate() + 90));
            }
            else{
                itsBall.setDirX(Math.cos(anElement.getRotate() - 90));
                itsBall.setDirY(Math.sin(anElement.getRotate() - 90));
            }


            double rapport = 1;
            double couple = 1;
            if(anElement.getTranslateX() < 0){
                rapport = Math.abs(itsBall.getX() - anElement.getLayoutX());
            }
            else{
                rapport = Math.abs(anElement.getLayoutX() + anElement.getWidth() - itsBall.getX());
            }
            couple = 2 * (rapport/anElement.getWidth());

            double egaliseur = calcEqualizer(couple, couple, itsBall.getDirX(), itsBall.getDirY());
            itsBall.setDirX(itsBall.getDirX() * egaliseur);
            itsBall.setDirY((-Math.abs(itsBall.getDirY())) * egaliseur);
        }

        else if (anElement.getRotate() == 0){
            //Lateral case
            if ((anElement.getLayoutY() <= itsBall.getY()) && (anElement.getLayoutY() + anElement.getHeight() >= itsBall.getY())){
                if (anElement.getLayoutX() + 1 > (itsBall.getX() + itsBall.getRadius() + 1)){
                    if (itsBall.getDirX() > 0){
                        itsBall.setDirX((-itsBall.getDirX()));
                    }
                    itsBall.setX(itsBall.getX() - aShapeCollision.getBoundsInLocal().getWidth() - 2);
                } 
                else if ((anElement.getLayoutX() + anElement.getWidth() - 1 < (itsBall.getX() - itsBall.getRadius()))){
                    if (itsBall.getDirX() < 0){
                        itsBall.setDirX((-itsBall.getDirX()));
                    }
                    itsBall.setX(itsBall.getX() + aShapeCollision.getBoundsInLocal().getWidth() + 2);
                }
            } else if (anElement.getLayoutY() + anElement.getHeight() - 2 < (itsBall.getY() - ballP.getRadius())){
                itsBall.setY(itsBall.getY() + aShapeCollision.getBoundsInLocal().getHeight() + 2);
                if (itsBall.getDirY() < 0){
                    itsBall.setDirY((itsBall.getDirY() * -1));
                }
            } else if (anElement.getLayoutY() < itsBall.getY() + ballP.getRadius() + 1){
                itsBall.setY(itsBall.getY() - aShapeCollision.getBoundsInLocal().getHeight() - 2);
                if (itsBall.getDirY() > 0){
                    itsBall.setDirY((-itsBall.getDirY()));
                }
            }

        }
        //RIGHT FLIPPER  ===================================================================================================================
        else if (anElement.getTranslateX() > 0){

            //Inferior right flipper collision
            if (((botLeftPointY < itsBall.getY() - itsBall.getRadius()) || botRightPointY < itsBall.getY()) && itsBall.getDirY() < 0){
                itsBall.setDirY(1);
            }

            //Lateral right flipper collision
            else if (botLeftPointX >= itsBall.getX() + itsBall.getRadius() && botLeftPointY >= itsBall.getY() + itsBall.getRadius() && topLeftPointX <= itsBall.getX() + itsBall.getRadius() && topLeftPointY <= itsBall.getY() + itsBall.getRadius()){
                itsBall.setX(itsBall.getX() - aShapeCollision.getBoundsInLocal().getWidth() - 1);
                if (itsBall.getDirX() > 0){
                    itsBall.rotateDirX(oldDirX, oldDirY, anElement.getRotate() + 90);
                    itsBall.rotateDirY(oldDirX, oldDirY, anElement.getRotate() + 90);
                }
            }

            //Superior right flipper collision
            else{
                itsBall.setY(itsBall.getY() - aShapeCollision.getBoundsInLocal().getHeight());
                itsBall.rotateDirX(oldDirX, oldDirY, anElement.getRotate());
                itsBall.rotateDirY(oldDirX, oldDirY, anElement.getRotate());
            }
            double egaliseur = calcEqualizer(oldDirX, oldDirY, itsBall.getDirX(), itsBall.getDirY());
            itsBall.setDirX(itsBall.getDirX() * egaliseur);
            itsBall.setDirY(itsBall.getDirY() * egaliseur);

        } //FLIPPER GAUCHE =============================================================================================================================
        else  if (anElement.getTranslateX() < 0){

            //Left inferior collision
            if (((botLeftPointY > itsBall.getY() - itsBall.getRadius()) || botRightPointY > itsBall.getY()) && itsBall.getDirY() < 0){
                    itsBall.setDirY(1);
            }

            //Left lateral collision
            else if (botRightPointX < itsBall.getX() + itsBall.getRadius() && botRightPointY >= itsBall.getY() + itsBall.getRadius() && topRightPointX < itsBall.getX() + itsBall.getRadius() && topRightPointY <= itsBall.getY() + itsBall.getRadius()){
                itsBall.setX(itsBall.getX() + aShapeCollision.getBoundsInLocal().getWidth() + 1);
                if (itsBall.getDirX() < 0){
                    itsBall.rotateDirX(oldDirX, oldDirY, anElement.getRotate() + 90);
                    itsBall.rotateDirY(oldDirX, oldDirY, anElement.getRotate() + 90);
                }
            }

            //Left flipper superior collision
            else{
                itsBall.setY(itsBall.getY() - aShapeCollision.getBoundsInLocal().getHeight());
                itsBall.rotateDirX(oldDirX, oldDirY, anElement.getRotate());
                itsBall.rotateDirY(oldDirX, oldDirY, anElement.getRotate());
            }
            double egaliseur = calcEqualizer(oldDirX, oldDirY, itsBall.getDirX(), itsBall.getDirY());
            itsBall.setDirX(itsBall.getDirX() * egaliseur);
            itsBall.setDirY(itsBall.getDirY() * egaliseur);
        }
    }

    public double calcEqualizer(double oldX, double oldY, double eqX, double eqY){
        if(Math.abs(eqX) > Math.abs(eqY)){
            return Math.abs(oldX) / Math.abs(eqX);
        }
        else{
            return Math.abs(oldY) / Math.abs(eqY);
        }
    }

    public static void stopTimer(){
        itsTimer.cancel();
        timerLight.cancel();
    }

    public Playground(){
    }
}