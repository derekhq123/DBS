package com.example.dbs;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
    Button show;
    Button hide;
    TextView deposit;
    ArrayList<String> productList=new ArrayList<>();
    ListView listView;
    InvestmentAdapter adapter;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        show=(Button)findViewById(R.id.show);
        hide=(Button) findViewById(R.id.hide);
        deposit=(TextView)findViewById(R.id.deposit);
        productList.add("Product1");
        productList.add("Product2");
        productList.add("Product3");
        productList.add("Product4");
        productList.add("Product5");
        productList.add("Product6");
        productList.add("Product7");
        adapter=new InvestmentAdapter(productList,Main4Activity.this);
        listView=(ListView)findViewById(R.id.BoughtList);
        listView.setAdapter(adapter);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewpageAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewpageAdapter);
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deposit.setVisibility(View.VISIBLE);
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deposit.setVisibility(View.INVISIBLE);
            }
        });
    }
}
class InvestmentAdapter extends BaseAdapter {
    private ArrayList<String> list=new ArrayList<>();
    private Context context;

    public InvestmentAdapter(ArrayList<String> input, Context context){
        list=input;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public View getView(final int position, View convertView, ViewGroup parent){
        View view=convertView;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout3, null);
        }
        TextView textView=(TextView)view.findViewById(R.id.product);
        textView.setText(list.get(position));
        return view;
    }

}
class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.slide1,R.drawable.slide2,R.drawable.slide3};

    @Override
    public int getCount(){
        return images.length;
    }

    public boolean isViewFromObject(View view, Object object){
        return view== object;
    }

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    public void destroy(ViewGroup container, int  position, Object object){
        ViewPager vp =  (ViewPager) container;
        View view  = (View) object;
        vp.removeView(view);
    }
}
