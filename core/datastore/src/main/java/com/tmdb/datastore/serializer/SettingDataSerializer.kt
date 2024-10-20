package com.tmdb.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.tmdb.datastore.model.DarkThemeConfigResponse
import com.tmdb.datastore.model.SettingsDataResponse
import com.tmdb.datastore.model.ThemeBrandResponse
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json


class SettingDataSerializer @Inject constructor() : Serializer<SettingsDataResponse> {

    override val defaultValue: SettingsDataResponse
        get() = SettingsDataResponse(
            themeBrand = ThemeBrandResponse.DEFAULT,
            darkThemeConfig = DarkThemeConfigResponse.FOLLOW_SYSTEM,
            useDynamicColor = false
        )

    override suspend fun readFrom(input: InputStream): SettingsDataResponse {
        return try {
            Json.decodeFromString(
                SettingsDataResponse.serializer(), input.readBytes().decodeToString()
            )
        } catch (serialization: SerializationException) {
            throw CorruptionException("Unable to read Settings", serialization)
        }
    }

    override suspend fun writeTo(t: SettingsDataResponse, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(SettingsDataResponse.serializer(), t).encodeToByteArray()
            )
        }
    }
}
