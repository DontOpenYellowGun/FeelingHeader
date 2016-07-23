package com.sven.feelingheader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sven.feelingheader.widget.Titanic;
import com.sven.feelingheader.widget.TitanicTextView;

public class Act_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        TitanicTextView tv = (TitanicTextView) findViewById(R.id.my_text_view);
        // set fancy typeface
        assert tv != null;
        /*tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));*/
        // start animation
        new Titanic().start(tv);
    }
}
