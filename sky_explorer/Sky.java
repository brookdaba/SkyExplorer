/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sky_explorer;

/**
 *
 * @author bsd47
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

//this class creats the field all the other objects interact in
// 
public class Sky extends JPanel implements Runnable {
    
    //initializations
    public final int width = 500, height = 700; //Frame dimentions
    public List<cloud> bb = new ArrayList();//list of type cloud 
   
    public plane c; //plain variable(it's easier to have a Global variable that is going to be declaired once to be used in different methods)
    Random ran = new Random();
    
    public int dy = 1; //this variable controls the speed of the clouds. for level one it is 1
    public int Blocks=0; //this is a counter variable the conunts how many clouds have been created
    public int score;//this is a counter variable that tracks the score of the player. the variable is later used to constantly desplay the score and also at the end
    private final Font f1 = new Font("TimesRoman", Font.PLAIN, 13);//this font is the default font i picked
    private final Font f2 = new Font("TimesRoman", Font.BOLD, 23);// this font is used at the end when the game is over
    //the next two lines of codes import an image to the background and set it to a variable
    private final ImageIcon ii = new ImageIcon("src/PIC/skybg.png");
    private final Image d = ii.getImage();
    private int imgY =0; //initializes the first location variable of the background image 'd'
    private int imgY2 = 0-height;//initializes the second location variable of 'd'
    
/** this method creates a new cloud object and add it to the list of Blocks.
 * it increments the number of clouds that has been created and calculates 
 * the total score.
 * The first Block is Created when this object is first called in the main 
 * method(Sky_Explorer.java)
 */
    public void newBlock(){
        Random rand = new Random();
        Blocks++;
        score=Blocks*2;             
        cloud bl = new cloud(rand.nextInt(300), dy);
        bb.add(bl);
        
    }
    
//this method creates a new plane object and saves it to the a plane Type varaible    
    public void newPlane(){
        this.c = new plane(250);
        
    }
    
