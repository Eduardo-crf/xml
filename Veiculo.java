package com.example.myava2;

public class Veiculo {
    private long id;
    private String modelo;
    private String marca;
    private double ano;
    private int quilometragem;
    private String categoria;

    // Constructor
    public Veiculo(long id, String modelo, String marca, double ano, int quilometragem, String categoria) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.quilometragem = quilometragem;
        this.categoria = categoria;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return modelo;
    }

    public void setName(String modelo) {
        this.modelo = modelo;
    }

    public String getDescription() {
        return marca;
    }

    public void setDescription(String marca) {
        this.marca = marca;
    }

    public double getPrice() {
        return ano;
    }

    public void setPrice(double ano) {
        this.ano = ano;
    }

    public int getQuantity() {
        return quilometragem;
    }

    public void setQuantity(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getCategory() {
        return categoria;
    }

    public void setCategory(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return modelo + " - " + categoria;
    }
}