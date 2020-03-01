package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class MotorJoystickCommand extends CommandBase
{
    public MotorJoystickCommand()
    {
        addRequirements(ExampleSubsystem.getInstance());
    }

    public void execute()
    {
        ExampleSubsystem.getInstance().setMotorSpeedRaw(ExampleSubsystem.getInstance().getJoystickTurn());
    }

    public boolean isFinished()
    {
        return false;
    }
}