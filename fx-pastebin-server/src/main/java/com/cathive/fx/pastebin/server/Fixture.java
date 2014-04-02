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

package com.cathive.fx.pastebin.server;

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
import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;

/**
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
                    pasteType.setId(i);
                    pasteType.setName(rand());
                    testPasteTypeRepo.save(pasteType);
                });
        LongStream.range(1, 30).forEach(
                i -> {
                    UserProfile user = new UserProfile();
                    user.setId(i);
                    user.setName(rand());
                    testUserProfileRepo.save(user);
                });
        LongStream.range(1, 30).forEach(
                i -> {
                    Paste p = new Paste();
                    p.setId(i);
                    p.setTitle(rand());
                    p.setContent(rand());
                    p.setCreation(now());
                    p.setUserProfile(createUserProfile(i, p));
                    p.setPasteType(createPasteType(i));
                    testPasteRepo.save(p);
                }
        );
    }

    private PasteType createPasteType(long i) {
        PasteType pasteType = new PasteType();
        pasteType.setId(i);
        pasteType.setName(rand());
        return pasteType;
    }

    private String rand() {
        return randomUUID().toString().substring(1, 10);
    }

    private UserProfile createUserProfile(long i, Paste p) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(i);
        userProfile.setName("Name");
        userProfile.setPastes(singletonList(p));
        return userProfile;
    }


}
