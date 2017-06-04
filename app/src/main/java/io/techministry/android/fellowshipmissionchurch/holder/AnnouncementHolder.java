package io.techministry.android.fellowshipmissionchurch.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.techministry.android.fellowshipmissionchurch.R;
import io.techministry.android.fellowshipmissionchurch.models.Announcement;
import io.techministry.android.fellowshipmissionchurch.utils.FirebaseUtilities;
import io.techministry.android.fellowshipmissionchurch.utils.Utilities;


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
        String user_id = Utilities.getDeviceID(mContext);
        ImageView photo = (ImageView) mView.findViewById(R.id.photo);
        ImageView like = (ImageView) mView.findViewById(R.id.like);
        TextView title = (TextView) mView.findViewById(R.id.title);
        TextView likes = (TextView) mView.findViewById(R.id.likes);

        title.setText(announcement.getTitle());
        String l = String.valueOf(announcement.getLikes());
        if(!l.equals("null")) {
            if(!l.equals("0")) {
                likes.setText(String.valueOf(announcement.getLikes()));
            }
        }
        if(FirebaseUtilities.isLiked(mContext,user_id,announcement.getLiked())){
            like.setImageResource(R.mipmap.favorite);
        }else{
            like.setImageResource(R.mipmap.favorite_border);
        }

    }
}
