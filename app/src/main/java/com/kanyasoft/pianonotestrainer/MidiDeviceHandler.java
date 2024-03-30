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
       // Toast.makeText(context, "Try to connect", Toast.LENGTH_SHORT).show();
        if (midiManager == null) {
            Toast.makeText(context, "MIDI not supported on this device", Toast.LENGTH_SHORT).show();
            return;
        }

        MidiManager.OnDeviceOpenedListener deviceOpenedListener = new MidiManager.OnDeviceOpenedListener() {
            @Override
            public void onDeviceOpened(MidiDevice device) {
                Toast.makeText(context, "onDeviceOpened", Toast.LENGTH_SHORT).show();
                if (device != null) {
                    Toast.makeText(context, "MIDI device connected", Toast.LENGTH_SHORT).show();
                    // Here you can perform any additional actions when the MIDI device is connected
                } else {
                    Toast.makeText(context, "Failed to open MIDI device", Toast.LENGTH_SHORT).show();
                }
            }
        };

        // Get the list of MIDI devices
        MidiDeviceInfo[] infos = new MidiDeviceInfo[0];

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            Toast.makeText(context, "MIDI device is on USB", Toast.LENGTH_SHORT).show();
            infos = midiManager.getDevicesForTransport(MidiManager.TRANSPORT_MIDI_BYTE_STREAM).toArray(new MidiDeviceInfo[0]);

        }
        for (MidiDeviceInfo info : infos) {
            Toast.makeText(context, "MIDI device is on USB", Toast.LENGTH_SHORT).show();
            // Check if the device is an input device
            if (info.getType() == MidiDeviceInfo.TYPE_USB) {
                // Try to open the device
                midiManager.openDevice(info, deviceOpenedListener, new Handler(Looper.getMainLooper()));

                // Only open one device for simplicity
                break;
            }
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
