package com.example.myava2;

import android.content.Context;

import java.util.List;

public class VeiculoRepository {
    private VeiculoDAO veiculoDAO;

    public VeiculoRepository(Context context) {
        veiculoDAO = new VeiculoDAO(context);
        veiculoDAO.open();
    }

    public void close() {
        veiculoDAO.close();
    }

    public Veiculo createVeiculo(String modelo, String marca, double ano, int quilometragem, String categoria) {
        return veiculoDAO.createVeiculo(modelo, marca, ano, quilometragem, categoria);
    }

    public void deleteVeiculo(long id) {
        veiculoDAO.deleteVeiculo(id);
    }

    public List<Veiculo> getAllVeiculos() {
        return veiculoDAO.getAllVeiculos();
    }

    public Veiculo getVeiculoById(long id) {
        List<Veiculo> veiculos = veiculoDAO.getAllVeiculos();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getId() == id) {
                return veiculo;
            }
        }
        return null;
    }

    public void updateVeiculo(Veiculo veiculo) {
        veiculoDAO.updateVeiculo(veiculo);
    }
}