package com.cathive.fx.pastebin.common.transfer;

import com.cathive.fx.pastebin.common.model.Paste;
import com.cathive.fx.pastebin.common.model.UserProfile;
import javafx.util.Builder;

/**
 * Builder for {@link com.cathive.fx.pastebin.common.transfer.PasteDto} instances.
 */
public class PasteDtoBuilder implements Builder<PasteDto> {

    private Long id;
    private String title;
    private String content;
    private Long userProfileId;
    private Long pasteTypeId;


    public static PasteDtoBuilder create() {
        return new PasteDtoBuilder();
    }

    public static PasteDtoBuilder create(final Paste entity) {
        if (entity == null) { return null; }
        final PasteDtoBuilder builder = new PasteDtoBuilder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .pasteTypeId(entity.getPasteType().getId());
        final UserProfile userProfile = entity.getUserProfile();
        if (userProfile != null) {
            builder.userProfileId(userProfile.getId());
        }
        return builder;
    }


    public PasteDtoBuilder id(final Long id) {
        this.id = id;
        return this;
    }

    public PasteDtoBuilder title(final String title) {
        this.title = title;
        return this;
    }
    public PasteDtoBuilder content(final String content) {
        this.content = content;
        return this;
    }

    public PasteDtoBuilder pasteTypeId(final Long contentTypeId) {
        this.pasteTypeId = contentTypeId;
        return this;
    }

    public PasteDtoBuilder userProfileId(final Long userProfileId) {
        this.userProfileId = userProfileId;
        return this;
    }


    @Override
    public PasteDto build() {
        final PasteDto dto = new PasteDto();
        dto.setId(this.id);
        dto.setTitle(this.title);
        dto.setContent(this.content);
        dto.setPasteTypeId(this.pasteTypeId);
        dto.setUserProfileId(this.userProfileId);
        return dto;
    }

}
