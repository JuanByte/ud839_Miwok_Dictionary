package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {
    //Handle all audio play
    MediaPlayer mediaPlayer;
    // Handle AudioManager
    AudioManager mAudioManager;
    // Audio manager listener
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    // we pause our audio and set it from the beginning
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    // release all media player resources and audio focus
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    mediaPlayer.start();
            }
        }
    };
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        // Create an instance of AudioManager to manage audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        //Create list of words
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.string.where_are_you_going, R.string.minto_wuksus, R.raw.phrase_where_are_you_going));
        words.add(new Word(R.string.what_is_your_name, R.string.tinnә_oyaase_nә, R.raw.phrase_what_is_your_name));
        words.add(new Word(R.string.my_name_is, R.string.oyaaset, R.raw.phrase_my_name_is));
        words.add(new Word(R.string.how_are_you_feeling, R.string.michәksәs, R.raw.phrase_how_are_you_feeling));
        words.add(new Word(R.string.im_feeling_good, R.string.kuchi_achit, R.raw.phrase_im_feeling_good));
        words.add(new Word(R.string.are_you_coming, R.string.eenesaa, R.raw.phrase_are_you_coming));
        words.add(new Word(R.string.yes_im_coming, R.string.hee_eenem, R.raw.phrase_yes_im_coming));
        words.add(new Word(R.string.im_coming, R.string.eenem, R.raw.phrase_im_coming));
        words.add(new Word(R.string.lets_go, R.string.yoowutis, R.raw.phrase_lets_go));
        words.add(new Word(R.string.come_here, R.string.enni_nem, R.raw.phrase_come_here));
        //Create an {@link ArrayAdapter}, whose data source is a list of String.
        //adapter knows how to create layouts for each item in this list, using the
        //simple_list_item_1.xml layout resource defined in the Android framework.
        //This list item layout contains a single{@link TextView}, which the adapter will
        //set to display a single word.
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_listyout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);
        //Set audio for each listview throw the method setOnClickListener, which takes object of OnclickItemListener
        //as a parameter and then override the method onItemClick Listener to play the audio.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();
                // Get the current word position
                Word word = words.get(position);
                //Request audio Focus for playback
                final int aFrequest = mAudioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (aFrequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Create a media player object with the corresponding audio file
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getmAudioResourceId());
                    // Play the audio for the corresponding word
                    mediaPlayer.start();
                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });
        return rootView;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            // Release audio focus regardless of whether or not we were granted audio focus. This
            //also unregister the AudioFocusChangeListener so we don't get any more callbacks.
            mAudioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

    /**
     * Realise media resources player whenever the user leaves the activity
     */
    @Override
    public void onStop() {
        super.onStop();
        Log.v("MainActivity", "Activity has Stopped");
        releaseMediaPlayer();
    }


}
