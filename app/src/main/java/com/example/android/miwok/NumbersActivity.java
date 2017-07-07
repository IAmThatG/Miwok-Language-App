
/*
 * Copyright (c) 2017.
 * Author: Gabriel Okolie.
 *  This project is licensed to the above named author as an intellectual property
 *  and should not be used without the consent of the author.
 */

package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.android.miwok.utilities.Word;
import com.example.android.miwok.utilities.WordAdapter;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // TODO: 6/28/2017 Create an ArrayList of words
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("lutti", "one"));
        words.add(new Word("otiiko", "two"));
        words.add(new Word("tolookosu", "three"));
        words.add(new Word("oyyisa", "four"));
        words.add(new Word("massokka", "five"));
        words.add(new Word("temmokka", "six"));
        words.add(new Word("kenekaku", "seven"));
        words.add(new Word("kawinta", "eight"));
        words.add(new Word("wo'e", "nine"));
        words.add(new Word("na’aacha", "ten"));

        WordAdapter adapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}
