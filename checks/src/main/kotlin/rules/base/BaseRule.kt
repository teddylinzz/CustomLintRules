package com.example.lint.rules.base

/**
 * Created by teddylin on 18/12/2017.
 */

abstract class BaseRule {
    abstract fun check(text : String) : Boolean
}