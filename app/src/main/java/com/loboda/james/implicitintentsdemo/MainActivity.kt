package com.loboda.james.implicitintentsdemo

/*
 * Copyright (C) 2022 James Loboda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.loboda.james.implicitintentsdemo.databinding.ActivityMainBinding

const val REQUEST_CODE_PHOTO = 0
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // current way of doing startActivityForResult
        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent: Intent? = result.data
                val data = intent?.data
                data?.let {
                    setImage(it)
                }
            } else {
                Log.d("Stuff", "not successful")
            }
        }

        binding.btnTakePhoto.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                // mime type (any) images
                it.type = "image/*"
                startForResult.launch(it)

                // DEPRECATED: startActivityForResult(it, REQUEST_CODE_PHOTO)
                // ... then override onActivityResult()
            }
        }
    }

    private fun setImage(uri: Uri) {
        binding.ivSelectedImage.setImageURI(uri)
    }

    // DEPRECATED WAY
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_PHOTO) {
//            val uri = data?.data
//            uri?.let {
//                setImage(it)
//            }
//        }
//    }
}