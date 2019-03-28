package com.modiwu.mah.message;

import android.os.Parcel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MentionedInfo;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Administrator on 2019/3/28.
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */
@MessageTag(value = "XY:BpMsg", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class CustomizeBPMessage extends MessageContent {
    public String getName() {
        return rname;
    }


    private String rname = "";

    public CustomizeBPMessage(String rname) {
        this.rname = rname;
    }

    //给消息赋值。
    public CustomizeBPMessage(Parcel in) {
        rname = ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
        setMentionedInfo(ParcelUtils.readFromParcel(in, MentionedInfo.class));
    }

    /**
     * 将类的数据写入外部提供的 Parcel 中。
     *
     * @param dest  对象被写入的 Parcel。
     * @param flags 对象如何被写入的附加标志。
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeToParcel(dest, this.rname);
        ParcelUtils.writeToParcel(dest, this.getUserInfo());
        ParcelUtils.writeToParcel(dest, this.getMentionedInfo());
    }

    public CustomizeBPMessage(byte[] data) {
        String jsonStr = null;
        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
        }

        try {
            JSONObject e = new JSONObject(jsonStr);
            if (e.has("rname")) {
                rname = (e.optString("rname"));
            }

            if (e.has("user")) {
                this.setUserInfo(this.parseJsonToUserInfo(e.getJSONObject("user")));
            }

            if (e.has("mentionedInfo")) {
                this.setMentionedInfo(this.parseJsonToMentionInfo(e.getJSONObject("mentionedInfo")));
            }
        } catch (JSONException var4) {
            RLog.e("CustomizeBPMessage", jsonStr + "JSONException " + var4.getMessage());
        }
    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<CustomizeBPMessage> CREATOR = new Creator<CustomizeBPMessage>() {

        @Override
        public CustomizeBPMessage createFromParcel(Parcel source) {
            return new CustomizeBPMessage(source);
        }

        @Override
        public CustomizeBPMessage[] newArray(int size) {
            return new CustomizeBPMessage[size];
        }
    };

    /**
     * 描述了包含在 Parcelable 对象排列信息中的特殊对象的类型。
     *
     * @return 一个标志位，表明Parcelable对象特殊对象类型集合的排列。
     */
    public int describeContents() {
        return 0;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("rname", this.getName());

            if (this.getJSONUserInfo() != null) {
                jsonObj.putOpt("user", this.getJSONUserInfo());
            }

            if (this.getJsonMentionInfo() != null) {
                jsonObj.putOpt("mentionedInfo", this.getJsonMentionInfo());
            }
        } catch (JSONException var4) {
            RLog.e("CustomizeBPMessage", "JSONException " + var4.getMessage());
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
            return null;
        }
    }
}
