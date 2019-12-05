package org.ghrobotics.frc2020.subsytems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import org.ghrobotics.frc2020.Constants
import org.ghrobotics.frc2020.commands.TurretCommand
import org.ghrobotics.lib.commands.FalconSubsystem
import org.ghrobotics.lib.mathematics.units.nativeunits.DefaultNativeUnitModel
import org.ghrobotics.lib.motors.FalconMotor
import org.ghrobotics.lib.motors.ctre.FalconSRX
import org.ghrobotics.lib.motors.rev.FalconMAX

//import com.revrobotics.CANSparkMaxLowLevel
//import org.ghrobotics.lib.motors.rev.FalconMAX

object Turret : FalconSubsystem() {

    private val turretMotor = FalconMAX(CANSparkMax(Constants.kTurretId, CANSparkMaxLowLevel.MotorType.kBrushless),DefaultNativeUnitModel)

    fun spinTurret(output : Double){

        turretMotor.setDutyCycle(output)
    }

    init{

        defaultCommand = TurretCommand()

    }
}