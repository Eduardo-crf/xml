package com.example.myava2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myava2.R;

public class EditVeiculoActivity extends Activity {
    private VeiculoRepository veiculoRepository;
    private long veiculoId;
    private EditText modeloEditText, marcaEditText, anoEditText, quilometragemEditText, categoriaEditText;
    private Veiculo veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_veiculo);

        veiculoRepository = new VeiculoRepository(this);

        veiculoId = getIntent().getLongExtra("veiculoId", 0);
        veiculo = veiculoRepository.getVeiculoById(veiculoId);

        modeloEditText = findViewById(R.id.modeloEditText);
        marcaEditText = findViewById(R.id.marcaEditText);
        anoEditText = findViewById(R.id.anoEditText);
        quilometragemEditText = findViewById(R.id.quilometragemEditText);
        categoriaEditText = findViewById(R.id.categoriaEditText);

        modeloEditText.setText(veiculo.getName());
        marcaEditText.setText(veiculo.getDescription());
        anoEditText.setText(String.valueOf(veiculo.getPrice()));
        quilometragemEditText.setText(String.valueOf(veiculo.getQuantity()));
        categoriaEditText.setText(veiculo.getCategory());

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veiculo.setName(modeloEditText.getText().toString());
                veiculo.setDescription(marcaEditText.getText().toString());
                veiculo.setPrice(Double.parseDouble(anoEditText.getText().toString()));
                veiculo.setQuantity(Integer.parseInt(quilometragemEditText.getText().toString()));
                veiculo.setCategory(categoriaEditText.getText().toString());

                veiculoRepository.updateVeiculo(veiculo);
                finish();
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veiculoRepository.deleteVeiculo(veiculoId);
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