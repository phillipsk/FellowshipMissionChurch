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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.techministry.android.fellowshipmissionchurch.R;
import io.techministry.android.fellowshipmissionchurch.adapters.CustomRecyclerAdapter;
import io.techministry.android.fellowshipmissionchurch.model.AboutUsModel;

public class AboutUsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View rv = inflater.inflate(R.layout.fragment_about_us, container, false);
        return rv;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerview);



        String[] titles = getActivity().getResources().getStringArray(R.array.models_titles);
        String[] arraylist = getActivity().getResources().getStringArray(R.array.models_details);
        ArrayList<AboutUsModel> models = new ArrayList<>();
        int size = titles.length;
        for (int i = 0; i< size; i++) {
            models.add(new AboutUsModel(titles[i], arraylist[i]));
        }

        setUpRecyclerView(rv, models);

    }

    private void setUpRecyclerView(RecyclerView rv, ArrayList<AboutUsModel> models) {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        CustomRecyclerAdapter adapter = new CustomRecyclerAdapter(models);
        rv.setAdapter(adapter);
    }

    //
//    private void setupRecyclerView(RecyclerView recyclerView) {
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
//        recyclerView.setAdapter(Sim);
//        final SimpleStringRecyclerViewAdapter adapter = new SimpleStringRecyclerViewAdapter(Arrays.asList(DummyData.sDummyStrings));
//
//        recyclerView.setAdapter(adapter);
//        //  getRandomSublist(DummyData.sDummyStrings, 5)));
//    }
//
//  /*  private List<String> getRandomSublist(String[] array, int amount) {
//        ArrayList<String> list = new ArrayList<>(amount);
//        Random random = new Random();
//        while (list.size() < amount) {
//            list.add(array[random.nextInt(array.length)]);
//        }
//        return list;
//    }*/
//
//    public static class SimpleStringRecyclerViewAdapter
//        extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {
//
//        private List<String> values;
//
//        public static class ViewHolder extends RecyclerView.ViewHolder {
//            String data;
//            public final View view;
//            public final TextView textView;
//
//            public ViewHolder(View view) {
//                super(view);
//                this.view = view;
//                textView = (TextView) view.findViewById(android.R.id.text1);
//            }
//
//            @Override
//            public String toString() {
//                return super.toString() + " '" + textView.getText();
//            }
//        }
//
//        public String getValueAt(int position) {
//            return values.get(position);
//        }
//
//        public SimpleStringRecyclerViewAdapter(List<String> items) {
//            values = items;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view =
//                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.data = values.get(position);
//            holder.textView.setText(holder.data);
//
//            holder.view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, DetailActivity.class);
//                    intent.putExtra(DetailActivity.EXTRA_DATA_ID, holder.data);
//                    context.startActivity(intent);
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return values.size();
//        }
//    }
}
