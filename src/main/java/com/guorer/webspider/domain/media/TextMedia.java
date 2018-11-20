package com.guorer.webspider.domain.media;

import com.guorer.webspider.enums.StoreTypeEnum;
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
