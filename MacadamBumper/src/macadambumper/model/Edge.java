package macadambumper.model;

import javafx.scene.shape.Line;

/**
 *
 * @author User
 */
public class Edge extends PhysicalElement{
    public Edge(Line aLine){
        super(aLine.getLayoutX(), aLine.getLayoutY(),aLine);
    }

    public Line getItsLine() {
        Line theRes=(Line)this.itsShape;
        return theRes;
    }

    public double getAngleLine(){
        Line theLine= (Line)this.itsShape;
        return Math.atan2((theLine.getEndX() - theLine.getStartX()),(theLine.getEndY() -theLine.getStartY()))*180/Math.PI;
    }
    
    @Override
    public void draw(){

    }

}

