package com.cathive.fx.pastebin.server.repository;

import com.cathive.fx.pastebin.common.model.Paste;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.stream.LongStream;

import static java.util.UUID.randomUUID;

/**
 * @author Alexander Erben
 */
@Singleton
public class DefaultPasteRepository extends AbstractRepository<Paste, Long> implements PasteRepository {

    @PostConstruct
    public void fillWithDummyData() {
        /* LongStream.range(1, 30).forEach(
                i -> {
                    Paste p = new Paste();
                    p.setId(i);
                    p.setTitle(randomUUID().toString());
                    p.setContent(randomUUID().toString());
                    this.save(p);
                }
        ); */
    }
}
