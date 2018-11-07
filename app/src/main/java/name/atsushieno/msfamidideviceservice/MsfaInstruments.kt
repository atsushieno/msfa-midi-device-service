package name.atsushieno.msfamidideviceservice

import android.content.Context

class MsfaInstruments
{
    companion object {
        public fun loadInstrumentData (context: Context) : ByteArray {
            val patchData = ByteArray(4104)
            val patchIs = context.getResources().openRawResource(R.raw.rom1a)
            patchIs.read(patchData)
            return patchData
        }
    }
}