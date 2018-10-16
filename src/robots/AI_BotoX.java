/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;
import robocode.*;

/**
 *
 * @author itiel
 */
public class AI_BotoX extends Robot {
    
    private double MAX_X;
    private double MAX_Y;
    private double HALF_X;
    private double HALF_Y;
    private double FIRE_DISTANCE;
    
    private double hNORTH = 0;
    private double hEAST = 90;
    private double hSOUTH = 180;
    private double hWEST = 270;
    
   
    
    public void Move() {
        
        LookAtFront();
        ahead(200);
        turnRight(90);
        ahead(200);
        turnLeft(90);
        ahead(200);
        turnRight(90);
        ahead(200);
        turnLeft(90);
        ahead(200);
    }
    
    public void LookAtFront() {
        
        if (getHeading() == hSOUTH) {
            turnLeft(180);
        }else if(getHeading() == hWEST) {
            turnLeft(90);
        }else if(getHeading() == hEAST) {
            turnRight(90);
        }
    }

    @Override
    public void run() {
       
       MAX_X = getBattleFieldWidth();
       MAX_Y = getBattleFieldHeight();
       
       HALF_X = MAX_X / 2;
       HALF_Y = MAX_Y / 2;
       
       FIRE_DISTANCE = MAX_X * 0.25;
 
       LookAtFront();
       
       out.println("RUNNING!");
 
       while(true) {
           Move();
       }
   }

   @Override
   public void onHitByBullet(HitByBulletEvent e) {
       Move();
   }
   
   public void onDetectedRobot() {
       
   }
   
   @Override
   public void onScannedRobot(ScannedRobotEvent e) {
       ramboMode();
       
       if (e.getDistance() < 10) turnLeft(90);
   }
   
   @Override
   public void onHitWall(HitWallEvent event) {

       // Vol anar més enllà de la pared dretana
        if (getX() >= HALF_X) {
                if (getY() >= HALF_Y) {
                    if (getHeading() >= hNORTH && getHeading() <= hEAST) {
                        turnLeft(90);
                        out.println("UNO");
                    }else{
                        turnLeft(180);
                        out.println("DOS");
                    }
                }else{
                    if (getHeading() >= hEAST && getHeading() <= hSOUTH) {
                        turnLeft(90);
                        out.println("TRES");
                    }else{
                         turnLeft(180);
                        out.println("QUATRO");
                    }
                }
        }else{
                if (getY() >= HALF_Y) {
                    if (getHeading() >= hNORTH && getHeading() <= hWEST) {
                        turnLeft(90);
                        out.println("CINCO");
                    }else{
                        turnLeft(getHeading());
                        out.println("SEIS");
                    }
                }else{
                    if (getHeading() >= hNORTH && getHeading() <= hSOUTH) {
                        turnLeft(90);
                        out.println("SIETE");
                    }else{
                        turnLeft(getHeading());
                        out.println("OCHO");
                    }
                }  
        }

   }
   
   public void ramboMode() {
       if(getEnergy() > 50) {
            fire(2);
            scan();
            
       }
       else {
            fire(1);
            scan();
       }       
   }
    
}
