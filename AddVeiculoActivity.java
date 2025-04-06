package com.example.myava2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myava2.R;

public class AddVeiculoActivity extends Activity {
    private VeiculoRepository veiculoRepository;
    private EditText modeloEditText, marcaEditText, anoEditText, quilometragemEditText, categoriaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_veiculo);

        veiculoRepository = new VeiculoRepository(this);

        modeloEditText = findViewById(R.id.modeloEditText);
        marcaEditText = findViewById(R.id.marcaEditText);
        anoEditText = findViewById(R.id.anoEditText);
        quilometragemEditText = findViewById(R.id.quilometragemEditText);
        categoriaEditText = findViewById(R.id.categoriaEditText);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modelo = modeloEditText.getText().toString();
                String marca = marcaEditText.getText().toString();
                double ano = 0;
                int quilometragem = 0;

                try {
                    ano = Double.parseDouble(anoEditText.getText().toString());
                    quilometragem = Integer.parseInt(quilometragemEditText.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(AddVeiculoActivity.this, "Preço ou Quantidade inválidos", Toast.LENGTH_SHORT).show();
                    return;
                }

                String categoria = categoriaEditText.getText().toString();

                veiculoRepository.createVeiculo(modelo, marca, ano, quilometragem, categoria);
                Toast.makeText(AddVeiculoActivity.this, "Produto adicionado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        veiculoRepository.close();
        super.onDestroy();
    }
}