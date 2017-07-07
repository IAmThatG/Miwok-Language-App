/*
 * Copyright (c) 2017.
 * Author: Gabriel Okolie.
 *  This project is licensed to the above named author as an intellectual property
 *  and should not be used without the consent of the author.
 */

package com.example.android.miwok.utilities;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.miwok.R;

import java.util.List;

/**
 * Created by GABRIEL on 7/4/2017.
 */

public class WordAdapter extends ArrayAdapter<Word>
{
    private static final String LOG_TAG = WordAdapter.class.getSimpleName();
    
    /**
     * Creates a {@link WordAdapter} object which is a <b>Custom Adapter</b> that extends
     * from the {@link ArrayAdapter} class of type {@link Word}.
     * @param context The information about the current state of the view.
     * @param objects The list of words
     */
    public WordAdapter(@NonNull Activity context, @NonNull List<Word> objects)
    {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        //check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        //get the Word object located at this position in the list
        Word currentWord = getItem(position);

        //find the TextView in list_item.xml with id miwok_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        //set the text of the miwokTextView as the miwokTranslation of the currentWord
        miwokTextView.setText(currentWord != null ? currentWord.getMiwokTranslation() : null);

        //find the TextView in list_item.xml with id default_text_view
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        //set the text of the defaultTextView as the dafaultTranslation of the currentWord
        defaultTextView.setText(currentWord != null ? currentWord.getDefaultTranslation() : null);

        //return the whole list_item layout with the updated TextViews
        return listItemView;
    }
}
