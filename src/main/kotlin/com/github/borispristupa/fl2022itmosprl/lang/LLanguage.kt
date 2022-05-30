package com.github.borispristupa.fl2022itmosprl.lang

import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon


object LLanguage: Language("L") {
    val icon: Icon = IconLoader.getIcon("/icons/L_14x16.svg", LLanguage::class.java)

    object LLanguageFileType : LanguageFileType(LLanguage) {
        override fun getName(): String {
            return "L Language"
        }

        override fun getDescription(): String {
            return "L language"
        }

        override fun getDefaultExtension(): String {
            return "llang"
        }

        override fun getIcon(): Icon {
            return LLanguage.icon
        }
    }
}