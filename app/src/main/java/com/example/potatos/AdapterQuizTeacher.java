package com.example.potatos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterQuizTeacher extends BaseAdapter {

    private LayoutInflater mInflater;
    private String[] quizNames;
    private boolean isChecked;

    AdapterQuizTeacher(Context c, String[] quizName, boolean isChecked) {
        this.quizNames = quizName;
        this.isChecked = isChecked;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public  int getCount() { return quizNames.length; }

    @Override
    public Object getItem(int position) { return quizNames[position]; }

    @Override
    public long getItemId(int position) { return position; }

    public boolean isChecked() {return isChecked; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.quiz_listview_detail_btn, null);
        TextView quizNameTextView = v.findViewById(R.id.quizNameListView);

        String quizName = quizNames[position];
        quizNameTextView.setText(quizName);

        return v;
    }
}
