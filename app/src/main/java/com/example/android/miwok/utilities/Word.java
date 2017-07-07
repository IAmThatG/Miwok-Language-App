/*
 * Copyright (c) 2017.
 * Author: Gabriel Okolie.
 *  This project is licensed to the above named author as an intellectual property
 *  and should not be used without the consent of the author.
 */

package com.example.android.miwok.utilities;

import android.support.annotation.NonNull;

/**
 * Created by GABRIEL on 7/3/2017.
 */

public class Word
{
    /** The Miwok translation*/
    private String mMiwokTranslation;

    /** The default translation*/
    private String mDefaultTranslation;

    /** The image resource id for a word*/
    private int mImageResourceId;

    /**
     * Creates a new {@link Word} object
     * @param MiwokTranslation The miwok translation of a word
     * @param DefaultTranslation The default translation of a word
     */
    public Word(@NonNull String MiwokTranslation, @NonNull String DefaultTranslation)
    {
        mMiwokTranslation = MiwokTranslation;
        mDefaultTranslation = DefaultTranslation;
    }

    public Word(String MiwokTranslation, String DefaultTranslation, int ImageResourceId)
    {
        mMiwokTranslation = MiwokTranslation;
        mDefaultTranslation = DefaultTranslation;
        mImageResourceId = ImageResourceId;
    }

    /**
     * Gets the default translation of the word
     * @return {@link String} mDefaultTranslation
     */
    public String getDefaultTranslation()
    {
        return mDefaultTranslation;
    }

    /**
     * Gets the miwok translation of the word
     * @return {@link String} mMiwokTranslation
     */
    public  String getMiwokTranslation()
    {
        return mMiwokTranslation;
    }



    public int getImageResourceId()
    {
        return mImageResourceId;
    }
}
