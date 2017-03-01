/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.techministry.android.fellowshipmissionchurch.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import io.techministry.android.fellowshipmissionchurch.AnnouncementDetailActivity;
import io.techministry.android.fellowshipmissionchurch.PlayerActivity;
import io.techministry.android.fellowshipmissionchurch.R;
import io.techministry.android.fellowshipmissionchurch.holder.AnnouncementHolder;
import io.techministry.android.fellowshipmissionchurch.models.Announcement;

public class AnnouncementListFragment extends Fragment {

    FirebaseDatabase mDatabase;
    Query mQuery;
    private FirebaseRecyclerAdapter<Announcement, AnnouncementHolder> mRecyclerViewAdapter;
    RecyclerView rv;
    Context context;
    LinearLayoutManager linearLayoutManager;
    String TAG = "Announcements";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

        // Get an instance of the firebase database
        mDatabase = FirebaseDatabase.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        rv = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);
        setUpRecyclerView(rv);


        
        fetchAnnouncementsFromFirebase();

        return rv;
    }

    private void setUpRecyclerView(RecyclerView recycler_view) {
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);


        recycler_view.setLayoutManager(linearLayoutManager);
    }

    private void fetchAnnouncementsFromFirebase() {
        DatabaseReference databaseReference = mDatabase.getReference("announcements");
        mQuery = databaseReference.orderByChild("created_at").limitToFirst(500);

//        databaseReference.orderByKey().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d(TAG,dataSnapshot.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d(TAG,databaseError.toString());
//
//            }
//        });
    }


    @Override
    public void onStart() {
        super.onStart();

        attachRecyclerViewAdapter();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mRecyclerViewAdapter != null) {
            mRecyclerViewAdapter.cleanup();
        }
    }


    private void attachRecyclerViewAdapter() {
            mRecyclerViewAdapter = new FirebaseRecyclerAdapter<Announcement, AnnouncementHolder>(
                    Announcement.class, R.layout.adapter_announcement_item, AnnouncementHolder.class, mQuery) {

                @Override
                public void populateViewHolder(AnnouncementHolder viewHolder, final Announcement announcement, final int position) {
                    viewHolder.setData(announcement);

                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            if(announcement.getContent_type().equals("video")) {
                                Intent intent = new Intent(context, PlayerActivity.class);
                                intent.putExtra("video_url", announcement.getVideo_url());
                                startActivity(intent);
                            }else {
                                Intent intent = new Intent(context, AnnouncementDetailActivity.class);
                                intent.putExtra("announcement", announcement);
                                startActivity(intent);
                            }


                        }
                    });
                }
            };
            rv.setAdapter(mRecyclerViewAdapter);
    }


    private void viewAnnouncementText(Announcement announcement){
        new AlertDialog.Builder(context)
                .setMessage(announcement.getContent())
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }





}
