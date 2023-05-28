## AST -> TAC

### Proposta de refatoração

#### Parte 1 (Estrutural):

- Dragon Book: "Three-address code is built from two concepts: addresses and instructions"
  - Classe TacModule para comportar Address e Instruction
  - Antiga classe TAC será renomeada para Instruction
  
- Aumentar coesão
  - Refatorar a classe Address para não receber uma classe proveniente do OberonModule
  - Nesse caso seria necessário pensar o que podemos fazer com os tipos presentes na AST e como eles deveriam ficar dentro da abstração TAC
  
- Diminuir acoplamento
  - Essa refatoração para aumentar coesão resultaria numa diminuição do acoplamento também, já que as abstrações AST e TAC não ficariam apoiadas as duas em Types de AST (cada IR teria sua própria abordagem quanto aos tipos)

- Desenho de arquitetura
  - AS IS

![Arquitetura geração TAC](../imagens/estado-atual-geracao-tac.png)

  - PROPOSTA DE REFATORAÇÃO

![Arquitetura Pós Refatoração]()


#### Parte 2 (Detalhes):

- Retirar método e colocar como beforeEach() nos testes:
```
  //somente para testes
  def reset(): Unit = {
    tc = new TypeChecker()
    expVisitor = new ExpressionTypeVisitor(tc)
    Temporary.reset()
    LabelGenerator.reset()
  }
```

#### Comentários

a) Referência ao handout de Stanford

The convention followed in the examples below is that t1, t2, and so on refer to variables (either declared variables or temporaries) and L1, L2, etc. are used for labels.
Labels mark the target for a goto/branch and are used to identify function/method definitions and vtables.

b) Código escrito para a procedure call

```
      case ProcedureCallStmt(name, argsExps) =>
        val pushParams = argsExps.map { argument => { PushParam(new Temporary(argument.accept(expVisitor).get), "") } }
        val functionCall = Call(name)
        val popParam = PopParam(4, "")
        pushParams :+ functionCall :+ popParam
```