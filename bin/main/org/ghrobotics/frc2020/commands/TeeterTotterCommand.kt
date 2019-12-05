package org.ghrobotics.frc2020.commands

import asSource
import com.ctre.phoenix.sensors.PigeonIMU
import edu.wpi.first.wpilibj.experimental.command.SendableCommandBase
import org.ghrobotics.frc2020.subsytems.DriveSubsystem
import org.ghrobotics.lib.commands.FalconCommand
import org.ghrobotics.lib.mathematics.units.derivedunits.velocity
import org.ghrobotics.lib.mathematics.units.meter

class TeeterTotterCommand : FalconCommand(DriveSubsystem){
    private val P = .02
    private val I = 0.0
    private val D = 0.0
    var integral = 0.0
    private var setpoint = 0.0F
    var current = 0.0F
    var previousError  = 0.0F
    private var output = 0.0

    //private val gyro = PigeonIMU(17)

    private fun  PID(){
        var myPitch = DriveSubsystem.gyro.roll
        val error = setpoint - myPitch
        this.integral += error*0.02
        val derivative = (error - this.previousError) / .02
        this.output = P*error + I*this.integral + D*derivative
        previousError = error
        println("Error: $error")
        println("Output: $output")
        println("roll: $myPitch")

    }

    override fun execute() {
        PID()
//        DriveSubsystem.setVelocity(-output.meter.velocity, -output.meter.velocity)
        DriveSubsystem.setOutput(output, output)

    }

    


}