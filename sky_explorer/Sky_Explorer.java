/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sky_explorer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author bsd47
 */
//this class contains the main method
public class Sky_Explorer {

    /**
     * @param args the command line arguments
     */
    //This class contains the main method and contains instructions to how to build the frame
    public static void main(String[] args) {
        // TODO code application logic here
    JFrame frame = new JFrame();// creats a new frame
    frame.setSize(500, 700);// sets the size
    Sky jp = new Sky(); //creats the field the objects are desplayed in and are set to interact... AKA the SKY
    
    
    
    
    
    frame.add(jp);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    jp.newPlane();//creates the plane 
    jp.newBlock();//creates the first 
  
    //Starts the first cloud to go down
    jp.run();
   
            
    }
}
