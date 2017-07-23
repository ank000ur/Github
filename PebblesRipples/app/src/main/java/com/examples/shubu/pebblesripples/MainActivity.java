package com.examples.shubu.pebblesripples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    private EditText name,pno,cno;
    EditText address;
    Spinner purpose;
    private Button add;
    private Firebase mRef, mRootRef,mRootRef1,mRootRef2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        name= (EditText) findViewById(R.id.editname);
        pno= (EditText) findViewById(R.id.editnumber);
        cno= (EditText) findViewById(R.id.editcno);
        address= (EditText) findViewById(R.id.editaddress);

        purpose= (Spinner) findViewById(R.id.spinnerpurpose);

        add= (Button) findViewById(R.id.addbutton);
        mRootRef = new Firebase("https://pebblesripples.firebaseio.com");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name1=name.getText().toString();
                String pno1=pno.getText().toString();
                String cno1=cno.getText().toString();
                String address1=address.getText().toString();
                String purpose1=purpose.getSelectedItem().toString();

                mRootRef1=mRootRef.child("Visitors");

                mRootRef2=mRootRef1.push();
                String id=mRootRef2.toString();
                id=id.replace("https://pebblesripples.firebaseio.com/Visitors/-","");

                Firebase mRefChild1 = mRootRef2.child("Name");
                Firebase mRefChild2 = mRootRef2.child("Phone Number");
                Firebase mRefChild3 = mRootRef2.child("Car Number");
                Firebase mRefChild4 = mRootRef2.child("Address");
                Firebase mRefChild5 = mRootRef2.child("Purpose");
                Firebase mRefChild6 = mRootRef2.child("Id");

                if( TextUtils.isEmpty(name1) || TextUtils.isEmpty(pno1) || TextUtils.isEmpty(cno1) || TextUtils.isEmpty(address1) || TextUtils.isEmpty(purpose1)){
                    Toast.makeText(MainActivity.this,"Please Fill all the Columns",Toast.LENGTH_SHORT).show();
                }else {

                    mRefChild1.setValue(name1);
                    mRefChild2.setValue(pno1);
                    mRefChild3.setValue(cno1);
                    mRefChild4.setValue(address1);
                    mRefChild5.setValue(purpose1);
                    mRefChild6.setValue(id);

                    Toast.makeText(MainActivity.this,"Data Added Successfully !!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Retrieve.class));

                }



            }
        });
    }
}
