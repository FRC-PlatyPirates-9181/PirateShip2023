// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FourBarArms extends SubsystemBase {
  private final CANSparkMax _armMotor = new CANSparkMax(Constants.IntakeConstants.armMotor, MotorType.kBrushless);
  private final RelativeEncoder _encoder = _armMotor.getEncoder();

  private double _armPower = Constants.IntakeConstants.armMotorPower;

  /** Creates a new FourBarArms. */
  public FourBarArms() {
    setDefaultCommand(new RunCommand(this::stop, this));

    _armMotor.restoreFactoryDefaults();

    _armMotor.burnFlash();
  }

  public void stop() {
    _armMotor.stopMotor();
  }

  public void armIn() {
    _armMotor.set(_armPower);
  }

  public void armOut() {
    _armMotor.set(-_armPower); 
  }

  public void go(double armPower) {
    _armMotor.set(armPower);
  }

  public void setArmPower (double armPower) {
    _armPower = armPower; 
  }

  public double getPosition () {
    return _encoder.getPosition();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Arm Position", _encoder.getPosition());
  }
}
