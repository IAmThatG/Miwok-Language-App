/* Copyright (c) 2017. Author: Gabriel Okolie. This project is licensed to the above named author as an intellectual property and should not be used without the consent of the author. */
package com.example.android.miwok;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer; import android.os.Bundle; import android.support.v7.app.AppCompatActivity; import android.view.View; import android.widget.AdapterView; import android.widget.ListView; import com.example.android.miwok.utilities.Word; import com.example.android.miwok.utilities.WordAdapter; import java.util.ArrayList;
public class NumbersActivity extends AppCompatActivity
{
    private MediaPlayer mMediaPlayer;
    private ListView listView;
    private ArrayList<Word> words ;
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

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        /*Create an ArrayList of words*/
        words = new ArrayList<>();
        words.add(new Word("lutti", "one", R.raw.number_one, R.drawable.number_one));
        words.add(new Word("otiiko", "two", R.raw.number_two, R.drawable.number_two));
        words.add(new Word("tolookosu", "three", R.raw.number_three, R.drawable.number_three));
        words.add(new Word("oyyisa", "four", R.raw.number_four, R.drawable.number_four));
        words.add(new Word("massokka", "five", R.raw.number_five, R.drawable.number_five));
        words.add(new Word("temmokka", "six", R.raw.number_six, R.drawable.number_six));
        words.add(new Word("kenekaku", "seven", R.raw.number_seven, R.drawable.number_seven));
        words.add(new Word("kawinta", "eight", R.raw.number_eight, R.drawable.number_eight));
        words.add(new Word("wo'e", "nine", R.raw.number_nine, R.drawable.number_nine));
        words.add(new Word("na’aacha", "ten", R.raw.number_ten, R.drawable.number_ten));

        //create an Adapter for view reusability and optimal system performance when working with long or undetermined lists
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

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
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, resourceId);

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
