/*-
 *  Copyright (C) 2013 Sam Malone 
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.peterbaldwin.vlcremote.app;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import org.peterbaldwin.client.android.vlcremote.R;
import org.peterbaldwin.vlcremote.net.MediaServer;

/**
 *
 * @author Sam Malone
 */
public class CommonPlaybackButtonsListenener implements View.OnClickListener, View.OnLongClickListener {
    
    private MediaServer mMediaServer;
    private Context context;
    
    public CommonPlaybackButtonsListenener(Context ctx, MediaServer server) {
        context = ctx;
        mMediaServer = server;
    }
    
    public void setUp(View view) {
        ImageButton mButtonCrop = (ImageButton) view.findViewById(R.id.menu_playlist_cycle_crop);
        ImageButton mButtonSubtitles = (ImageButton) view.findViewById(R.id.menu_playlist_cycle_subtitles);
        ImageButton mButtonFullscreen = (ImageButton) view.findViewById(R.id.menu_playlist_button_fullscreen);
        ImageButton mButtonAudioTrack = (ImageButton) view.findViewById(R.id.menu_playlist_cycle_audio_track);
        ImageButton mButtonAspectRatio = (ImageButton) view.findViewById(R.id.menu_playlist_cycle_aspect_ratio);

        setupImageButtonListeners(mButtonCrop, mButtonSubtitles, mButtonFullscreen, mButtonAudioTrack, mButtonAspectRatio);
    }
    
    private void setupImageButtonListeners(ImageButton... imageButtons) {
        for(ImageButton b : imageButtons) {
            if(b != null) {
                b.setOnClickListener(this);
                b.setOnLongClickListener(this);
            }
        }
    }

    /** {@inheritDoc} */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_playlist_cycle_crop:
                mMediaServer.status().command.key("crop");
                break;
            case R.id.menu_playlist_cycle_subtitles:
                mMediaServer.status().command.key("subtitle-track");
                break;
            case R.id.menu_playlist_button_fullscreen:
                mMediaServer.status().command.fullscreen();
                break;
            case R.id.menu_playlist_cycle_audio_track:
                mMediaServer.status().command.key("audio-track");
                break;
            case R.id.menu_playlist_cycle_aspect_ratio:
                mMediaServer.status().command.key("aspect-ratio");
                break;
        }
    }

    public boolean onLongClick(View v) {
        Toast.makeText(context, v.getContentDescription(), Toast.LENGTH_SHORT).show();
        return true;
    }
    
}
