package com.example.challenge_chapter6_fix.data

import android.content.Context
import android.net.Uri
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataUserManager(private val context: Context){

    companion object {
        private const val DATASTORE_NAME = "login_preferences"
        private val USERNAME = stringPreferencesKey("username_key")
        private val PASSWORD = stringPreferencesKey("password_key")
        private val NAME = stringPreferencesKey("name_key")
        private val EMAIL = stringPreferencesKey("email_key")
        private val BIRTHDAY = stringPreferencesKey("birthday_key")
        private val NOMOR = stringPreferencesKey("nomor_key")
        private val PROFILE_IMAGE = stringPreferencesKey("image_key")
        private val IS_PROFILE = booleanPreferencesKey("profile_key")
        private val IS_LOGIN = booleanPreferencesKey("is_login_key")

        private val Context.dataUser by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }

    suspend fun saveImage(uri: String){
        context.dataUser.edit { preferences ->
            preferences[PROFILE_IMAGE] = uri
        }
    }

    suspend fun removeImage(){
        context.dataUser.edit { preferences ->
            preferences.remove(PROFILE_IMAGE)
        }
    }

    suspend fun setIsProfile(isProfile: Boolean) {
        context.dataUser.edit { preferences ->
            preferences[IS_PROFILE] = isProfile
        }
    }

    suspend fun setUsername(username: String) {
        context.dataUser.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    suspend fun setPassword(password: String) {
        context.dataUser.edit { preferences ->
            preferences[PASSWORD] = password
        }
    }

    suspend fun setName(name: String) {
        context.dataUser.edit { preferences ->
            preferences[NAME] = name
        }
    }

    suspend fun setEmail(email: String) {
        context.dataUser.edit { preferences ->
            preferences[EMAIL] = email
        }
    }

    suspend fun setBirthday(birthday: String) {
        context.dataUser.edit { preferences ->
            preferences[BIRTHDAY] = birthday
        }
    }

    suspend fun setNomor(nomor: String) {
        context.dataUser.edit { preferences ->
            preferences[NOMOR] = nomor
        }
    }

    suspend fun setIsLogin(isLogin: Boolean) {
        context.dataUser.edit { preferences ->
            preferences[IS_LOGIN] = isLogin
        }
    }

    suspend fun getPreferences(context: Context): Flow<Preferences> {
        return context.dataUser.data
    }


    fun getPassword(): Flow<String>{
        return context.dataUser.data.map { preferences ->
            preferences[PASSWORD] ?: ""
        }
    }

    fun getIsLogin(): Flow<Boolean>{
        return context.dataUser.data.map { preferences ->
            preferences[IS_LOGIN] ?: false
        }
    }

    fun getUsername(): Flow<String> {
        return context.dataUser.data.map { preferences ->
            preferences[USERNAME] ?: ""
        }
    }

    fun getName(): Flow<String> {
        return context.dataUser.data.map { preferences ->
            preferences[NAME] ?: ""
        }
    }

    fun getEmail(): Flow<String> {
        return context.dataUser.data.map { preferences ->
            preferences[EMAIL] ?: ""
        }
    }
    fun getBirthday(): Flow<String> {
        return context.dataUser.data.map { preferences ->
            preferences[BIRTHDAY] ?: ""
        }
    }

    fun getNomor(): Flow<String> {
        return context.dataUser.data.map { preferences ->
            preferences[NOMOR] ?: ""
        }
    }

    fun getIsProfile(): Flow<Boolean>{
        return context.dataUser.data.map { preferences ->
            preferences[IS_PROFILE] ?: false
        }
    }

}