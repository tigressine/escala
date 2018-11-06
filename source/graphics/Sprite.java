// Part of Escala.
// Written by Spencer Phillips.

package escala.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import escala.Game;

/*
 * This is a base class for all sprite types:
 *      Plane
 *      Truck
 *      Boat
 *
 * TODO:  fix max speed so it corresponds to frame rate and scale of map.
 * */

public class Sprite extends JComponent {

    public double DELTA = 0.5;
    private double posX;
    private double posY;

    private double velocity;
    private double orientation;
    private double maxSpeed;

    private int width = 10;
    private int height = 10;

    Point[] path;       //first priority is to follow path
    int step;           //how far along path are we?
    Point destination;  //final destination (use if path is null)
    private boolean arrived;

    private int screenWidth;
    private int screenHeight;
    Color color;

    private Game game;

    public Sprite(int x, int y, double speed, Game game) {
        this.game = game;
        this.screenWidth = game.getWidth();
        this.screenHeight = game.getHeight();

        this.posX = x;
        this.posY = y;
        setMaxSpeed(speed);
        //System.out.println("MAX SPEED "+maxSpeed);
        this.arrived = true;
    }

    //TODO:: this hasn't been tested
    private void followPath(){
        Point prevDestination = destination;
        destination = path[step];
        moveTowardsDestination();

        //if we arrived at our intermediate step, move to next step
        if(arrived){
            step++;
            arrived = false;
        }

        //clean up
        destination = prevDestination;
    }


    // allow sprite to wrap horizontally around the world
    private void moveTowardsDestinationWrapped(){

        double prevX = posX;

        double delta = 0.5 * (double) screenWidth;

        if(posX < delta && destination.getX() - posX > delta){
            //if sprite is on left side, and faster to wrap around
            posX += screenWidth;
            moveTowardsDestination();
	    if(posX > screenWidth)
                posX -= screenWidth;
        } else if (posX > delta && posX - destination.getX() > delta){
            //if sprite is on right side, and faster to wrap around
            posX -= screenWidth;
            moveTowardsDestination();

            if(posX < 0.0)
                posX += screenWidth;
        } else {
                moveTowardsDestination();
        }

        // DEBUGGING TELEPORTS...
        //if ( Math.abs(prevX - posX) > maxSpeed)
        //    if ( prevX - maxSpeed > 0.0 || prevX + maxSpeed < screenWidth)
        //        System.out.println("JUMPED from " + prevX + " to " + posX);
    }


    //this now works
    private void moveTowardsDestination(){
        double dx = destination.getX() - posX;
        double dy = destination.getY() - posY;
        double dxdy = Math.abs(dx) + Math.abs(dy);
        double dist = Math.sqrt(dx*dx + dy*dy);

        //can we get to destination in 1 frame?, else move in correct direction
        if(dist < maxSpeed){
            posX = destination.getX();
            posY = destination.getY();
            arrived = true;
        } else {
            posX += ( dx / dxdy ) * maxSpeed;
            posY += ( dy / dxdy ) * maxSpeed;
        }

    }

    //TODO::: this may need to be changed
    //Update method
    public void update(){

        // If we already arrived, there is nothing to do.
        if(arrived)
            return;

        //follow path, or move towards destination,
        //or continue in same direction.
        if(path != null && path.length > step){
            followPath();
            return;
        } else if(destination != null) {
            moveTowardsDestinationWrapped();
            return;
        } else {

            posX += maxSpeed * Math.cos(orientation);
            posY += maxSpeed * Math.sin(orientation);
        }

        //TODO: Sanity Check to make sure we are still on the map...
    }

    public void translate(double dx, double dy){
        posX += dx;
        posY += dy;
    }

    //Arrived At Destination
    public boolean arrivedAtDestination(){
        return arrived;
    }

    //Color
    public void setColor(Color c) {
        color = c;
    }
    public Color getColor() {
        return color;
    }

    //Destination
    public void setDestination(Point d) {
        destination = d;
        arrived = false;
    }
    public Point getDestination() {
        return destination;
    }

    //Path
    public void setPath(Point[] p) {
        path = p;
        if(path != null)
            arrived = false;
    }
    public Point[] getPath() {
        return path;
    }
    public Point getNextLocationOnPath() {
        if(path != null)
            return path[0];
        if(destination != null)
            return destination;
        return null;
    }


    //Velocity
    public void setVelocity(double v) {
        velocity = v;
    }

    public double getVelocity() {
        return velocity;
    }

    //Position
    public void setPositionX(int x) {
        posX = x;
    }

    public double getPositionX() {
        return posX;
    }

    public void setPositionY(int y) {
        posY = y;
    }

    public double getPositionY(){
        return posY;
    }

    public double getMaxSpeed(){
        return maxSpeed;
    }

	/*Enter desired speed in mph and you will recieve something that looks reasonable
	at a time scale of 1 day per second.  */
    public void setMaxSpeed(double speed){
        double scale = this.game.getScale();
        double fps = (double) this.game.getGoalFPS();
        double gameSpeed = (double) this.game.getGameSpeed();

        this.maxSpeed = (2.0 * speed * scale * gameSpeed) / fps;
		//System.out.println("Speed: " + this.maxSpeed);
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    //TODO: override this in child classes
    public void paintComponent(Graphics g) {
      //Declare Image variable
      Image img = null;

      //Load image file and catch execption
      try {
			     img = ImageIO.read(new File("./data/plane/" + "plane" + ".png"));
		  } catch (IOException e) {
			     e.printStackTrace();
		  }

      //Use this image to draw in needed location
      g.drawImage(img, (int)posX, (int)posY, 49, 21, null);
    }
}
