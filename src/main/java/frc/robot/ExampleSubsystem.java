package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase
{
    private static ExampleSubsystem singleton;

    private final SendableChooser<MotorType> motorChooser = new SendableChooser<>();

    private int motorId;

    private int joystickId;

    private final double JOYSTICK_DEAD_ZONE = 0.2;

    private VictorSPX victorController;

    private TalonSRX talonController;

    private TalonFX falconController;

    private CANSparkMax sparkController;

    public static double goalPercent;

    private Joystick joystickController;
  
    enum MotorType 
    {
        Falcon, TalonSRX, VictorSPX, NEO550, NEO, None;
    }

    private ExampleSubsystem()
    {
        for(int i = 0; i < MotorType.values().length; i++)
        {
            if(MotorType.values()[i] == MotorType.None)
                motorChooser.setDefaultOption("None", MotorType.None);
            else
                motorChooser.addOption(MotorType.values()[i].name(), MotorType.values()[i]);
        }

        smartDashboardInit();
    }

    public static void init()
    {
        if(singleton == null)
            singleton = new ExampleSubsystem();
    }

    public static ExampleSubsystem getInstance()
    {
        if(singleton == null)
            init();
        return singleton;
    }

    private void smartDashboardInit()
    {
        SmartDashboard.putNumber("Motor ID", 0);
        SmartDashboard.putNumber("Goal Percent", 0);
        SmartDashboard.putNumber("Joystick ID", 0);
        SmartDashboard.putData(motorChooser);
    }

    
    public double getJoystickTurn()
    {
        return snapDriveJoystick(joystickController.getX(), JOYSTICK_DEAD_ZONE);
    }

    public double snapDriveJoystick(double x, double deadZone)
    {
        if(Math.abs(x) < deadZone)
            return 0.0;
        return x;
    }

    public void setMotor()
    {
        System.out.println("Set Motor has beed called: " + motorChooser.getSelected());
        motorId = (int)SmartDashboard.getNumber("Motor ID", 0);
        joystickId = (int) SmartDashboard.getNumber("Joystick ID", 0);
        joystickController = new Joystick(joystickId);
        if(motorChooser.getSelected() == MotorType.Falcon)
        {
            falconController = new TalonFX(motorId);
        }

        else if(motorChooser.getSelected() == MotorType.TalonSRX)
        {
            talonController = new TalonSRX(motorId);
        }

        else if(motorChooser.getSelected() == MotorType.VictorSPX)
        {
            victorController = new VictorSPX(motorId);
        }

        else if(motorChooser.getSelected() == MotorType.NEO550)
        {
            sparkController = new CANSparkMax(motorId, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);
        }

        else if(motorChooser.getSelected() == MotorType.NEO)
        {
            sparkController = new CANSparkMax(motorId, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushed);
        }
    }

    public void setMotorSpeedRaw(double percent)
    {
        System.out.println("Set Motor Speed Raw Has Been Called: " + percent);
        if(motorChooser.getSelected() == MotorType.Falcon)
        {
            falconController.set(ControlMode.PercentOutput, percent);
        }

        else if(motorChooser.getSelected() == MotorType.TalonSRX)
        {
            talonController.set(ControlMode.PercentOutput, percent);
        }

        else if(motorChooser.getSelected() == MotorType.VictorSPX)
        {
            victorController.set(ControlMode.PercentOutput, percent);
            System.out.println("Victor called");
        }

        else if(motorChooser.getSelected() == MotorType.NEO550 || motorChooser.getSelected() == MotorType.NEO)
        {
            sparkController.set(percent);
        }
    }

    public void periodic()
    {
        goalPercent = SmartDashboard.getNumber("Goal Percent", 0);
    }
}