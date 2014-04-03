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

package com.cathive.fx.pastebin.server;

import com.cathive.fx.pastebin.common.JsonConverter;

import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * @author Alexander Erben (a_erben@outlook.com)
 */
@Singleton
public class Configuration {

    @Produces
    @Named("converter")
    public JsonConverter getJsonConverter(){
        return new JsonConverter();
    }
}
