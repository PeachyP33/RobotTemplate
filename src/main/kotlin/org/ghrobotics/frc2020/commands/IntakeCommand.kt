package org.ghrobotics.frc2020.commands

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj.GenericHID
import org.ghrobotics.frc2020.Constants
import org.ghrobotics.frc2020.Controls
import org.ghrobotics.frc2020.commands.TurretCommand.Companion.output
import org.ghrobotics.frc2020.subsytems.DriveSubsystem
import org.ghrobotics.frc2020.subsytems.Intake
import org.ghrobotics.frc2020.subsytems.Intake.masterMotor
import org.ghrobotics.frc2020.subsytems.Intake.slaveMotor
import org.ghrobotics.lib.commands.FalconCommand
import org.ghrobotics.lib.commands.FalconSubsystem
import org.ghrobotics.lib.wrappers.hid.*

class IntakeCommand(private val releasing: Boolean) : FalconCommand(Intake){

    init{

        addRequirements(Intake)
    }


        val driverController = xboxController(0) {

            triggerAxisButton(GenericHID.Hand.kRight).change(IntakeCommand(true))
            button(kBumperRight).change(IntakeCommand(false))




    }
    companion object {

        val output1 = Controls.driverController.getRawButton(kBumperLeft)
        val output2 = Controls.driverController.getRawButton(kBumperRight)
        val output3 = Controls.driverController.getRawButton(kA)
    }


    private fun state(){

        if (output1()){

            masterMotor.outputInverted = true
            slaveMotor.outputInverted = false
            Intake.setSolenoid(true)
            Intake.masterMotor.setDutyCycle(0.5)
        }

        if (output2()){

            masterMotor.outputInverted = false
            slaveMotor.outputInverted = true
            Intake.setSolenoid(false)
            Intake.masterMotor.setDutyCycle(-0.5)
        }
    }

    override fun execute(){
        state()
        if (output3()){
            Intake.wristMotor.setDutyCycle(0.5)
        }

        //masterMotor.set(ControlMode.PercentOutput, 0.5 )


    }

}


