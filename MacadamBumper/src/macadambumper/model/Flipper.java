package macadambumper.model;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author User
 */
public class Flipper extends PhysicalElement{
    private boolean isLeft;
    private boolean isUp;
    private boolean isGoingUp;

    public boolean isIsGoingUp() {
        return isGoingUp;
    }

    public void setIsGoingUp(boolean isGoingUp) {
        this.isGoingUp = isGoingUp;
    }
    private int itsPower;

    public int getItsPower() {
        return itsPower;
    }

    public void setItsPower(int itsPower) {
        this.itsPower = itsPower;
    }

    public Flipper(double aX, double aY, boolean isLeft, Rectangle aRect){
        super(aX, aY,aRect);
        this.isLeft = isLeft;
        this.isUp = false;
        this.itsPower = 1;
        Image flipper = new Image("/macadambumper/images/flipperNeon.png",false);
        aRect.setFill(new ImagePattern(flipper));
    }

    public void setIsUp(boolean isUp) {
        this.isUp = isUp;
    }

    public boolean getIsUp(){
        return this.isUp;
    }

    public double calcPivotX(double x, double tx, double y, double ty, double theta){
        x-=tx;
        if(theta>=0){
            return -(x * Math.sin(theta) + y * Math.cos(theta));
        }
        else{
            return (x * Math.sin(theta) + y * Math.cos(theta));
        }
    }

    public double calcPivotY(double x, double tx, double y, double ty, double theta){
        y-=ty;
        if(theta>=0){
            return (x * Math.cos(theta) - y * Math.sin(theta));
        }
        else{
            return -(x * Math.cos(theta) - y * Math.sin(theta));
        }
    }

    @Override
    public void draw(){
        Rectangle itsFlipper = (Rectangle)this.itsShape;
        //GAUCHE
        if(this.isLeft){
            //MONTER
            if(this.isUp){
                if(itsFlipper.getRotate()>-30.0){
                    itsFlipper.setRotate(itsFlipper.getRotate()-0.7);
                    this.isGoingUp=true;
                }else{
                    this.isGoingUp=false;
                }
            }
            //DESCENDRE
            else{
                if(itsFlipper.getRotate()<30.0){
                    itsFlipper.setRotate(itsFlipper.getRotate()+0.7);
                    this.isGoingUp=false;
                }
            }
        }
        //DROITE
        else{
            //MONTER
            if(this.isUp){
                if(itsFlipper.getRotate()<30.0){
                    itsFlipper.setRotate(itsFlipper.getRotate()+0.7);
                    this.isGoingUp=true;
                }else this.isGoingUp=false;
            }
            //DESCENDRE
            else{
                if(itsFlipper.getRotate()>-30.0){
                    itsFlipper.setRotate(itsFlipper.getRotate()-0.7);
                    this.isGoingUp=false;
                }
            }
        }
    }
}