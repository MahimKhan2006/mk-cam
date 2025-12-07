package com.mahim.mkcam.ai.data.presets

import android.content.Context
import com.mahim.mkcam.core.domain.model.Preset
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PresetManager @Inject constructor(
    private val context: Context
) {
    private val _presets = mutableListOf<Preset>()
    val presets: List<Preset> get() = _presets

    fun loadPresets() {
        // Load built-in presets
        // TODO: Load from assets or raw resources
        // For now, load dummy defaults
        _presets.add(
            Preset(
                id = "p1",
                name = "Night Sky",
                description = "Enhance star field",
                prompt = "Enhance the night sky: vivid Milky Way band..."
            )
        )
    }

    fun parsePresetXml(inputStream: InputStream): Preset? {
        // Simple XML Parser implementation
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)

            var eventType = parser.eventType
            var name = ""
            var prompt = ""
            var description = ""

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    when (parser.name) {
                        "name" -> name = parser.nextText()
                        "prompt" -> prompt = parser.nextText()
                        "description" -> description = parser.nextText()
                    }
                }
                eventType = parser.next()
            }
            return Preset(
                id = System.currentTimeMillis().toString(), // Generate ID
                name = name,
                description = description,
                prompt = prompt
            )

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
