package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/6.
 * com.modiwu.mah.mvp.model.bean
 */

public class MeFangAnBean extends BaseBean {

    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * order_no : 20180207104617423000
         * order_title : 未来幻想
         * order_desc : 楼盘：中华小区
         户型：两室一厅,84.00
         方案：未来幻想,整装
         软装：未来幻想
         * order_fee : 155400
         * user_id : 0
         * user_name : 王龙
         * user_phone : 15949885832
         * city_code : 370600
         * area_code : 370602
         * building_id : 1
         * building_name : 中华小区
         * fangan_top_id : 5
         * fangan_top_name : 未来幻想
         * fangan_id : 5
         * fangan_name : 未来幻想
         * fangan_r_id : 7
         * fangan_r_name : 未来幻想
         * fangan_type : 整装
         * order_time : 2018-02-07 10:46:17
         * order_status : 待付款
         * order_fee_yuan : 1554.00
         */

        public String order_no;
        public String order_title;
        public String order_desc;
        public int order_fee;
        public int user_id;
        public String user_name;
        public String user_phone;
        public String city_code;
        public String area_code;
        public int building_id;
        public String building_name;
        public int fangan_top_id;
        public String fangan_top_name;
        public int fangan_id;
        public String fangan_name;
        public int fangan_r_id;
        public String fangan_r_name;
        public String fangan_type;
        public String order_time;
        public String order_status;
        public String order_fee_yuan;
    }
}
