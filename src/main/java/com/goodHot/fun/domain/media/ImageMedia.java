package com.goodHot.fun.domain.media;

import com.goodHot.fun.enums.MediaEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ImageMedia extends AbstractMedia {

    public ImageMedia(String url){
        this.url = url;
    }

    private String url;
    private MediaEnum type = MediaEnum.JPEG;

}
