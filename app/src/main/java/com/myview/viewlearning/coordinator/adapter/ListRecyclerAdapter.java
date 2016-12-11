package com.myview.viewlearning.coordinator.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myview.viewlearning.R;

import java.util.List;

/**
 * 作者：tracyxia 时间：16/12/11/16:48 描述： 版本：
 */

public class ListRecyclerAdapter
		extends
			RecyclerView.Adapter<ListRecyclerAdapter.DefineViewHolder> {

	private List<String> list;

	public ListRecyclerAdapter(List<String> list) {
		this.list = list;
	}

	@Override
	public int getItemCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public void onBindViewHolder(DefineViewHolder viewHolder, int position) {
		viewHolder.setData(list.get(position));
	}

	@Override
	public DefineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater
				.from(parent.getContext());
		View view = layoutInflater.inflate(R.layout.item, parent, false);
		return new DefineViewHolder(view);
	}

	static class DefineViewHolder extends RecyclerView.ViewHolder {

		TextView tvTitle;

		public DefineViewHolder(View itemView) {
			super(itemView);
			tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
		}

		public void setData(String data) {
			tvTitle.setText(data);
		}

	}

}
