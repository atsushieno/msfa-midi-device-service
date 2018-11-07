package name.atsushieno.msfamidideviceservice

import android.media.midi.*
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var midi_manager: MidiManager
    lateinit var midi_input: MidiInputPort
    lateinit var midi_client: MsfaMidiReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        midi_manager = getSystemService(MidiDeviceService.MIDI_SERVICE) as MidiManager

        this.direct_button.setOnClickListener {
            if (!this::midi_client.isInitialized) {
                midi_client = MsfaMidiReceiver()
                midi_client.initialize(this)
            }
            play_sound(midi_client)
        }

        this.service_button.setOnClickListener {
            run {
                if (!this::midi_input.isInitialized) {
                    val deviceInfo = midi_manager.devices.first { d -> d.properties.getString(MidiDeviceInfo.PROPERTY_PRODUCT).equals ("JMsfaMidi") }
                    midi_manager.openDevice(deviceInfo, { dev ->
                        run {
                            midi_input = dev.openInputPort(0)
                            play_sound(midi_input)
                        }
                    }, null)
                } else
                    play_sound(midi_input)
            }
        }
    }

    fun play_sound(midi : MidiReceiver) {
        midi.send(arrayOf(0xB0.toByte(), 7, 120).toByteArray(), 0, 3)
        midi.send(arrayOf(0xC0.toByte(), 0).toByteArray(), 0, 2)
        midi.send(arrayOf(0x90.toByte(), 0x40, 120).toByteArray(), 0, 3)
        midi.send(arrayOf(0x90.toByte(), 0x44, 120).toByteArray(), 0, 3)
        midi.send(arrayOf(0x90.toByte(), 0x47, 120).toByteArray(), 0, 3)
        AsyncTask.execute {
            Thread.sleep(5000)
            midi.send(arrayOf(0x80.toByte(), 0x40, 0).toByteArray(), 0, 3)
            midi.send(arrayOf(0x80.toByte(), 0x44, 0).toByteArray(), 0, 3)
            midi.send(arrayOf(0x80.toByte(), 0x47, 0).toByteArray(), 0, 3)
        }
    }
}
