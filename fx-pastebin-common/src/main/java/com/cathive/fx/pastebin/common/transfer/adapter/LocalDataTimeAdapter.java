/*
 * Copyright (C) 2014 The Cat Hive Developers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cathive.fx.pastebin.common.transfer.adapter;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

/**
 * Static adapter method that can handle the new java.time classes correctly.
 * @author Benjamin P. Jung
 */
public class LocalDataTimeAdapter {

    public static String printLocalDateTime(final LocalDateTime localDateTime) {
        return localDateTime.format(ISO_DATE_TIME);
    }


    public static LocalDateTime parseLocalDateTime(final String string) {
        return LocalDateTime.parse(string, ISO_DATE_TIME);
    }

}
