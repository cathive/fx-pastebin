package com.cathive.fx.pastebin.common.transfer;

import com.cathive.fx.pastebin.common.model.Paste;
import javafx.util.Builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Builder for {@link com.cathive.fx.pastebin.common.transfer.PasteListDto} instances.
 * @author Benjamin P. Jung
 */
public class PasteListDtoBuilder implements Builder<PasteListDto> {

    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    public static PasteListDtoBuilder create() {
        return new PasteListDtoBuilder();
    }

    public static PasteListDtoBuilder create(final Collection<Paste> entities) {
        if (entities == null) { return null; }
        final PasteListDtoBuilder builder = new PasteListDtoBuilder();
        final List<PasteDto> pastes = new ArrayList<>(entities.size());
        for (final Paste paste: entities) {
            pastes.add(PasteDtoBuilder.create(paste).build());
        }
        builder.pastes(pastes);
        return builder;
    }


    private Collection<PasteDto> pastes;


    public PasteListDtoBuilder pastes(final Collection<PasteDto> pastes) {
        this.pastes = pastes;
        return this;
    }


    @Override
    public PasteListDto build() {
        final PasteListDto dto = OBJECT_FACTORY.createPasteListDto();
        dto.getPaste().addAll(this.pastes);
        return dto;
    }

}
