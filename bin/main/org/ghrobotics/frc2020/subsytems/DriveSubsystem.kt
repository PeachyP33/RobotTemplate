package org.ghrobotics.frc2020.subsytems


import asSource
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.ctre.phoenix.sensors.PigeonIMU
import com.kauailabs.navx.frc.AHRS
import com.team254.lib.physics.DifferentialDrive
import edu.wpi.first.wpilibj.SPI
import edu.wpi.first.wpilibj.SPI.Port
import org.ghrobotics.frc2020.Constants
import org.ghrobotics.frc2020.commands.TeeterTotterCommand
import org.ghrobotics.frc2020.commands.TeleopDriveCommand
import org.ghrobotics.frc2020.commands.curvatureDriveCommand
import org.ghrobotics.lib.commands.FalconSubsystem
import org.ghrobotics.lib.localization.Localization
import org.ghrobotics.lib.localization.TankEncoderLocalization
import org.ghrobotics.lib.mathematics.twodim.control.TrajectoryTracker
import org.ghrobotics.lib.mathematics.units.amp
//import org.ghrobotics.lib.mathematics.units.derived.LinearVelocity
import org.ghrobotics.lib.mathematics.units.derivedunits.LinearVelocity
import org.ghrobotics.lib.mathematics.units.derivedunits.feetPerSecond
import org.ghrobotics.lib.mathematics.units.millisecond
import org.ghrobotics.lib.motors.LinearFalconMotor
import org.ghrobotics.lib.motors.ctre.FalconSPX
import org.ghrobotics.lib.motors.ctre.FalconSRX
import org.ghrobotics.lib.subsystems.drive.TankDriveSubsystem


object DriveSubsystem : TankDriveSubsystem() {
    override val differentialDrive: DifferentialDrive
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val leftMotor: LinearFalconMotor
        get() = leftMaster
    override val rightMotor: LinearFalconMotor
        get() = rightMaster
    override val trajectoryTracker: TrajectoryTracker
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates. //extends FalconSubsystem base class


    private val leftMaster = FalconSRX(Constants.kDriveLeftMasterId, Constants.kDriveNativeUnitModel)
    private val leftSlave = FalconSPX(Constants.kDriveLeftSlaveId, Constants.kDriveNativeUnitModel)


    private val rightMaster = FalconSRX(Constants.kDriveRightMasterId, Constants.kDriveNativeUnitModel)
    private val rightSlave = FalconSPX(Constants.kDriveRightSlaveId, Constants.kDriveNativeUnitModel)


    val gyro = AHRS(SPI.Port.kMXP)
    
    override val localization = TankEncoderLocalization(
            gyro.asSource(),
            { leftMaster.encoder.position },
            { rightMaster.encoder.position }
    )

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


        rightMaster.configCurrentLimit(true, FalconSRX.CurrentLimitConfig(
                90.amp, //peak current limit
                500.millisecond, //peak current limit duration
                38.amp //continuous current limit
                //u can use .amp, .inches, .millisecond to specify units
            )
        )

        //enable encoder data
        leftMaster.feedbackSensor = FeedbackDevice.QuadEncoder
        rightMaster.feedbackSensor = FeedbackDevice.QuadEncoder


        leftMaster.talonSRX.config_kP(0, 10.0)
        rightMaster.talonSRX.config_kP(0, 10.0)

        defaultCommand = TeleopDriveCommand()
        //defaultCommand = curvatureDriveCommand()


    }


    fun setOutput(left: Double, right: Double){
        leftMaster.setDutyCycle(left) //(percentoutput)sends it voltage between 0 and 12
        rightMaster.setDutyCycle(right)
//        leftMaster.setVelocity(left)
    }
    //.value is meters per sec
    fun setVelocity(left: LinearVelocity, right: LinearVelocity){
        leftMaster.setVelocity(left.value, left.feetPerSecond * kf)//adds arbitrary feed forward to kp * (setpoint - current)
        rightMaster.setVelocity(right.value, right.feetPerSecond * kf)
        //final output {kp * [setpoint - current(velocity)] + kf}
    }




}



