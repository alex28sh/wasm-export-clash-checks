@file:OptIn(ExperimentalJsExport::class)

package org.example.project

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("f")
fun f_(a: Int) = a + 1