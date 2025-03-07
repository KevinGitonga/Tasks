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
package com.app.tasks.core.utils

import java.time.Clock
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

/**
 * Converts the [TemporalAccessor] to a formatted string based on the provided pattern and timezone.
 */
fun TemporalAccessor.toFormattedPattern(
    pattern: String,
    zone: ZoneId,
): String = DateTimeFormatter.ofPattern(pattern).withZone(zone).format(this)

/**
 * Converts the [TemporalAccessor] to a formatted string based on the provided pattern and timezone.
 */
fun TemporalAccessor.toFormattedPattern(
    pattern: String,
    clock: Clock = Clock.system(ZoneId.systemDefault()),
): String = toFormattedPattern(pattern = pattern, zone = clock.zone)
