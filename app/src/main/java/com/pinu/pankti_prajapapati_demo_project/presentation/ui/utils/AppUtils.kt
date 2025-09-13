package com.pinu.pankti_prajapapati_demo_project.presentation.ui.utils

import com.pinu.pankti_prajapapati_demo_project.domain.model.HoldingDataModel


object AppUtils {

    fun formatDouble(value: Double): String {
        return String.format("%,.2f", value)
    }

    fun String.inRupeeFormat(): String {
        return "₹ $this"
    }


    fun Double.modifiedAmount(): String {
        return formatDouble(this).inRupeeFormat()
    }
}

val dummyHoldingsList = listOf(
    HoldingDataModel("MAHABANK", 990, 35.0, 38.05, 40.0),
    HoldingDataModel("ICICI", 100, 110.0, 118.25, 105.0),
    HoldingDataModel("SBI", 150, 501.0, 550.05, 590.0),
    HoldingDataModel("TATA STEEL", 200, 110.65, 137.0, 100.05),
    HoldingDataModel("INFOSYS", 121, 1245.45, 1305.0, 1103.85),
    HoldingDataModel("AIRTEL", 415, 370.1, 340.75, 290.0),
    HoldingDataModel("UCO BANK", 2000, 28.15, 18.05, 22.25),
    HoldingDataModel("NHPC", 900, 80.75, 88.05, 70.65),
    HoldingDataModel("SJVN", 400, 105.0, 113.05, 110.0),
    HoldingDataModel("PNB BANK", 100, 100.0, 132.05, 145.55),
    HoldingDataModel("RELIANCE", 50, 2450.0, 2500.0, 2600.0),
    HoldingDataModel("HDFC", 75, 1750.0, 1800.25, 1700.0),
    HoldingDataModel("MARUTI", 30, 6800.0, 7000.0, 7200.0),
    HoldingDataModel("TCS", 150, 3400.0, 3500.0, 3300.0),
    HoldingDataModel("HCL", 200, 980.0, 1000.0, 1050.0),
    HoldingDataModel("WIPRO", 300, 480.0, 500.0, 520.0),
    HoldingDataModel("BPCL", 80, 380.0, 400.0, 420.0),
    HoldingDataModel("HPCL", 60, 290.0, 300.0, 320.0),
    HoldingDataModel("ONGC", 120, 140.0, 150.0, 160.0),
    HoldingDataModel("IOC", 200, 110.0, 120.0, 130.0),
    HoldingDataModel("HINDALCO", 150, 380.0, 400.0, 420.0),
    HoldingDataModel("ADANI PORTS", 500, 780.0, 800.0, 820.0),
    HoldingDataModel("CIPLA", 100, 880.0, 900.0, 920.0),
    HoldingDataModel("JSW STEEL", 250, 580.0, 600.0, 620.0),
    HoldingDataModel("AXIS BANK", 300, 680.0, 700.0, 720.0),
)

val dummyHolding = HoldingDataModel(
    symbol = "MAHABANK", quantity = 990, avgPrice = 35.0, ltp = 38.05, close = 40.0
)