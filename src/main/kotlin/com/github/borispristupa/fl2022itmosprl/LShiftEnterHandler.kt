package com.github.borispristupa.fl2022itmosprl

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorModificationUtil
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler
import com.intellij.util.DocumentUtil

class LShiftEnterHandler: EditorWriteActionHandler(/* runForEachCaret = */ true) {
    override fun isEnabledForCaret(editor: Editor, caret: Caret, dataContext: DataContext?): Boolean {
        return super.isEnabledForCaret(editor, caret, dataContext)
    }

    override fun executeWriteAction(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        if (caret == null)
            return

        val lnPlusIndent = "\n" + DocumentUtil.getIndent(editor.document, caret.offset)
        EditorModificationUtil.insertStringAtCaret(editor, lnPlusIndent)
    }
}