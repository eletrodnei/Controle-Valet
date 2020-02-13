/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodnei_miranda.bean;

import java.util.Date;

/**
 *
 * @author Rodnei Miranda da Silva – Polo Mauá - 297297
 * @author Rodnei
 */
public class Valet {

    private long id;
    private String modelo;
    private String placa;
    private Date entrada;
    private Date saida;
    private double preco;

    public Valet(String modelo, String placa, Date entrada) {
        this.modelo = modelo;
        this.placa = placa;
        this.entrada = entrada;
    }

    public Valet(long id, String modelo, String placa, Date entrada, Date saida, double preco) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.entrada = entrada;
        this.saida = saida;
        this.preco = preco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
