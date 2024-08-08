package com.example.prodigy_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        //Buttons
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonDecimal: Button = findViewById(R.id.buttonDecimal)
        val buttonClear: Button = findViewById(R.id.buttonClear)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonOpenParenthesis: Button = findViewById(R.id.buttonOpenParenthesis)
        val buttonCloseParenthesis: Button = findViewById(R.id.buttonCloseParenthesis)

        //making lists for buttons for evaluations
        val numberButtons = listOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)
        val operatorButtons = listOf(buttonAdd, buttonSubtract, buttonMultiply, buttonDivide, buttonOpenParenthesis, buttonCloseParenthesis)

        //adding buttons pressed to be dispayed and then evaluated
        numberButtons.forEach { button ->
            button.setOnClickListener {
                appendToDisplay((it as Button).text.toString())
            }
        }

        operatorButtons.forEach { button ->
            button.setOnClickListener {
                appendToDisplay((it as Button).text.toString())
            }
        }

        buttonDecimal.setOnClickListener { appendToDisplay(".") }
        buttonClear.setOnClickListener { tvDisplay.text = "" }
        buttonEquals.setOnClickListener { calculateResult() }
    }

    private fun appendToDisplay(text: String) {
        tvDisplay.append(text)
    }

    //evaluating the expression and replacing the multiplication and division symbols with their operators
    private fun calculateResult() {
        try {
            val expressionText = tvDisplay.text.toString()
                .replace("×", "*")
                .replace("÷", "/")

            val expression = ExpressionBuilder(expressionText).build()
            val result = expression.evaluate()
            tvDisplay.text = result.toString()
        } catch (e: Exception) {
            tvDisplay.text = "Error"
        }
    }
}
