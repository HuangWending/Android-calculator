import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var outputText: EditText
    private lateinit var btn0: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    // 声明剩下的按钮

    private var operand1: Double = 0.0
    private var operand2: Double = 0.0
    private var operator: Char? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        outputText = findViewById(R.id.outputText)
        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        // 初始化剩下的按钮

        // 设置数字按钮的点击事件
        btn0.setOnClickListener { appendDigit("0") }
        btn1.setOnClickListener { appendDigit("1") }
        btn2.setOnClickListener { appendDigit("2") }
        // 设置剩下的数字按钮的点击事件

        // 设置运算符按钮的点击事件
        findViewById<Button>(R.id.btnPlus).setOnClickListener { setOperator('+') }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { setOperator('-') }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperator('*') }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperator('/') }

        // 设置等号按钮的点击事件
        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculateResult() }
    }

    private fun appendDigit(digit: String) {
        outputText.append(digit)
    }

    private fun setOperator(op: Char) {
        operator = op
        operand1 = outputText.text.toString().toDouble()
        outputText.text.clear()
    }

    private fun calculateResult() {
        if (operator != null) {
            operand2 = outputText.text.toString().toDouble()
            val result = when (operator) {
                '+' -> operand1 + operand2
                '-' -> operand1 - operand2
                '*' -> operand1 * operand2
                '/' -> operand1 / operand2
                else -> 0.0
            }
            outputText.setText(result.toString())
            operator = null
        }
    }
}
