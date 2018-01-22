package top.jplayer.baseprolibrary.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import top.jplayer.baseprolibrary.R;

/**
 * Created by Obl on 2018/1/22.
 * top.jplayer.baseprolibrary.ui.adapter
 */

public class AdapterGradLayout extends DelegateAdapter.Adapter {
    private GridLayoutHelper helper;
    private Context context;

    public AdapterGradLayout(Context context, GridLayoutHelper helper) {
        this.helper = helper;
        helper.setBgColor(R.color.azure);
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewholder(LayoutInflater.from(context).inflate(R.layout.layout_test, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewholder) holder).text.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        private TextView text;

        public MyViewholder(View view) {
            super(view);
            text = view.findViewById(R.id.tvNum);
        }
    }
}
