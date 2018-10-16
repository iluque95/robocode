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

   public void run() {
       turnLeft(getHeading());
       while(true) {
           ahead(1000);
           turnRight(90);
       }
   }
   public void onScannedRobot(ScannedRobotEvent e) {
       fire(1);
       scan();
   }
   public void onHitByBullet(HitByBulletEvent e) {
       turnLeft(180);
   }
    
}
