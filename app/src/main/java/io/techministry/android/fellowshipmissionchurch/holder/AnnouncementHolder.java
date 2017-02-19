package io.techministry.android.fellowshipmissionchurch.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.techministry.android.fellowshipmissionchurch.R;
import io.techministry.android.fellowshipmissionchurch.models.Announcement;


/**
 * Created by Akinsete on 6/6/16.
 */
public class AnnouncementHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;


    public AnnouncementHolder(View itemView){
        super(itemView);

        mView = itemView;
        mContext = mView.getContext();
    }


    public void setData(Announcement announcement) {
        ImageView photo = (ImageView) mView.findViewById(R.id.photo);
        TextView title = (TextView) mView.findViewById(R.id.title);

        title.setText(announcement.getTitle());

    }
}
