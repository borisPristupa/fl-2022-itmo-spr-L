package com.github.borispristupa.fl2022itmosprl.lang

import com.intellij.lexer.Lexer
import com.intellij.testFramework.LexerTestCase

class LexerTest : LexerTestCase() {
    private fun doTest() = doFileTest(LLanguage.LLanguageFileType.defaultExtension)

    fun testHello() = doTest()
    fun testComments() = doTest()
    fun testExpressions() = doTest()
    fun testStatements() = doTest()

    override fun createLexer(): Lexer {
        return LLexer()
    }

    override fun getPathToTestDataFile(extension: String): String {
        return "src/test/resources/" + dirPath + "/" + getTestName(true) + extension
    }

    override fun getDirPath(): String {
        return "lexer"
    }
}