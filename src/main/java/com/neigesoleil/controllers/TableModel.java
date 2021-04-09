package com.neigesoleil.controllers;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String entetes [];
    private Object donnees [][] ;

    public TableModel(Object donnees [][], String entetes []) {
        this.donnees = donnees;
        this.entetes = entetes;
    }

    @Override
    public int getRowCount() {
        return this.donnees.length; // Retourne le nombre de ligne
    }

    @Override
    public int getColumnCount() {
        return this.entetes.length; // Retourne le nombre de colonnes
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.donnees[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return this.entetes[column]; // Retourne le nom d'une colonne
    }

    public void ajouterLigne(Object ligne []) {
        Object [][] matrice = new Object[this.donnees.length+1][this.entetes.length];
        int i=0;
        for(i=0; i<this.donnees.length; i++) {
            matrice[i] = this.donnees[i];
        }
        matrice [this.donnees.length] = ligne;
        //actualiser
        this.setDonnees(matrice);
    }

    public void supprimerLigne(int numero) {
        Object [][] matrice = new Object[this.donnees.length-1][this.entetes.length];
        int i=0, j=0;
        for(i=0; i<this.donnees.length; i++) {
            if(i!=numero) {
                matrice[j] = this.donnees[i];
                j++;
            }
        }
        //actualiser
        this.setDonnees(matrice);
    }

    public void modifierLigne(int numero, Object ligne []) {
        Object [][] matrice = new Object[this.donnees.length][this.entetes.length];
        int i=0, j=0;
        for(i=0; i<this.donnees.length; i++) {
            if(i!=numero) {
                matrice[j] = this.donnees[i];
                j++;
            }else {
                matrice[j] = ligne;
                j++;
            }
        }
        //actualiser
        this.setDonnees(matrice);
    }

    public void setDonnees (Object matrice[][])
    {
        this.donnees = matrice;
        this.fireTableDataChanged(); //on actualise l'affichage
    }

}
