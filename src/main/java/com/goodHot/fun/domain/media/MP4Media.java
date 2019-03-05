package com.goodHot.fun.domain.media;

import com.goodHot.fun.enums.MediaEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MP4Media extends AbstractMedia {

    public MP4Media(String videoUrl, String posterUrl, Integer width, Integer height, Boolean hasAudio) {
        this.videoUrl = videoUrl;
        this.posterUrl = posterUrl;
        this.height = height;
        this.width = width;
        this.hasAudio = hasAudio;
    }

    public MP4Media() {}

    /**
     * 视频URL
     */
    private String videoUrl;
    /**
     * 封面图URL
     */
    private String posterUrl;
    private Integer height;
    private Integer width;
    /**
     * 是否有声音
     */
    private Boolean hasAudio;

    private MediaEnum type = MediaEnum.VIDEO;
}
