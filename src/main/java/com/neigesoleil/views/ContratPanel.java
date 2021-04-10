package com.neigesoleil.views;

import com.neigesoleil.controllers.NeigeSoleil;
import com.neigesoleil.controllers.TableModel;
import com.neigesoleil.models.Contrat;
import com.neigesoleil.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ContratPanel extends JPanel implements ActionListener, MouseListener {

    private ArrayList<Contrat> allContrats = NeigeSoleil.getAllContrats();
    private ArrayList<User> allProprietaire = NeigeSoleil.getAllProprietaire();
    private Contrat editingContrat;
    private Boolean editStatus =false;
    private JPanel mainPanel = new JPanel();

    /***** DATA PANEL *****/
    private JPanel dataPanel = new JPanel();
    private JTable dataTable;
    private JScrollPane scrollPane;
    private TableModel tabModel;

    /***** FORM PANEL *****/
    private JPanel formPanel = new JPanel();
    private JButton btnValider = new JButton("Valider");
    private JButton btnAnnuler = new JButton("Annuler");
    private JLabel lbUser = new JLabel("Proprietaire");
    private JLabel lbNom = new JLabel("Nom");
    private JLabel lbAdresse = new JLabel("Adresse");
    private JLabel lbCodePostale = new JLabel("Code Postale");
    private JLabel lbVille = new JLabel("Ville");
    private JLabel lbDescription = new JLabel("Description");
    private JLabel lbType = new JLabel("Type");
    private JLabel lbExposition = new JLabel("Exposition");
    private JLabel lbSurfaceHabitable = new JLabel("Surface Habitable");
    private JLabel lbSurfaceBalcon = new JLabel("Surface Balcon");
    private JLabel lbCapacite = new JLabel("Capacite");
    private JLabel lbDistance = new JLabel("Distance des pistes");
    private JLabel lbStatus = new JLabel("Status");
    private JLabel lbPrixHaut = new JLabel("Prix Haut");
    private JLabel lbPrixMoyen = new JLabel("Prix Moyen");
    private JLabel lbPrixBas = new JLabel("Prix Bas");
    private JLabel lbDateDebut = new JLabel("Debut du contrat");
    private JLabel lbDateFin = new JLabel("Fin du contrat");

    private JComboBox cbxUserList = new JComboBox(this.getUserList());
    private JTextField txtNom = new JTextField();
    private JTextField txtAdresse = new JTextField();
    private JTextField txtCodePostale = new JTextField();
    private JTextField txtVille = new JTextField();
    private JTextArea areaDescription = new JTextArea();
    private String[] type = {"F1","F2","F3","F4","F5"};
    private JComboBox cbxType = new JComboBox(type);
    private String[] exposition = {"Sud", "Sud-est", "Sud-ouest", "Nord", "Nord-est", "Nord-ouest", "Est", "Ouest"};
    private JComboBox cbxExposition = new JComboBox(exposition);
    private JTextField txtSurfaceHabitable = new JTextField();
    private JTextField txtSurfaceBalcon = new JTextField();
    private JTextField txtCapacite = new JTextField();
    private JTextField txtDistancePistes = new JTextField();
    private String[] status = {"AVAIL", "EXPIRED", "CHECK"};
    private JComboBox cbxStatus = new JComboBox(status);
    private JTextField txtPrixHaut = new JTextField();
    private JTextField txtPrixMoyen = new JTextField();
    private JTextField txtPrixBas = new JTextField();
    private JTextField txtDateDebut = new JTextField("YYYY-MM-DD");
    private JTextField txtDateFin = new JTextField("YYYY-MM-DD");

    /***** BOTTOM PANEL *****/
    private JPanel southPanel = new JPanel();
    private JButton btnConfirmer = new JButton("Confirmer le contrat");
    private JButton btnModifier = new JButton("Modifier");
    private JButton btnSupprimer = new JButton("Supprimer");

    public ContratPanel() {
        this.setLayout(new BorderLayout());
        this.mainPanel.setLayout(new GridLayout(2,1));

        /***** FORM PANEL *****/
        this.addFormPanel();


        /***** DATA PANEL *****/
        this.dataPanel.setLayout(new BorderLayout());
        this.dataPanel.setOpaque(true);
        String header[] = {"Id", "Proprietaire", "Nom", "Adresse", "Type","Prix Moyen","Status", "Debut du contrat", "Fin du Contrat", "Proposé le"};
        this.tabModel = new TableModel(this.setData(), header);
        this.dataTable = new JTable(this.tabModel);
        this.dataTable.getTableHeader().setBackground(Color.WHITE);
        this.scrollPane = new JScrollPane(this.dataTable);
        this.dataPanel.add(this.scrollPane);
        this.mainPanel.add(this.dataPanel);

        /***** SOUTH PANEL *****/
        this.southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.southPanel.add(btnConfirmer);
        this.southPanel.add(btnModifier);
        this.southPanel.add(btnSupprimer);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.PAGE_END);

        this.btnAnnuler.addActionListener(this);
        this.btnConfirmer.addActionListener(this);
        this.btnModifier.addActionListener(this);
        this.btnValider.addActionListener(this);
        this.btnSupprimer.addActionListener(this);
        this.dataTable.addMouseListener(this);
    }

    public void addFormPanel(){
        this.formPanel.setLayout(new GridLayout(7,6));
        this.formPanel.add(lbUser);
        this.formPanel.add(cbxUserList);
        this.formPanel.add(lbNom);
        this.formPanel.add(txtNom);
        this.formPanel.add(lbAdresse);
        this.formPanel.add(txtAdresse);
        this.formPanel.add(lbCodePostale);
        this.formPanel.add(txtCodePostale);
        this.formPanel.add(lbVille);
        this.formPanel.add(txtVille);
        this.formPanel.add(lbDescription);
        this.formPanel.add(areaDescription);
        this.formPanel.add(lbType);
        this.formPanel.add(cbxType);
        this.formPanel.add(lbExposition);
        this.formPanel.add(cbxExposition);
        this.formPanel.add(lbSurfaceHabitable);
        this.formPanel.add(txtSurfaceHabitable);
        this.formPanel.add(lbSurfaceBalcon);
        this.formPanel.add(txtSurfaceBalcon);
        this.formPanel.add(lbCapacite);
        this.formPanel.add(txtCapacite);
        this.formPanel.add(lbDistance);
        this.formPanel.add(txtDistancePistes);
        this.formPanel.add(lbStatus);
        this.formPanel.add(cbxStatus);
        this.formPanel.add(lbPrixHaut);
        this.formPanel.add(txtPrixHaut);
        this.formPanel.add(lbPrixMoyen);
        this.formPanel.add(txtPrixMoyen);
        this.formPanel.add(lbPrixBas);
        this.formPanel.add(txtPrixBas);
        this.formPanel.add(lbDateDebut);
        this.formPanel.add(txtDateDebut);
        this.formPanel.add(lbDateFin);
        this.formPanel.add(txtDateFin);
        this.formPanel.add(btnAnnuler);
        this.formPanel.add(btnValider);
        this.mainPanel.add(this.formPanel);
    }

    public void refresh(){
        this.allProprietaire = NeigeSoleil.getAllProprietaire();
        this.allContrats = NeigeSoleil.getAllContrats();
        this.tabModel.setDonnees(this.setData());
        this.cleanUserField();
    }

    public void editContrat() {
        this.btnValider.setText("Modifier");
        int num = this.dataTable.getSelectedRow();
        int idContrat = Integer.parseInt(this.dataTable.getValueAt(num, 0).toString());
        this.editingContrat = NeigeSoleil.getContrat(idContrat);
        this.editStatus = true;
        if(this.editingContrat != null) {
            this.cbxUserList.setSelectedItem(this.editingContrat.getUser());
            this.txtNom.setText(this.editingContrat.getNom());
            this.txtAdresse.setText(this.editingContrat.getAdresse());
            this.txtCodePostale.setText(this.editingContrat.getCode_postale());
            this.txtVille.setText(this.editingContrat.getVille());
            this.areaDescription.setText(this.editingContrat.getDescription());
            this.cbxType.setSelectedItem(this.editingContrat.getType());
            this.cbxExposition.setSelectedItem(this.editingContrat.getExposition());
            this.txtSurfaceHabitable.setText(String.valueOf(this.editingContrat.getSurface_habitable()));
            this.txtSurfaceBalcon.setText(String.valueOf(this.editingContrat.getSurface_balcon()));
            this.txtCapacite.setText(String.valueOf(this.editingContrat.getCapacite()));
            this.txtDistancePistes.setText(String.valueOf(this.editingContrat.getDistance_pistes()));
            this.cbxStatus.setSelectedItem(this.editingContrat.getStatus());
            this.txtPrixHaut.setText(String.valueOf(this.editingContrat.getPrix_saison_haute()));
            this.txtPrixMoyen.setText(String.valueOf(this.editingContrat.getPrix_saison_moyenne()));
            this.txtPrixBas.setText(String.valueOf(this.editingContrat.getPrix_saison_basse()));
            this.txtDateDebut.setText(this.editingContrat.getDate_debut());
            this.txtDateFin.setText(this.editingContrat.getDate_fin());
        }
    }

    public void validateEdit(){
        if(this.editingContrat != null){
            this.editingContrat.setNom(this.txtNom.getText());
            this.editingContrat.setAdresse(this.txtAdresse.getText());
            this.editingContrat.setCode_postale(this.txtCodePostale.getText());
            this.editingContrat.setVille(this.txtVille.getText());
            this.editingContrat.setDescription(this.areaDescription.getText());
            this.editingContrat.setType((String) this.cbxType.getSelectedItem());
            this.editingContrat.setExposition((String)this.cbxExposition.getSelectedItem());
            this.editingContrat.setSurface_habitable(Float.parseFloat(this.txtSurfaceHabitable.getText()));
            this.editingContrat.setSurface_balcon(Float.parseFloat(this.txtSurfaceBalcon.getText()));
            this.editingContrat.setCapacite(Integer.parseInt(this.txtCapacite.getText()));
            this.editingContrat.setDistance_pistes(Float.parseFloat(this.txtDistancePistes.getText()));
            this.editingContrat.setStatus((String) this.cbxStatus.getSelectedItem());
            this.editingContrat.setPrix_saison_haute(Float.parseFloat(this.txtPrixHaut.getText()));
            this.editingContrat.setPrix_saison_moyenne(Float.parseFloat(this.txtPrixMoyen.getText()));
            this.editingContrat.setPrix_saison_basse(Float.parseFloat(this.txtPrixBas.getText()));
            this.editingContrat.setDate_debut(this.txtDateDebut.getText());
            this.editingContrat.setDate_fin(this.txtDateFin.getText());

            if(NeigeSoleil.updateContrat(this.editingContrat)){
                JOptionPane.showMessageDialog(this, "Modification reussie !");
                this.refresh();
            } else {
                JOptionPane.showMessageDialog(this, "Echec de la modification!");
            }
        }
    }

    public Object[][] setData(){
        Object [][] arr = new Object [this.allContrats.size()][10];
        int i =0;
        for(Contrat unContrat : this.allContrats){
            arr[i][0] = unContrat.getId();
            arr[i][1] = NeigeSoleil.getUser(unContrat.getUser()).getUsername();
            arr[i][2] = unContrat.getNom();
            arr[i][3] = unContrat.getAdresse() + ", " + unContrat.getCode_postale() + unContrat.getVille();
            arr[i][4] = unContrat.getType();
            arr[i][5] = unContrat.getPrix_saison_moyenne();
            arr[i][6] = unContrat.getStatus();
            arr[i][7] = unContrat.getDate_debut();
            arr[i][8] = unContrat.getDate_fin();
            arr[i][9] = unContrat.getDate_creation();
            i++;
        }
        return arr;
    }

    public String[] getUserList(){
        String[] userList = new String[this.allProprietaire.size()];
        for(int i=0; i<userList.length; i++){
            userList[i] = String.valueOf(this.allProprietaire.get(i).getId());
        }
        return userList;
    }

    public void cleanUserField() {
        this.txtAdresse.setText("");
        this.txtNom.setText("");
        this.txtCodePostale.setText("");
        this.txtVille.setText("");
        this.areaDescription.setText("");
        this.txtSurfaceHabitable.setText("");
        this.txtSurfaceBalcon.setText("");
        this.txtCapacite.setText("");
        this.txtDistancePistes.setText("");
        this.txtPrixHaut.setText("");
        this.txtPrixMoyen.setText("");
        this.txtPrixBas.setText("");
        this.txtDateDebut.setText("YYYY-MM-DD");
        this.txtDateFin.setText("YYYY-MM-DD");

        this.editStatus = false;
        this.btnValider.setText("Ajouter");
    }

    public void ajouterContrat() throws Exception {
        Contrat unContrat = new Contrat(
                Integer.parseInt((String) this.cbxUserList.getSelectedItem()),
                this.txtNom.getText(),
                this.txtAdresse.getText(),
                this.txtCodePostale.getText(),
                this.txtVille.getText(),
                this.areaDescription.getText(),
                (String) this.cbxType.getSelectedItem(),
                (String) this.cbxExposition.getSelectedItem(),
                Float.parseFloat(this.txtSurfaceHabitable.getText()),
                Float.parseFloat(this.txtSurfaceBalcon.getText()),
                Integer.parseInt(this.txtCapacite.getText()),
                Float.parseFloat(this.txtDistancePistes.getText()),
                (String) this.cbxStatus.getSelectedItem(),
                Float.parseFloat(this.txtPrixHaut.getText()),
                Float.parseFloat(this.txtPrixMoyen.getText()),
                Float.parseFloat(this.txtPrixBas.getText()),
                this.txtDateDebut.getText(),
                this.txtDateFin.getText()
                );
        if(NeigeSoleil.addContrat(unContrat)){
            JOptionPane.showMessageDialog(this, "Succes de l'ajout");
            this.refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Echec de l'ajout");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnValider){
            if(this.editStatus){
                this.validateEdit();
            } else {
                try {
                    this.ajouterContrat();
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(this, "Echec de l'ajout");
                }
            }

        }
        if (e.getSource()==btnAnnuler){
            this.cleanUserField();
        }
        if (e.getSource()==btnModifier){
            this.editContrat();
        }
        if (e.getSource()==btnConfirmer){
            int num = this.dataTable.getSelectedRow();
            int idContrat = Integer.parseInt(this.dataTable.getValueAt(num, 0).toString());
            Contrat unContrat = NeigeSoleil.getContrat(idContrat);
            if(unContrat.getStatus().equals("AVAIL")){
                JOptionPane.showMessageDialog(this,"Ce contrat est deja confirmer");
            } else {
                unContrat.setStatus("AVAIL");
                NeigeSoleil.updateContrat(unContrat);
                JOptionPane.showMessageDialog(this,"Confirmation reussie");
                this.refresh();
            }
        }
        if (e.getSource()==btnSupprimer){
            int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce contrat ?",
                    "Supression du contrat", JOptionPane.YES_NO_OPTION);
            if (retour == 0)
            {
                int num = this.dataTable.getSelectedRow();
                int idContrat = Integer.parseInt(this.dataTable.getValueAt(num, 0).toString());
                NeigeSoleil.DeleteContrat(idContrat);
                this.refresh();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount()==2){
            this.editContrat();
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
