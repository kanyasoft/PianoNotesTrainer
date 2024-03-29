package com.kanyasoft.pianonotestrainer;
import android.content.Context;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiDevice;
import android.media.midi.MidiManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;

public class MidiDeviceHandler {
    private static final String TAG = "MidiDeviceHandler";

    private Context context;
    private MidiManager midiManager;
    private MidiDevice midiDevice;
    private MidiManager.OnDeviceOpenedListener deviceOpenedListener;

    public MidiDeviceHandler(Context context) {
        this.context = context;
        this.midiManager = (MidiManager) context.getSystemService(Context.MIDI_SERVICE);
    }

    public void connect() {
        if (midiManager == null) {
            Log.e(TAG, "MIDI not supported on this device");
            return;
        }

        deviceOpenedListener = new MidiManager.OnDeviceOpenedListener() {
            @Override
            public void onDeviceOpened(MidiDevice device) {
                midiDevice = device;
                Log.i(TAG, "MIDI device opened successfully");
                // Here you can start processing MIDI input from the device
            }
        };

        MidiDeviceInfo[] infos = midiManager.getDevices();
        for (MidiDeviceInfo info : infos) {
            if (isYamahaDevice(info)) {
                midiManager.openDevice(info, deviceOpenedListener, new Handler(Looper.getMainLooper()));
                break;
            }
        }
    }

    private boolean isYamahaDevice(MidiDeviceInfo info) {
        String productName = info.getProperties().getString(MidiDeviceInfo.PROPERTY_PRODUCT);
        // Check if the product name contains "Yamaha" (case-insensitive)
        return productName != null && productName.toLowerCase().contains("yamaha");
    }

    public void disconnect() throws IOException {
        if (midiDevice != null) {
            midiDevice.close();
            midiDevice = null;
            Log.i(TAG, "MIDI device closed");
        }
    }

    // Method to handle MIDI input
    public void handleMidiInput(int note) {
        // Code to handle MIDI input
    }
}
