package name.atsushieno.msfamidideviceservice

import android.media.midi.MidiReceiver
import com.levien.synthesizer.android.AndroidGlue
import java.lang.IllegalArgumentException

class MsfaMidiReceiver : MidiReceiver, AutoCloseable
{
    val glue = AndroidGlue()
    var temp_array : ByteArray? = null

    constructor () {
        glue.start(44100, 44100)
    }

    override fun close() {
        glue.shutdown()
    }

    override fun onSend(msg: ByteArray?, offset: Int, count: Int, timestamp: Long) {
        if (msg == null)
            throw IllegalArgumentException("null msg")
        // FIXME: respect timestamp
        if (offset != 0 && count != msg.size)
            glue.sendMidi(msg.copyOfRange(offset, count))
        else
            glue.sendMidi(msg)
    }
}