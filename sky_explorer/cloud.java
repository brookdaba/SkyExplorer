/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sky_explorer;

/**
 *
 * @author bsd47
 */

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
//this is an object class for the clouds
public class cloud {
    private int x,y=2;//initializes the x,y position of the cloud
    private int dy;//initializes the speed of the cloud
    private final int w = 180;//initializes the width of the cloud
    private final int h = 70;//initializes the height of the cloud
    public Image i; //variable to hold the image file
    
   /**
    * constructor for the cloud objects
    * @param x the initial position of the cloud on the x axis
    * @param dy the speed of the cloud
    * it assigns the values taken from to the global variables in the class
    */
    public cloud(int x, int dy){
        this.x = x;
        this.dy = dy;
        ImageIcon ii = new ImageIcon("src/PIC/cloud2.png");
        Image d = ii.getImage();
        this.i = d;
    }
    /**
     * 
     * @return returns current x value
     */
    public int getX(){
        return x;
    }
    /**
     * @return returns current x value
     */
    public int getY(){
        return y;//returns current y value
    }
    /**
     * @return returns current x value
     */
    public int getH(){
        return h;//returns current h value
    }
    /**
     * @return returns current x value
     */
    public int getW(){
        return w;//returns current h value
    }
    /**
     * @return the image i
     */
    public Image getImage(){
        return i;
    }
    public void movedown(){
        y= y+dy; //changes the speed of the cloud depending the speed
    }
    public Rectangle[] getSections()
    {
        Rectangle [] sections = new Rectangle [3];
        sections[0] = new Rectangle(x+10,y+17,46,41);
       // System.out.println("x: "+x + " y: "+y);
        sections[1] = new Rectangle(x+56,y+5,52,55);
        sections[2] = new Rectangle(x+108,y+35,50,25);
        return sections;
    }
}
