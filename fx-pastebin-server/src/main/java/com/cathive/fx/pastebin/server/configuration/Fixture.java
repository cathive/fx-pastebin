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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static java.util.stream.LongStream.range;

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
        new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("pastebinPasteTypes.properties")))
                .lines()
                .map(i -> i.split("=")[0])
                .forEach(name -> {
                    PasteType pasteType = new PasteType();
                    pasteType.setName(name);
                    testPasteTypeRepo.save(pasteType);
                    testPasteTypeRepo.flush();
                });
        List<PasteType> all = new ArrayList<>(testPasteTypeRepo.findAll());
        Random rand = new Random();
        range(1, 1000).forEach(
                i -> {
                    UserProfile userProfile = new UserProfile();
                    userProfile.setName(rand());
                    UserProfile savedUser = testUserProfileRepo.save(userProfile);
                    testUserProfileRepo.flush();
                    Paste paste = new Paste();
                    paste.setTitle(rand());
                    paste.setContent(rand());
                    paste.setCreated(now());
                    paste.setUserProfile(savedUser);
                    PasteType toInsert = null;
                    while (toInsert == null)
                        toInsert = testPasteTypeRepo.findOne((long) rand.nextInt(all.size()));
                    paste.setPasteType(toInsert);
                    testPasteRepo.save(paste);
                }
        );


    }

    private String rand() {
        return randomUUID().toString().substring(1, 10);
    }
}
