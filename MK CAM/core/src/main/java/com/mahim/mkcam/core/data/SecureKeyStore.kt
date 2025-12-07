package com.mahim.mkcam.core.data

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SecureKeyStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val masterKey: MasterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secure_keys_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveApiKey(provider: String, key: String) {
        sharedPreferences.edit().putString(provider, key).apply()
    }

    fun getApiKey(provider: String): String? {
        return sharedPreferences.getString(provider, null)
    }

    fun removeApiKey(provider: String) {
        sharedPreferences.edit().remove(provider).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
    
    fun getAllProviders(): List<String> {
        return sharedPreferences.all.keys.toList()
    }
}
