package com.goodHot.fun.domain.media;

import com.goodHot.fun.enums.MediaEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TextMedia extends AbstractMedia {

    public TextMedia(String text){
        this.text = text;
    }

    private String text;
    private MediaEnum type = MediaEnum.TEXT;

}
