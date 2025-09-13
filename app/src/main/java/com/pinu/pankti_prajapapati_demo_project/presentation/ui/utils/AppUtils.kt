package com.pinu.pankti_prajapapati_demo_project.presentation.ui.utils
import java.util.Locale

object AppUtils {

    fun formatDouble(value: Double): String {
        return String.format(locale = Locale.ENGLISH,"%,.2f", value)
    }

    fun String.inRupeeFormat(): String {
        return "₹ $this"
    }


    fun Double.modifiedAmount(): String {
        return formatDouble(this).inRupeeFormat()
    }
}

