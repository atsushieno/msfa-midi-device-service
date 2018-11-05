package name.atsushieno.msfamidideviceservice

import android.media.midi.MidiDeviceService
import android.media.midi.MidiReceiver

class MsfaMidiDeviceService : MidiDeviceService()
{
    val input = MsfaMidiReceiver()

    override fun onGetInputPortReceivers(): Array<MidiReceiver> {
        return arrayOf(input)
    }
}