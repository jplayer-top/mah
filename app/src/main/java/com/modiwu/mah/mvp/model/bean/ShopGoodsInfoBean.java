package com.modiwu.mah.mvp.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/5.
 * com.modiwu.mah.mvp.model.bean
 */

public class ShopGoodsInfoBean extends BaseBean implements Parcelable {

    /**
     * goods : {"is_preheat":0,"goods_title":"小椅盒子","goods_price":77700,"goods_thumb":"https://mah.oss-cn-beijing.aliyuncs.com/foo/3.jpg","goods_stocks":180,"goods_id":10017,"goods_sn":"","goods_price_yuan":"777.00"}
     * attrs : [{"type_name":"颜色","type_id":5,"attrInfo":[{"attr_value":"黑色","attr_id":9},{"attr_value":"土豪金","attr_id":10}]}]
     * specs : [{"goods_attr_id":260,"goods_id":10017,"attr_id":"10","attr_values":"土豪金","goods_org_price":99900,"goods_best_price":77700,"goods_stock":100,"goods_best_price_yuan":"777.00","goods_org_price_yuan":"999.00"},{"goods_attr_id":259,"goods_id":10017,"attr_id":"9","attr_values":"黑色","goods_org_price":99900,"goods_best_price":88800,"goods_stock":80,"goods_best_price_yuan":"888.00","goods_org_price_yuan":"999.00"}]
     * args : [{"arg_id":1,"goods_id":10017,"arg_name":"产地","arg_value":"烟台"},{"arg_id":10000,"goods_id":10017,"arg_name":"长","arg_value":"2.2M"}]
     * fangans : [{"fangan_id":5,"building_id":1,"building_name":"中华小区","huxing_id":3,"huxing_type":"两室一厅","huxing_size":84,"fangan_name":"未来幻想","fangan_desc":"在遥远的未来，人们是住在火星上的，当然是火星的房子里的\t\t\t","fangan_avatar":"https://mah.oss-cn-beijing.aliyuncs.com/foo/fa1.jpg","fangan_adv_img":"/upload/fa/4e5859e54b7656ac9d8cb460590f2a06.jpg","fangan_style":"中式","fangan_per_price":2000,"fangan_price":150000,"fangan_dingjin":1,"fangan_city_code":"370600","fangan_area_code":"370602","fangan_type":"整装","fangan_top_id":0,"fangan_mr":"","designer_id":10000,"fangan_flag":"1","sort":""}]
     */

    public GoodsBean goods;
    public List<AttrsBean> attrs;
    public List<SpecsBean> specs;
    public List<ArgsBean> args;
    public List<FangansBean> fangans;

    protected ShopGoodsInfoBean(Parcel in) {
        attrs = in.createTypedArrayList(AttrsBean.CREATOR);
    }

