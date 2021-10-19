package macadambumper.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author User
 */
public class Launcher extends PhysicalElement{
    private SimpleDoubleProperty itsPower;
    private SimpleDoubleProperty itsPowerProgress;
    private double itsDirY;
    private boolean isRising;

    public Launcher(double aX, double aY, Rectangle aRect){
        super(aX, aY, aRect);
        aRect.setLayoutX(aX);
        aRect.setLayoutY(aY);
        this.itsDirY = 0.5;
        this.itsPower = new SimpleDoubleProperty();
        this.itsPower.set(0);
        this.isRising = true;
        this.itsPowerProgress = new SimpleDoubleProperty();
        this.itsPowerProgress.bind(itsPower.divide(245));
    }

    public void throwBall(Ball aBall){
        if (isRising && itsPower.getValue() < 0){

        }
        else{
            aBall.setSpeed(0.99);
            aBall.setDirY(-((itsPower.getValue()/120)*2.3)); //2.3 max value
        }
    }

    public boolean getRising(){
        return this.isRising;
    }

    public double getPower() {
        return this.itsPower.getValue();
    }

    public SimpleDoubleProperty getItsPowerProgress() {
        return itsPowerProgress;
    }

    public void setRising(boolean rising) {
        this.isRising = rising;
    }

    public void setPower(double aPower){
        this.itsPower.setValue(aPower);
    }

    public void setDirY(double aDirY){
        this.itsDirY = aDirY;
    }

    @Override
    public void draw(){
        if(!isRising && itsPower.getValue() <= 250){
            itsPower.set(itsPower.getValue()+0.147);
            this.setY(this.getY() + this.itsDirY);
        }
        else if(itsPower.getValue() > -10) {
            this.setY(this.getY() - this.itsDirY);
            itsPower.set(itsPower.getValue()-1);
        }
        this.setX(this.getX());
        this.getItsShape().setLayoutX(this.getX());
        this.getItsShape().setLayoutY(this.getY());
    }

}
