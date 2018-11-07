package name.atsushieno.msfamidideviceservice

import android.media.midi.MidiDeviceService
import android.media.midi.MidiReceiver

class MsfaMidiDeviceService : MidiDeviceService()
{
    lateinit var input : MsfaMidiReceiver

    override fun onGetInputPortReceivers(): Array<MidiReceiver> {
        if (!this::input.isInitialized) {
            input = MsfaMidiReceiver()
            input.initialize (this)
        }
        return arrayOf(input)
    }
}