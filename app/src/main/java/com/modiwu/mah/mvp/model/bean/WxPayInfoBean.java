package com.modiwu.mah.mvp.model.bean;

import com.google.gson.annotations.SerializedName;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2017/7/13.
 * com.ilanchuang.xiaoi.bean
 */

public class WxPayInfoBean extends BaseBean {

    /**
     * payType : 2
     * orderStr : {"appid":"wx71dafeac9c6868f8","noncestr":"s3qclvjmouhbop0if2frfbqchl","package":"Sign=WXPay","partnerid":"1381611602","sign":"E0FE8788BB1D2274C87F0B0D2CC150B8","timestamp":"1499930442"}
     */

    public int payType;
    public OrderStrBean orderStr;

    public static class OrderStrBean {
        /**
         * appid : wx71dafeac9c6868f8
         *         wx2afd95f49d8dd6be
         * noncestr : s3qclvjmouhbop0if2frfbqchl
         *            3482O93OwAnvTISD
         * package : Sign=WXPay
         * partnerid : 1381611602
         *             1516506511
         *        EBCEBDA273C55E4C465C45FD629C42B3
         * sign : E0FE8788BB1D2274C87F0B0D2CC150B8
         * timestamp : 1499930442
         */
        public String prepayid;
        public String appid;
        public String noncestr;
        @SerializedName("package")
        public String packageX;
        public String partnerid;
        public String sign;
        public String timestamp;
    }
}
