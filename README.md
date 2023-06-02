# Android-calculator
Kotlin和XML开发的一个计算器安卓(Android)程序

<?xml version="1.0" encoding="utf-8"?>

声明XML版本和编码。

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
  android:orientation="vertical">
定义一个垂直方向的线性布局作为根元素。它作为一个容器来容纳其他视图，并将它们垂直排列。

<EditText
    android:id="@+id/outputText"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="结果将在这里显示" />

创建一个用于显示结果的文本输入框。它具有给定的ID，可以通过该ID在代码中引用。宽度设置为填充父容器，高度根据内容自适应。占位文本设置为"结果将在这里显示"。

<GridLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:columnCount="4"
    android:rowCount="5">

创建一个网格布局，用于放置按钮。宽度设置为填充父容器，高度根据内容自适应。`columnCount`属性设置为4，表示网格布局有4列。`rowCount`属性设置为5，表示网格布局有5行。

<Button
    android:id="@+id/btn0"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_columnWeight="1"
    android:layout_rowWeight="1"
    android:text="0" />
  
创建一个按钮，具有给定的ID，可以通过该ID在代码中引用。宽度设置为0dp，权重设置为1，使按钮平均填充可用的列空间。高度根据内容自适应。按钮上显示的文本为"0"。

<!-- 剩下的按钮依此类推 -->

这里是其余的按钮，按照相同的模式进行定义。

<Button
    android:id="@+id/btnPlus"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_columnWeight="1"
    android:layout_rowWeight="1"
    android:text="+" />

创建一个用于加法的按钮，具有给定的ID，可以通过该ID在代码中引用。宽度设置为0dp，权重设置为1，使按钮平均填充可用的列空间。高度根据内容自适应。按钮上显示的文本为"+"。

<Button
    android:id="@+id/btnEquals"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_columnWeight="1"
    android:layout_rowWeight="1"
    android:text="=" />

创建一个用于等号的按钮，具有给定的ID，可以通过该ID在代码中引用。宽度设置

为0dp，权重设置为1，使按钮平均填充可用的列空间。高度根据内容自适应。按钮上显示的文本为"="。

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

导入所需的类和包。

class MainActivity : AppCompatActivity() {

定义一个名为`MainActivity`的类，它继承自`AppCompatActivity`类。

private lateinit var outputText: EditText
private lateinit var btn0: Button
private lateinit var btn1: Button
private lateinit var btn2: Button
// 声明剩下的按钮

声明私有的延迟初始化变量`outputText`、`btn0`、`btn1`和`btn2`，分别对应布局文件中的文本输入框和按钮。你还需要根据布局文件中定义的按钮数量继续声明其余的按钮。

private var operand1: Double = 0.0
private var operand2: Double = 0.0
private var operator: Char? = null

声明私有的可空变量`operand1`、`operand2`和`operator`，用于存储计算器操作数和操作符的值。

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
重写`onCreate`方法，在Activity创建时调用。通过`setContentView`方法设置布局文件为`activity_main.xml`。

outputText = findViewById(R.id.outputText)
btn0 = findViewById(R.id.btn0)
btn1 = findViewById(R.id.btn1)
btn2 = findViewById(R.id.btn2)
// 初始化剩下的按钮
使用`findViewById`方法初始化`outputText`、`btn0`、`btn1`和`btn2`变量，通过传入相应的资源ID来找到对应的视图。你还需要根据布局文件中定义的按钮数量继续初始化其余的按钮。

btn0.setOnClickListener { appendDigit("0") }
btn1.setOnClickListener { appendDigit("1") }
btn2.setOnClickListener { appendDigit("2") }
// 设置剩下的数字按钮的点击事件

为按钮设置点击事件监听器，当点击按钮时，调用`appendDigit`方法并传入相应的数字字符串。
  
findViewById<Button>(R.id.btnPlus).setOnClickListener { setOperator('+') }
findViewById<Button>(R.id.btnMinus).setOnClickListener { setOperator('-') }
findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperator('*') }
findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperator('/') }

为加法、减法、乘法和除法按钮设置点击事件监听器，当点击按钮时，调用`setOperator`方法并传入相应的操作符。

findViewById<Button>(R.id.btnEquals).setOnClickListener { calculateResult() }

为等号按钮设置点击事件监听器，当点击按钮时，调用`calculateResult`方法。

private fun appendDigit(digit: String) {
    outputText.append(digit)
}

`appendDigit`函数用于向`outputText`文本框追加数字。

private fun setOperator(op: Char) {
    operator = op
    operand1 = outputText.text.toString().toDouble()
    outputText

.text.clear()
}

`setOperator`函数用于设置操作符。它将传入的操作符赋值给`operator`变量，并将`outputText`文本框中的内容转换为`Double`类型并赋值给`operand1`变量。然后清空`outputText`文本框的内容。

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
`calculateResult`函数用于计算结果。如果`operator`不为null，则将`outputText`文本框中的内容转换为`Double`类型并赋值给`operand2`变量。然后根据`operator`的值执行相应的计算操作，并将结果存储在`result`变量中。最后，将`result`的值设置为`outputText`文本框的内容，并将`operator`设为null。
