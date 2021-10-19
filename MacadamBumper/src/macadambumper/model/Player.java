package macadambumper.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

/**
 *
 * @author User
 */
public class Player {
    private SimpleStringProperty itsName;
    private SimpleIntegerProperty itsScore;

    public SimpleStringProperty getName(){
        return this.itsName;
    }

    public Player(SimpleStringProperty aName, Label aLabel){
        this.itsName = aName;
        this.itsScore = new SimpleIntegerProperty(0);
        aLabel.textProperty().bind(itsScore.asString());
    }

}

