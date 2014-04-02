package com.cathive.fx.pastebin.server.repository;

import com.cathive.fx.pastebin.common.model.Paste;

import javax.inject.Singleton;

/**
 * @author Alexander Erben
 */
@Singleton
public class DefaultPasteRepository extends AbstractRepository<Paste, Long> implements PasteRepository {

}
