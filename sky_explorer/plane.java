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
import javax.swing.ImageIcon;
//this class is used to create a plane object
public class plane {
    //initializes the variables to hold the x and y positions of the plane.
    //the y position is constant because the plane is always at the bottom
    public int x;
    public final int y = 550;
    public Image i;//initializes the variable to hold the image
    public Rectangle sectionA;
    public Rectangle sectionB;
    /**
     * @param x horizontal position of the plain
     */
    public plane(int x){
        //sets the valus for the palnes position and the image
        this.x = x;
        ImageIcon i = new ImageIcon("src/PIC/car.png");
        Image car_pic = i.getImage();
        this.i = car_pic;
    }
    public Image getImage(){
        return i; //returns the image
    }
    public int getX(){
        return x;//returns the x position
    }
    public int getY(){
        return y;//returns the y position
    }
    public void movePlaneleft(){//changes the position of the plane to the left by nine pixels
        x = x-9;
        if(x <= 0){//stops the plane from moving when it reaches the most left side of the frame
            x = 0;
        }
    }
    public void movePlaneRight(){//moves the plane to the right by nine pixles
        x= x + 9;
        if(x >= 450){//stops moving when the x position of plane reaches 50 pixles (the plain's width) less of the frame width
            x = 450;
        }
    }
   
    /**
     * 
     * @param sec an array of Rectangle objects that represent the sections of the cloud
     * @return boolean to tell if there is an intersection between a cloud and a plane
     */
    public boolean checkColision(Rectangle[] sec)
    {
        sectionA = new Rectangle (x+17,550,16,70);
        sectionB = new Rectangle (x,y+25,50,20);
        //System.out.println("x: "+x + " y: "+y);
        
        boolean colid = false;
        for (Rectangle sec1 : sec) {
            if (sec1.intersects(sectionA) || sec1.intersects(sectionB)) {
                colid = true;
                break;
            }
        }
        
        return colid;
    }
}
