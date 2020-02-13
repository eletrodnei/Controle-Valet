/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodnei_miranda.negocio;

import com.rodnei_miranda.bean.Valet;
import com.rodnei_miranda.dao.ValetDAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rodnei Miranda da Silva – Polo Mauá - 297297
 */
public class ModeloValet extends AbstractTableModel {

    private List<Valet> valets = new ArrayList<>();
    private final DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final ValetDAO dao = new ValetDAO();

    public ModeloValet() {
        this.valets = dao.consultarTodos();
    }

    @Override
    public int getRowCount() {
        return valets.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Modelo";
            case 1:
                return "Placa";
            case 2:
                return "Data de Entrada";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int indiceLinha, int indiceColuna) {
        Valet v = this.valets.get(indiceLinha);
        switch (indiceColuna) {
            case 0:
                return v.getModelo();
            case 1:
                return v.getPlaca();
            case 2:
                return df.format(v.getEntrada());
            default:
                return null;
        }
    }

    public void adicionar(Valet v) {
        v = dao.salvar(v);
        this.valets.add(v);
        fireTableRowsInserted(this.valets.size() - 1, this.valets.size() - 1);
    }

    public void remover(Valet v) {
        dao.atualizar(v);
        this.valets.remove(v);
        fireTableRowsInserted(this.valets.size() - 1, this.valets.size() - 1);

    }

    public Valet getValet(int indice) {
        return this.valets.get(indice);
    }

}
