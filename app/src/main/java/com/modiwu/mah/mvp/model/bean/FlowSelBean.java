package com.modiwu.mah.mvp.model.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/17.
 * com.modiwu.mah.mvp.model.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class FlowSelBean extends BaseBean {

    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * flow_id : ZGJ010101
         * flow_name : 房屋720度整体检测
         * flow_std : ①进门防盗门现状
         * ②门窗及玻璃有无破损，开启推拉是否灵活，内外耐候胶有无破损
         * ③厨房下水及地漏是否畅通，管道有无破损
         * ④卫浴间下水及地漏是否畅通，管道有无破损
         * ⑤卫浴间、厨房、阳台排污管道顶部是否有漏水痕迹
         * ⑥卫浴间48小时闭水试验情况，厨房、阳台依据实际情况进行
         * ⑦现有强电电源、通话是否正常，摇表测试是否达到0.5兆欧以上
         * ⑧中央空调及取暖设施
         * ⑨家用电器及原有家具
         * ⑩进水管、水表、煤气表现状
         * ⑪施工临时用水水质检测，确定是否符合施工要求
         * ⑫地面、墙面、顶面是否有基层开裂空鼓现象，并注明开裂根本原因
         * ⑬地面、横梁、墙面及顶面整体放线检测平整度情况，并进行明确标记
         * ⑭原有电视、网络线、电话线入户、防盗系统，用万用表测试，是否通断，并用文字说明分别在什么位置
         * flow_sort : 01
         * flow_type : STD
         * seg_id : ZGJ0101
         * ver_id : ZGJ01
         * ct : 2018-09-11 16:48:18
         */

        public String flow_id;
        public String flow_name;
        public String flow_std;
        public String flow_sort;
        public String flow_type;
        public String seg_id;
        public String ver_id;
        public String ct;
    }
}
