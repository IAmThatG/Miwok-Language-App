package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.miwok.utilities.Word;
import com.example.android.miwok.utilities.WordAdapter;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity
{
    private ListView listView;
    private ArrayList<Word> words;
    private MediaPlayer mMediaPlayer;
    private AudioManager audioManager;

    /**
     * Implementation of the OnCompletionListener interface
     */
    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer mp)
        {
            //when playback is complete, release media player resource
            releaseMediaPlayer();
        }
    };

    /**
     * Implementation of the OnAudioFocusChangeListener interface
     */
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener()
    {
        @Override
        public void onAudioFocusChange(int focusChange)
        {
            switch (focusChange)
            {
                case AudioManager.AUDIOFOCUS_GAIN:
                    //audio focus has been regained and we can resume playback
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
//                    audioManager.unregisterMediaButtonEventReceiver(RemoteControlReceiver);
                    audioManager.abandonAudioFocus(audioFocusChangeListener);
                    //audio focus has been lost so we must stop playback
                    mMediaPlayer.stop();
                    //release the media resource
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    //audio focus has been lost for a short time so we pause playback
                    mMediaPlayer.pause();
                    //seek playback to the beginning
                    mMediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    //audio focus has been lost for a short time pause playback
                    mMediaPlayer.pause();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create an ArrayList of words
        words = new ArrayList<>();
        words.add(new Word("әpә", "father", R.raw.family_father, R.drawable.family_father));
        words.add(new Word("әṭa", "mother", R.raw.family_mother, R.drawable.family_mother));
        words.add(new Word("angsi", "son", R.raw.family_son, R.drawable.family_son));
        words.add(new Word("tune", "daughter", R.raw.family_daughter, R.drawable.family_daughter));
        words.add(new Word("taachi", "older brother", R.raw.family_older_brother, R.drawable.family_older_brother));
        words.add(new Word("chalitti", "younger brother",R.raw.family_younger_brother, R.drawable.family_younger_brother));
        words.add(new Word("teṭe", "older sister", R.raw.family_older_sister, R.drawable.family_older_sister));
        words.add(new Word("kolliti", "younger sister", R.raw.family_younger_sister, R.drawable.family_younger_sister));
        words.add(new Word("ama", "grandmother", R.raw.family_grandmother, R.drawable.family_grandmother));
        words.add(new Word("paapa", "grandfather", R.raw.family_grandfather, R.drawable.family_grandfather));

        //create an Adapter for view reusability and optimal system performance when working with long or undetermined lists
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

        //find the Adapter view type e.g. ListView
        listView = (ListView) findViewById(R.id.list);

        //set the adapter for the ListView
        listView.setAdapter(adapter);

        //create and setup the {@link AudioManager} to request audio focus
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //call the method that handles the playing of music for each word on the list
        setListItemListener();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        //release media player resource when the user leaves the activity
        releaseMediaPlayer();
    }

    /**
     * Plays the audio file of a word on the list when the word is clicked
     */
    private void setListItemListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int resourceId = words.get(position).getAudioResourceId();

                //Release the media player if it currently exist cos we are about to play a
                //different sound file. We did this just in case the user tries to play another
                //sound while the current playback isn't complete.
                releaseMediaPlayer();

                //request audio focus for playback
                int request = audioManager.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                //check if audio focus request is granted
                if (request == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
//                    audioManager.registerMediaButtonEventReceiver(RemoteControlReciever);

                    //we have audio focus now, proceed to creating media player
                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, resourceId);

                    //start playback
                    mMediaPlayer.start();

                    //set up a listener on the media player, so that we can stop and release the
                    //media player once the sound has finished playing
                    mMediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });
    }

    /**
     * Cleans up the media player thereby providing memory for other apps
     */
    private void releaseMediaPlayer()
    {
        //if the media player is not null, then it may be currently be playing a sound
        if (mMediaPlayer != null)
        {
            //regardless the current state of the media player, release its resources
            //bcos for us to call this method, it means we no longer need it.
            mMediaPlayer.release();

            //set the media player back to null which shows that it is not currently
            //configured to play anything
            mMediaPlayer = null;

            //Regardless of whether or not we were granted audio focus, abandon it.
            // This also unregisters the OnAudioFocusChangeListener so that we don't get anymore callbacks
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }
}
