package name.atsushieno.msfamidideviceservice

import android.media.midi.MidiReceiver
import com.levien.synthesizer.android.AndroidGlue
import java.lang.IllegalArgumentException
import android.R.raw
import android.content.Context
import android.util.Log
import java.io.IOException
import java.nio.charset.Charset


class MsfaMidiReceiver : MidiReceiver(), AutoCloseable
{
    val glue = AndroidGlue()
    var temp_array : ByteArray? = null
    lateinit var patch_names : ArrayList<String>

    public fun initialize (context: Context)
    {
        val patchData = MsfaInstruments.loadInstrumentData(context)
        glue.start(44100, 64)
        glue.sendMidi(patchData)
        patch_names = ArrayList()
        for (i in 0..31) {
            patch_names.add(String(patchData, 124 + 128 * i, 10, Charset.forName("ISO-8859-1")))
        }
        glue.setPlayState(true)
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