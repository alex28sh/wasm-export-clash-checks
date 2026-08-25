package org.example.project

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Tests for :webApp on the Kotlin/Wasm target.
 *
 * This project exists to exercise export-name clashes, so the tests pin down both the
 * returned values and which of the same-named declarations a call site resolves to.
 */
class MainWasmJsTest {

    @Test
    fun f4ReturnsOne() {
        assertEquals(1, f4())
    }

    @Test
    fun f4DelegatesToSharedNoArgF3() {
        // The wasmJs actual of f4() calls shared's no-arg f3(), which forwards to myFun2().
        assertEquals(1, myFun2())
        assertEquals(myFun2(), f3())
    }

    @Test
    fun f3OverloadsResolveByArity() {
        // Same package, same name, two different modules:
        //   f3(Int) comes from webApp/webMain, f3() from shared/wasmJsMain.
        assertEquals(3, f3(1))
        assertEquals(1, f3())
    }

    @Test
    fun f3AddsTwo() {
        assertEquals(2, f3(0))
        assertEquals(3, f3(1))
        assertEquals(1, f3(-1))
    }

    @Test
    fun f1IsIdentity() {
        assertEquals(0, f1(0))
        assertEquals(42, f1(42))
        assertEquals(-7, f1(-7))
    }

    @Test
    fun mainComposesF1OverF3() {
        // main() computes f1(f3(1)); cover the composition without running main() itself.
        assertEquals(3, f1(f3(1)))
    }

    @Test
    fun sharedWasmHelpersAreReachable() {
        assertEquals(2, f3_(1))
        assertEquals(2, f_(1))
    }

    @Test
    fun platformIsWasm() {
        assertEquals("Web with Kotlin/Wasm 2", getPlatform().name)
    }

    @Test
    fun greetingUsesWasmPlatform() {
        assertEquals("Hello, Web with Kotlin/Wasm 2!", Greeting().greet())
    }
}
