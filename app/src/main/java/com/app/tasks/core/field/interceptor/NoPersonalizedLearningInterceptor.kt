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
package com.app.tasks.core.field.interceptor

import android.view.inputmethod.EditorInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.InterceptPlatformTextInput
import androidx.compose.ui.platform.PlatformTextInputInterceptor
import androidx.compose.ui.platform.PlatformTextInputMethodRequest
import androidx.compose.ui.platform.PlatformTextInputSession

/**
 * Interceptor that disables the [EditorInfo.IME_FLAG_NO_PERSONALIZED_LEARNING] flag on text inputs.
 */
@OptIn(ExperimentalComposeUiApi::class)
object NoPersonalizedLearningInterceptor : PlatformTextInputInterceptor {
    override suspend fun interceptStartInputMethod(
        request: PlatformTextInputMethodRequest,
        nextHandler: PlatformTextInputSession,
    ): Nothing {
        val modifiedRequest =
            PlatformTextInputMethodRequest { outAttrs ->
                request.createInputConnection(outAttrs)
                    .also {
                        outAttrs.imeOptions =
                            outAttrs.imeOptions or EditorInfo.IME_FLAG_NO_PERSONALIZED_LEARNING
                    }
            }
        nextHandler.startInputMethod(modifiedRequest)
    }
}

/**
 * A [InterceptPlatformTextInput] that disables the [EditorInfo.IME_FLAG_NO_PERSONALIZED_LEARNING]
 * flag on text inputs to force the keyboard into "Incognito Mode".
 *
 * This is a modified version of the workaround provided by Google here:
 * https://issuetracker.google.com/issues/359257538#comment2
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun IncognitoInput(content: @Composable () -> Unit) {
    InterceptPlatformTextInput(
        interceptor = NoPersonalizedLearningInterceptor,
        content = content,
    )
}
