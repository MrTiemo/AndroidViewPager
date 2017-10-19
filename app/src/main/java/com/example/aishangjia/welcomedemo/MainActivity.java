package com.example.aishangjia.welcomedemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener  {

    private ViewPager mviewPager;
    private List<View> viewList;

    private int []imageIdArray;//图片资源的数组

    private Button joinButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //初始化进入应用按钮
        joinButton = (Button) findViewById(R.id.join_button);

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "点击了按钮", Toast.LENGTH_SHORT).show();
            }
        });
        initViewPager(); //初始化加载图片的viewpager
        joinButton.setVisibility(View.GONE);

    }
    /**
     * 加载图片ViewPager
     */
    private void initViewPager() {
        mviewPager = (ViewPager) findViewById(R.id.in_viewpager);
        //实例化图片资源
        imageIdArray = new int[]{R.mipmap.welcome_one,R.mipmap.welcome_two,R.mipmap.welcome_three};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0;i<len;i++){
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);
            //将ImageView加入到集合中
            viewList.add(imageView);
            //设置滑动监听
            mviewPager.setOnPageChangeListener(this);
        }

        //View集合初始化好后，设置Adapter
        mviewPager.setAdapter(new ViewPagerAdatper(viewList));
        //设置滑动监听
        mviewPager.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {
        //判断是否是最后一页，若是则显示进入应用的按钮
        if (position == imageIdArray.length - 1){
            joinButton.setVisibility(View.VISIBLE);
        }else {
            joinButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
