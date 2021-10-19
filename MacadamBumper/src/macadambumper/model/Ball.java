package macadambumper.model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author User
 */
public class Ball extends PhysicalElement{
    private double itsSpeed;
    private double itsDirX;
    private double itsDirY;

    public Ball(double aX, double aY, Circle aCircle){
        super(aX,aY,aCircle);
        this.itsSpeed = 0;
        this.itsDirX = 0;
        this.itsDirY = 0;
        Image bille = new Image("/macadambumper/images/bille.png",false);
        aCircle.setFill(new ImagePattern(bille));
    }

    public double getSpeed(){
        return this.itsSpeed;
    }

    public double getDirX(){
        return this.itsDirX;
    }

    public double getDirY(){
        return this.itsDirY;
    }

    public void setDirX(double aDirX){
        this.itsDirX = aDirX;
    }

    public void setDirY(double aDirY){
        this.itsDirY = aDirY;
    }
    public void rotateDirX(double oldX, double oldY, double anAngle){
        double theRes = (Math.cos(anAngle) * oldX)-(Math.sin(anAngle) * oldY);
        this.setDirX(theRes);
    }
    public void rotateDirY(double oldX, double oldY,double anAngle){
        double theRes=(Math.sin(anAngle) * oldX) + (Math.cos(anAngle) * oldY);
        this.setDirY(-theRes);
    }
    @Override
    public void draw(){
        this.itsDirY=this.itsDirY + (0.0035*this.itsSpeed);
        this.setX(this.getX()+(this.itsDirX*this.itsSpeed));
        this.setY(this.getY()+(this.itsDirY*this.itsSpeed));

    }
    public double getRadius(){
        Circle theRes = (Circle)this.itsShape;
        return theRes.getRadius();
    }
    public void setSpeed(double itsSpeed) {
        this.itsSpeed = itsSpeed;
    }

}
