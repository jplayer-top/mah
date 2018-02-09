package com.modiwu.mah.mvp.model.bean;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2017/7/13.
 * com.ilanchuang.xiaoi.bean
 */

public class AliPayInfoBean extends BaseBean {
    /**
     * payType : 1
     * orderStr : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2088611240075044&biz_content=%7B%22body%22%3A%22%E5%B0%8F%E6%A4%85%E7%9B%B8%E4%BC%B4-%E5%B0%8F%E6%A4%85%E7%9B%92%E5%AD%90%22%2C%22goods_type%22%3A%221%22%2C%22out_trade_no%22%3A%222017071312000020151%22%2C%22passback_params%22%3A%22%7B%5Cn+++%5C%22ct%5C%22%3A+%5C%222017-07-13+12%3A00%3A01%5C%22%5Cn%7D%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22seller_id%22%3A%22ytdaykj%40163.com%22%2C%22subject%22%3A%22%E5%B0%8F%E6%A4%85%E7%9B%92%E5%AD%90%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22888.0%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&sign=nuy7EF0G5z3t%2BeJ0zjxEX3EoJ9bYkpRS37gLeTq7u5k6USdfN6DaOO5cnSYXaO1Zi6jMA%2B4OyySuTs5lzK8QwCk23VjrzS%2BLlfH3CF0bMEz8IHi5Kk%2B49nKqcvibnfR8zOkLZrimYxkNnC2yMcoAwX6C1%2BI9xNqEdlgv1PG0F2A%3D&sign_type=RSA2&timestamp=2017-07-13+12%3A00%3A05&version=1.0
     */
    public int payType;
    public String orderStr;
}
