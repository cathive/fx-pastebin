/*
 * Copyright (C) 2014 The Cat Hive Developers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cathive.fx.pastebin.server.rest;

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.common.model.PasteType;
import com.cathive.fx.pastebin.common.model.UserProfile;
import com.cathive.fx.pastebin.server.repository.PasteRepository;
import com.cathive.fx.pastebin.server.repository.PasteTypeRepository;
import com.cathive.fx.pastebin.server.repository.UserProfileRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Iterator;

import static java.time.LocalDateTime.now;
import static java.util.Arrays.asList;

/**
 * @author Alexander Erben (a_erben@outlook.com)
 */

@Singleton
@Startup
public class TestFixture {

    private final PasteType[] PASTE_TYPES = {
            pt("c", "C"),
            pt("d", "D"),
            pt("delphi", "Delphi")
    };

    private final UserProfile[] USER_PROFILES = {
            u("John Doe"),
            u("Peter Griffin"),
            u("Heisenberg")
    };

    private final Paste[] PASTES ={
            p("main", "int main(int argc, char *argv[]){}", now()),
            p( "main", "void main() {}", now()),
            p( "main", "no such thing", now())};

    @Inject
    private PasteRepository testPasteRepo;

    @Inject
    private PasteTypeRepository testPasteTypeRepo;

    @Inject
    private UserProfileRepository testUserProfileRepo;

    @PostConstruct
    public void setup() {
        asList(PASTE_TYPES).forEach(testPasteTypeRepo::save);
        asList(USER_PROFILES).forEach(testUserProfileRepo::save);
        testPasteTypeRepo.flush();
        testUserProfileRepo.flush();
        Iterator<PasteType> pt = testPasteTypeRepo.findAll().iterator();
        Iterator<UserProfile> up = testUserProfileRepo.findAll().iterator();
        asList(PASTES).forEach(
                paste -> {
                    paste.setUserProfile(up.next());
                    paste.setPasteType(pt.next());
                    testPasteRepo.save(paste);
                }
        );
    }

    private PasteType pt(String name, String des) {
        return new PasteType(name, des);
    }

    private UserProfile u(String name) {
        return new UserProfile(name);
    }

    private Paste p( String title, String content, LocalDateTime time) {
        return new Paste(title, content, time);
    }
}
