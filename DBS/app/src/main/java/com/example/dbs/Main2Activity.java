package com.example.dbs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    Adapter adapter;
    static ArrayList <TransferInfo> transferCount=new ArrayList<>();
    TextView title;
    Button submit;
    Intent intent;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        title=(TextView)findViewById(R.id.title);
        submit=(Button)findViewById(R.id.SUBMIT);
        transferCount.add(new TransferInfo());
        adapter=new Adapter(transferCount,Main2Activity.this);
        ListView listView=(ListView)findViewById(R.id.transfer);
        listView.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                intent=new Intent(context,Main3Activity.class);


                Toast.makeText(getApplicationContext(),
                        "Submit successfully",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}

class Adapter extends BaseAdapter {
    private ArrayList<TransferInfo> list=new ArrayList<>();
    private Context context;

    public Adapter(ArrayList<TransferInfo> input, Context context){
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
            view = inflater.inflate(R.layout.layout, null);
        }

        final Spinner spinner=(Spinner)view.findViewById(R.id.accountInfo);
        spinner.setSelection(list.get(position).AccountNumber);
        spinner.setTag(position);
        final Spinner spinner2=(Spinner)view.findViewById(R.id.myaccountinfo);
        spinner2.setSelection(list.get(position).myaccountNumber);
        spinner2.setTag(position);
        final EditText editText=(EditText)view.findViewById(R.id.amount);
        //editText.setText(list.get(position).Amount);
        editText.setTag(position);
        final Button add=(Button)view.findViewById(R.id.Add);
        final Button delete=(Button)view.findViewById(R.id.Delete);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                list.add(position+1,new TransferInfo());
                notifyDataSetChanged();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                list.remove(position);
                notifyDataSetChanged();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                list.get((Integer)spinner.getTag()).AccountNumber=spinner.getSelectedItemPosition();
                if(spinner.getSelectedItemPosition()==0){
                    list.get((Integer)spinner.getTag()).AccountNumber=0;
                }else if(spinner.getSelectedItemPosition()==1){
                    list.get((Integer)spinner.getTag()).AccountNumber=1;
                }else if(spinner.getSelectedItemPosition()==2){
                    list.get((Integer)spinner.getTag()).AccountNumber=2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                list.get((Integer)spinner.getTag()).AccountNumber=spinner.getSelectedItemPosition();
                if(spinner2.getSelectedItemPosition()==0){
                    list.get((Integer)spinner2.getTag()).myaccountNumber=0;
                }else if(spinner2.getSelectedItemPosition()==1){
                    list.get((Integer)spinner2.getTag()).myaccountNumber=1;
                }else if(spinner2.getSelectedItemPosition()==2){
                    list.get((Integer)spinner2.getTag()).myaccountNumber=2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                list.get((Integer)editText.getTag()).Amount=editText.getText().toString();
            }
        });
        return view;
    }

}
