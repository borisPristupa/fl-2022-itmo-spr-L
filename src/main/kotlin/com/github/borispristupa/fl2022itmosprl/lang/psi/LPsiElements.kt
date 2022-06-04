package com.github.borispristupa.fl2022itmosprl.lang.psi

import com.github.borispristupa.fl2022itmosprl.lang.LElementType
import com.github.borispristupa.fl2022itmosprl.lang.LLanguage
import com.intellij.codeInsight.lookup.AutoCompletionPolicy
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.*
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope
import com.intellij.psi.util.*
import com.intellij.refactoring.suggested.startOffset

open class LPsiElement(node: ASTNode): ASTWrapperPsiElement(node)

class LFnDef(node: ASTNode): LPsiElement(node), PsiNameIdentifierOwner {
    override fun getNameIdentifier(): PsiElement {
        return firstChild
    }

    override fun getName(): String? {
        return nameIdentifier.text
    }

    override fun setName(name: String): PsiElement {
        return nameIdentifier.apply {
            replace(
                PsiFileFactory.getInstance(project)
                    .createFileFromText(LLanguage, """$name(){} main(){}""")
                    .descendantsOfType<LFnDef>()
                    .first()
                    .nameIdentifier
            )
        }
    }

    override fun getUseScope(): SearchScope {
        return LocalSearchScope(containingFile)
    }
}

class LParameterDef(node: ASTNode): LPsiElement(node), PsiNameIdentifierOwner {
    override fun getNameIdentifier(): PsiElement {
        return this
    }

    override fun getName(): String? {
        return nameIdentifier.text
    }

    override fun setName(name: String): PsiElement {
        return nameIdentifier.apply {
            replace(
                PsiFileFactory.getInstance(project)
                    .createFileFromText(LLanguage, """fn($name){} main(){}""")
                    .descendantsOfType<LParameterDef>()
                    .first()
                    .nameIdentifier
            )
        }
    }

    override fun getUseScope(): SearchScope {
        return LocalSearchScope(parentOfType<LFnDef>()!!)
    }
}

class LPsiRef(node: ASTNode): ASTWrapperPsiElement(node), PsiNameIdentifierOwner {
    override fun getNameIdentifier(): PsiElement {
        return this
    }

    override fun getName(): String? {
        return nameIdentifier.text
    }

    override fun setName(name: String): PsiElement {
        return nameIdentifier.apply {
            replace(
                PsiFileFactory.getInstance(project)
                    .createFileFromText(LLanguage, """main() {$name = 1}""")
                    .descendantsOfType<LPsiRef>()
                    .first()
                    .nameIdentifier
            )
        }
    }

    override fun getReference(): PsiReference {
        return object : PsiReferenceBase<LPsiRef>(this, textRange.shiftLeft(startOffset)) {
            override fun resolve(): PsiElement? {
                val definitions = parentOfType<LFnDef>()!!.descendantsOfType<LParameterDef>() +
                        containingFile.descendantsOfType<LFnDef>()

                return definitions.find { it.nameIdentifier!!.textMatches(text) }
            }

            override fun getVariants(): Array<Any> {
                run {
                    val parentExpr = element.parents(withSelf = false)
                        .lastOrNull { it.elementType in LElementType.EXPRESSION }

                    val isolatedText = if (parentExpr != null) {
                        "main() {x = ${parentExpr.text}}"
                    } else {
                        val parentStatement = element.parents(withSelf = true)
                            .first { it.elementType in LElementType.STATEMENTS }
                        "main() {${parentStatement.text}}"
                    }

                    if (PsiFileFactory.getInstance(project)
                            .createFileFromText(LLanguage, isolatedText)
                            .descendantsOfType<PsiErrorElement>()
                            .any()) {
                        return emptyArray()
                    }
                }

                val definitions = parentOfType<LFnDef>()!!.descendantsOfType<LParameterDef>() +
                        containingFile.descendantsOfType<LFnDef>()
                return definitions.map {
                    LookupElementBuilder.create(it)
                        .withIcon(LLanguage.icon)
                        .withTypeText(containingFile.name)
                        .withAutoCompletionPolicy(AutoCompletionPolicy.GIVE_CHANCE_TO_OVERWRITE)
                }.toList().toTypedArray()
            }

            override fun handleElementRename(newElementName: String): PsiElement {
                return element.setName(newElementName)
            }
        }
    }
}
