package top.jplayer.baseprolibrary.ui.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.model.bean.SampleBean;

/**
 * Created by Administrator on 2018/1/29.
 */

public class SampleAdapter extends BaseQuickAdapter<SampleBean.DataBean.ListBean, BaseViewHolder> {
    public SampleAdapter(List<SampleBean.DataBean.ListBean> sampleBeans) {
        super(R.layout.adapter_sample, sampleBeans);
    }

    @Override
    protected void convert(BaseViewHolder helper, SampleBean.DataBean.ListBean item) {
        helper.setText(R.id.tvTime, item.sendTime)
                .addOnClickListener(R.id.tvTime);
    }
}
