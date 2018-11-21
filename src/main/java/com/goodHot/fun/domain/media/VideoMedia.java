package com.goodHot.fun.domain.media;

import com.goodHot.fun.enums.MediaEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VideoMedia extends AbstractMedia {

    public VideoMedia(String url) {
        this.url = url;
    }

    private String url;
    private String posterUrl;
    private MediaEnum type = MediaEnum.VIDEO;
}
