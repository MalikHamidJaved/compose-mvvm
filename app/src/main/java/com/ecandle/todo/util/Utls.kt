package com.ecandle.todo.util

import com.ecandle.todo.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException


class Utls {
}

suspend fun internetCheck(): Boolean = withContext(Dispatchers.IO)
{

    val runTime = Runtime.getRuntime()

    try {

        val ipProcess = runTime.exec("/system/bin/ping -c 1 www.google.com")
        val exitValue: Int = ipProcess.waitFor()
        return@withContext (exitValue == 0)

    } catch (e: IOException) {

        e.printStackTrace()

    } catch (e: InterruptedException) {

        e.printStackTrace()
    }

    return@withContext false
}

@Composable
fun LoadingAnimation(speed: Float) {

    val compositionLoading by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.anim_loading))
    val progressLoading by animateLottieCompositionAsState(
        composition = compositionLoading,
        isPlaying = true,
        speed = speed,
        restartOnPlay = true,
        iterations = LottieConstants.IterateForever
    )

    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(top = 1.dp)
    ) {

        LottieAnimation(
            composition = compositionLoading,
            progress = progressLoading,
            modifier = Modifier.size(45.dp)
        )

    }
}
