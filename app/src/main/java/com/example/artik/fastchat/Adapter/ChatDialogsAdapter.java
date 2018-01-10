package com.example.artik.fastchat.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.artik.fastchat.Holder.QBUnreadMessageHolder;
import com.example.artik.fastchat.R;
import com.quickblox.chat.model.QBChatDialog;

import java.util.ArrayList;

/**
 * Created by artik on 09.01.2018.
 */

public class ChatDialogsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<QBChatDialog> qbChatDialogs;

    public ChatDialogsAdapter(Context context, ArrayList<QBChatDialog> qbChatDialogs) {
        this.context = context;
        this.qbChatDialogs = qbChatDialogs;
    }

    @Override
    public int getCount() {
        return qbChatDialogs.size();
    }

    @Override
    public Object getItem(int i) {
        return qbChatDialogs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
        {
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_chat_dialog, null);

            TextView txtTitle, txtMessage;
            ImageView imageView, image_uread;

            txtMessage =view.findViewById(R.id.list_chat_dialog_message);
            txtTitle = view.findViewById(R.id.list_chat_dialog_title);
            imageView = view.findViewById(R.id.image_chatDialog);
            image_uread = view.findViewById(R.id.image_unread);
            txtMessage.setText(qbChatDialogs.get(position).getLastMessage());
            txtTitle.setText(qbChatDialogs.get(position).getName());

            ColorGenerator generator = ColorGenerator.MATERIAL;
            int randomColor = generator.getRandomColor();

            TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig()
                    .withBorder(4)
                    .endConfig()
                    .round();

            TextDrawable drawable = builder.build(txtTitle.getText().toString().substring(0,1).toUpperCase(), randomColor );

            imageView.setImageDrawable(drawable);


            //setunreamessage
            TextDrawable.IBuilder unreadBuilder = TextDrawable.builder().beginConfig()
                    .withBorder(4)
                    .endConfig()
                    .round();
            int unread_count = QBUnreadMessageHolder.getInstance().getBundle().getInt(qbChatDialogs.get(position).getDialogId());
            if(unread_count > 0)
            {
                TextDrawable unread_drawable = unreadBuilder.build("" + unread_count, Color.BLUE);
                image_uread.setImageDrawable(unread_drawable);
            }

        }
        return view;
    }
}
