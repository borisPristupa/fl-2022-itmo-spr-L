package com.github.borispristupa.fl2022itmosprl

import com.github.borispristupa.fl2022itmosprl.lang.psi.LPsiElement
import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement

class LRefactoringSupportProvider: RefactoringSupportProvider() {
    override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
        return element is LPsiElement && element is PsiNamedElement
    }
}