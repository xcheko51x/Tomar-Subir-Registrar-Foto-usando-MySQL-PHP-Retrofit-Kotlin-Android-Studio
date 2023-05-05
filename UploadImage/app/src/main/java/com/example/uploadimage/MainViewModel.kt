package com.example.uploadimage

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class MainViewModel: ViewModel() {

    var mensaje = MutableLiveData<String>()

    fun enviarFoto(image: ImageModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webSevice.enviarImage(image.idImage, image.nomImage, image.image)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    mensaje.value = response.body()
                }
            }
        }
    }

    fun convertirBitmapToBase64(ivFoto: ImageView): String {
        val bitmap = (ivFoto.drawable as BitmapDrawable).bitmap
        var fotoEnBase64 = ""

        if (bitmap != null) {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            fotoEnBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT)

        }
        return fotoEnBase64
    }
}