package org.firstinspires.ftc.teamcode.capacitaciones;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Teleoperado")

public class Teleoperado extends OpMode{
    /*
    * En la siguiente sesión, debemos implementar el metodo clip de la librería
    * com.qualcomm.robotcore.util
    */
    public DcMotor motor;
    @Override
    public void init(){
        telemetry.addData("Hola", " Mundo");
        hardwareMap.get(DcMotor.class, "Motor_derecho");
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setDirection(DcMotor.Direction.FORWARD);
    }
    @Override
    public void init_loop(){

    }
    @Override
    public void loop(){
        motor.setPower(gamepad1.left_stick_x);
        int limit = Range.clip(motor.getCurrentPosition(),0,2450);
        telemetry.addData("Clip output:", limit);
    }
    @Override
    public void start(){

    }
    @Override
    public void stop(){

    }
}
