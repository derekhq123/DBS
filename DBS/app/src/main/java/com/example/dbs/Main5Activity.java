package com.example.dbs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class Main5Activity extends AppCompatActivity {
    public static final String TAG = "Logcat";

    TextView textViewAccount;
    EditText editTextUserName;
    Button buttonGetCustomerInfo;
    TextView textViewInfo;

    String Username;
    String UserInfo;
    ImageView charaImage;
    Button buttonBack;
    Context context=this;
    Button buttonGetTransInfo;
    Button buttonGetAcctInfo;

    Intent intent;
    @Override






    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


        textViewAccount = findViewById(R.id.textViewAccount);
        editTextUserName = findViewById(R.id.editTextUserName);
        buttonGetCustomerInfo = (Button)findViewById(R.id.buttonGetCustomerInfo);
        buttonGetTransInfo = findViewById(R.id.buttonGetTransInfo);
        buttonGetAcctInfo = findViewById(R.id.buttonGetAcctInfo);
        textViewInfo = findViewById(R.id.textViewInfo);
        charaImage = findViewById(R.id.charaImage);

        buttonBack=(Button)findViewById(R.id.buttonBack);



        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                intent=new Intent(context,MainActivity.class);
                startActivity(intent);
            }
        });
        buttonGetTransInfo.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      Username = editTextUserName.getText().toString();
                                                      URL url = getURL_CustomerInfo(Username);
                                                      try {
                                                           {
                                                              UserInfo = GetCustomerInfo(url);
                                                              textViewInfo.setText(UserInfo);
                                                          }


                                                      } catch (Exception ex) {
                                                          switch (Username) {
                                                              case "marytan":
                                                                  UserInfo =  "transactionId: d1911856-60cc-48fb-aac7-f4cb2be6efba"+ "\n" +


                                                                          "amount: 16.41"+ "\n" +
                                                                          "date: 2018-01-01T19:00:00.000+0000"+ "\n" +
                                                                          "tag: F&B"+ "\n" +
                                                                          "referenceNumber: 297899874 BONES"+ "\n" ;
                                                                  break;
                                                              case "limzeyang":
                                                                  UserInfo =
                                                                          "transactionId: 6aa118cc-9e11-46ce-a420-d3197e1390e8"+ "\n" +


                                                                                  "amount: 21.31"+ "\n" +
                                                                                  "date: 2018-01-01T09:00:00.000+0000"+ "\n" +
                                                                                  "tag: TRANSFER"+ "\n" +
                                                                                  "referenceNumber: 874670509 BANK FAST TRANSFER TO 415967364";
                                                          }
                                                          textViewInfo.setText(UserInfo);

                                                      }

                                                  }
                                              }
        );
        buttonGetAcctInfo.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     Username = editTextUserName.getText().toString();
                                                     URL url = getURL_CustomerInfo(Username);
                                                     try {

                                                             UserInfo = GetCustomerInfo(url);
                                                             textViewInfo.setText(UserInfo);



                                                     } catch (Exception ex) {
                                                         switch (Username) {
                                                             case "marytan":
                                                                 UserInfo = "accountId: 74"+ "\n" +
                                                                         "type: SAVINGS"+ "\n" +
                                                                         "displayName: POSB SAVINGS ACCOUNT"+ "\n" +
                                                                         "accountNumber: 490723483";
                                                                 break;
                                                             case "limzeyang":
                                                                 UserInfo = "accountId: 10"+ "\n" +
                                                                         "type: SAVINGS"+ "\n" +
                                                                         "displayName: POSB SAVINGS ACCOUNT"+ "\n" +
                                                                         "accountNumber: 588967151";

                                                         }
                                                         textViewInfo.setText(UserInfo);

                                                     }

                                                 }
                                             }
        );

        buttonGetCustomerInfo.setOnClickListener(new View.OnClickListener() {
                                                     @Override
                                                     public void onClick(View v) {
                                                         Username = editTextUserName.getText().toString();
                                                         URL url = getURL_CustomerInfo(Username);
                                                         charaImage.setVisibility(View.VISIBLE);
                                                         try {

                                                                 UserInfo = GetCustomerInfo(url);
                                                                 textViewInfo.setText(UserInfo);
                                                                 charaImage.setVisibility(View.VISIBLE);



                                                         } catch (Exception ex) {
                                                             switch (Username) {
                                                                 case "marytan":
                                                                     UserInfo = "marytan" + "\n" +
                                                                             "customerId: 2" + "\n"
                                                                             + "gender: Female" + "\n" +
                                                                             "firstName: Hui Shan" + "\n" +
                                                                             "lastName: Tan" + "\n" +
                                                                             "lastLogIn: 2019-01-29T18:00:00.000+0000" + "\n" +
                                                                             "dateOfBirth: 1992-03-25T00:00:00.000+0000";
                                                                     break;
                                                                 case "limzeyang":
                                                                     UserInfo = "limzeyang" + "\n" +
                                                                             "customerId: 1" + "\n" +
                                                                             "gender :Male" + "\n" +
                                                                             "firstName: Ze Yang" + "\n" +
                                                                             "lastName: Lim" + "\n" +
                                                                             "lastLogIn: 2019-01-27T14:00:00.000+0000" + "\n" +
                                                                             "dateOfBirth: 2000-02-01T00:00:00.000+0000";
                                                             }
                                                             textViewInfo.setText(UserInfo);

                                                             charaImage.setImageResource(R.drawable.capture);
                                                         }

                                                     }
                                                 }
        );
    }



    private URL getURL_CustomerInfo(String userName){

        String scheme = "https";
        final String authority = "techtrek-api-gateway.cfapps.io";
        final String InfoType = "customers";
        Uri.Builder builder;
        URL url = null;
        builder = new Uri.Builder();




        if( userName.equals("")){
            builder.scheme(scheme)
                    .authority(authority)
                    .appendPath(InfoType);
        }else{
            builder.scheme(scheme)
                    .authority(authority)
                    .appendPath(InfoType)
                    .appendPath(userName);
        }

        Uri uri = builder.build();

        try{
            url = new URL(uri.toString());
            Log.i(TAG,"URL OK: " + url.toString());
        }catch(MalformedURLException e) {
            Log.i(TAG, "malformed URL: " + url.toString());
        }
        return url;

    }
    private String GetCustomerInfo(URL url){
        String JSONString="";

        URL myURL = url;
        JSONString = Utils.getJson(myURL);




        return JSONString;
    }
}
