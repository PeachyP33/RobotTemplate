/*
 * FRC Team 5190
 * Green Hope Falcons
 */

package org.ghrobotics.frc2020

import com.team254.lib.physics.DCMotorTransmission
import com.team254.lib.physics.DifferentialDrive
import org.ghrobotics.lib.mathematics.units.amp
import org.ghrobotics.lib.mathematics.units.inch
import org.ghrobotics.lib.mathematics.units.lb
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitLengthModel
import org.ghrobotics.lib.mathematics.units.nativeunits.nativeUnits
import kotlin.math.pow

@Suppress("MemberVisibilityCanBePrivate", "unused")
object Constants {

    // Robot
    val kRobotMass = 60.lb
    const val kRobotMomentOfInertia = 10.0
    const val kRobotAngularDrag = 12.0
    val kRobotTrackWidth = 27.inch


    // Drivetrain
    const val kDriveLeftMasterId = 1
    const val kDriveLeftSlaveId = 2
    const val kDriveRightMasterId = 3
    const val kDriveRightSlaveId = 4
    const val kDrivePigeonId = 5

    val kDriveWheelRadius = 3.inch
    val kDriveNativeUnitModel = NativeUnitLengthModel(1440.nativeUnits, kDriveWheelRadius)//1440 is number of ticks

    const val kDriveKp = 2.75
    const val kDriveKd = 2.0

    const val kDriveKv = .15
    const val kDriveKa = .00
    const val kDriveKs = 1.2

    const val kDriveBeta = 2.0
    const val kDriveZeta = 0.7

    val kDriveDCTransmission = DCMotorTransmission(
        1 / kDriveKv,
        kDriveWheelRadius.value.pow(2) * kRobotMass.value / (2.0 * kDriveKa),
        kDriveKs
    )

    val kDriveModel = DifferentialDrive(
        kRobotMass.value,
        kRobotMomentOfInertia,
        kRobotAngularDrag,
        kDriveWheelRadius.value,
        kRobotTrackWidth.value / 2.0,
        kDriveDCTransmission,
        kDriveDCTransmission
    )

    val kDriveCurrentLimit = 38.amp

}
