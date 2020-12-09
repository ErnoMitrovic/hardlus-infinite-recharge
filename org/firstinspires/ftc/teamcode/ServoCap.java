package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
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
        /*Programar el modo clip para los servos de rotación standard
        * también llamados angulares
        */
        modoClick(gamepad1.a);
    }
    public void modoClick(boolean press){
        boolean currentState = false;
        boolean prevState = false;
        boolean max = false;
        double position = 0;
        if (press){
            if(!prevState && !max){
                position = 0.8;
                max = true;
            }
            else if(!prevState && max){
                position = 0;
                max = true;
            }
        }
        else prevState = press;
        servoStandard.setPosition(position);
        telemetry.addData("Position servo: ", servoStandard.getPosition());
    }
    //Debug en compilador:
    /*
    Scanner sc = new Scanner(System.in);
        boolean currentState = false;
        boolean press = input(sc);
        boolean prevState = false;
        boolean max = false;
        int position = 0;
        while (true){
            if(press){
                if(!prevState && !max){
                    position = 8;
                    max = true;
                    prevState = true;
                }else if (!prevState && max){
                    position = 0;
                    max = false;
                    prevState = true;
                } 
            }else prevState = press;
            System.out.println("Current position: " + position);
            press = input(sc);
        }
    }
    public static boolean input(Scanner sc){
        return sc.next().toUpperCase().equals("P") ? true : false;
    }
    */
}
