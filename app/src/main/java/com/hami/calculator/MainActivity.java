package com.hami.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private AppCompatButton functionButtonClear, operatorButtonPercentage, functionButtonDelete, operatorButtonDivision;
    private AppCompatButton numericButton7, numericButton8, numericButton9, operatorButtonMultiply;
    private AppCompatButton numericButton4, numericButton5, numericButton6, operatorButtonMinus;
    private AppCompatButton numericButton1, numericButton2, numericButton3, operatorButtonPlus;
    private AppCompatButton numericButton00, numericButton0, functionalButtonDecimal, arithmeticButtonEquals;

    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the TextView and Buttons
        resultTextView = findViewById(R.id.resultTextView);
        functionButtonClear = findViewById(R.id.FunctionButtonClear);
        operatorButtonPercentage = findViewById(R.id.operatorButtonPercentage);
        functionButtonDelete = findViewById(R.id.functionButtonDelete);
        operatorButtonDivision = findViewById(R.id.operatorButtonDivision);

        numericButton7 = findViewById(R.id.numericButton7);
        numericButton8 = findViewById(R.id.numericButton8);
        numericButton9 = findViewById(R.id.numericButton9);
        operatorButtonMultiply = findViewById(R.id.operatorButtonMultiply);

        numericButton4 = findViewById(R.id.numericButton4);
        numericButton5 = findViewById(R.id.numericButton5);
        numericButton6 = findViewById(R.id.numericButton6);
        operatorButtonMinus = findViewById(R.id.operatorButtonMinus);

        numericButton1 = findViewById(R.id.numericButton1);
        numericButton2 = findViewById(R.id.numericButton2);
        numericButton3 = findViewById(R.id.numericButton3);
        operatorButtonPlus = findViewById(R.id.operatorButtonPlus);

        numericButton00 = findViewById(R.id.numericButton00);
        numericButton0 = findViewById(R.id.numericButton0);
        functionalButtonDecimal = findViewById(R.id.functionalButtonDecimal);
        arithmeticButtonEquals = findViewById(R.id.arithmeticButtonEquals);

        // Set onClickListeners for numeric buttons
        setNumericButtonListeners();

        // Set onClickListeners for operator buttons
        setOperatorButtonListeners();

        // Set onClickListeners for functional buttons
        setFunctionalButtonListeners();
    }

    private void setNumericButtonListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatButton button = (AppCompatButton) view;
                String buttonText = button.getText().toString();

                // Check if the current input is "0" and the button clicked is "00"
                if (currentInput.equals("0") && buttonText.equals("00")) {
                    // Do nothing, keep current input as "0"
                    return;
                }

                // Check if current input is "0" and button clicked is a number
                if (currentInput.equals("0") && !buttonText.equals(".")) {
                    currentInput = buttonText; // Replace "0" with the number clicked
                } else {
                    currentInput += buttonText; // Concatenate the number to current input
                }

                resultTextView.setText(currentInput);
            }
        };

        numericButton1.setOnClickListener(listener);
        numericButton2.setOnClickListener(listener);
        numericButton3.setOnClickListener(listener);
        numericButton4.setOnClickListener(listener);
        numericButton5.setOnClickListener(listener);
        numericButton6.setOnClickListener(listener);
        numericButton7.setOnClickListener(listener);
        numericButton8.setOnClickListener(listener);
        numericButton9.setOnClickListener(listener);
        numericButton0.setOnClickListener(listener);

        // Special case for numericButton00
        numericButton00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the current input is "0"
                if (!currentInput.equals("0")) {
                    currentInput += "00";
                    resultTextView.setText(currentInput);
                }
            }
        });
    }


    private void setOperatorButtonListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatButton button = (AppCompatButton) view;
                operator = button.getText().toString();
                firstOperand = Double.parseDouble(currentInput);
                currentInput = "";
            }
        };

        operatorButtonPlus.setOnClickListener(listener);
        operatorButtonMinus.setOnClickListener(listener);
        operatorButtonMultiply.setOnClickListener(listener);
        operatorButtonDivision.setOnClickListener(listener);
        operatorButtonPercentage.setOnClickListener(listener);
    }

    private void setFunctionalButtonListeners() {
        functionButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentInput = "";
                resultTextView.setText("0");
            }
        });

        functionButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentInput.length() > 0) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    resultTextView.setText(currentInput);
                }
            }
        });

        arithmeticButtonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double secondOperand = Double.parseDouble(currentInput);
                double result = 0;

                switch (operator) {
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                    case "x":
                        result = firstOperand * secondOperand;
                        break;
                    case "/":
                        if (secondOperand != 0) {
                            result = firstOperand / secondOperand;
                        } else {
                            resultTextView.setText("Error");
                            return;
                        }
                        break;
                    case "%":
                        result = firstOperand % secondOperand;
                        break;
                }

                resultTextView.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
            }
        });

        functionalButtonDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentInput.contains(".")) {
                    currentInput += ".";
                    resultTextView.setText(currentInput);
                }
            }
        });
    }

}