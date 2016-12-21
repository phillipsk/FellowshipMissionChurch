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
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import io.techministry.android.fellowshipmissionchurch.DetailActivity;
import io.techministry.android.fellowshipmissionchurch.R;
import io.techministry.android.fellowshipmissionchurch.data.DummyData;
import java.util.Arrays;
import java.util.List;

import static android.R.attr.data;

public class ElementListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        final SimpleStringRecyclerViewAdapter adapter =
            new SimpleStringRecyclerViewAdapter(Arrays.asList(DummyData.sDummyStrings));

        recyclerView.setAdapter(adapter);
        //  getRandomSublist(DummyData.sDummyStrings, 5)));
    }

  /*  private List<String> getRandomSublist(String[] array, int amount) {
        ArrayList<String> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            list.add(array[random.nextInt(array.length)]);
        }
        return list;
    }*/

    public static class SimpleStringRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        private List<String> values;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            String data;
            public final View view;
            public final TextView textView;

            public ViewHolder(View view) {
                super(view);
                this.view = view;
                textView = (TextView) view.findViewById(android.R.id.text1);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + textView.getText();
            }
        }

        public String getValueAt(int position) {
            return values.get(position);
        }

        public SimpleStringRecyclerViewAdapter(List<String> items) {
            values = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.data = values.get(position);
            holder.textView.setText(holder.data);

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_DATA_ID, holder.data);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return values.size();
        }
    }
}
