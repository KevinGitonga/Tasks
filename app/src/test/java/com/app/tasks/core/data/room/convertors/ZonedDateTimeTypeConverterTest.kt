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

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test
import java.time.ZonedDateTime

class ZonedDateTimeTypeConverterTest {
    private val typeConverter = ZonedDateTimeTypeConverter()

    @Test
    fun `fromTimestamp should return null when value is null`() {
        val value: Long? = null

        val result = typeConverter.fromTimestamp(value)

        assertNull(result)
    }

    @Test
    fun `fromTimestamp should return correct ZonedDateTime when value is not null`() {
        val expected = ZonedDateTime.parse("2023-12-15T20:38:06Z")
        val value = expected.toEpochSecond()

        val result = typeConverter.fromTimestamp(value)

        assertEquals(expected, result)
    }

    @Test
    fun `toTimestamp should return null when value is null`() {
        val value: ZonedDateTime? = null

        val result = typeConverter.toTimestamp(value)

        assertNull(result)
    }

    @Test
    fun `toTimestamp should return correct Long when value is not null`() {
        val value = ZonedDateTime.parse("2023-12-15T20:38:06Z")
        val expected = value.toEpochSecond()

        val result = typeConverter.toTimestamp(value)

        assertEquals(expected, result)
    }
}
