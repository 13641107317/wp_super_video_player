package com.example.wp.wp_super_video_player.indicator;

import java.util.List;

/**
 * Created by wangpeng .
 */
public interface IPagerIndircatorView extends IPagerChangeListener {

    void setPositionData(List<PositionEntity> list);
}
