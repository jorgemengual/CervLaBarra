package com.example.applabarra.menu.tienda;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.applabarra.R;
import com.google.android.material.textfield.TextInputEditText;

public class PaymentActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioEfectivo, radioTarjeta;
    private Button btnConfirmarPago;
    private LinearLayout cardDetailsContainer;
    private TextInputEditText etCardNumber, etExpiry, etCVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        radioGroup = findViewById(R.id.radioGroupPago);
        radioEfectivo = findViewById(R.id.radioEfectivo);
        radioTarjeta = findViewById(R.id.radioTarjeta);
        btnConfirmarPago = findViewById(R.id.btnConfirmarPago);

        cardDetailsContainer = findViewById(R.id.cardDetailsContainer);
        etCardNumber = findViewById(R.id.etCardNumber);
        etExpiry = findViewById(R.id.etExpiry);
        etCVV = findViewById(R.id.etCVV);

        // Inicialmente ocultamos el contenedor de datos de tarjeta
        cardDetailsContainer.setVisibility(LinearLayout.GONE);

        // Mostrar/ocultar el formulario de tarjeta según la opción seleccionada
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioTarjeta) {
                cardDetailsContainer.setVisibility(LinearLayout.VISIBLE);
            } else {
                cardDetailsContainer.setVisibility(LinearLayout.GONE);
            }
        });

        btnConfirmarPago.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(PaymentActivity.this, "Selecciona una opción de pago", Toast.LENGTH_SHORT).show();
            } else {
                String metodoPago;
                if (selectedId == R.id.radioEfectivo) {
                    metodoPago = "efectivo en el bar";
                } else {
                    // Validar que se hayan introducido los datos de la tarjeta
                    String cardNumber = etCardNumber.getText().toString().trim();
                    String expiry = etExpiry.getText().toString().trim();
                    String cvv = etCVV.getText().toString().trim();
                    if (TextUtils.isEmpty(cardNumber) || TextUtils.isEmpty(expiry) || TextUtils.isEmpty(cvv)) {
                        Toast.makeText(PaymentActivity.this, "Por favor, completa los detalles de la tarjeta", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    metodoPago = "tarjeta en la app";
                }
                Toast.makeText(PaymentActivity.this,
                        "Pedido realizado. En dos días laborales tendrás tu pedido listo para recoger.",
                        Toast.LENGTH_LONG).show();
                // Limpia el carrito y regresa a la TiendaActivity
                CarritoCompras.getInstance().clear();
                startActivity(new Intent(PaymentActivity.this, TiendaActivity.class));
                finish();
            }
        });
    }
}

