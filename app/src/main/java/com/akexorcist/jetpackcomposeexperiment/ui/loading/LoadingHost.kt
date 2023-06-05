package com.akexorcist.jetpackcomposeexperiment.ui.loading

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.RecomposeScope
import androidx.compose.runtime.State
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine

sealed class LoadingAction {
    object Show : LoadingAction()
    object Dismiss : LoadingAction()
}

class LoadingHostState {
    var currentLoadingData by mutableStateOf<LoadingData?>(null)
        private set

    suspend fun perform(action: LoadingAction) {
        when (action) {
            LoadingAction.Show -> show()
            LoadingAction.Dismiss -> dismiss()
        }
    }

    private suspend fun show() {
        return suspendCancellableCoroutine { continuation ->
            currentLoadingData = DefaultLoadingData(continuation)
        }
    }

    private fun dismiss() {
        currentLoadingData = null
    }
}

interface LoadingData

class DefaultLoadingData(
    private val continuation: CancellableContinuation<Unit>
) : LoadingData {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DefaultLoadingData

        if (continuation != other.continuation) return false

        return true
    }

    override fun hashCode(): Int {
        return continuation.hashCode()
    }
}

@Composable
fun LoadingHost(
    hostState: LoadingHostState,
    modifier: Modifier = Modifier,
    content: @Composable (LoadingData) -> Unit,
) {
    FadeInFadeOutWithScale(
        current = hostState.currentLoadingData,
        modifier = modifier,
        content = content
    )
}

@Composable
fun rememberLoadingHostState(): LoadingHostState = remember { LoadingHostState() }

@Composable
private fun FadeInFadeOutWithScale(
    current: LoadingData?,
    modifier: Modifier = Modifier,
    content: @Composable (LoadingData) -> Unit
) {
    val state = remember { FadeInFadeOutState<LoadingData?>() }
    if (current != state.current) {
        state.current = current
        val keys = state.items.map { it.key }.toMutableList()
        if (!keys.contains(current)) {
            keys.add(current)
        }
        state.items.clear()
        keys.filterNotNull().mapTo(state.items) { key ->
            FadeInFadeOutAnimationItem(key) { children ->
                val isVisible = key == current
                val duration = if (isVisible) LoadingFadeInMillis else LoadingFadeOutMillis
                val delay = LoadingFadeOutMillis + LoadingInBetweenDelayMillis
                val animationDelay = if (isVisible && keys.filterNotNull().size != 1) delay else 0
                val opacity = animatedOpacity(
                    animation = tween(
                        easing = LinearEasing,
                        delayMillis = animationDelay,
                        durationMillis = duration
                    ),
                    visible = isVisible,
                    onAnimationFinish = {
                        if (key != state.current) {
                            // leave only the current in the list
                            state.items.removeAll { it.key == key }
                            state.scope?.invalidate()
                        }
                    }
                )
                Box(
                    Modifier
                        .graphicsLayer(
                            alpha = opacity.value
                        )
                        .semantics {
                            liveRegion = LiveRegionMode.Polite
                        }
                ) {
                    children()
                }
            }
        }
    }
    Box(modifier) {
        state.scope = currentRecomposeScope
        state.items.forEach { (item, opacity) ->
            key(item) {
                opacity {
                    content(item!!)
                }
            }
        }
    }
}


@Composable
private fun animatedOpacity(
    animation: AnimationSpec<Float>,
    visible: Boolean,
    onAnimationFinish: () -> Unit = {}
): State<Float> {
    val alpha = remember { Animatable(if (!visible) 1f else 0f) }
    LaunchedEffect(visible) {
        alpha.animateTo(
            if (visible) 1f else 0f,
            animationSpec = animation
        )
        onAnimationFinish()
    }
    return alpha.asState()
}

private class FadeInFadeOutState<T> {
    // we use Any here as something which will not be equals to the real initial value
    var current: Any? = Any()
    var items = mutableListOf<FadeInFadeOutAnimationItem<T>>()
    var scope: RecomposeScope? = null
}

private data class FadeInFadeOutAnimationItem<T>(
    val key: T,
    val transition: FadeInFadeOutTransition
)

private typealias FadeInFadeOutTransition = @Composable (content: @Composable () -> Unit) -> Unit

private const val LoadingFadeInMillis = 150
private const val LoadingFadeOutMillis = 75
private const val LoadingInBetweenDelayMillis = 0