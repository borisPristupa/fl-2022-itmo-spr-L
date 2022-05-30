import os

from antlr4 import *
from antlr4.tree.Trees import Trees

from antlr_files.LLexer import LLexer
from antlr_files.LParser import LParser

def main():
    pwd = os.getcwd()
    parse("if_example.txt")


def parse(path):
    with open(path, 'r') as file:
        content = file.read()

    antlr_data = InputStream(content)
    lexer = LLexer(antlr_data)
    data_stream = CommonTokenStream(lexer)
    parser = LParser(data_stream)
    visualize_AST(parser)


def visualize_AST(parser):
    tree = parser.start()
    print(Trees.toStringTree(tree, None, parser))


if __name__ == "__main__":
    main()
