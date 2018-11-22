package com.goodHot.fun.domain.media;

import com.goodHot.fun.enums.MediaEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JPEGMedia extends AbstractMedia {

    public JPEGMedia(String url, Integer width, Integer height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public JPEGMedia() {
    }

    private String url;
    private Integer width;
    private Integer height;

    private MediaEnum type = MediaEnum.JPEG;

}
