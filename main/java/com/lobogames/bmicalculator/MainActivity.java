package com.lobogames.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Variables de tipo "Class" o "Fields"
    private TextView resultadoEditText;
    private Button calcularButton;
    private RadioButton hombreButton;
    private RadioButton mujerButton;
    private EditText edadEditText;
    private EditText metrosEditText;
    private EditText centimetrosEditText;
    private EditText pesoEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setupButtonClickListener();
    }

    // declaramos e inicializamos todas variables/objs con los "id" de las Views
    private void findViews(){
        hombreButton = findViewById(R.id.radio_button_hombre);
        mujerButton = findViewById(R.id.radio_button_mujer);
        edadEditText = findViewById(R.id.edit_text_edad);
        metrosEditText = findViewById(R.id.edit_text_metros);
        centimetrosEditText = findViewById(R.id.edit_text_centimetros);
        pesoEditText = findViewById(R.id.edit_text_peso);
        calcularButton = findViewById(R.id.button_calcular);
        resultadoEditText = findViewById(R.id.text_view_resultado);
    }

    private void setupButtonClickListener() {
        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double bmiFinal = calculateBmi();

                String edadText = edadEditText.getText().toString();
                int edad = Integer.parseInt(edadText);

                if(edad >= 18){
                    displayResult(bmiFinal);
                }else{
                    mostrarConsejo(bmiFinal);
                }
            }
        });
    }


    private double calculateBmi() {
        String metrosText = metrosEditText.getText().toString();
        String centimetrosText = centimetrosEditText.getText().toString();
        String pesoText = pesoEditText.getText().toString();

        int metros = Integer.parseInt(metrosText);
        int centimetros = Integer.parseInt(centimetrosText);
        int peso = Integer.parseInt(pesoText);

        // Sumamos los metros con los centimetros convertidos en metros para obtener altura total en metros
        double totalAlturaMetros = metros + (centimetros * 0.01);

        // Calculo final del índice de masa corporal (imc | bmi)
        return peso / (totalAlturaMetros * totalAlturaMetros);
    }

    private void displayResult(double bmiParam){
        // Para quitarle decimales al "double" de bmi, creamos un obj de tipo DecimalFormat para darle un nuevo formato
        // Convierte el valor "double" de la variable "bmi" en String para poder asignarla al EditText de resultado
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmiParam);

        //Estructura condicional para saber que mensaje mostrar en base al imc arrojado
        String fullResultString;

        if(bmiParam < 18.5){
            fullResultString = "IMC: " + bmiTextResult + " - Tu peso actual es bajo";
        }else if(bmiParam > 25){
            fullResultString = "IMC: " + bmiTextResult + " - Tenés sobrepeso";
        }else{
            fullResultString = "IMC: " + bmiTextResult + " - Tu peso actual es saludable";
        }

        resultadoEditText.setText(fullResultString);
    }

    private void mostrarConsejo(double bmiParam2) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmiParam2);

        String fullResultString;

        if(hombreButton.isChecked()){
            fullResultString = "IMC: " + bmiTextResult + " - Como sos menor de edad, por favor consultá con tu médico el rango saludable para hombres";

        }else if(mujerButton.isChecked()){
            fullResultString = "IMC: " + bmiTextResult + " - Como sos menor de edad, por favor consultá con tu médico el rango saludable para mujeres";

        }else{
            fullResultString = "IMC: " + bmiTextResult + " - Como sos menor de edad, por favor consultá con tu médico el rango saludable";
        }

        resultadoEditText.setText(fullResultString);
    }


}


