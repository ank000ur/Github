package com.examples.shubu.pebblesripples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Retrieve extends AppCompatActivity {

    private EditText carn,reason;
    private Button request;
    private Firebase mRef,mRootRef,mRootRef1,mRootRef2;
    private TextView status;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        carn= (EditText) findViewById(R.id.editnumber1);
        reason= (EditText) findViewById(R.id.editreason);
        status= (TextView) findViewById(R.id.textstatus);
        status.setText("Nothing");

        request= (Button) findViewById(R.id.requestbutton1);

        mRootRef = new Firebase("https://pebblesripples.firebaseio.com");

        mRootRef1=mRootRef.child("Requests");

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carn1=carn.getText().toString();
                String reason1=reason.getText().toString();
                String approve="0";

                mRootRef1=mRootRef.child("Requests");
                mRootRef2=mRootRef1.push();
                String id=mRootRef2.toString();
                id=id.replace("https://pebblesripples.firebaseio.com/Requests/-","");

                Firebase mRefChild1=mRootRef2.child("Car Number");
                Firebase mRefChild2=mRootRef2.child("Reason");
                Firebase mRefChild3=mRootRef2.child("approve");
                Firebase mRefChild4=mRootRef2.child("id");

                if(TextUtils.isEmpty(carn1) || TextUtils.isEmpty(reason1)){
                    Toast.makeText(Retrieve.this,"Please Fill all the Credentials",Toast.LENGTH_SHORT).show();
                }else{
                    mRefChild1.setValue(carn1);
                    mRefChild2.setValue(reason1);
                    mRefChild3.setValue(approve);
                    mRefChild4.setValue(id);

                    Toast.makeText(Retrieve.this,"Request Sent",Toast.LENGTH_SHORT).show();
                     status.setText("In Transit");
                    /* if(id.contains("0")){
                        status.setText("In Transit");
                    }*/

                   // check();

                }


            }
        });





    }

    private void check() {
        mRootRef1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
