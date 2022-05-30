# Generated from L.g4 by ANTLR 4.7.2
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .LParser import LParser
else:
    from LParser import LParser

# This class defines a complete generic visitor for a parse tree produced by LParser.

class LVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by LParser#start.
    def visitStart(self, ctx:LParser.StartContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#statement.
    def visitStatement(self, ctx:LParser.StatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#whileStatement.
    def visitWhileStatement(self, ctx:LParser.WhileStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#ifStatement.
    def visitIfStatement(self, ctx:LParser.IfStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#bracesBlockStatement.
    def visitBracesBlockStatement(self, ctx:LParser.BracesBlockStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#functionSpecifier.
    def visitFunctionSpecifier(self, ctx:LParser.FunctionSpecifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#assignment.
    def visitAssignment(self, ctx:LParser.AssignmentContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#expression.
    def visitExpression(self, ctx:LParser.ExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#baseExpression.
    def visitBaseExpression(self, ctx:LParser.BaseExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#unaryExpression.
    def visitUnaryExpression(self, ctx:LParser.UnaryExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#powerExpression.
    def visitPowerExpression(self, ctx:LParser.PowerExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#multDivExpression.
    def visitMultDivExpression(self, ctx:LParser.MultDivExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#addExpression.
    def visitAddExpression(self, ctx:LParser.AddExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#relationExpression.
    def visitRelationExpression(self, ctx:LParser.RelationExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#logicalAndExpression.
    def visitLogicalAndExpression(self, ctx:LParser.LogicalAndExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#logicalOrExpression.
    def visitLogicalOrExpression(self, ctx:LParser.LogicalOrExpressionContext):
        return self.visitChildren(ctx)



del LParser