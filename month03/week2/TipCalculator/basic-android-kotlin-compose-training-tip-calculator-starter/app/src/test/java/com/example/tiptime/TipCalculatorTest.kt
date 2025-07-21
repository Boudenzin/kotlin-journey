package com.example.tiptime
import org.junit.Test
import java.text.NumberFormat
import org.junit.Assert.*


class TipCalculatorTest {

    @Test
    fun CalculateTip_20PercentNoRoundUp() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)

        val actualTip = calculateTip(amount, tipPercent, false)
        assertEquals(expectedTip, actualTip)


    }
}