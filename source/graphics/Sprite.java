
package escala.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;

/*
 * This is a base class for all sprite types:
 * 		Plane
 * 		Truck
 * 		Boat
 * 
 * NOTE:  The path and destination functions do not work properly...
 * */

public class Sprite extends JComponent {
	
	public double DELTA = 0.5;
	private double posX;
	private double posY;
	
	private double velocity;
	private double orientation;
	private double maxSpeed;

	Point[] path;  		//first priority is to follow path
	int step;			//how far along path are we?
	Point destination;  //final destination (use if path is null)
	private boolean arrived;
	
	
	Color color;
	
	public Sprite(int x, int y, double speed, int goalFPS) {
		posX = x;
		posY = y;
		maxSpeed = speed / (double) Escala.GOAL_FPS;
		arrived = true;
	}
	
	//TODO:: this hasn't been tested
	private void followPath(){
		double dx, dy;
		dx = path[step].getX() - posX;
		dy = path[step].getY() - posY;
		
		//if there, stop : else, continue at max speed
		if(dx*dx + dy*dy <= DELTA){
			step++;
			
			//if this is final destination, stop
			if(path.length <= step){
				arrived = true;
				step = 0;
				velocity = 0.0;
				posX = path[step].getX();
				posY = path[step].getY();
			}
			return;
		} else {
			//how far will destination be after this turn?
			double newDist = Math.sqrt( dx*dx + dy*dy ) - maxSpeed;
			if(newDist <= 0.0){
				posX = path[step].getX();
				posY = path[step].getY();
				step++;
				return;
			}
					
			//x and y distance to destination (remember your trig)
			orientation = Math.atan(dy / dx);
			posX += maxSpeed * Math.cos(orientation);
			posY += maxSpeed * Math.sin(orientation);
		}
	}
	
	//TODO: This TOTALLY DOESN'T WORK... but fun anyways...
	private void moveTowardsDestination(){
		double dx, dy;
		dx = destination.getX() - posX;
		dy = destination.getY() - posY;
		
		//System.out.println("Current position: " + posX + ", "+ posY);
		//System.out.println("Current destinat: " + destination.getX() + ", " + destination.getY());
		//System.out.println(" * Distance to destination: " + dx + ", " + dy);
		
		double newDist = Math.sqrt( dx*dx + dy*dy ) - maxSpeed;
		orientation = Math.atan(dy / dx);
		
		if(newDist <= 0.0) {
			//System.out.println(" * Arrived");
			arrived = true;
			velocity = 0.0;
			posX = destination.getX();
			posY = destination.getY();
		} else {
			//how far will destination be after this turn?
			//System.out.println(" * Distance to destination: " + newDist);
					
			//x and y distance to destination
			posX += maxSpeed * Math.cos(orientation);
			posY += maxSpeed * Math.sin(orientation);
			
			if(dx < 0)
				posX *= -1.0;
			if(dy < 0)
				posY *= -1.0;
		}
	}
	
	//TODO::: this may need to be changed
	//Update method
	public void update(){
		double dx, dy;
		
		//follow path, or move towards destination, 
		//or continue in same direction.
		if(path != null && path.length > step){
			followPath();
			return;
		} else if(destination != null) {
			moveTowardsDestination();
			return;
		}
			
		posX += maxSpeed * Math.cos(orientation);
		posY += maxSpeed * Math.sin(orientation);
		
		
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
	
	//TODO: override this in child classes
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		Rectangle box = new Rectangle((int) posX, (int) posY, 10, 10);
		g2.draw(box);
	}
	
	
	
}
