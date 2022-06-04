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


    # Visit a parse tree produced by LParser#funcInnerStatement.
    def visitFuncInnerStatement(self, ctx:LParser.FuncInnerStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#entryPoint.
    def visitEntryPoint(self, ctx:LParser.EntryPointContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#functionSpecifier.
    def visitFunctionSpecifier(self, ctx:LParser.FunctionSpecifierContext):
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


    # Visit a parse tree produced by LParser#skipStatement.
    def visitSkipStatement(self, ctx:LParser.SkipStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#functionInvokation.
    def visitFunctionInvokation(self, ctx:LParser.FunctionInvokationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#assignment.
    def visitAssignment(self, ctx:LParser.AssignmentContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#arithmeticExpr.
    def visitArithmeticExpr(self, ctx:LParser.ArithmeticExprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#baseExpr.
    def visitBaseExpr(self, ctx:LParser.BaseExprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#compare.
    def visitCompare(self, ctx:LParser.CompareContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LParser#logicExpr.
    def visitLogicExpr(self, ctx:LParser.LogicExprContext):
        return self.visitChildren(ctx)



del LParser