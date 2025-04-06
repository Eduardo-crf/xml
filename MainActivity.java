package com.example.myava2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myava2.R;

import java.util.List;

public class MainActivity extends Activity {
    private VeiculoRepository veiculoRepository;
    private ListView listView;
    private ArrayAdapter<Veiculo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        veiculoRepository = new VeiculoRepository(this);
        listView = findViewById(R.id.listView);

        List<Veiculo> veiculos = veiculoRepository.getAllVeiculos();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, veiculos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Veiculo veiculo = (Veiculo) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this,EditVeiculoActivity.class);
                intent.putExtra("veiculoId", veiculo.getId());
                startActivity(intent);
            }
        });

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddVeiculoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(veiculoRepository.getAllVeiculos());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        veiculoRepository.close();
        super.onDestroy();
    }
}