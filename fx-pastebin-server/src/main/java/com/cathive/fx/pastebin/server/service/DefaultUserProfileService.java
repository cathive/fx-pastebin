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

package com.cathive.fx.pastebin.server.service;

import com.cathive.fx.pastebin.common.model.UserProfile;
import com.cathive.fx.pastebin.server.repository.UserProfileRepository;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.Collection;

/**
 * @author Alexander Erben
 */
@Singleton
public class DefaultUserProfileService implements UserProfileService {

    @Inject
    private UserProfileRepository userProfileRepository;

    @Override
    public Collection<UserProfile> findAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile findUserProfileById(Long id) {
        return userProfileRepository.findOne(id);
    }

    @Override
    public UserProfile saveUserProfile(UserProfile toSave) {
        return userProfileRepository.save(toSave);
    }

    @Override
    public void deleteUserProfile(UserProfile toDelete) {
        userProfileRepository.delete(toDelete);
    }
}
