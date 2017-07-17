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
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** The audio resource id for a word */
    private int mAudioResourceId;

    /** The default id if no image is provided */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Creates a new {@link Word} object
     * @param MiwokTranslation The word in the Miwok language
     * @param DefaultTranslation The word in a language the user is already familiar with
     * @param AudioResourceId The resource id of the audio file associated with this word
     */
    public Word(@NonNull String MiwokTranslation, @NonNull String DefaultTranslation, int AudioResourceId)
    {
        mAudioResourceId = AudioResourceId;
        mMiwokTranslation = MiwokTranslation;
        mDefaultTranslation = DefaultTranslation;
    }

    /**
     * Creates a new {@link Word} object
     * @param MiwokTranslation The word in the Miwok language
     * @param DefaultTranslation The word in a language the user is already familiar with
     * @param AudioResourceId The resource id of the audio file associated with this word
     * @param ImageResourceId The drawable resource id for the image that visually describes this word
     */
    public Word(@NonNull String MiwokTranslation, @NonNull String DefaultTranslation, int AudioResourceId, int ImageResourceId)
    {
        this(MiwokTranslation, DefaultTranslation, AudioResourceId);
        mImageResourceId = ImageResourceId;
    }

    /**
     * Gets the default translation of the word
     * @return a {@link String}
     */
    String getDefaultTranslation()
    {
        return mDefaultTranslation;
    }

    /**
     * returns the miwok translation of the word
     * @return a {@link String}
     */
    String getMiwokTranslation()
    {
        return mMiwokTranslation;
    }

    /**
     * Returns the image resource id for a word
     * @return an integer
     */
    int getImageResourceId()
    {
        return mImageResourceId;
    }

    /**
     * Returns the audio resource id for a word
     * @return an integer
     */
    public int getAudioResourceId()
    {
        return mAudioResourceId;
    }

    /**
     * Returns whether or not a word has an image
     * @return <code>true</code> if word has image or <code>false</code> if otherwise
     */
    public boolean hasImage()
    {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
