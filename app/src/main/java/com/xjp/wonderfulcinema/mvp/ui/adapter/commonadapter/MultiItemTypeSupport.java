package com.xjp.wonderfulcinema.mvp.ui.adapter.commonadapter;

public interface MultiItemTypeSupport<T>
{
	int getLayoutId(int itemType);

	int getItemViewType(int position, T t);
}