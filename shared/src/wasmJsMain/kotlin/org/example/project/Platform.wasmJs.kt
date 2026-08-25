package org.example.project

import kotlin.wasm.ExperimentalWasmInterop
import kotlin.wasm.WasmExport

//@OptIn(ExperimentalJsExport::class)
//@JsExport
//@JsName("f")
@OptIn(ExperimentalWasmInterop::class)
@WasmExport("_start")
fun f3_(a: Int) = a + 1
//
//@OptIn(ExperimentalWasmInterop::class)
//@WasmExport("f")
//fun myFun() = 1

class WasmPlatform: Platform {
    private val t = f3_(1)
    override val name: String = "Web with Kotlin/Wasm $t"
}

actual fun getPlatform(): Platform = WasmPlatform()