package com.goodHot.fun.domain.media;

import com.goodHot.fun.enums.MediaEnum;
import lombok.Data;
import lombok.ToString;

/**
 * coub: 视频、音频 是分开的两个文件
 */
@Data
@ToString
public class CoubEmbedMedia extends AbstractMedia {

    public CoubEmbedMedia() {

    }

    public CoubEmbedMedia(String url) {
        this.url = url;
    }

    private String url;
    private MediaEnum type = MediaEnum.COUB_EMBED;

    // 视频URL
    private String videoURL;
    // 音频URL
    private String audioURL;
    private Integer width;
    private Integer height;

}
