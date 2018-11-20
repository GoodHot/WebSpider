package com.goodHot.fun.webspider.domain.media;

import com.goodHot.fun.webspider.enums.StoreTypeEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MP4Media extends AbstractMedia {

    private String url;
    private Integer width;
    private Integer height;
    private Integer size;
    private Double duration;

    @Override
    public StoreTypeEnum getType() {
        return StoreTypeEnum.GIF;
    }
}
