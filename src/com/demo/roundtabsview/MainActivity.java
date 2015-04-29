package com.demo.roundtabsview;

import java.util.ArrayList;

import com.demo.roundtabsview.RoundTab.OnItemClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private RoundTab round_tab;
	private ViewPager round_vp;
	private Button vp_bt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		round_tab = (RoundTab) findViewById(R.id.round_tab);

		final ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("1");
		arrayList.add("1");
		arrayList.add("1");
		arrayList.add("1");
		arrayList.add("1");
		arrayList.add("1");
		arrayList.add("1");
		

		final RoundTabAdapter roundTabAdapter = new RoundTabAdapter(arrayList,
				this);
		round_tab.setAdapter(roundTabAdapter);
		
		round_vp = (ViewPager) findViewById(R.id.round_vp);
		round_vp.setOffscreenPageLimit(2);//设置预加载  分别是左边2个  右边2个   共4个
		final VpAdapter vpAdapter = new VpAdapter(arrayList, this);
		final int startPosition = vpAdapter.getCount()/2; // 轮播起始位置
		round_vp.setAdapter(vpAdapter);
		
		vp_bt = (Button) findViewById(R.id.vp_bt);
		
		//TODO  获取到数据之前 loading  且界面不可点击   loading拦截touch事件
		
		vp_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				arrayList.add("1");
				arrayList.add("1");
				arrayList.add("1");
				
				//刷新
//				round_tab.setAdapter(roundTabAdapter);
				vpAdapter.notifyDataSetChanged();
				round_tab.notifyDataSetChanged(round_vp.getCurrentItem()-startPosition);
//				vpAdapter.notifyAll();
			}
		});
		
		round_vp.setCurrentItem(startPosition);//初始化到
		
		round_vp.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				
				System.out.println(" ##############################  position : " + position);
				round_tab.moveToTarget(position - startPosition);//转换为实际数据位置
				System.out.println(" ##############################  position - startPosition : " + (position - startPosition));
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		round_tab.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(View v, int position) {
				// TODO Auto-generated method stub
				
				int vpPosition = position+startPosition;
				System.out.println(" ##############################  vpPosition : " + vpPosition);
				
				if(vpPosition>=0){
					round_vp.setCurrentItem(vpPosition);
				}
			}
		});
	}

}
