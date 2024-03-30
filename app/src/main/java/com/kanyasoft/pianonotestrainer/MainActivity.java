package com.kanyasoft.pianonotestrainer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private MidiDeviceHandler midiDeviceHandler;
    private NoteView noteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteView = findViewById(R.id.note_view);
        midiDeviceHandler = new MidiDeviceHandler(this, noteView);
        midiDeviceHandler.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        midiDeviceHandler.disconnect();
    }
}
