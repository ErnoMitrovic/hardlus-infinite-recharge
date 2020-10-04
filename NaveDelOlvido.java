package org.firstinspires.ftc.teamcode.capacitaciones;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Disabled

public class NaveDelOlvido {
    public DcMotor upRight = null;
    public DcMotor upLeft = null;
    public DcMotor downRight = null;
    public DcMotor downLeft = null;
    
    private Orientation angles;
    private BNO055IMU imu;
    private LinearOpMode programa;
    public NaveDelOlvido(LinearOpMode programa){
        this.programa = programa;
    }
    public void getHardware (HardwareMap hwMap){
        upRight = hwMap.get(DcMotor.class, "frente_derecha");
        upLeft = hwMap.get(DcMotor.class, "frente_izquierda");
        downRight = hwMap.get(DcMotor.class, "atras_derecha");
        downLeft = hwMap.get(DcMotor.class, "atras_izquierda");
        imu = hwMap.get(BNO055IMU.class, "imu");
        
        upRight.setDirection(DcMotorSimple.Direction.FORWARD);
        downRight.setDirection(DcMotorSimple.Direction.FORWARD);
        upLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        downLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public double desviacion(){
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        programa.sleep(25);
        return angles.firstAngle;
    }
    public void frenar(){
        upLeft.setPower(0);
        upRight.setPower(0);
        downLeft.setPower(0);
        downRight.setPower(0);
    }
    //Metodos para los autonomos
    //Medida de los encoders
    final int TICKS = 0;
    public void moverDistanciaRecta(double distancia, double velocidad){
        final int conversion = (int) Math.round(distancia * TICKS / 10.61 / Math.PI);
        double desiredPosition = desviacion();
        upRight.setTargetPosition(conversion);
        upLeft.setTargetPosition(conversion);
        downRight.setTargetPosition(conversion);
        downLeft.setTargetPosition(conversion);
        upRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        upLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        downRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        downLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(programa.opModeIsActive() && downLeft.isBusy() && downRight.isBusy() && 
        upLeft.isBusy() && upRight.isBusy()){
            double desviacion = desviacion();
            double error = (desiredPosition - desviacion) / desiredPosition;
            final double PROPORCIONAL = 0.002;
            double leftPower = 0;
            double rightPower = 0;
            if (distancia > 0){
                leftPower = velocidad;
                rightPower = velocidad;
                leftPower -= leftPower * error * PROPORCIONAL;
                rightPower += rightPower * error * PROPORCIONAL;
            }
            else if (distancia < 0){
                velocidad *= -1;
                leftPower = velocidad;
                rightPower = velocidad;
                leftPower -= leftPower * error * PROPORCIONAL;
                rightPower += rightPower * error * PROPORCIONAL;
            }
            upLeft.setPower(leftPower);
            downLeft.setPower(leftPower);
            upRight.setPower(rightPower);
            downRight.setPower(rightPower);
        }
        frenar();
    }
    public void moverDistanciaLateral(double distancia, double velocidad){
        final int conversion = (int) Math.round(distancia * TICKS / 10.61 / Math.PI);
        double desiredPosition = desviacion();
        upRight.setTargetPosition(-conversion);
        upLeft.setTargetPosition(conversion);
        downRight.setTargetPosition(conversion);
        downLeft.setTargetPosition(-conversion);
        upRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        upLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        downRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        downLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(programa.opModeIsActive() && downLeft.isBusy() && downRight.isBusy() && 
        upLeft.isBusy() && upRight.isBusy()){
            double desviacion = desviacion();
            double error = (desiredPosition - desviacion) / desiredPosition;
            final double PROPORCIONAL = 0.002;
            double upPower = 0;
            double downPower = 0;
            if (distancia > 0){
                upPower = velocidad;
                downPower = velocidad;
                upPower -= leftPower * error * PROPORCIONAL;
                downPower += rightPower * error * PROPORCIONAL;
            }
            else if (distancia < 0){
                velocidad *= -1;
                upPower = velocidad;
                downPower = velocidad;
                upPower -= leftPower * error * PROPORCIONAL;
                downPower += rightPower * error * PROPORCIONAL;
            }
            upLeft.setPower(upPower);
            downLeft.setPower(downPower);
            upRight.setPower(upPower);
            downRight.setPower(downPower);
        }
        frenar();
    }
}
