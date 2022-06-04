import os
from unittest import TestCase

import parser


class TestParser(TestCase):
    def setUp(self) -> None:
        self.test_basic()

    def test_basic(self) -> None:
        parser.Parser.parse(os.path.dirname(__file__) + "/test1.txt")
        self.assertIsNone(parser.Parser.error_output)
        parser.Parser.parse(os.path.dirname(__file__) + "/test6.txt")
        self.assertIsNone(parser.Parser.error_output)

    def test_operators(self) -> None:
        parser.Parser.parse(os.path.dirname(__file__) + "/test2.txt")
        self.assertIsNone(parser.Parser.error_output)

    def test_functions(self) -> None:
        parser.Parser.parse(os.path.dirname(__file__) + "/test3.txt")
        self.assertIsNone(parser.Parser.error_output)
        parser.Parser.parse(os.path.dirname(__file__) + "/test7.txt")
        self.assertIsNone(parser.Parser.error_output)

    def test_complex_expr(self) -> None:
        parser.Parser.parse(os.path.dirname(__file__) + "/test4.txt")
        self.assertIsNone(parser.Parser.error_output)
        parser.Parser.parse(os.path.dirname(__file__) + "/test5.txt")
        self.assertIsNone(parser.Parser.error_output)

    def test_wrong_input(self) -> None:
        parser.Parser.parse(os.path.dirname(__file__) + "/error_test1.txt")
        self.assertIsNotNone(parser.Parser.error_output)
        parser.Parser.parse(os.path.dirname(__file__) + "/error_test2.txt")
        self.assertIsNotNone(parser.Parser.error_output)
        parser.Parser.parse(os.path.dirname(__file__) + "/error_test3.txt")
        self.assertIsNotNone(parser.Parser.error_output)
        parser.Parser.parse(os.path.dirname(__file__) + "/error_test4.txt")
        self.assertIsNotNone(parser.Parser.error_output)
        parser.Parser.parse(os.path.dirname(__file__) + "/error_test5.txt")
        self.assertIsNotNone(parser.Parser.error_output)
        parser.Parser.parse(os.path.dirname(__file__) + "/error_test6.txt")
        self.assertIsNotNone(parser.Parser.error_output)
        parser.Parser.parse(os.path.dirname(__file__) + "/error_test7.txt")
        self.assertIsNotNone(parser.Parser.error_output)
        parser.Parser.parse(os.path.dirname(__file__) + "/error_test8.txt")
        self.assertIsNotNone(parser.Parser.error_output)
        parser.Parser.parse(os.path.dirname(__file__) + "/error_test9.txt")
        self.assertIsNotNone(parser.Parser.error_output)
