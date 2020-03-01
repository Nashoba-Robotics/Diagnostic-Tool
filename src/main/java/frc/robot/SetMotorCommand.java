package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SetMotorCommand extends CommandBase
{
    public SetMotorCommand()
    {
        addRequirements(ExampleSubsystem.getInstance());
    }

    @Override
    public void execute()
    {
        ExampleSubsystem.getInstance().setMotor();
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}