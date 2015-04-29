package com.demo.roundtabsview;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VpAdapter extends PagerAdapter {

	private ArrayList<String> images;
	private Context context;
	
	private SparseArray<View> showViewsList;
	private ArrayList<View> removeViewsList;
	
	
	public VpAdapter(ArrayList<String> images,Context context){
		this.context = context;
		this.images = images;
		showViewsList = new SparseArray<View>();
		removeViewsList = new ArrayList<View>();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
//		return images == null?0:images.size();
//		return images == null?0:images.size();
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		
		container.removeView(showViewsList.get(position));
		removeViewsList.add(showViewsList.get(position));
		showViewsList.remove(getActuallyPosition(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		
		View view;
		Holder holder;
		if(removeViewsList.size()>0){
			view = removeViewsList.remove(0);
			holder = (Holder) view.getTag();
		}else{
			view = View.inflate(context, R.layout.item_vpager, null);
			holder = new Holder();
			holder.tv = (TextView) view.findViewById(R.id.vp_tv);
		}
		
		holder.tv.setText("这是个测试   : " + getActuallyPosition(position));
		
		view.setTag(holder);
		
		showViewsList.put(position, view);
		((ViewPager) container).addView(view);
		
		return view;
	}
	
	private class Holder{
		TextView tv;
	}
	

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		
		for (int i = 0; i < showViewsList.size(); i++) {
			int key = showViewsList.keyAt(i);
			View view = showViewsList.get(key);
			Holder holder = (Holder)view.getTag();
			holder.tv.setText("这是个刷新测试 : " + getActuallyPosition(key));
		}
		
	}

	private int getActuallyPosition(int position){
		if(images==null||images.size()==0){
			return position;
		}
		int temp = position - getCount()/2;
		if(temp>=0){
			return temp%(images.size());
		}else{
			if(temp%images.size()==0){
				return 0;
			}
			return temp%(images.size()) + images.size();
		}
	}
	
}
