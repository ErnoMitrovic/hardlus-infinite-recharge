package org.firstinspires.ftc.teamcode.capacitaciones;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(name="Teleoperado")

public class Teleoperado extends OpMode{
    public DcMotor upRight;
    public DcMotor upLeft;
    public DcMotor downRight;
    public DcMotor downLeft;
    @Override
    public void init(){
        upRight = hardwareMap.get(DcMotor.class, "arriba_derecho");
        downRight = hardwareMap.get(DcMotor.class, "abajo_derecho");
        upLeft = hardwareMap.get(DcMotor.class, "arriba_izquierdo");
        downLeft = hardwareMap.get(DcMotor.class, "abajo_izquierdo");
        upLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        upRight.setDirection(DcMotorSimple.Direction.REVERSE);
        downLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        downRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    @Override
    public void init_loop(){

    }
    @Override
    public void loop(){
        double drive, turn, lateral;
        drive = -gamepad1.left_stick_y;
        turn = gamepad1.left_stick_x;
        lateral = gamepad1.right_stick_x;
        double upRightPower = Range.clip(drive - turn - lateral, -1, 1);
        double upLeftPower = Range.clip(drive + turn + lateral, -1, 1);
        double downRightPower = Range.clip(drive - turn + lateral, -1, 1);
        double downLeftPower = Range.clip(drive + turn - lateral, -1, 1);
        upRight.setPower(upRightPower);
        downRight.setPower(downRightPower);
        upLeft.setPower(upLeftPower);
        downLeft.setPower(downLeftPower);
        if(gamepad1.left_bumper){
            upRightPower *= 0.8;
            upLeftPower *= 0.8;
            downRightPower *= 0.8;
            downLeftPower *= 0.8;
        }
        if(gamepad1.right_bumper){
            upRightPower *= 0.6;
            upLeftPower *= 0.6;
            downRightPower *= 0.6;
            downLeftPower *= 0.6;
        }
    }
    @Override
    public void start(){
        
    }
    @Override
    public void stop(){
        
    }
}
