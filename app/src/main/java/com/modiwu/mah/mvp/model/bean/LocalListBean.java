package com.modiwu.mah.mvp.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/2/7.
 * com.modiwu.mah.mvp.model.bean
 */

public class LocalListBean extends BaseBean implements Parcelable {

    public List<RecordsBean> records;

    protected LocalListBean(Parcel in) {
        records = in.createTypedArrayList(RecordsBean.CREATOR);
    }

    public static final Creator<LocalListBean> CREATOR = new Creator<LocalListBean>() {
        @Override
        public LocalListBean createFromParcel(Parcel in) {
            return new LocalListBean(in);
        }

        @Override
        public LocalListBean[] newArray(int size) {
            return new LocalListBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(records);
    }

    public static class RecordsBean implements Parcelable {
        /**
         * rp_id : 22
         * user_id : 0
         * rp_name : 一个人
         * rp_phone : 15875587558
         * rp_province : 北京市
         * rp_city : 市辖区
         * rp_area : 东城区
         * rp_addr : 林场地处东经
         * rp_default : 1
         * ct : 2017-07-11 11:34:15
         * phone : 158****7558
         */

        public int rp_id;
        public int user_id;
        public String rp_name;
        public String rp_phone;
        public String rp_province;
        public String rp_city;
        public String rp_area;
        public String rp_addr;
        public int rp_default;
        public String ct;
        public String phone;

        protected RecordsBean(Parcel in) {
            rp_id = in.readInt();
            user_id = in.readInt();
            rp_name = in.readString();
            rp_phone = in.readString();
            rp_province = in.readString();
            rp_city = in.readString();
            rp_area = in.readString();
            rp_addr = in.readString();
            rp_default = in.readInt();
            ct = in.readString();
            phone = in.readString();
        }

        public static final Creator<RecordsBean> CREATOR = new Creator<RecordsBean>() {
            @Override
            public RecordsBean createFromParcel(Parcel in) {
                return new RecordsBean(in);
            }

            @Override
            public RecordsBean[] newArray(int size) {
                return new RecordsBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(rp_id);
            dest.writeInt(user_id);
            dest.writeString(rp_name);
            dest.writeString(rp_phone);
            dest.writeString(rp_province);
            dest.writeString(rp_city);
            dest.writeString(rp_area);
            dest.writeString(rp_addr);
            dest.writeInt(rp_default);
            dest.writeString(ct);
            dest.writeString(phone);
        }
    }
}
