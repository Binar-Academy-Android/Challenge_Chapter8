/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.challenge_chapter6_fix.viewModel

import android.app.Application
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.example.challenge_chapter6_fix.KEY_IMAGE_URI
import com.example.challenge_chapter6_fix.workers.BlurWorker
import com.example.challenge_chapter6_fix.workers.CleanUpWorker
import com.example.challenge_chapter6_fix.workers.SaveImageToFileWorker
import kotlinx.coroutines.launch


class BlurViewModel(application: Application) : ViewModel() {

    private val workManager = WorkManager.getInstance(application)

    private var imageUri: Uri? = null

    fun setImageUri(uri: Uri?) {
        imageUri = uri
    }

    internal fun applyBlur() {
        val blurRequest = OneTimeWorkRequest
            .Builder(BlurWorker::class.java)
            .setInputData(createInputDataForUri())
            .build()

        workManager.enqueue(blurRequest)
    }

    private fun createInputDataForUri(): Data {
        val builder = Data.Builder()
        imageUri?.let {
            builder.putString(KEY_IMAGE_URI, imageUri.toString())
        }
        return builder.build()
    }

    class BlurViewModelFactory(private val application: Application) :
        ViewModelProvider.AndroidViewModelFactory(application) {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(BlurViewModel::class.java)) {
                BlurViewModel(application) as T
            } else {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

}
