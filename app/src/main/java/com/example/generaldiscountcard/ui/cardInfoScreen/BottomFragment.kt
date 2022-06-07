package com.example.generaldiscountcard.ui.cardInfoScreen


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import com.example.generaldiscountcard.R
import com.example.generaldiscountcard.dataClasses.CardInfoObject
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.oned.Code128Writer
import kotlinx.android.synthetic.main.bottom_sheet.view.*


class BottomFragment: BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.bottom_sheet, container, false)

        val context = view.context
        val settingsCanWrite = hasWriteSettingsPermission(context)
        if (!settingsCanWrite) {
            changeWriteSettingsPermission(context)
        } else {
            changeScreenBrightness(context, BRIGHTNESS_OVERRIDE_FULL)
        }


        displayBitmap(CardInfoObject.cardNumber.toString(), view)


        return view
    }

    private fun hasWriteSettingsPermission(context: Context): Boolean {
        var ret = true
        ret = Settings.System.canWrite(context)
        return ret
    }

    private fun changeWriteSettingsPermission(context: Context) {
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
        context.startActivity(intent)
    }

    private fun changeScreenBrightness(context: Context, screenBrightnessValue: Float) {
        Settings.System.putInt(
            context.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS_MODE,
            Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
        )
        Settings.System.putInt(
            context.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS, screenBrightnessValue.toInt()
        )
    }

    private fun createBarcodeBitmap(
        barcodeValue: String,
        @ColorInt barcodeColor: Int,
        @ColorInt backgroundColor: Int,
        widthPixels: Int,
        heightPixels: Int,
    ): Bitmap {
        val bitMatrix = Code128Writer().encode(
            barcodeValue,
            BarcodeFormat.CODE_128,
            widthPixels,
            heightPixels
        )

        val pixels = IntArray(bitMatrix.width * bitMatrix.height)
        for (y in 0 until bitMatrix.height) {
            val offset = y * bitMatrix.width
            for (x in 0 until bitMatrix.width) {
                pixels[offset + x] =
                    if (bitMatrix.get(x, y)) barcodeColor else backgroundColor
            }
        }

        val bitmap = Bitmap.createBitmap(
            bitMatrix.width,
            bitMatrix.height,
            Bitmap.Config.ARGB_8888
        )
        bitmap.setPixels(
            pixels,
            0,
            bitMatrix.width,
            0,
            0,
            bitMatrix.width,
            bitMatrix.height
        )
        return bitmap
    }

    private fun displayBitmap(value: String, view: View) {

        val widthPixels = resources.getDimensionPixelSize(R.dimen.width_barcode)
        val heightPixels = resources.getDimensionPixelSize(R.dimen.height_barcode)

        view.image_barcode.setImageBitmap(
            createBarcodeBitmap(
                barcodeValue = value,
                barcodeColor = Color.BLACK,
                backgroundColor = Color.WHITE,
                widthPixels = widthPixels,
                heightPixels = heightPixels
            )
        )

        view.text_barcode_number.text = value
    }
}