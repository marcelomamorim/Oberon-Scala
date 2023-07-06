package br.unb.cic.oberon.ir.asm

/**
 * RISC-V Assembly Registers
 * Abstraction in Scala
 * @author marcelomamorim
 *
 *  RISC-V Assembly Doc:
 *  x1-x31 ::> "There are 31 general-purpose registers x1–x31, which hold integer values."
 *  f0-f21 ::> "The F extension adds 32 floating-point registers, f0–f31, each 32 bits wide"
 *
 */
class Register {}

/**
 *
 *  RISC-V Assembly Doc:
 *  x1-x31 ::> "There are 31 general-purpose registers x1–x31, which hold integer values."
 *
 */
class GeneralPurposeRegister() extends Register {}

/**
 *
 *  RISC-V Assembly Doc:
 *  f0-f21 ::> "The F extension adds 32 floating-point registers, f0–f31, each 32 bits wide"
 *
 */
case class FloatingPointRegister() extends Register {}
