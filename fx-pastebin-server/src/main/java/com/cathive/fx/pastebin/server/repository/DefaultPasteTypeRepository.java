package com.cathive.fx.pastebin.server.repository;

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.common.model.PasteType;
import com.cathive.fx.pastebin.common.model.UserProfile;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.stream.LongStream;

import static java.time.LocalDateTime.now;
import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;

/**
 * @author Alexander Erben
 */
@Singleton
public class DefaultPasteTypeRepository extends AbstractRepository<PasteType, Long> implements PasteTypeRepository {

}
