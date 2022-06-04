import argparse
import os

from antlr4 import *
from antlr4.error.ErrorListener import ErrorListener
from antlr4.tree.Trees import Trees

from antlr_files.LLexer import LLexer
from antlr_files.LParser import LParser


def main():
    parser = argparse.ArgumentParser(description='Parse path to file with code')
    print("Parser is ready. Write path to file with code using -p <file>")
    parser.add_argument('-p', dest='path', action='store')
    args = parser.parse_args()
    Parser.parse(os.getcwd() + args.path)
    print('Parsing finished')


class Parser:
    error_output = None

    @staticmethod
    def parse(path):
        try:
            with open(path, 'r') as file:
                content = file.read()
                Parser.error_output = None
                error_listener = ANTLRErrorListener()
                antlr_data = InputStream(content)
                lexer = LLexer(antlr_data)
                data_stream = CommonTokenStream(lexer)
                parser = LParser(data_stream)
                parser.removeErrorListeners()
                parser.addErrorListener(error_listener)
                Parser.print_AST(parser, path)

        except FileNotFoundError:
            print(f"File was not found{path}")
        except Exception as e:
            Parser.error_output = f'Error while parsing:\n' + str(e)
            print("Error while parsing:\n" + str(e))

    @staticmethod
    def print_AST(parser, path):
        tree = parser.start()
        print(Trees.toStringTree(tree, None, parser))
        with open(path + "_parsed", 'w') as f:
            try:
                f.write(Trees.toStringTree(tree, None, parser))
            except Exception as e:
                print("Error while printing AST:\n" + str(e))


class ANTLRErrorListener(ErrorListener):
    def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
        Parser.error_output = f"ANTLR parsing error when parsing line {line}.\n Message: {msg}"
        raise Exception(f"ANTLR parsing error when parsing line {line}. "
                        f"Message: {msg}")


if __name__ == "__main__":
    main()
