package com.neigesoleil.views;

import com.neigesoleil.controllers.NeigeSoleil;
import com.neigesoleil.controllers.TableModel;
import com.neigesoleil.models.Contrat;
import com.neigesoleil.models.Reservation;
import com.neigesoleil.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ReservationPanel extends JPanel implements ActionListener, MouseListener {

    private ArrayList<Reservation> allReservations = NeigeSoleil.getAllReservation();
    private ArrayList<User> allUser = NeigeSoleil.getAllUsers();
    private ArrayList<Contrat> allContrat = NeigeSoleil.getAllContrats();
    private Reservation editingReservation;
    private Boolean editStatus = false;

    private JPanel reservationPanel = new JPanel();
    private JPanel resOptionPanel = new JPanel();
    private JPanel resFormPanel = new JPanel();
    private JButton btnModifier = new JButton("Modifier");
    private JButton btnConfirmer = new JButton("Confirmer la reservation");
    private JButton btnAnnuler = new JButton("Annuler la reservation");
    private JButton btnSupprimer = new JButton("Supprimer la reservation");

    private JPanel locationPanel = new JPanel();

    /**** RESERVATION FORM ****/
    private JLabel lbUser = new JLabel("Utilisateur");
    private JLabel lbPropriete = new JLabel("Propriete");
    private JLabel lbDateDebut = new JLabel("Debut du sejour");
    private JLabel lbFinSejour = new JLabel("Fin du sejour");
    private JLabel lbStatus = new JLabel("Status");

    private JComboBox cbxUser = new JComboBox(this.getUserList());
    private JComboBox cbxPropriete = new JComboBox(this.getProprieteList());
    String[] statusList = {"WAIT", "LOCATION", "CANCEL"};
    private JComboBox cbxStatus = new JComboBox(statusList);
    private JTextField txtDateDebut = new JTextField("YYYY-MM-DD");
    private JTextField txtDateFin = new JTextField("YYYY-MM-DD");
    private JButton btnValider = new JButton("Valider");
    private JButton btnAbandonner = new JButton("Abandonner");


    /**** RESERVATION DATA ****/
    private JPanel resDataPanel = new JPanel();
    private JTable resDataTable;
    private JScrollPane reScrollPane;
    private TableModel resTabModel;

    private JPanel multipleDataPanel = new JPanel();


    public ReservationPanel(){

        this.setLayout(new GridLayout(2,1));

        /**** RESERVATION OPTION BUTTON ****/
        this.reservationPanel.setLayout(new BorderLayout());
        this.resOptionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.resOptionPanel.add(this.btnConfirmer);
        this.resOptionPanel.add(this.btnModifier);
        this.resOptionPanel.add(this.btnAnnuler);
        this.resOptionPanel.add(this.btnConfirmer);
        this.reservationPanel.add(resOptionPanel, BorderLayout.PAGE_START);

        /**** RESERVATION FORM PANEL ****/
        this.resFormPanel.setLayout(new GridLayout(6,2));
        this.resFormPanel.add(lbUser);
        this.resFormPanel.add(cbxUser);
        this.resFormPanel.add(lbPropriete);
        this.resFormPanel.add(cbxPropriete);
        this.resFormPanel.add(lbDateDebut);
        this.resFormPanel.add(txtDateDebut);
        this.resFormPanel.add(lbFinSejour);
        this.resFormPanel.add(txtDateFin);
        this.resFormPanel.add(lbStatus);
        this.resFormPanel.add(cbxStatus);
        this.resFormPanel.add(this.btnAbandonner);
        this.resFormPanel.add(this.btnValider);
        this.reservationPanel.add(this.resFormPanel, BorderLayout.LINE_START);


        /**** RESERVATION DATA ****/
        this.resDataPanel.setLayout(new BorderLayout());
        JLabel lbReservations = new JLabel("LES RESERVATIONS EN ATTENTES");
        this.resDataPanel.setOpaque(true);
        String headerRes[] = {"Id", "Utilisateur", "Contrat", "Status", "Date", "Debut du sejour", "Fin du sejour"};
        this.resTabModel = new TableModel(this.setResData(), headerRes);
        this.resDataTable = new JTable(this.resTabModel);
        this.resDataTable.getTableHeader().setBackground(Color.WHITE);
        this.reScrollPane = new JScrollPane(this.resDataTable);
        this.resDataPanel.add(this.reScrollPane);
        this.resDataPanel.add(lbReservations, BorderLayout.PAGE_START);
        this.reservationPanel.add(resDataPanel);

        this.locationPanel.setLayout(new BorderLayout());
        this.multipleDataPanel.setLayout(new GridLayout(1,1));

        this.btnAbandonner.addActionListener(this);
        this.btnAnnuler.addActionListener(this);
        this.btnConfirmer.addActionListener(this);
        this.btnModifier.addActionListener(this);
        this.btnValider.addActionListener(this);
        this.resDataTable.addMouseListener(this);

        this.add(reservationPanel);
        this.add(multipleDataPanel);

    }

    public void refresh() {
        this.allReservations = NeigeSoleil.getAllReservation();
        this.allContrat = NeigeSoleil.getAllContrats();
        this.allUser = NeigeSoleil.getAllUsers();
        this.resTabModel.setDonnees(this.setResData());
        this.cleanResField();
    }

    public void cleanResField() {
        this.txtDateDebut.setText("");
        this.txtDateFin.setText("");
        this.editStatus = false;
        this.btnValider.setText("Valider");
    }

    public String[] getUserList(){
        String[] userList = new String[this.allUser.size()];
        for(int i=0; i<userList.length; i++){
            userList[i] = String.valueOf(this.allUser.get(i).getId());
            System.out.println(userList[i]);
        }
        return userList;
    }

    public String[] getProprieteList(){
        String[] proprieteList = new String[this.allContrat.size()];
        for(int i=0; i<proprieteList.length; i++){
            proprieteList[i] = String.valueOf(this.allContrat.get(i).getId());
        }
        return proprieteList;
    }

    public Object [][] setResData(){
        Object [][] arr = new Object [this.allReservations.size()][7];
        int i =0;
        for(Reservation uneReservation : this.allReservations){
            this.setData(uneReservation, arr, i);
            i++;
        }
        return arr;
    }


    public void setData(Reservation uneReservation, Object[][] arr, int i){
        arr[i][0] = uneReservation.getId();
        arr[i][1] = NeigeSoleil.getUser(uneReservation.getUser()).getFirst_name();
        arr[i][2] = NeigeSoleil.getContrat(uneReservation.getPropriete()).getNom();
        arr[i][3] = uneReservation.getStatus_reservation();
        arr[i][4] = uneReservation.getDate_reservation();
        arr[i][5] = uneReservation.getDate_debut_sejour();
        arr[i][6] = uneReservation.getDate_fin_sejour();
    }

    public void ajouterReservation() {
        Reservation uneReservation = new Reservation(
                Integer.parseInt((String) this.cbxUser.getSelectedItem()),
                Integer.parseInt((String) this.cbxPropriete.getSelectedItem()),
                (String) this.cbxStatus.getSelectedItem(),
                this.txtDateDebut.getText(),
                this.txtDateFin.getText());
        if(NeigeSoleil.addReservation(uneReservation)){
            JOptionPane.showMessageDialog(this, "Succes de la nouvelle reservation");
            this.refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Echec de l'ajout, verifiez les informations");
        }
        this.cleanResField();
    }

    public void editReservation() {
        this.btnValider.setText("Modifier");
        int num = this.resDataTable.getSelectedRow();
        int idReservation = Integer.parseInt(this.resDataTable.getValueAt(num, 0).toString());
        this.editingReservation = NeigeSoleil.getReservation(idReservation);
        if(this.editingReservation != null) {
            this.cbxUser.setSelectedItem(String.valueOf(this.editingReservation.getUser()));
            this.cbxPropriete.setSelectedItem(String.valueOf(this.editingReservation.getPropriete()));
            this.cbxStatus.setSelectedItem(this.editingReservation.getStatus_reservation());
            this.txtDateDebut.setText(this.editingReservation.getDate_debut_sejour());
            this.txtDateFin.setText(this.editingReservation.getDate_fin_sejour());
            this.editStatus = true;
        }
    }

    public void validateEdit(){
        if(this.editingReservation != null){
            this.editingReservation.setUser(Integer.parseInt((String) this.cbxUser.getSelectedItem()));
            this.editingReservation.setPropriete(Integer.parseInt((String) this.cbxPropriete.getSelectedItem()));
            this.editingReservation.setStatus_reservation((String) this.cbxStatus.getSelectedItem());
            this.editingReservation.setDate_debut_sejour(this.txtDateDebut.getText());
            this.editingReservation.setDate_fin_sejour(this.txtDateFin.getText());
            if(NeigeSoleil.updateReservation(this.editingReservation)){
                JOptionPane.showMessageDialog(this, "Modification reussie !");
            } else {
                JOptionPane.showMessageDialog(this, "Echec de la modification!");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnAbandonner){
            this.cleanResField();
        }
        if(e.getSource()==btnAnnuler){

        }
        if (e.getSource()==btnConfirmer){

        }
        if (e.getSource()==btnModifier){
            this.editReservation();
        }
        if (e.getSource()==btnValider){
            if(this.editStatus){
                this.validateEdit();
                this.refresh();
            } else {
                this.ajouterReservation();
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            this.editReservation();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
