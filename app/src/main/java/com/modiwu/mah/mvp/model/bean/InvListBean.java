package com.modiwu.mah.mvp.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/15.
 * com.modiwu.mah.mvp.model.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class InvListBean extends BaseBean {
    public List<InvtsBean> invts;

    public static class InvtsBean implements Parcelable {
        /**
         * invite_id : 13
         * user_id : 10005
         * ide_type : WM
         * phone : 17667936541
         * project_id : DMA20180913100054
         * project_name : 公寓 108-3单元-502
         * invu_id : 10005
         * invu_name : fjnndff
         * invu_phone : 17667936541
         * status : 0
         * ct : 2018-09-15 14:23:29
         */

        public int invite_id;
        public int user_id;
        public String ide_type;
        public String phone;
        public String project_id;
        public String project_name;
        public int invu_id;
        public String invu_name;
        public String invu_phone;
        public String status;
        public String ct;

        protected InvtsBean(Parcel in) {
            invite_id = in.readInt();
            user_id = in.readInt();
            ide_type = in.readString();
            phone = in.readString();
            project_id = in.readString();
            project_name = in.readString();
            invu_id = in.readInt();
            invu_name = in.readString();
            invu_phone = in.readString();
            status = in.readString();
            ct = in.readString();
        }

        public static final Creator<InvtsBean> CREATOR = new Creator<InvtsBean>() {
            @Override
            public InvtsBean createFromParcel(Parcel in) {
                return new InvtsBean(in);
            }

            @Override
            public InvtsBean[] newArray(int size) {
                return new InvtsBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(invite_id);
            dest.writeInt(user_id);
            dest.writeString(ide_type);
            dest.writeString(phone);
            dest.writeString(project_id);
            dest.writeString(project_name);
            dest.writeInt(invu_id);
            dest.writeString(invu_name);
            dest.writeString(invu_phone);
            dest.writeString(status);
            dest.writeString(ct);
        }
    }
}
