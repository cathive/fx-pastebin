package com.cathive.fx.pastebin.server.repository;

import com.cathive.fx.pastebin.common.model.UserProfile;

import javax.inject.Singleton;

/**
 * @author Alexander Erben
 */
@Singleton
public class DefaultUserProfileRepository
        extends AbstractRepository<UserProfile, Long> implements UserProfileRepository {
}
