package org.example.project

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@OptIn(ExperimentalJsExport::class)
@JsExport
@JsName("fa1")
fun f1(a: Int) = a

expect fun f4(): Int

fun f3(a: Int) = a + 2

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val a = f1(f3(1))
    println(a)
    println(f4())
    ComposeViewport {
        App()
    }
}