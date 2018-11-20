package com.goodHot.fun.webspider.domain.media;

import com.goodHot.fun.webspider.enums.StoreTypeEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TextMedia extends AbstractMedia {

    private String text;

    @Override
    public StoreTypeEnum getType() {
        return StoreTypeEnum.TEXT;
    }
}
