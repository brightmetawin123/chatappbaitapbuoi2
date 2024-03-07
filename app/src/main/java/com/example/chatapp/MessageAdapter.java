package com.example.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chatapp.R;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {

    private Context mContext;
    private List<Message> mMessages;

    public MessageAdapter(Context context, List<Message> messages) {
        super(context, 0, messages);
        mContext = context;
        mMessages = messages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.messenger_item, parent, false);
        }

        Message currentMessage = mMessages.get(position);

        TextView messageText = listItem.findViewById(R.id.messageText);
        messageText.setText(currentMessage.getText1());

        return listItem;
    }
}