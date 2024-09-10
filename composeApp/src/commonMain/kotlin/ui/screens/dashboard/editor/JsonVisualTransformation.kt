package ui.screens.dashboard.editor

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import ui.theme.JsonKey
import ui.theme.JsonNumber
import ui.theme.JsonString
import ui.theme.JsonValue
import ui.theme.jsonScopeColors

class JsonVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            buildAnnotatedStringWithColors(text.toString()),
            OffsetMapping.Identity
        )
    }

    private fun buildAnnotatedStringWithColors(json: String): AnnotatedString {

        val tokens = findTokens(json)

        return AnnotatedString.Builder().apply {
            append(json)
            tokens.keys.forEach {
                addStyle(
                    style = SpanStyle(
                        color = JsonKey,
                    ),
                    start = it.first,
                    end = it.second
                )
            }
            tokens.strings.forEach {
                addStyle(
                    style = SpanStyle(
                        color = JsonString,
                    ),
                    start = it.first,
                    end = it.second
                )
            }
            tokens.numbers.forEach {
                addStyle(
                    style = SpanStyle(
                        color = JsonNumber,
                    ),
                    start = it.first,
                    end = it.second
                )
            }
            tokens.values.forEach {
                addStyle(
                    style = SpanStyle(
                        color = JsonValue,
                    ),
                    start = it.first,
                    end = it.second
                )
            }
            tokens.scopes.forEachIndexed { i, it ->
                addStyle(
                    style = SpanStyle(
                        color = jsonScopeColors[i % jsonScopeColors.size],
                    ),
                    start = it.start,
                    end = it.start + 1
                )
                it.end?.let {
                    addStyle(
                        style = SpanStyle(
                            color = jsonScopeColors[i % jsonScopeColors.size],
                        ),
                        start = it,
                        end = it + 1
                    )
                }

            }
        }.toAnnotatedString()
    }

    private fun findTokens(json: String): TokenIndexes {
        val keys = mutableListOf<Pair<Int, Int>>()
        val strings = mutableListOf<Pair<Int, Int>>()
        val numbers = mutableListOf<Pair<Int, Int>>()
        val values = mutableListOf<Pair<Int, Int>>()
        val scopes = mutableListOf<TokenIndexes.Scope>()

        var lastQuotation: Int? = null
        var lastNumber: Int? = null

        json.forEachIndexed { i, char ->
            val prev = json.getOrNull(i - 1)
            val next = json.getOrNull(i + 1)
            if (prev != '\\') {
                lastQuotation?.let {
                    if (char == '"') {
                        if (next == ':') {
                            keys.add(Pair(it, i + 1))
                        } else {
                            strings.add(Pair(it, i + 1))
                        }
                        lastQuotation = null
                    }
                } ?: run {
                    when {
                        char == '"' -> {
                            lastQuotation = i
                        }

                        char.isDigit() -> {
                            if (next?.isDigit() != true) {
                                lastNumber?.let {
                                    numbers.add(Pair(it, i + 1))
                                    lastNumber = null
                                } ?: run {
                                    numbers.add(Pair(i, i + 1))
                                }
                            } else if (lastNumber == null) {
                                lastNumber = i
                            } else {
                            }
                        }

                        json.findStringAt(i, "true") -> values.add(Pair(i, i + "true".length))
                        json.findStringAt(i, "false") -> values.add(Pair(i, i + "false".length))
                        json.findStringAt(i, "null") -> values.add(Pair(i, i + "null".length))
                        char == '[' -> scopes.add(TokenIndexes.Scope(i, null, TokenIndexes.ScopeType.LIST))
                        char == '{' -> scopes.add(TokenIndexes.Scope(i, null, TokenIndexes.ScopeType.OBJECT))
                        char == ']' -> {
                            val lastUnclosed = scopes.indexOfLast { it.type == TokenIndexes.ScopeType.LIST && it.end == null }
                            if (lastUnclosed > -1) {
                                scopes[lastUnclosed] = scopes[lastUnclosed].copy(end = i)
                            } else {
                            }
                        }

                        char == '}' -> {
                            val lastUnclosed = scopes.indexOfLast { it.type == TokenIndexes.ScopeType.OBJECT && it.end == null }
                            if (lastUnclosed > -1) {
                                scopes[lastUnclosed] = scopes[lastUnclosed].copy(end = i)
                            } else {
                            }
                        }

                        else -> {}
                    }
                }
            }
        }
        return TokenIndexes(keys, strings, numbers, values,scopes)
    }

    private fun String.findStringAt(index: Int, string: String): Boolean {
        string.forEachIndexed { i, char ->
            if (char != this[index + i]) return false
        }
        return true
    }

}

data class TokenIndexes(
    val keys: List<Pair<Int, Int>> = emptyList(),
    val strings: List<Pair<Int, Int>> = emptyList(),
    val numbers: List<Pair<Int, Int>> = emptyList(),
    val values: List<Pair<Int, Int>> = emptyList(),
    val scopes: List<Scope> = emptyList(),
) {

    data class Scope(
        val start: Int,
        val end: Int?,
        val type: ScopeType
    )

    enum class ScopeType {
        LIST, OBJECT
    }
}


