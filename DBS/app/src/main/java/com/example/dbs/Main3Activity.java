package com.example.dbs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    Intent intent;
    transferListAdapter adapter;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView=(TextView) findViewById(R.id.myaccountNumber);
        if (Main2Activity.transferCount.get(0).myaccountNumber==0){
            textView.setText("My Account Number: "+"0000000003");
        }else if(Main2Activity.transferCount.get(0).myaccountNumber==1){
            textView.setText("My Account Number: "+"0000000004");
        }else{
            textView.setText("My Account Number: "+"0000000005");
        }
        adapter=new transferListAdapter(Main2Activity.transferCount,Main3Activity.this);
        ListView listView=(ListView)findViewById(R.id.transferList);
        listView.setAdapter(adapter);
    }
}

class transferListAdapter extends BaseAdapter {
    private ArrayList<TransferInfo> list;
    private Context context;

    public transferListAdapter(ArrayList<TransferInfo> input, Context context){
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
            view = inflater.inflate(R.layout.layout2, null);
        }
        TextView textView1=(TextView)view.findViewById(R.id.number);
        if (list.get(position).AccountNumber==0){
            textView1.setText("Account Number: "+"0000000000");
        }else if(list.get(position).AccountNumber==1){
            textView1.setText("Account Number: "+"0000000001");
        }else{
            textView1.setText("Account Number: "+"0000000002");
        }
        TextView textView2=(TextView)view.findViewById(R.id.amount);
        textView2.setText("Transfer Amount: "+list.get(position).Amount);

        return view;
    }

}
