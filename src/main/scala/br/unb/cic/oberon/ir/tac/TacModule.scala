package br.unb.cic.oberon.ir.tac

// Dragon Book: "Three-address code is built from two concepts: addresses and instructions"
case class TacModule(addresses : List[Address], instructions: List[Instruction]) {}
