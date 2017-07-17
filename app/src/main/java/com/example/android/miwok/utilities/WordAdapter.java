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
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.miwok.R;

import java.util.List;

/**
 * Created by GABRIEL on 7/4/2017.
 */

public class WordAdapter extends ArrayAdapter<Word>
{
    private static final String LOG_TAG = WordAdapter.class.getSimpleName();

    /** background color resource id for the list of words */
    private int mColourResourceId;

    /** Default state where the list of words have no background color */
    private static final int NO_COLOUR = -1;

    /**
     * Creates a {@link WordAdapter} object which is a <b>Custom Adapter</b> that extends
     * from the {@link ArrayAdapter} class of type {@link Word}.
     * @param context The information about the current state of the view.
     * @param objects The list of words
     */
    public WordAdapter(@NonNull Activity context, @NonNull List<Word> objects, int colorResourceId)
    {
        super(context, 0, objects);
        mColourResourceId = colorResourceId;
    }

    private boolean hasColor()
    {
        return mColourResourceId != NO_COLOUR;
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

        if (currentWord != null)
        {
            //find the linear layout which is the parent layout for the text views of our list item
            LinearLayout textLayout = (LinearLayout) listItemView.findViewById(R.id.text_container);

            if (hasColor())
            {
                int color = ContextCompat.getColor(getContext(), mColourResourceId);
                //set the background colour of the linear layout
                textLayout.setBackgroundColor(color);
            }

            //find the TextView in list_item.xml with id miwok_text_view
            TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
            //set the text of the miwokTextView as the miwokTranslation of the currentWord
            miwokTextView.setText(currentWord.getMiwokTranslation());

            //find the TextView in list_item.xml with id default_text_view
            TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
            //set the text of the defaultTextView as the dafaultTranslation of the currentWord
            defaultTextView.setText(currentWord.getDefaultTranslation());

            //find the ImageView in list_item.xml with id word_image
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.word_image);

            if (currentWord.hasImage())
            {
                //set the content of the image
                imageView.setImageResource(currentWord.getImageResourceId());
                //make the image visible incase the reusable view has was previously INVISIBLE or GONE
                imageView.setVisibility(View.VISIBLE);
            }
                else
                //otherwise hide the image view
                imageView.setVisibility(View.GONE);
        }

        //return the whole list_item layout with the updated TextViews
        return listItemView;
    }
}
