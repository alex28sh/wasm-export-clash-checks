package org.example.project

import kotlin.wasm.ExperimentalWasmInterop
import kotlin.wasm.WasmExport


@OptIn(ExperimentalWasmInterop::class)
//@WasmExport("f")
fun myFun2() = 1

@OptIn(ExperimentalWasmInterop::class)
//@WasmExport("f")
fun f3() = myFun2()