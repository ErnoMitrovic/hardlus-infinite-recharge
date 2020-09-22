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
        //Con el método clip solo se admiten los valores entre -1 y 1
        double upRightPower = Range.clip(drive - turn - lateral, -1, 1);
        double upLeftPower = Range.clip(drive + turn + lateral, -1, 1);
        double downRightPower = Range.clip(drive - turn + lateral, -1, 1);
        double downLeftPower = Range.clip(drive + turn - lateral, -1, 1);
    }
    @Override
    public void start(){
        
    }
    @Override
    public void stop(){
        
    }
}
