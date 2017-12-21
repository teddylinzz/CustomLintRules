package com.example.lint.rules

import com.example.lint.rules.base.BaseRule
import com.example.lint.utils.StringUtils
import com.intellij.openapi.util.text.StringUtil

/**
 * Created by teddylin on 18/12/2017.
 */
open class BracesRule : BaseRule() {

    override fun check(text: String): Boolean {
//        println("input :" + text)

        var matcher = StringUtils.getMatcher(text, "[\\r\\n|\\n|\\s]*")
        val trimString = matcher.replaceAll("")

//        println("trimString :" + trimString)

        matcher = StringUtils.getMatcher(trimString, "[^\\)]*")

        var matchCount = 0

        if (matcher.find()) {
//            println("[" + matcher.start() + "," + matcher.end() + "]");
            val s = trimString.substring(matcher.start(), matcher.end())
            matchCount = s.split("\\(".toRegex()).toTypedArray().size - 1
        }

        var patternString = String.format("\\){%d}", matchCount)

//        println("patternString : " + patternString)

        matcher = StringUtils.getMatcher(trimString, patternString)

        var result = ""
        if (matcher.find()) {
            result = trimString.substring(matcher.end())
        }

//        println(result.get(0) + "\n")

        if (StringUtil.isEmpty(result)) {
            return false
        }

        if (result.get(0) == '{' || result.get(0) == '?') {
            return true
        }

        return false
    }
}
