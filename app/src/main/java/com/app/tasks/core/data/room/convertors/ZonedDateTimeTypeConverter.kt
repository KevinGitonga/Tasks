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
package com.app.tasks.core.data.room.convertors

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

/**
 * A [TypeConverter] to convert a [ZonedDateTime] to and from a [Long].
 */
@ProvidedTypeConverter
class ZonedDateTimeTypeConverter {
    /**
     * A [TypeConverter] to convert a [Long] to a [ZonedDateTime].
     */
    @TypeConverter
    fun fromTimestamp(
        value: Long?,
    ): ZonedDateTime? =
        value?.let {
            ZonedDateTime.ofInstant(Instant.ofEpochSecond(it), ZoneOffset.UTC)
        }

    /**
     * A [TypeConverter] to convert a [ZonedDateTime] to a [Long].
     */
    @TypeConverter
    fun toTimestamp(
        localDateTime: ZonedDateTime?,
    ): Long? = localDateTime?.toEpochSecond()
}
