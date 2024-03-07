package com.example.chatapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private EditText mMessageEditText;
    private Button mSendButton;
    ListView listview ;
    MessageAdapter AMessageAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        listview = findViewById(R.id.messageListView);


        mMessageEditText = findViewById(R.id.messageEditText);
        mSendButton = findViewById(R.id.sendButton);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = mMessageEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(message)) {
                    sendMessageToDatabase(message);
                }
            }
        });

        DatabaseReference messagesRef = FirebaseDatabase.getInstance().getReference().child("messages");
        messagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Message> messages = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Message message = snapshot.getValue(Message.class);
                    messages.add(message);
                }
                AMessageAdapter = new MessageAdapter(getApplicationContext(), messages);
                listview.setAdapter(AMessageAdapter);
                AMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi nếu có
            }
        });


    }

    private void sendMessageToDatabase(String message) {
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference messagesRef = mDatabase.child("messages");
        String messageId = messagesRef.push().getKey();

        if (messageId != null) {
            Message newMessage = new Message(userId, message);
            messagesRef.child(messageId).setValue(newMessage);
        }
    }


}