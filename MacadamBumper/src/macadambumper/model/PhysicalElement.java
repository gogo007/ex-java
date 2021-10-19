package macadambumper.model;

import javafx.scene.shape.Shape;

/**
 *
 * @author User
 */
public abstract class PhysicalElement {
    private double itsX;
    private double itsY;
    Shape itsShape;
    
    public PhysicalElement(double aX, double aY, Shape aShape){
        this.itsX = aX;
        this.itsY = aY;
        this.itsShape=aShape;
    }

    public double getX(){
        return this.itsX;
    }

    public double getY(){
        return this.itsY;
    }

    public void setX(double aValue){
        this.itsX = aValue;
        this.itsShape.setLayoutX(itsX);
    }

    public void setY(double aValue){
        this.itsY = aValue;
        this.itsShape.setLayoutY(itsY);
    }

    public Shape getItsShape() {
        return itsShape;
    }
    public abstract void draw();
}

