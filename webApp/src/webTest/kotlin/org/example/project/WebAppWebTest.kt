package org.example.project

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * :webApp tests that hold for both web targets, so they run under jsTest and wasmJsTest.
 *
 * f4() is deliberately left to MainWasmJsTest: its jsMain actual is still a TODO().
 */
class WebAppWebTest {

    @Test
    fun f1IsIdentity() {
        assertEquals(0, f1(0))
        assertEquals(1, f1(1))
        assertEquals(Int.MAX_VALUE, f1(Int.MAX_VALUE))
    }

    @Test
    fun f3AddsTwo() {
        assertEquals(3, f3(1))
        assertEquals(0, f3(-2))
    }

    @Test
    fun sharedWebExportIsReachable() {
        assertEquals(2, f_(1))
    }

    @Test
    fun sharedGreetingUtilIsReachable() {
        assertEquals("Hello, world!", sayHello("world"))
    }

    @Test
    fun greetingMatchesPlatformName() {
        assertEquals(sayHello(getPlatform().name), Greeting().greet())
    }
}
