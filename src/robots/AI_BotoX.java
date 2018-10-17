/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;
import java.awt.Color;
import robocode.*;
import robocode.util.Utils;

/**
 *
 * @author itiel
 */
public class AI_BotoX extends AdvancedRobot {
    
    public void run() { 
        // Robot Customization
        setBodyColor(Color.BLUE);
        setGunColor(Color.GRAY);
        setRadarColor(Color.GRAY);
        setBulletColor(Color.BLUE);
            
        // Robot adjust
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForRobotTurn(true);
        turnRadarRightRadians(Double.POSITIVE_INFINITY);
        
        while(true) {
            scan();
            execute();
        }
    }

    public void onScannedRobot (ScannedRobotEvent e) {
        double position = e.getBearingRadians()+getHeadingRadians();
	double latVel = e.getVelocity() * Math.sin(e.getHeadingRadians() - position);
	setTurnRadarLeftRadians(getRadarTurnRemainingRadians());
	
        double gunTurnAmt = robocode.util.Utils.normalRelativeAngle(position - getGunHeadingRadians() + latVel/10);//
        setTurnGunRightRadians(gunTurnAmt); 
        
        setTurnRightRadians(robocode.util.Utils.normalRelativeAngle(position - getHeadingRadians() + latVel/getVelocity()));
	
        setAhead((e.getDistance() - 200));
        
	if(e.getDistance() < 210 ) setFire(3);
    }
    
    public void onHitWall(HitWallEvent e) {
        setAhead(-1);
    }
    
}