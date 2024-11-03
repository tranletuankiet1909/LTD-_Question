package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Button mButtonTrue, mButtonFalse, mButtonNext, mButtonPrev;
    private TextView mtxtQuiz;
    private Question q1, q2, q3, q4, q5;
    private Question[] q;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        q1 = new Question(getString(R.string.quiz1), true);
        q2 = new Question(getString(R.string.quiz2), false);
        q3 = new Question(getString(R.string.quiz3), true);
        q4 = new Question(getString(R.string.quiz4), true);
        q5 = new Question(getString(R.string.quiz5), false);
        q = new Question[]{q1, q2, q3, q4, q5};

        mtxtQuiz = findViewById(R.id.txtQuiz);
        mButtonPrev = findViewById(R.id.btnPrev);
        mButtonNext = findViewById(R.id.btnNext);
        mButtonTrue = findViewById(R.id.btnTrue);
        mButtonFalse = findViewById(R.id.btnFalse);

        mtxtQuiz.setText(q[position].getContent());
        checkPosition(position);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position >= 0 && position <= q.length - 1 ) {
                    checkPosition(position);
                    mtxtQuiz.setText(q[position].getContent());
                }
            }
        });
        mButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position >= 0 && position <= q.length - 1 ) {
                    checkPosition(position);
                    mtxtQuiz.setText(q[position].getContent());
                }
            }
        });
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(v, q[position].getAnswer());
            }
        });
        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(v, !q[position].getAnswer());
            }
        });
    }

    private void checkPosition(int position) {
        mButtonNext.setEnabled(position < q.length - 1);
        mButtonPrev.setEnabled(position > 0);
    }

    private void showToast(View view, Boolean result) {
        if (result) {
            Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this,"Incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}