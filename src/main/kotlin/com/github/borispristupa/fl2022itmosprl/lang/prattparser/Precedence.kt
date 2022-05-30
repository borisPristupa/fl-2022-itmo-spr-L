package com.github.borispristupa.fl2022itmosprl.lang.prattparser

object Precedence {
    const val OR = 1
    const val AND = 2
    const val NOT = 3
    const val CMP = 4
    const val SUM = 5
    const val PRODUCT = 6
    const val NEGATE = 7
    const val POWER = 8
    const val CALL = 9
}