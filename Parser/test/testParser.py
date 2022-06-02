import os
from unittest import TestCase

import parser


class TestParser(TestCase):
    def setUp(self) -> None:
        self.test_basic()

    def test_basic(self) -> None:
        parser.parse(os.path.dirname(__file__) + "/test1.txt")
        parser.parse(os.path.dirname(__file__) + "/test6.txt")

    def test_operators(self) -> None:
        parser.parse(os.path.dirname(__file__) + "/test2.txt")

    def test_functions(self) -> None:
        parser.parse(os.path.dirname(__file__) + "/test3.txt")

    def test_complex_expr(self) -> None:
        parser.parse(os.path.dirname(__file__) + "/test4.txt")
        parser.parse(os.path.dirname(__file__) + "/test5.txt")

    def test_wrong_input(self) -> None:
        with self.assertRaises(Exception):
            parser.parse(os.path.dirname(__file__) + "/error_test1.txt")
            parser.parse(os.path.dirname(__file__) + "/error_test2.txt")
            parser.parse(os.path.dirname(__file__) + "/error_test3.txt")
            parser.parse(os.path.dirname(__file__) + "/error_test4.txt")
            parser.parse(os.path.dirname(__file__) + "/error_test5.txt")
            parser.parse(os.path.dirname(__file__) + "/error_test6.txt")
            parser.parse(os.path.dirname(__file__) + "/error_test7.txt")


