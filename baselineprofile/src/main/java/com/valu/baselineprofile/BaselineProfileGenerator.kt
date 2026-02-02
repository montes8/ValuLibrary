package com.valu.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This test class generates a basic startup baseline profile for the target package.
 *
 * We recommend you start with this but add important user flows to the profile to improve their performance.
 * Refer to the [baseline profile documentation](https://d.android.com/topic/performance/baselineprofiles)
 * for more information.
 *
 * You can run the generator with the "Generate Baseline Profile" run configuration in Android Studio or
 * the equivalent `generateBaselineProfile` gradle task:
 * ```
 * ./gradlew :composeApp:generateReleaseBaselineProfile
 * ```
 * The run configuration runs the Gradle task and applies filtering to run only the generators.
 *
 * Check [documentation](https://d.android.com/topic/performance/benchmarking/macrobenchmark-instrumentation-args)
 * for more information about available instrumentation arguments.
 *
 * After you run the generator, you can verify the improvements running the [StartupBenchmarks] benchmark.
 *
 * When using this class to generate a baseline profile, only API 33+ or rooted API 28+ are supported.
 *
 * The minimum required version of androidx.benchmark to generate a baseline profile is 1.2.0.
 **/
@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val baselineRule = BaselineProfileRule()

    @Test
    fun generate() = baselineRule.collect(
        packageName = "com.valu.valulibrary", // Tu namespace según tu gradle
        includeInStartupProfile = true
    ) {
        // 1. Inicia la aplicación (Esto optimiza tu ScreenMySplash)
        pressHome()
        startActivityAndWait()

        // 2. Espera a que termine tu Splash y la navegación hacia Home
        // Como tu Splash dura 1000ms + lógica de carga, damos un margen
        device.waitForIdle()
        Thread.sleep(3000) // Tiempo suficiente para queviewModel.loadValidateLogin() termine

        // 3. Optimizar InitScreen (La que tiene el video y textos largos)
        // Buscamos el componente que tiene el scroll
        val scrollColumn = device.findObject(By.scrollable(true))
        scrollColumn?.scroll(Direction.DOWN, 0.5f)
        device.waitForIdle()

        // 4. Navegar a la pestaña de Productos (ProductScreen)
        // Asumiendo que tu BottomBar tiene el texto o una descripción de contenido
        val productTab = device.findObject(By.text("Product")) // O el nombre que uses en TayDestinations
        productTab?.click()

        // 5. Optimizar el LazyColumn de Productos
        // Esto es vital para que el scroll del LazyColumn sea suave en gamas bajas
        val productList = device.findObject(By.scrollable(true))
        productList?.fling(Direction.DOWN) // Un movimiento rápido para estresar el renderizado
        device.waitForIdle()

        // 6. Navegar a Categorías (MoresScreen)
        val moreTab = device.findObject(By.text("More")) // Ajustar según tu stringResource
        moreTab?.click()

        // 7. Optimizar el LazyVerticalGrid
        val grid = device.findObject(By.scrollable(true))
        grid?.scroll(Direction.DOWN, 1f)
        device.waitForIdle()
    }
}