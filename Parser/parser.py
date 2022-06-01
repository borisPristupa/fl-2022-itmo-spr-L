import argparse
import os

from antlr4 import *
from antlr4.tree.Trees import Trees

from antlr_files.LLexer import LLexer
from antlr_files.LParser import LParser


def main():
    parser = argparse.ArgumentParser(description='Parse path to file with code')
    print("Parser is ready. Write path to file with code using -p <file>")
    parser.add_argument('-p', dest='path', action='store')
    args = parser.parse_args()
    parse(os.getcwd() + args.path)
    print('Parsing finished')


def parse(path):
    try:
        with open(path, 'r') as file:
            content = file.read()
            antlr_data = InputStream(content)
            lexer = LLexer(antlr_data)
            data_stream = CommonTokenStream(lexer)
            parser = LParser(data_stream)
            visualize_AST(parser, path)
    except FileNotFoundError:
        print(f"File was not found{path}")


def visualize_AST(parser, path):
    tree = parser.start()
    print(Trees.toStringTree(tree, None, parser))
    with open(path + "_parsed", 'w') as f:
        f.write(Trees.toStringTree(tree, None, parser))


if __name__ == "__main__":
    main()
