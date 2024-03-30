package com.kanyasoft.pianonotestrainer;

import android.content.Context;
import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class MidiDeviceHandler {
    private Context context;
    private MidiManager midiManager;
    private NoteView noteView;

    public MidiDeviceHandler(Context context, NoteView noteView) {
        this.context = context;
        this.noteView = noteView;
        this.midiManager = (MidiManager) context.getSystemService(Context.MIDI_SERVICE);
    }

    public void connect() {
        if (midiManager == null) {
            Toast.makeText(context, "MIDI not supported on this device", Toast.LENGTH_SHORT).show();
            return;
        }

        MidiManager.OnDeviceOpenedListener deviceOpenedListener = new MidiManager.OnDeviceOpenedListener() {
            @Override
            public void onDeviceOpened(MidiDevice device) {
                if (device != null) {
                    Toast.makeText(context, "MIDI device connected", Toast.LENGTH_SHORT).show();
                    // Here you can perform any additional actions when the MIDI device is connected
                } else {
                    Toast.makeText(context, "Failed to open MIDI device", Toast.LENGTH_SHORT).show();
                }
            }
        };

        // Get the list of MIDI devices
        MidiDeviceInfo[] infos = midiManager.getDevices();

        // Check if the SDK version supports the USB transport type
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Toast.makeText(context, "SDK is OK", Toast.LENGTH_SHORT).show();
            // For Android 6.0 (Marshmallow) and above, use USB transport type
            for (MidiDeviceInfo info : infos) {
                Toast.makeText(context, "there is some device", Toast.LENGTH_SHORT).show();
                // Check if the device is an input device and supports USB transport
                if (info.getType() == MidiDeviceInfo.TYPE_USB) {
                    // Try to open the device
                    midiManager.openDevice(info, deviceOpenedListener, new Handler(Looper.getMainLooper()));
                    // Only open one device for simplicity
                    break;
                }
            }
        } else {
            // For older Android versions, use a fallback mechanism
            // You can implement alternative logic here for older versions
            Toast.makeText(context, "MIDI device recognition is not supported on this device", Toast.LENGTH_SHORT).show();
        }
    }


    public void disconnect() {
        // Add your MIDI device disconnection logic here
    }

    // Method to handle MIDI input
    public void handleMidiInput(String note) {
        noteView.setCurrentNote(note);
        Toast.makeText(context, "Pressed note: " + note, Toast.LENGTH_SHORT).show();
    }
}
