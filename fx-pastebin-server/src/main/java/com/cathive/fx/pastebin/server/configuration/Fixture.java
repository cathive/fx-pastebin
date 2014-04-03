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

package com.cathive.fx.pastebin.server.configuration;

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
import java.util.stream.LongStream;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

/**
 * Fills the repositories with sample data.
 *
 * @author Alexander Erben
 */
@Singleton
@Startup
public class Fixture {

    @Inject
    private PasteRepository testPasteRepo;

    @Inject
    private PasteTypeRepository testPasteTypeRepo;

    @Inject
    private UserProfileRepository testUserProfileRepo;

    @PostConstruct
    public void setup() {
        LongStream.range(1, 30).forEach(
                i -> {
                    PasteType pasteType = new PasteType();
                    pasteType.setName(rand());
                    PasteType savedType = testPasteTypeRepo.save(pasteType);

                    UserProfile userProfile = new UserProfile();
                    userProfile.setName(rand());
                    UserProfile savedUser = testUserProfileRepo.save(userProfile);

                    Paste paste = new Paste();
                    paste.setTitle(rand());
                    paste.setContent(rand());
                    paste.setCreated(now());

                    paste.setUserProfile(savedUser);
                    paste.setPasteType(savedType);
                    testPasteRepo.save(paste);
                    testPasteTypeRepo.flush();
                });
    }

    private String rand() {
        return randomUUID().toString().substring(1, 10);
    }
}
