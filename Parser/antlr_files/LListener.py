# Generated from L.g4 by ANTLR 4.7.2
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .LParser import LParser
else:
    from LParser import LParser

# This class defines a complete listener for a parse tree produced by LParser.
class LListener(ParseTreeListener):

    # Enter a parse tree produced by LParser#start.
    def enterStart(self, ctx:LParser.StartContext):
        pass

    # Exit a parse tree produced by LParser#start.
    def exitStart(self, ctx:LParser.StartContext):
        pass


    # Enter a parse tree produced by LParser#statement.
    def enterStatement(self, ctx:LParser.StatementContext):
        pass

    # Exit a parse tree produced by LParser#statement.
    def exitStatement(self, ctx:LParser.StatementContext):
        pass


    # Enter a parse tree produced by LParser#funcInnerStatement.
    def enterFuncInnerStatement(self, ctx:LParser.FuncInnerStatementContext):
        pass

    # Exit a parse tree produced by LParser#funcInnerStatement.
    def exitFuncInnerStatement(self, ctx:LParser.FuncInnerStatementContext):
        pass


    # Enter a parse tree produced by LParser#entryPoint.
    def enterEntryPoint(self, ctx:LParser.EntryPointContext):
        pass

    # Exit a parse tree produced by LParser#entryPoint.
    def exitEntryPoint(self, ctx:LParser.EntryPointContext):
        pass


    # Enter a parse tree produced by LParser#functionSpecifier.
    def enterFunctionSpecifier(self, ctx:LParser.FunctionSpecifierContext):
        pass

    # Exit a parse tree produced by LParser#functionSpecifier.
    def exitFunctionSpecifier(self, ctx:LParser.FunctionSpecifierContext):
        pass


    # Enter a parse tree produced by LParser#whileStatement.
    def enterWhileStatement(self, ctx:LParser.WhileStatementContext):
        pass

    # Exit a parse tree produced by LParser#whileStatement.
    def exitWhileStatement(self, ctx:LParser.WhileStatementContext):
        pass


    # Enter a parse tree produced by LParser#ifStatement.
    def enterIfStatement(self, ctx:LParser.IfStatementContext):
        pass

    # Exit a parse tree produced by LParser#ifStatement.
    def exitIfStatement(self, ctx:LParser.IfStatementContext):
        pass


    # Enter a parse tree produced by LParser#bracesBlockStatement.
    def enterBracesBlockStatement(self, ctx:LParser.BracesBlockStatementContext):
        pass

    # Exit a parse tree produced by LParser#bracesBlockStatement.
    def exitBracesBlockStatement(self, ctx:LParser.BracesBlockStatementContext):
        pass


    # Enter a parse tree produced by LParser#skipStatement.
    def enterSkipStatement(self, ctx:LParser.SkipStatementContext):
        pass

    # Exit a parse tree produced by LParser#skipStatement.
    def exitSkipStatement(self, ctx:LParser.SkipStatementContext):
        pass


    # Enter a parse tree produced by LParser#functionInvokation.
    def enterFunctionInvokation(self, ctx:LParser.FunctionInvokationContext):
        pass

    # Exit a parse tree produced by LParser#functionInvokation.
    def exitFunctionInvokation(self, ctx:LParser.FunctionInvokationContext):
        pass


    # Enter a parse tree produced by LParser#assignment.
    def enterAssignment(self, ctx:LParser.AssignmentContext):
        pass

    # Exit a parse tree produced by LParser#assignment.
    def exitAssignment(self, ctx:LParser.AssignmentContext):
        pass


    # Enter a parse tree produced by LParser#arithmeticExpr.
    def enterArithmeticExpr(self, ctx:LParser.ArithmeticExprContext):
        pass

    # Exit a parse tree produced by LParser#arithmeticExpr.
    def exitArithmeticExpr(self, ctx:LParser.ArithmeticExprContext):
        pass


    # Enter a parse tree produced by LParser#baseExpr.
    def enterBaseExpr(self, ctx:LParser.BaseExprContext):
        pass

    # Exit a parse tree produced by LParser#baseExpr.
    def exitBaseExpr(self, ctx:LParser.BaseExprContext):
        pass


    # Enter a parse tree produced by LParser#compare.
    def enterCompare(self, ctx:LParser.CompareContext):
        pass

    # Exit a parse tree produced by LParser#compare.
    def exitCompare(self, ctx:LParser.CompareContext):
        pass


    # Enter a parse tree produced by LParser#logicExpr.
    def enterLogicExpr(self, ctx:LParser.LogicExprContext):
        pass

    # Exit a parse tree produced by LParser#logicExpr.
    def exitLogicExpr(self, ctx:LParser.LogicExprContext):
        pass


