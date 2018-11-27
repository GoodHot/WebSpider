package com.goodHot.fun.domain.media;

import com.goodHot.fun.enums.MediaEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CoubEmbedMedia extends AbstractMedia {

    public CoubEmbedMedia(String url){
        this.url = url;
    }

    private String url;
    private MediaEnum type = MediaEnum.COUB_EMBED;

}
