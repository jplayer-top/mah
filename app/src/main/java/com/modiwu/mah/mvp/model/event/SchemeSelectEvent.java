package com.modiwu.mah.mvp.model.event;

/**
 * Created by Administrator on 2018/2/8.
 */

public class SchemeSelectEvent {
    public String city_code;
    public String area_code;
    public String building_id;
    public String fangan_style;
    public String huxing_type;

    public SchemeSelectEvent(String city_code, String area_code, String building_id, String fangan_style, String huxing_type) {
        this.city_code = city_code;
        this.area_code = area_code;
        this.building_id = building_id;
        this.fangan_style = fangan_style;
        this.huxing_type = huxing_type;
    }
}
