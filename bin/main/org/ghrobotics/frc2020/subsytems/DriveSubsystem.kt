package org.ghrobotics.frc2020.subsytems

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import org.ghrobotics.frc2020.Constants
import org.ghrobotics.frc2020.commands.TeleopDriveCommand
import org.ghrobotics.lib.commands.FalconSubsystem
import org.ghrobotics.lib.mathematics.units.amp
import org.ghrobotics.lib.mathematics.units.derivedunits.LinearVelocity
import org.ghrobotics.lib.mathematics.units.derivedunits.feetPerSecond
import org.ghrobotics.lib.mathematics.units.millisecond
import org.ghrobotics.lib.motors.ctre.FalconSRX

object DriveSubsystem : FalconSubsystem() { //extends FalconSubsystem base class

    //4 motor variables
    private val leftMaster = FalconSRX(Constants.kDriveLeftMasterId, Constants.kDriveNativeUnitModel)//FalconSRX is motor name
    private val leftSlave = FalconSRX(Constants.kDriveLeftSlaveId, Constants.kDriveNativeUnitModel)
    //get motor ids and native unit conversion things

    private val rightMaster = FalconSRX(Constants.kDriveRightMasterId, Constants.kDriveNativeUnitModel)
    private val rightSlave = FalconSRX(Constants.kDriveRightSlaveId, Constants.kDriveNativeUnitModel)

    //set constant, 17 ft/s is max speed, multiply by 12 because max volts is 12
    const val kf = 12/17.0

    init{
        //follow sir
        leftSlave.follow(leftMaster)
        rightSlave.follow(rightMaster)

        leftMaster.outputInverted = false
        leftSlave.outputInverted = false

        //invert right so that it goes forward
        rightMaster.outputInverted = true
        rightSlave.outputInverted = true

        leftMaster.configCurrentLimit(true, FalconSRX.CurrentLimitConfig(
                90.amp,
                500.millisecond,
                38.amp
             )
        )

        //if robot is being pushed into a wall and current goes crazy, limit the current so that it doesn't pop and die
        //very very important
        //ctr click on methods to see their parameters and stuff to include
        rightMaster.configCurrentLimit(true, FalconSRX.CurrentLimitConfig(
                90.amp, //peak current limit
                500.millisecond, //peak current limit duration
                38.amp //continuous current limit
                //u can use .amp, .inches, .millisecond to specify units yayy falconlibrary
            )
        )

        //enable encoder data
        leftMaster.feedbackSensor = FeedbackDevice.QuadEncoder
        rightMaster.feedbackSensor = FeedbackDevice.QuadEncoder

        //configure p constant. If p is too high, then velocity values oscillate. P is multiplied to error of
        // setpoint - current (velocity)
        leftMaster.talonSRX.config_kP(0, 10.0)
        rightMaster.talonSRX.config_kP(0, 10.0)

        defaultCommand = TeleopDriveCommand()
        //sets default command to use

    }

    //function
    //if one is 1 and the other is -1 (12, -12), robot will spin spin
    fun set(left: Double, right: Double){ //in Kotlin variables go first, then datatype
        leftMaster.setDutyCycle(left) //(percentoutput)sends it voltage between 0 abd 12
        rightMaster.setDutyCycle(right)
    }
    //.value is meters per sec
    fun setVelocity(left: LinearVelocity, right: LinearVelocity){
        leftMaster.setVelocity(left.value, left.feetPerSecond * kf)//adds arbitrary feed forward to kp * (setpoint - current)
        rightMaster.setVelocity(right.value, right.feetPerSecond * kf)
        //final output {kp * [setpoint - current(velocity)] + kf}
    }


}



