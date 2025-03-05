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
package com.app.tasks.core.button

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.app.tasks.core.utils.Text
import com.app.tasks.core.utils.rememberVectorPainter
import kotlinx.parcelize.Parcelize

/**
 * Data class representing the resources required for an icon.
 *
 * @property iconPainter Painter for the icon.
 * @property contentDescription String for the icon's content description.
 * @property testTag The optional test tag to associate with this icon.
 */
data class IconResource(
    val iconPainter: Painter,
    val contentDescription: String,
    val testTag: String? = null,
)

/**
 * Data class representing the resources required for an icon and is friendly to use in ViewModels.
 *
 * @property iconRes Resource for the icon.
 * @property contentDescription The icon's content description.
 * @property testTag The optional test tag to associate with this icon.
 */
@Parcelize
data class IconRes(
    @DrawableRes
    val iconRes: Int,
    val contentDescription: Text,
    val testTag: String? = null,
) : Parcelable

/**
 * A helper method to convert a list of [IconRes] to a list of [IconResource].
 */
@Composable
fun List<IconRes>.toIconResources(): List<IconResource> = this.map { it.toIconResource() }

/**
 * A helper method to convert an [IconRes] to an [IconResource].
 */
@Composable
fun IconRes.toIconResource(): IconResource =
    IconResource(
        iconPainter = rememberVectorPainter(id = iconRes),
        contentDescription = contentDescription(),
        testTag = testTag,
    )
