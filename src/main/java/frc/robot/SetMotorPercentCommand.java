package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SetMotorPercentCommand extends CommandBase
{
    public SetMotorPercentCommand()
    {
        addRequirements(ExampleSubsystem.getInstance());
    }

    public void execute()
    {
        ExampleSubsystem.getInstance().setMotorSpeedRaw(ExampleSubsystem.goalPercent);
    }

    public void end(boolean interrupted)
    {
        System.out.println("Set Motor Percent Command Has Ended " + interrupted);
        ExampleSubsystem.getInstance().setMotorSpeedRaw(0);
    }

    public boolean isFinished()
    {
        return false;
    }
}