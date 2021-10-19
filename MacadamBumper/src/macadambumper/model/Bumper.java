package macadambumper.model;

import javafx.scene.shape.Circle;

public class Bumper extends PhysicalElement{

    private double itsBumperPower;

    public Bumper(double aX, double aY, Circle aCircle){
        super(aX,aY,aCircle);
        if(aCircle.getRadius() == 18f){
            this.itsBumperPower = 1.5;
        }
        else{
            this.itsBumperPower = 1.0;
        }
    }

    public double getBumperPower() {
        return this.itsBumperPower;
    }

    @Override
    public void draw() {

    }
}
