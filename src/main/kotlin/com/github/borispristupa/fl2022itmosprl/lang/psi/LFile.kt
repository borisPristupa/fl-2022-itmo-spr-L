package com.github.borispristupa.fl2022itmosprl.lang.psi

import com.github.borispristupa.fl2022itmosprl.lang.LLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class LFile(viewProvider: FileViewProvider): PsiFileBase(viewProvider, LLanguage) {
    override fun getFileType(): FileType {
        return LLanguage.LLanguageFileType
    }

    override fun toString(): String {
        return LLanguage.displayName + " file"
    }
}