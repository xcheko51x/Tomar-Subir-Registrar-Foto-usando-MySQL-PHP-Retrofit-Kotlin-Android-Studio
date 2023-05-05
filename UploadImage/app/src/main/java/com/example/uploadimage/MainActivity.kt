package com.example.uploadimage

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.uploadimage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var base64 = ""

    private val camaraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val extras = result.data?.extras
            val imgBitmap = extras!!["data"] as Bitmap?
            binding.ivFoto.setImageBitmap(imgBitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.mensaje.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        binding.btnCapturarFoto.setOnClickListener {
            val isPermiso = Permisos().checkCamaraPermiso(this)
            if (isPermiso) {
                abrirCamara()
            }
        }

        binding.btnSubirFoto.setOnClickListener {

            base64 = viewModel.convertirBitmapToBase64(binding.ivFoto)

            if (binding.etNombre.text.isNullOrEmpty()) {
                Toast.makeText(this, "DEBES PONER EL NOMBRE DE LA IMAGEN", Toast.LENGTH_SHORT).show()
            } else {
                val imagen = ImageModel(System.currentTimeMillis().toString(), binding.etNombre.text.toString().trim(), base64)
                viewModel.enviarFoto(imagen)
                binding.etNombre.setText("")
                binding.ivFoto.setImageResource(0)
            }
        }

    }

    private fun abrirCamara() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            camaraLauncher.launch(intent)
        }
    }
}