/*
 * Copyright 2025 Kevin Gitonga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.app.tasks.core.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Convenience method for observing event flow from [BaseViewModel].
 *
 * By default, events will only be consumed when the associated screen is
 * resumed, to avoid bugs like duplicate navigation calls. To override
 * this behavior, a given event type can implement [BackgroundEvent].
 */
@Composable
fun <E> EventsEffect(
    viewModel: BaseViewModel<*, E, *>,
    lifecycleOwner: Lifecycle = LocalLifecycleOwner.current.lifecycle,
    handler: suspend (E) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow
            .filter {
                it is BackgroundEvent ||
                    lifecycleOwner.currentState.isAtLeast(Lifecycle.State.RESUMED)
            }.onEach { handler.invoke(it) }
            .launchIn(this)
    }
}
