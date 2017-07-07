package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android.miwok.utilities.Word;
import com.example.android.miwok.utilities.WordAdapter;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // TODO: 6/28/2017 Create an ArrayList of words
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("minto wuksus", "Where are you going?"));
        words.add(new Word("tinnә oyaase'nә", "What is your name?"));
        words.add(new Word("oyaaset...", "My name is..."));
        words.add(new Word("michәksәs?", "How are you feeling?"));
        words.add(new Word("kuchi achit", "I'm feeling good."));
        words.add(new Word("әәnәs'aa?", "Are you coming?"));
        words.add(new Word("hәә’ әәnәm", "Yes i'm coming."));
        words.add(new Word("әәnәm", "I'm coming."));
        words.add(new Word("yoowutis", "Lets go."));
        words.add(new Word("әnni'nem", "come here."));

        WordAdapter adapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}
