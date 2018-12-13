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
public class FamilyFragment extends Fragment {
    //Handle all audio play
    MediaPlayer mediaPlayer;

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


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the word list layout
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        // Create an instance of AudioManager to manage audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        //Create list of words
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.string.father, R.string.әpә, R.drawable.family_father, R.raw.family_father));
        words.add(new Word(R.string.mother, R.string.eta, R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word(R.string.son, R.string.angsi, R.drawable.family_son, R.raw.family_son));
        words.add(new Word(R.string.daughter, R.string.tune, R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word(R.string.older_brother, R.string.taachi, R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word(R.string.younger_brother, R.string.chalitti, R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word(R.string.older_sister, R.string.teṭe, R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word(R.string.younger_sister, R.string.kolliti, R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word(R.string.ama, R.string.ama, R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word(R.string.paapa, R.string.paapa, R.drawable.family_grandfather, R.raw.family_grandfather));
        //Create an {@link ArrayAdapter}, whose data source is a list of String.
        //adapter knows how to create layouts for each item in this list, using the
        //simple_list_item_1.xml layout resource defined in the Android framework.
        //This list item layout contains a single{@link TextView}, which the adapter will
        //set to display a single word.
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_family);
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
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
