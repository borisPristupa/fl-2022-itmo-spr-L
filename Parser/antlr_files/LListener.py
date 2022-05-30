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


    # Enter a parse tree produced by LParser#functionSpecifier.
    def enterFunctionSpecifier(self, ctx:LParser.FunctionSpecifierContext):
        pass

    # Exit a parse tree produced by LParser#functionSpecifier.
    def exitFunctionSpecifier(self, ctx:LParser.FunctionSpecifierContext):
        pass


    # Enter a parse tree produced by LParser#assignment.
    def enterAssignment(self, ctx:LParser.AssignmentContext):
        pass

    # Exit a parse tree produced by LParser#assignment.
    def exitAssignment(self, ctx:LParser.AssignmentContext):
        pass


    # Enter a parse tree produced by LParser#expression.
    def enterExpression(self, ctx:LParser.ExpressionContext):
        pass

    # Exit a parse tree produced by LParser#expression.
    def exitExpression(self, ctx:LParser.ExpressionContext):
        pass


    # Enter a parse tree produced by LParser#baseExpression.
    def enterBaseExpression(self, ctx:LParser.BaseExpressionContext):
        pass

    # Exit a parse tree produced by LParser#baseExpression.
    def exitBaseExpression(self, ctx:LParser.BaseExpressionContext):
        pass


    # Enter a parse tree produced by LParser#unaryExpression.
    def enterUnaryExpression(self, ctx:LParser.UnaryExpressionContext):
        pass

    # Exit a parse tree produced by LParser#unaryExpression.
    def exitUnaryExpression(self, ctx:LParser.UnaryExpressionContext):
        pass


    # Enter a parse tree produced by LParser#powerExpression.
    def enterPowerExpression(self, ctx:LParser.PowerExpressionContext):
        pass

    # Exit a parse tree produced by LParser#powerExpression.
    def exitPowerExpression(self, ctx:LParser.PowerExpressionContext):
        pass


    # Enter a parse tree produced by LParser#multDivExpression.
    def enterMultDivExpression(self, ctx:LParser.MultDivExpressionContext):
        pass

    # Exit a parse tree produced by LParser#multDivExpression.
    def exitMultDivExpression(self, ctx:LParser.MultDivExpressionContext):
        pass


    # Enter a parse tree produced by LParser#addExpression.
    def enterAddExpression(self, ctx:LParser.AddExpressionContext):
        pass

    # Exit a parse tree produced by LParser#addExpression.
    def exitAddExpression(self, ctx:LParser.AddExpressionContext):
        pass


    # Enter a parse tree produced by LParser#relationExpression.
    def enterRelationExpression(self, ctx:LParser.RelationExpressionContext):
        pass

    # Exit a parse tree produced by LParser#relationExpression.
    def exitRelationExpression(self, ctx:LParser.RelationExpressionContext):
        pass


    # Enter a parse tree produced by LParser#logicalAndExpression.
    def enterLogicalAndExpression(self, ctx:LParser.LogicalAndExpressionContext):
        pass

    # Exit a parse tree produced by LParser#logicalAndExpression.
    def exitLogicalAndExpression(self, ctx:LParser.LogicalAndExpressionContext):
        pass


    # Enter a parse tree produced by LParser#logicalOrExpression.
    def enterLogicalOrExpression(self, ctx:LParser.LogicalOrExpressionContext):
        pass

    # Exit a parse tree produced by LParser#logicalOrExpression.
    def exitLogicalOrExpression(self, ctx:LParser.LogicalOrExpressionContext):
        pass


