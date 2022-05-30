package com.github.borispristupa.fl2022itmosprl

import com.github.borispristupa.fl2022itmosprl.lang.LElementType
import com.github.borispristupa.fl2022itmosprl.lang.psi.LFile
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegateAdapter
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.util.elementType
import com.intellij.psi.util.parents
import com.intellij.refactoring.suggested.endOffset
import com.intellij.util.DocumentUtil
import com.intellij.util.text.CharArrayUtil

class LSemicolonEnterHandler: EnterHandlerDelegateAdapter() {
    override fun preprocessEnter(
        file: PsiFile,
        editor: Editor,
        caretOffset: Ref<Int>,
        caretAdvance: Ref<Int>,
        dataContext: DataContext,
        originalHandler: EditorActionHandler?
    ): EnterHandlerDelegate.Result {
        if (file !is LFile) return EnterHandlerDelegate.Result.Continue

        val offset = caretOffset.get()
        val document = editor.document
        val lineEnd = DocumentUtil.getLineEndOffset(offset, document)

        PsiDocumentManager.getInstance(file.getProject()).commitDocument(document)

        val line = document.getLineNumber(offset)
        val lastNonWsOffset = CharArrayUtil.shiftBackward(document.charsSequence, offset, " \t\n")
        if (DocumentUtil.isLineEmpty(document, line)
            || !CharArrayUtil.isEmptyOrSpaces(document.charsSequence, offset, lineEnd)
            || document.charsSequence[lastNonWsOffset] == ';') {
            return EnterHandlerDelegate.Result.Continue
        }

        val statement = file.findElementAt(lastNonWsOffset)
            ?.parents(withSelf = true)
            ?.filter { it.elementType in LElementType.STATEMENTS }
            ?.takeWhile { it.elementType !is LElementType.STATEMENT_LIST }
            ?.lastOrNull()
            ?: file.findElementAt(lastNonWsOffset)
                ?.parents(withSelf = true)
                ?.find { it.elementType is LElementType.STATEMENT_LIST }
            ?: return EnterHandlerDelegate.Result.Continue

        if (document.getLineNumber(statement.endOffset) != line) {
            return EnterHandlerDelegate.Result.Continue
        }

        val statementList = statement.parents(withSelf = false)
            .find { it.elementType is LElementType.STATEMENT_LIST }
            ?: return EnterHandlerDelegate.Result.Continue

        if (statementList.children.count { it.elementType in LElementType.STATEMENTS } == 1) {
            return EnterHandlerDelegate.Result.Continue
        }

        document.insertString(lastNonWsOffset + 1, ";")
        caretOffset.set(offset + 1)
        return EnterHandlerDelegate.Result.Continue
    }
}