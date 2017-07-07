package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android.miwok.utilities.Word;
import com.example.android.miwok.utilities.WordAdapter;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // TODO: 6/28/2017 Create an ArrayList of words
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("әpә", "father"));
        words.add(new Word("әṭa", "mother"));
        words.add(new Word("angsi", "son"));
        words.add(new Word("tune", "daughter"));
        words.add(new Word("taachi", "older brother"));
        words.add(new Word("chalitti", "younger brother"));
        words.add(new Word("teṭe", "older sister"));
        words.add(new Word("kolliti", "younger sister"));
        words.add(new Word("ama", "grandmother"));
        words.add(new Word("paapa", "grandfather"));

        WordAdapter adapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}
