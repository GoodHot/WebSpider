package com.goodHot.fun.domain.media;

import com.goodHot.fun.enums.MediaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class GIFMedia extends AbstractMedia {

    public GIFMedia(String url) {
        this.url = url;
    }

    private String url;
    private MediaEnum type = MediaEnum.GIF;

}
