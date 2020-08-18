package br.unb.cic.oberon.parser

import org.antlr.v4.runtime._

import br.unb.cic.oberon.ast._
import scala.jdk.CollectionConverters._

/**
  * A parser for the Oberon language using 
  * the ANTLR infrastructure. The main idea of 
  * this parser is to parse a Oberon source code 
  * and convert it into our Oberon AST representation
  * in Scala. Here we use the ANTLR idiom for parsing 
  * source code. 
  * 
  * @author rbonifacio   
  */
object ScalaParser {
  def parse(input: String) : OberonModule = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new OberonLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new OberonParser(tokens)

    val visitor = new ParserVisitor
    visitor.visitCompilationUnit(parser.compilationUnit())
    visitor.module
  }
}

class ParserVisitor {

  var module : OberonModule = _

  def visitCompilationUnit(ctx: OberonParser.CompilationUnitContext): Unit = {
    val name = ctx.name
    val constants = ctx.constant().asScala.toList.map(c => visitConstant(c))
    val variables = ctx.varDeclaration().asScala.toList.map(v => visitVariableDeclaration(v))
    module = OberonModule(name.getText, constants, variables)
  }

  /**
   * Visit a constant
   * @param ctx  constant declaration visited
   * @return a constant declaration representation
   */
  def visitConstant(ctx: OberonParser.ConstantContext): Constant = {
    val variable = Variable(ctx.varName.getText)
    val value = visitExpression(ctx.exp)
    Constant(variable, value)
  }

  /**
   * Visit a variable declaration
   * @param ctx variable declaration context
   * @return a variable declaration representation
   */
  def visitVariableDeclaration(ctx: OberonParser.VarDeclarationContext): VariableDeclaration = {
    val variables = ctx.vars.asScala.toList.map(v => Variable(v.getText))
    val variableType = visitOberonType(ctx.varType)
    VariableDeclaration(variables, variableType)
  }

  /**
   * Visit an expression node
   * @param ctx the context expression
   * @return the abstract representation of an expression.
   */
  def visitExpression(ctx: OberonParser.ExpressionContext) : Expression = {
    IntValue(ctx.Number().getText.toInt)
  }

  /**
   * Visit an oberon type.
   *
   * Note: this implementation uses pattern matching
   * as an expression.
   *
   * @param ctx the oberon type context
   * @return a oberon type representation.
   */
  def visitOberonType(ctx: OberonParser.OberonTypeContext) : Type =
    ctx.getText match {
      case "INTEGER" => IntegerType
      case "BOOLEAN" => BooleanType
      case _         => UndefinedType
    }
}
