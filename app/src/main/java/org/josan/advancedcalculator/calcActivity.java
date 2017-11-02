package org.josan.advancedcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class calcActivity extends AppCompatActivity implements View.OnClickListener {

    // Widgets
    private TextView resultTextView;
    private Button addButton;
    private Button subtractButton;
    private Button multiplyButton;
    private Button divButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button acButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button deleteButton;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button pointButton;
    private Button zeroButton;
    private Button signButton;
    private Button equalButton;

    // Datos
    private double mAccumulator;
    private char mOp;
    private boolean firstOperation;
    private boolean secondEquals;
    private double numberRepeat = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        connectWidgets();
        connectEventsToWidgets();

        initializeValues();
    }

    /**
     * Inicializa todos los atributos miembros de los non-widget.
     */
    private void initializeValues() {
        mAccumulator = 0;
        mOp = 0;
        resultTextView.setText("0");
    }

    /**
     * Settea OnClickListener en todos los botones de la interfaz.
     */
    private void connectEventsToWidgets() {
        addButton.setOnClickListener(this);
        subtractButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);
        divButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        signButton.setOnClickListener(this);
        equalButton.setOnClickListener(this);
        pointButton.setOnClickListener(this);
        acButton.setOnClickListener(this);
        zeroButton.setOnClickListener(this);
        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);
    }

    /**
     * Conecta todos los widgets a sus elementos de la interfaz correspondientes.
     */
    private void connectWidgets() {
        resultTextView = (TextView) findViewById(R.id.text_view_result);
        addButton = (Button) findViewById(R.id.button_add);
        subtractButton = (Button) findViewById(R.id.button_subtract);
        multiplyButton = (Button) findViewById(R.id.button_multiply);
        divButton = (Button) findViewById(R.id.button_DIV);
        sevenButton = (Button) findViewById(R.id.button_seven);
        eightButton = (Button) findViewById(R.id.button_eight);
        nineButton = (Button) findViewById(R.id.button_nine);
        acButton = (Button) findViewById(R.id.button_AC);
        fourButton = (Button) findViewById(R.id.button_four);
        fiveButton = (Button) findViewById(R.id.button_five);
        sixButton = (Button) findViewById(R.id.button_six);
        deleteButton = (Button) findViewById(R.id.button_delete);
        oneButton = (Button) findViewById(R.id.button_one);
        twoButton = (Button) findViewById(R.id.button_two);
        threeButton = (Button) findViewById(R.id.button_three);
        pointButton = (Button) findViewById(R.id.button_point);
        zeroButton = (Button) findViewById(R.id.button_zero);
        signButton = (Button) findViewById(R.id.button_sign);
        equalButton = (Button) findViewById(R.id.button_equal);
    }

    /**
     * Se llama cuando una vista ha sido clickeada.
     *
     * @param v La vista que ha sido clickeada.
     */
    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        switch (v.getId()) {
            case R.id.button_zero:
            case R.id.button_one:
            case R.id.button_two:
            case R.id.button_three:
            case R.id.button_four:
            case R.id.button_five:
            case R.id.button_six:
            case R.id.button_seven:
            case R.id.button_eight:
            case R.id.button_nine:
                readNumber(button);
                break;
            case R.id.button_add:
            case R.id.button_subtract:
            case R.id.button_multiply:
            case R.id.button_DIV:
                applyOperation(button);
                break;
            case R.id.button_AC:
                initializeValues();
                break;
            case R.id.button_sign:
                changeSign();
                break;
            case R.id.button_point:
                addPoint();
                break;
            case R.id.button_delete:
                deleteNumber();
                break;
            case R.id.button_equal:
                makeOperation();
                break;
            default:
                // Error
        }
    }

    /**
     * Hace la operación seleccionada.
     */
    private void makeOperation() {
        double secondNumber = Double.parseDouble(resultTextView.getText().toString());
        double total = 0;

        if (secondEquals){
            mAccumulator = numberRepeat;
        } else {
            numberRepeat = secondNumber;
        }

        switch (mOp) {
            case '+':
                total = mAccumulator + secondNumber;
                break;
            case '-':
                total = mAccumulator - secondNumber;
                break;
            case '*':
                total = mAccumulator * secondNumber;
                break;
            case '/':
                total = mAccumulator / secondNumber;
                break;
            default:
        }

        long totalInt = (long) total;

        if (total == (double) totalInt) {
            resultTextView.setText(totalInt + "");
        } else {
            resultTextView.setText(total + "");
        }
        secondEquals = true;

    }
    /**
     * Aplica la operación seleccionada.
     */
    private void applyOperation(Button button) {
        mOp = button.getText().toString().charAt(0);
        mAccumulator = Double.parseDouble(resultTextView.getText().toString());

        //resultTextView.setText("0");
        secondEquals = false;
        firstOperation = true;
    }

    /**
     * Elimina un número.
     */
    private void deleteNumber() {
        String actualNumber = resultTextView.getText().toString();

        if (actualNumber.length() == 1 || actualNumber.length() == 2 && Integer.parseInt(actualNumber) < 0) {
            resultTextView.setText("0");
        } else {
            if (actualNumber.charAt(actualNumber.length() - 2) != '.') {
                resultTextView.setText(
                        actualNumber.substring(0, actualNumber.length() - 1)
                );
            } else {
                resultTextView.setText(
                        actualNumber.substring(0, actualNumber.length() - 1)
                );
            }

        }
    }
    /**
     * Añade un punto al número.
     */
    private void addPoint() {
        if (resultTextView.getText().toString().indexOf('.') == -1) {
            resultTextView.setText(
                    resultTextView.getText().toString() + '.'
            );
        }
    }

    private void changeSign() {
        String actualNumber = resultTextView.getText().toString();

        if (!actualNumber.equals("0")) {
            if (actualNumber.charAt(0) == '-') {
                actualNumber = actualNumber.substring(1);
            } else {
                actualNumber = '-' + actualNumber;
            }
            resultTextView.setText(actualNumber);
        }
    }
    /**
     * Lee y añade el número seleccionado.
     */
    private void readNumber(Button button) {
        String digit = button.getText().toString();
        String actualNumber = resultTextView.getText().toString();
        boolean intNumber = (actualNumber.indexOf('.') == -1) ? true : false;

        Log.d("calcActivity", "mAccumulator = " + mAccumulator + " | mOp = " + mOp);


        if (firstOperation == true) {
            resultTextView.setText("0");
            resultTextView.setText(digit);
            firstOperation = false;

        } else {
            if (intNumber) {
                if (actualNumber.equals("0")) {
                    resultTextView.setText(digit);
                } else {
                    resultTextView.setText(
                            resultTextView.getText().toString() + digit
                    );
                }

                if (resultTextView.getText().toString().equals("0") && mOp == '/') {
                    equalButton.setEnabled(false);
                } else {
                    equalButton.setEnabled(true);
                }
            } else{
                resultTextView.setText(resultTextView.getText().toString() + digit);
            }
        }
    }
}