//this variable is initiated here. it shows the status of the plane
    boolean Colid = false;
    
    //this constructor contains codes for the KeyListener that moves the plane.
    public Sky(){
        
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyListener() {
        public void KeyListener( KeyEvent e){    
        }

        @Override
        public void keyTyped(KeyEvent e) {
            myKeyEvt(e, "keyTyped");
        }

        @Override
        public void keyPressed(KeyEvent e) {
            myKeyEvt(e, "keyPressed");
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myKeyEvt(e, "keyReleased");
        }
        private void myKeyEvt(KeyEvent e, String text) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_KP_LEFT || key == KeyEvent.VK_LEFT)
            {
                //moves the plane to the left twice
                c.movePlaneleft();
            }
            else if (key == KeyEvent.VK_KP_RIGHT || key == KeyEvent.VK_RIGHT)
            {
                //moves the plane to the right
                c.movePlaneRight();
            }
        }
            
    
    });}
    
 //this code below draws what will be on the frame   
    @Override
    public void paint( Graphics g )
 {
     super.paint(g);
     Graphics2D g2d = (Graphics2D)g;
     
     //this chunck of code draws the background image and also moves it when repaint is called
     if(imgY >= height)
         imgY = 0-height;//repositions the first location variable of the image
     if(imgY2 >= height )
        imgY2 = -height;//repostions the second location variable of the image
     g2d.drawImage(d, 0, imgY, width, height, this);//draws the image using the first location variable
     imgY++;
     if(imgY >=0 || imgY2 >=0)
          g2d.drawImage(d, 0,  imgY2, width, height, this);//redraws the same image using the second location variable
     imgY2++;
      
     //this line of code draws the plane
     g2d.drawImage(c.getImage(), c.getX(), c.getY(), 50,70,this);
     
        //this loop draws all the clouds in the loop
     bb.stream().forEach((b) -> {
         g2d.drawImage(b.getImage(), b.getX(), b.getY(), b.getW(), b.getH(), this);
     });
     
     //this block of code will show the "Game over" message if there is a colusion between a cloud and the plane
     if(Colid && Blocks < 60){
         g.setFont(f2);
         g.drawString("GAME OVER", 200, 300);
         g.drawString("High Score: "+score, 195, 335); 
     }
     if(Blocks >= 60){
          g.setFont(f2);
         g.drawString("Congradulation !!!", 200, 300);
         g.drawString("YOU WON", 205, 335);
         g.drawString("High Score: "+score, 195, 365);
     }
     //the three blocks of codes below desplay the level of the player
     //block #1
     if(Blocks >= 20 && Blocks < 40){
         if(Blocks == 20 || Blocks == 21){
             g.setColor(Color.red);
             g.setFont(f2);
         g.drawString("LEVEL 2", 200, 300);
         }
         g.setColor(Color.white);
         g.fillRect(130, 9, 70, 30);
         g.setColor(Color.BLACK);
         g.setFont(f1);
         g.drawString("Level 2", 150, 27);   
     }
     //block #2
     else if(Blocks >= 40){
         if(Blocks == 40 || Blocks == 41){
             g.setColor(Color.red);
             g.setFont(f2);
         g.drawString("LEVEL 3", 200, 300);
         }
         g.setColor(Color.white);
         g.fillRect(130, 9, 70, 30);
         g.setColor(Color.BLACK);
         g.setFont(f1);
         g.drawString("Level 3", 150, 27);
     }
     //block #3
     else{
         g.setColor(Color.white);
         g.fillRect(130, 9, 70, 30);
         g.setColor(Color.BLACK);
         g.setFont(f1);
         g.drawString("Level 1", 150, 27);
         
     }
     
     //this block of code despalys the number of clouds that have passed and the current score of the player
     g.setColor(Color.BLACK);
     String s = Integer.toString(Blocks);
     String s2 = Integer.toString(score);
     g.setFont(f1);
     g.drawString("Clouds: "+s, 350, 20);
     g.drawString("Score: "+s2, 350, 40);
     
         
 }
  
    //the following method starts the movment of the different objects
    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run(){
        int num = 0;//this is a counter used to determin the distance between clouds
        
        while (!Colid){//the movment is enabled as long as there are no colusions
            try{
               
                num++;
                //because when the level increases the speed of the clouds increases and changes the distance between the clouds
                //that is desplayed. inorder to avoid that, the folowing blocks of if/else codes are deployed to change when the counter should be reset
                //when the counter is rest a new cloud object is created and added to the list
            if(Blocks >= 20){
               if(num >= 200){
                    newBlock();
                    num = 0;
                   }
            }
           if(Blocks >= 40 ){
               if(num >= 150){
                    newBlock();
                    num = 0;
                }
           }
             
           
           else{
                if(num >= 300){
                    newBlock();
                    num = 0;
                   
                    
                }
           }
                
                Thread.sleep(10);
                
            } catch (InterruptedException e)
            {
            }
            
            
            for(cloud bss: bb){//looks into all the clouds in the list
                bss.movedown();//moves all the clouds in the list
                Colid = c.checkColision(bss.getSections());//Colission test
                if(Colid){
                System.out.println("x: "+bb.get(0).getX()+" y:"+bb.get(0).getY());
                System.out.println("for the plane \n x:"+c.getX()+" y:"+c.getY());
                
                                  
                break;
                
                }
                repaint(); //repaintes all the new changes
                
            }
/*the code below removes the first cloud object in the list once five clouds have past after it
 the reson i did that is because  for almost all of the of the codes above that has to do with the clouds
 it has to look through all the clouds in the list. after the sixth cloud has fallen the first cloud
 has no use and as more clouds are added to the list the whole program will just go slow.*/
           if(bb.size()>=6){
               bb.remove(1);
            }
     
 //the two if statments change the speed of the clouds depending on the which level the player is in
           if(Blocks >= 20){
               dy = 2;
           }
           if(Blocks >= 40){
               dy = 3;
           }
        
        if(Blocks >= 60){
            Colid=true;
        }
        

    
}
}
}
