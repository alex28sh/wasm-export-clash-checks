plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}
// Kotlin/Wasm multi-module (closed-world) linking.
//
// Off:  one whole-program .wasm per executable; all Kotlin modules share a single
//       Wasm export namespace, so @JsExport/@WasmExport names collide across modules.
// On:   one .wasm per Kotlin module (klib), linked closed-world at instantiation time;
//       the module passed via -include is the main module. Each module gets its own
//       export namespace, so the same name may legitimately appear in several modules.
//
// Toggle with -Pwasm.multimodule=false to compare the two modes. KGP 2.4.20-RC has no
// DSL for this, so the flag goes straight on the wasmJs link tasks of every subproject.
val wasmMultimodule =
    providers.gradleProperty("wasm.multimodule").orNull?.toBoolean() ?: true

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.targets.js.ir.KotlinJsIrLink>()
        .matching { it.name.endsWith("KotlinWasmJs") }
        .configureEach {
            if (wasmMultimodule) {
                compilerOptions.freeCompilerArgs.add("-Xwasm-generate-closed-world-multimodule")
            }
            compilerOptions.freeCompilerArgs.add("-Xwasm-IC-generate-unchanged-modules")
            compilerOptions.freeCompilerArgs.add("-Xwasm-debug-info")
            compilerOptions.freeCompilerArgs.add("-Xwasm-generate-wat")
        }
}
