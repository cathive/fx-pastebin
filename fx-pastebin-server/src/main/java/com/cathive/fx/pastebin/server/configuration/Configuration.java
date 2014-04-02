package com.cathive.fx.pastebin.server.configuration;

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.common.model.PasteType;
import com.cathive.fx.pastebin.common.model.UserProfile;
import com.cathive.fx.pastebin.server.repository.InMemPasteRepository;
import com.cathive.fx.pastebin.server.server.DefaultPastebinService;
import com.cathive.fx.pastebin.server.server.PastebinService;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.stream.LongStream;

import static java.util.UUID.randomUUID;

/**
 * @author Alexander Erben
 */
@Named
public class Configuration {

    @Produces
    public PastebinService getPastebinService() {
        InMemPasteRepository inMemPasteRepository = new InMemPasteRepository();
        LongStream.range(1, 30).forEach(i -> {
            Paste p = new Paste();
            p.setId(i);
            p.setContent(randomUUID().toString());
            p.setTitle(randomUUID().toString());
            p.setPasteType(new PasteType());
            p.setUserProfile(new UserProfile());
        });
        return new DefaultPastebinService(inMemPasteRepository);
    }

}
