package com.kanyasoft.pianonotestrainer;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    private NoteView noteView;
    private MidiDeviceHandler midiDeviceHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteView = new NoteView(this);
        LinearLayout layout = findViewById(R.id.layout);
        layout.addView(noteView);

        midiDeviceHandler = new MidiDeviceHandler(this);
        midiDeviceHandler.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //midiDeviceHandler.disconnect();
    }
}
