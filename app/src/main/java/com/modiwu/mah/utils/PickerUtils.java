package com.modiwu.mah.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.modiwu.mah.mvp.model.event.PickerItemSel;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Obl on 2018/9/14.
 * com.modiwu.mah.utils
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PickerUtils {
    public TimePickerView pvTime;
    public OptionsPickerView mPickerView;


    public void initTimePicker(Context context) {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1930, 0, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), 11, 28);
        //时间选择器
        pvTime = new TimePickerView.Builder(context, (date, v) -> {//选中事件回调
            // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
            /*btn_Time.setText(getTime(date));*/
            TextView textView = (TextView) v;
            textView.setText(getTime(date));
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return format.format(date);
    }

    public void initStringPicker(final ArrayList<String> optionsItems, int
            position, Context context) {
        mPickerView = new OptionsPickerView.Builder(context, (options1, options2, options3, v) -> {
            String value = optionsItems.get(options1);
            if (v != null) {
                TextView textView = (TextView) v;
                textView.setText(value);
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setSubCalSize(18)//确定和取消文字大小
                .setContentTextSize(21)//滚轮文字大小
                .setDividerColor(Color.DKGRAY)
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(position)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .build();
        mPickerView.setPicker(optionsItems);//添加数据源
    }

    public void initStringPicker(final ArrayList<String> optionsItems, Context context, String type) {
        mPickerView = new OptionsPickerView.Builder(context, (options1, options2, options3, v) -> {
            String value = optionsItems.get(options1);
            EventBus.getDefault().post(new PickerItemSel(options1, value, type));
            if (v != null) {
                TextView textView = (TextView) v;
                textView.setText(value);
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setSubCalSize(18)//确定和取消文字大小
                .setContentTextSize(21)//滚轮文字大小
                .setDividerColor(Color.DKGRAY)
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .build();
        mPickerView.setPicker(optionsItems);//添加数据源
    }
}
