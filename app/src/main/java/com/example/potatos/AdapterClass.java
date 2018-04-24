package com.example.potatos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Patrick Sullivan on 4/3/2018.
 *
 */

public class AdapterClass extends BaseAdapter {

    private LayoutInflater mInflater;
    private String[] classNames;
    private int[] numOfQuizzes;
    private int[] numOfOpenQuizzes;
    private int[] numOfStudents;

    AdapterClass(Context c, String[] classNames, int[] numOfQuizzes,
                 int[] numOfOpenQuizzes, int[] numOfStudents){
        this.classNames = classNames;
        this.numOfQuizzes = numOfQuizzes;
        this.numOfOpenQuizzes = numOfOpenQuizzes;
        this.numOfStudents = numOfStudents;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return classNames.length;
    }

    @Override
    public Object getItem(int position) {
        return classNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.class_listview_detail, null);
        TextView classNameTextView = v.findViewById(R.id.quizNameTextView);
        TextView numQuizzesTextView = v.findViewById(R.id.numQuizzesTextView);
        TextView numOpenQuizzesTextView = v.findViewById(R.id.numOpenQuizzesTextView);
        TextView numOfStudentTextView = v.findViewById(R.id.numStudentsTextView);

        String name = classNames[position];
        int quiz = numOfQuizzes[position];
        int openQuiz = numOfOpenQuizzes[position];
        int student = numOfStudents[position];

        classNameTextView.setText(name);
        numQuizzesTextView.setText(quiz);
        numOpenQuizzesTextView.setText(openQuiz);
        numOfStudentTextView.setText(student);

        return v;
    }
}
