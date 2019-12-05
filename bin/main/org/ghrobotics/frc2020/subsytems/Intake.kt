package org.ghrobotics.frc2020.subsytems

import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.Solenoid
import edu.wpi.first.wpilibj.Talon
import org.ghrobotics.frc2020.Constants
import org.ghrobotics.frc2020.commands.IntakeCommand
import org.ghrobotics.lib.commands.FalconSubsystem
import org.ghrobotics.lib.mathematics.units.amp
import org.ghrobotics.lib.mathematics.units.millisecond
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitModel
import org.ghrobotics.lib.motors.ctre.FalconSRX
import org.ghrobotics.lib.wrappers.hid.*

object Intake : FalconSubsystem() {
    val masterMotor = //TalonSRX(Constants.kIntakeLeftID)
            FalconSRX(Constants.kIntakeLeftID, Constants.kDriveNativeUnitModel)
    val slaveMotor = FalconSRX(Constants.kIntakeRightID, Constants.kDriveNativeUnitModel)

    val wristMotor = FalconSRX(Constants.kwristID, Constants.kDriveNativeUnitModel)

    private val solenoid = Solenoid(Constants.kPCMId, Constants.kIntakeLauncherSolenoidId)
    init {

        slaveMotor.follow(masterMotor)

        masterMotor.outputInverted = true
        slaveMotor.outputInverted = false

        masterMotor.configCurrentLimit(true, FalconSRX.CurrentLimitConfig(
                90.amp,
                500.millisecond,
                25.amp
        )
        )

        slaveMotor.configCurrentLimit(true, FalconSRX.CurrentLimitConfig(
                90.amp,
                500.millisecond,
                25.amp
        )
        )

        wristMotor.configCurrentLimit(true, FalconSRX.CurrentLimitConfig(
                90.amp,
                500.millisecond,
                25.amp
        )
        )


        setSolenoid(false)

    }

    fun setSolenoid(extended: Boolean) {
        solenoid.set(extended)

    }

    init{

        defaultCommand = IntakeCommand(true)
    }

}