    public static final Creator<ShopGoodsInfoBean> CREATOR = new Creator<ShopGoodsInfoBean>() {
        @Override
        public ShopGoodsInfoBean createFromParcel(Parcel in) {
            return new ShopGoodsInfoBean(in);
        }

        @Override
        public ShopGoodsInfoBean[] newArray(int size) {
            return new ShopGoodsInfoBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(attrs);
    }

    public static class GoodsBean {
        /**
         * is_preheat : 0
         * goods_title : 小椅盒子
         * goods_price : 77700
         * goods_thumb : https://mah.oss-cn-beijing.aliyuncs.com/foo/3.jpg
         * goods_stocks : 180
         * goods_id : 10017
         * goods_sn :
         * goods_price_yuan : 777.00
         */

        public int is_preheat;
        public String goods_title;
        public int goods_price;
        public String goods_thumb;
        public int goods_stocks;
        public int goods_id;
        public String goods_sn;
        public String goods_price_yuan;
    }

    public static class AttrsBean implements Parcelable{
        /**
         * type_name : 颜色
         * type_id : 5
         * attrInfo : [{"attr_value":"黑色","attr_id":9},{"attr_value":"土豪金","attr_id":10}]
         */

        public String type_name;
        public int type_id;
        public List<AttrInfoBean> attrInfo;

        protected AttrsBean(Parcel in) {
            type_name = in.readString();
            type_id = in.readInt();
            attrInfo = in.createTypedArrayList(AttrInfoBean.CREATOR);
        }

        public static final Creator<AttrsBean> CREATOR = new Creator<AttrsBean>() {
            @Override
            public AttrsBean createFromParcel(Parcel in) {
                return new AttrsBean(in);
            }

            @Override
            public AttrsBean[] newArray(int size) {
                return new AttrsBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(type_name);
            dest.writeInt(type_id);
            dest.writeTypedList(attrInfo);
        }

        public static class AttrInfoBean implements Parcelable{
            /**
             * attr_value : 黑色
             * attr_id : 9
             */

            public String attr_value;
            public int attr_id;

            protected AttrInfoBean(Parcel in) {
                attr_value = in.readString();
                attr_id = in.readInt();
            }

            public static final Creator<AttrInfoBean> CREATOR = new Creator<AttrInfoBean>() {
                @Override
                public AttrInfoBean createFromParcel(Parcel in) {
                    return new AttrInfoBean(in);
                }

                @Override
                public AttrInfoBean[] newArray(int size) {
                    return new AttrInfoBean[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(attr_value);
                dest.writeInt(attr_id);
            }
        }
    }

    public static class SpecsBean {
        /**
         * goods_attr_id : 260
         * goods_id : 10017
         * attr_id : 10
         * attr_values : 土豪金
         * goods_org_price : 99900
         * goods_best_price : 77700
         * goods_stock : 100
         * goods_best_price_yuan : 777.00
         * goods_org_price_yuan : 999.00
         */

        public int goods_attr_id;
        public int goods_id;
        public String attr_id;
        public String attr_values;
        public int goods_org_price;
        public int goods_best_price;
        public int goods_stock;
        public String goods_best_price_yuan;
        public String goods_org_price_yuan;
    }

    public static class ArgsBean {
        /**
         * arg_id : 1
         * goods_id : 10017
         * arg_name : 产地
         * arg_value : 烟台
         */

        public int arg_id;
        public int goods_id;
        public String arg_name;
        public String arg_value;
    }

    public static class FangansBean {
        /**
         * fangan_id : 5
         * building_id : 1
         * building_name : 中华小区
         * huxing_id : 3
         * huxing_type : 两室一厅
         * huxing_size : 84.0
         * fangan_name : 未来幻想
         * fangan_desc : 在遥远的未来，人们是住在火星上的，当然是火星的房子里的
         * fangan_avatar : https://mah.oss-cn-beijing.aliyuncs.com/foo/fa1.jpg
         * fangan_adv_img : /upload/fa/4e5859e54b7656ac9d8cb460590f2a06.jpg
         * fangan_style : 中式
         * fangan_per_price : 2000
         * fangan_price : 150000
         * fangan_dingjin : 1
         * fangan_city_code : 370600
         * fangan_area_code : 370602
         * fangan_type : 整装
         * fangan_top_id : 0
         * fangan_mr :
         * designer_id : 10000
         * fangan_flag : 1
         * sort :
         */

        public int fangan_id;
        public int building_id;
        public String building_name;
        public int huxing_id;
        public String huxing_type;
        public double huxing_size;
        public String fangan_name;
        public String fangan_desc;
        public String fangan_avatar;
        public String fangan_adv_img;
        public String fangan_style;
        public int fangan_per_price;
        public int fangan_price;
        public int fangan_dingjin;
        public String fangan_city_code;
        public String fangan_area_code;
        public String fangan_type;
        public int fangan_top_id;
        public String fangan_mr;
        public int designer_id;
        public String fangan_flag;
        public String sort;
    }
}
