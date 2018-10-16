/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;
import robocode.*;
import robocode.util.Utils;

/**
 *
 * @author itiel
 */
public class AI_BotoX extends AdvancedRobot {
    
    private double MAX_X;
    private double MAX_Y;
    
    private double hNORTH = 0;
    private double hEAST = 90;
    private double hSOUTH = 180;
    private double hWEST = 270;
    
    private double loop = 60.0;
    private boolean ready = false;
    
    
    public boolean LegalPos() {
        boolean legal = true;


        if (getX() == MAX_X && getHeading() == hEAST) {
            legal = false;
        }else if(getX() == 0 && getHeading() == hWEST) {
            legal = false;
        }else if(getY() == MAX_Y && getHeading() == hNORTH) {
            legal = false;
        }else if(getY() == 0 && getHeading() == hSOUTH) {
            legal = false;
        }

        return legal;

    }
    
    public void Move() {
        
        if (LegalPos()) {
            LookAtFront();
            ahead(100);
            turnRight(90);
            ahead(100);
            turnLeft(90);
            ahead(100);
            turnRight(90);
            ahead(100);
            turnLeft(90);
            ahead(100);
        }else{
            turnLeft(180);
        }
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
        
       LookAtFront();        
       
       setAdjustRadarForGunTurn(true);
       
       setTurnRadarRight(Double.POSITIVE_INFINITY);
       setTurnGunRight(Double.POSITIVE_INFINITY);
       
       
       while(true) {
           scan();
           execute();  
       }
   }
   
 
   public void onScannedRobot(ScannedRobotEvent e) {
       double radarTurn = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
       
       setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));
       setTurnGunRightRadians(Utils.normalRelativeAngle(radarTurn));
       
   }
   
   public void ramboMode() {
        fire(3);
   }
  
}
