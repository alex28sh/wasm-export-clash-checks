package org.example.project

import kotlin.wasm.ExperimentalWasmInterop
import kotlin.wasm.WasmExport

@OptIn(ExperimentalWasmInterop::class)
@WasmExport("_start")
actual fun f4(): Int {
    f3()
    return 1
}