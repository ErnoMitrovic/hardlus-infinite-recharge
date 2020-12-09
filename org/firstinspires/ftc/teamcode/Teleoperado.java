package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(name="Teleoperado")

public class Teleoperado extends OpMode{
    private NaveDelOlvido robot = new NaveDelOlvido();
    @Override
    public void init(){
        robot.getHardware(hardwareMap);
    }
    @Override
    public void init_loop(){

    }
    @Override
    public void loop(){
        double drive, turn, lateral, wobble;
        drive = -gamepad1.left_stick_y;
        turn = gamepad1.left_stick_x;
        lateral = gamepad1.right_stick_x;
        double upRightPower = Range.clip(drive - turn - lateral, -1, 1);
        double upLeftPower = Range.clip(drive + turn + lateral, -1, 1);
        double downRightPower = Range.clip(drive - turn + lateral, -1, 1);
        double downLeftPower = Range.clip(drive + turn - lateral, -1, 1);
        if(gamepad1.right_bumper && gamepad1.left_bumper){
            upRightPower *= 0.45;
            upLeftPower *= 0.45;
            downRightPower *= 0.45;
            downLeftPower *= 0.45;
        }
        else if(gamepad1.left_bumper){
            upRightPower *= 0.8;
            upLeftPower *= 0.8;
            downRightPower *= 0.8;
            downLeftPower *= 0.8;
        }
        else if(gamepad1.right_bumper){
            upRightPower *= 0.6;
            upLeftPower *= 0.6;
            downRightPower *= 0.6;
            downLeftPower *= 0.6;
        }
        wobblePower = Range.clip(-gamepad2.left_stick_y * 0.8, -0.8, 0.8);
        
        robot.wobble.setPower(wobblePower);
        
        robot.upRight.setPower(upRightPower);
        robot.downRight.setPower(downRightPower);
        robot.upLeft.setPower(upLeftPower);
        robot.downLeft.setPower(downLeftPower);
    }
    @Override
    public void start(){
        
    }
    @Override
    public void stop(){
        
    }
}
