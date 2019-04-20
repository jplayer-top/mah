package com.modiwu.mah.message;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.modiwu.mah.R;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;

/**
 * Created by Administrator on 2019/3/28.
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

@ProviderTag(messageContent = CustomizeBPMessage.class, showPortrait = false, centerInHorizontal = true, showSummaryWithName = false)
public class CustomizeBPMessageItemProvide extends IContainerItemProvider.MessageProvider<CustomizeBPMessage> {


    class ViewHolder {
        TextView rname;

    }

    @Override
    public View newView(Context context, ViewGroup group) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_customize_bp_message, group, false);
        ViewHolder holder = new ViewHolder();
        holder.rname = view.findViewById(R.id.tvName);
        view.setTag(holder);
        return view;
    }


    @Override
    public void bindView(View view, int position, CustomizeBPMessage content, UIMessage message) {
        ViewHolder holder = (ViewHolder) view.getTag();
        String strName = content.getName();
        holder.rname.setText(strName);


    }

    @Override
    public Spannable getContentSummary(CustomizeBPMessage data) {
        return new SpannableString("通知");
    }

    @Override
    public void onItemClick(View view, int i, CustomizeBPMessage customizeBPMessage, UIMessage uiMessage) {

    }

    @Override
    public void onItemLongClick(View view, int i, CustomizeBPMessage customizeBPMessage, UIMessage uiMessage) {

    }

}