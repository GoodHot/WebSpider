package com.guorer.webspider.domain.media;

import com.guorer.webspider.enums.StoreTypeEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MP4Media extends AbstractMedia {

    @Override
    public StoreTypeEnum getType() {
        return StoreTypeEnum.GIF;
    }
}
