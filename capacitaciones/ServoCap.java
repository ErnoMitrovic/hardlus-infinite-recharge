package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;


public class ServoCap extends OpMode{
    public Servo servoStandard = null;
    //CRServo extiende de DCMotorSimple
    public CRServo servoContinous = null;
    public void init(){
        servoStandard = hardwareMap.get(Servo.class, "servo_standard");
        servoContinous = hardwareMap.get(CRServo.class, "servo_continuo");
        servoStandard.setPosition(0.5);
        servoContinous.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    public void loop(){
        //[0, 1.0]
        servoStandard.setPosition(0.8);
        telemetry.addData("Current position", servoStandard.getPosition());
        servoContinous.setPower(0.3);
        /*Programar el modo click para los servos de rotación standard
        * también llamados angulares
        */
    }
	// todo: write your code here
}
