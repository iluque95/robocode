/*torre
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;
import robocode.*;

/**
 *
 * @author heri
 */
public class Torreta extends Robot{
    
    private static double bearingThreshold = 5;
    
    public void run(){
        turnLeft(getHeading());
        while (true){
            turnGunLeft(90);
            turnRadarLeft(90);
        }
    }
    
    double normalizeBearing(double bearing){
        while (bearing > 180) bearing-=360;
        while (bearing < -180) bearing +=360;
        return bearing;
    }
    
    public void onScannedRobot(ScannedRobotEvent e){
        if (normalizeBearing(e.getBearing()) < bearingThreshold) fire(1);
    }
    
    public void onHitByBullet(HitByBulletEvent e){
   turnLeft(180);
   ahead(50);
    }
    
}
