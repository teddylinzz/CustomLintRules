package rules

import com.example.lint.rules.base.BaseRule

/**
 * Created by teddylin on 21/12/2017.
 */
open class CommentRule : BaseRule() {
    override fun check(text: String): Boolean {
        return checkChineseCharacters(text)
    }

    fun checkChineseCharacters(text: String = ""): Boolean {
        return text.codePoints().anyMatch { codepoint ->
            Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN} ||
                text.equals("lang", true)
    }
}