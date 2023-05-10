package com.example.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAllBinds()
    }

    private fun setAllBinds() {
        with(binding) {
            binding.mathOperation.text = "0"
            btn0.setOnClickListener { setTextFields(btn0.text.toString()) }
            btn1.setOnClickListener { setTextFields(btn1.text.toString()) }
            btn2.setOnClickListener { setTextFields(btn2.text.toString()) }
            btn3.setOnClickListener { setTextFields(btn3.text.toString()) }
            btn4.setOnClickListener { setTextFields(btn4.text.toString()) }
            btn5.setOnClickListener { setTextFields(btn5.text.toString()) }
            btn6.setOnClickListener { setTextFields(btn6.text.toString()) }
            btn7.setOnClickListener { setTextFields(btn7.text.toString()) }
            btn8.setOnClickListener { setTextFields(btn8.text.toString()) }
            btn9.setOnClickListener { setTextFields(btn9.text.toString()) }
            plusBtn.setOnClickListener { setTextFields(plusBtn.text.toString()) }
            minusBtn.setOnClickListener { setTextFields(minusBtn.text.toString()) }
            rightBrBtn.setOnClickListener { setTextFields(rightBrBtn.text.toString()) }
            leftBrBtn.setOnClickListener { setTextFields(leftBrBtn.text.toString()) }
            devideBtn.setOnClickListener { setTextFields(devideBtn.text.toString()) }
            multiplyBtn.setOnClickListener { setTextFields(multiplyBtn.text.toString()) }
            pointBtn.setOnClickListener { setTextFields(pointBtn.text.toString()) }
            acBtn.setOnClickListener {
                clearField()
            }
            backBtn.setOnClickListener {
                removeLastChar()
            }
            binding.equalBtn.setOnClickListener {
                try {
                    doMathCalculation()
                } catch (e: Exception) {
                    if (mathOperation.text.contains(DEVIDE_ZERO)) {
                        showDevideZeroToast()
                    } else {
                        showWrongOperationToast()
                    }
                }
            }
        }

    }

    private fun clearField() {
        binding.mathOperation.text = EMPTY_STRING
    }

    private fun showWrongOperationToast() {
        Toast.makeText(
            this@MainActivity,
            WRONG_OPERATION,
            Toast.LENGTH_LONG
        )
            .show()
    }

    private fun showDevideZeroToast() {
        Toast.makeText(this@MainActivity, CANT_DEVIDE_ZERO, Toast.LENGTH_LONG)
            .show()
    }

    private fun doMathCalculation() {
        val ex = ExpressionBuilder( binding.mathOperation.text.toString()).build()
        val result = ex.evaluate()
        val longres = result.toLong()

        if (result == longres.toDouble())
            binding.mathOperation.text = longres.toString()
        else
            binding.mathOperation.text = result.toString()
    }

    private fun removeLastChar() {
        val str =   binding.mathOperation.text.toString()
        if (str.isNotEmpty()) {
            binding.mathOperation.text = str.substring(0, str.length - 1)
        }
    }

    private fun setTextFields(str: String) {
        if(binding.mathOperation.text.toString() == "0") {
            removeLastChar()
        }
        binding.mathOperation.append(str)
    }

    companion object {
        private const val DEVIDE_ZERO = "/0"
        private const val WRONG_OPERATION = "Невозможно выполнить данную операцию"
        private const val CANT_DEVIDE_ZERO = "Нельзя делить на 0!"
        private const val EMPTY_STRING = "0"
    }
}