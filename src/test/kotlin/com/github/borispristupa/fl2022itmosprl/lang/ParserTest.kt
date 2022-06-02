package com.github.borispristupa.fl2022itmosprl.lang

import com.intellij.testFramework.ParsingTestCase

class ParserTest: ParsingTestCase("parser", "llang", true, LParserDefinition()) {
    override fun getTestDataPath(): String {
        return "src/test/resources"
    }

    fun testHello() = doTest(true)
    fun testExpressions() = doTest(true)
    fun testStatements() = doTest(true)
}