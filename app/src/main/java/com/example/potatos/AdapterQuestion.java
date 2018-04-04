package com.example.potatos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class AdapterQuestion extends BaseAdapter {

    private LayoutInflater mInflater;
    private String[] numbers;
    private String[] points;
    private String[] answered;

    AdapterQuestion(Context c, String[] number, String[] points, String[] answered){
        this.numbers = number;
        this.points = points;
        this.answered = answered;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return numbers.length;
    }

    @Override
    public Object getItem(int position) {
        return numbers[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.question_listview_detail, null);
        TextView numberTextView = v.findViewById(R.id.numberTextView);
        TextView pointTextView = v.findViewById(R.id.pointsTextView);
        TextView answeredTextView = v.findViewById(R.id.answeredTextView);

        String number = numbers[position];
        String pointValue = points[position];
        String answer = answered[position];

        numberTextView.setText(number);
        pointTextView.setText(pointValue);
        answeredTextView.setText(answer);

        return v;
    }

}
