package com.neigesoleil.views;

import com.neigesoleil.controllers.NeigeSoleil;
import com.neigesoleil.controllers.TableModel;
import com.neigesoleil.models.Profile;
import com.neigesoleil.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class UserPanel extends JPanel implements ActionListener, MouseListener {

    ArrayList<User> allUsers = NeigeSoleil.getAllUsers();
    private User editingUser;
    private Boolean editStatus =false;

    private JLabel lbTitle = new JLabel("Neige et Soleil - Administration");
    /***** NORTH LAYOUT *****/
    private JPanel northPanel = new JPanel();
    private JButton btnModifier = new JButton("Modifier");
    private JButton btnSupprimer = new JButton("Supprimer");

    /***** DATA PANEL LAYOUT *****/
    private JPanel dataPanel = new JPanel();
    private JTable dataTable;
    private JScrollPane scrollPane;
    private TableModel tabModel;

    /***** SOUTH LAYOUT *****/
    private JPanel southPanel = new JPanel();

    /***** EDIT PANEL *****/
    private JPanel userForm = new JPanel();
    private JButton btnValider = new JButton("Ajouter");
    private JButton btnAnnuler = new JButton("Annuler");
    private JLabel lbNom = new JLabel("Nom : ");
    private JTextField txtNom = new JTextField();
    private JLabel lbPrenom = new JLabel("Prenom : ");
    private JTextField txtPrenom = new JTextField();
    private JLabel lbusername = new JLabel("Nom d'utilisateur : ");
    private JTextField txtUsername = new JTextField();
    private JLabel lbEmail = new JLabel("Email : ");
    private JTextField txtEmail = new JTextField();
    private JLabel lbProprietaire = new JLabel("Est proprietaire : ");
    private JCheckBox cProprietaire = new JCheckBox("Est un propriétaire");

    JButton btnEditPassword = new JButton("Changer le mot de passe");
    JButton btnProfile = new JButton("Profil");

    private JTextField txtAdresse = new JTextField();
    private JTextField txtCodePostale = new JTextField();
    private JTextField txtVille = new JTextField();
    private JTextField txtTelephone = new JTextField();
    private JTextField txtRib = new JTextField();
    JComponent [] profilInputs =  new JComponent [] {
            new JLabel("Adresse : "),
            this.txtAdresse,
            new JLabel("Code Postal : "),
            this.txtCodePostale,
            new JLabel("Ville : "),
            this.txtVille,
            new JLabel("Telephone : "),
            this.txtTelephone,
            new JLabel("RIB : "),
            this.txtRib
    };


    public UserPanel() {

        this.setLayout(new BorderLayout());

        /***** NORTH LAYOUT *****/
        this.btnModifier.setForeground(Color.DARK_GRAY);
        this.btnSupprimer.setForeground(Color.DARK_GRAY);
        this.northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.northPanel.add(this.btnModifier);
        this.northPanel.add(this.btnSupprimer);
        this.add(northPanel, BorderLayout.PAGE_START);

        /***** DATA PANEL LAYOUT *****/
        this.dataPanel.setLayout(new BorderLayout());
        this.dataPanel.setOpaque(true);
        String header[] = {"Id", "Nom d'utilisateur", "Nom", "Prenom", "Email", "Proprietaire"};
        this.tabModel = new TableModel(this.setData(), header);
        this.dataTable = new JTable(this.tabModel);
        this.dataTable.getTableHeader().setBackground(Color.WHITE);
        this.scrollPane = new JScrollPane(this.dataTable);
        this.dataPanel.add(this.scrollPane);
        this.add(this.dataPanel, BorderLayout.CENTER);

        /***** EDIT PANEL *****/
        this.btnProfile.setVisible(false);
        this.btnEditPassword.setVisible(false);

        this.userForm.setPreferredSize(new Dimension(250,600));
        this.userForm.setLayout(new GridLayout(15,1));
        this.userForm.add(this.lbusername);
        this.userForm.add(this.txtUsername);
        this.userForm.add(this.lbNom);
        this.userForm.add(this.txtNom);
        this.userForm.add(this.lbPrenom);
        this.userForm.add(this.txtPrenom);
        this.userForm.add(this.lbEmail);
        this.userForm.add(this.txtEmail);
        this.userForm.add(this.cProprietaire);
        this.userForm.add(btnAnnuler);
        this.userForm.add(btnValider);
        this.userForm.add(btnEditPassword);
        this.userForm.add(btnProfile);

        this.add(userForm, BorderLayout.LINE_START);

        this.btnValider.addActionListener(this);
        this.btnAnnuler.addActionListener(this);
        this.btnModifier.addActionListener(this);
        this.btnSupprimer.addActionListener(this);
        this.dataTable.addMouseListener(this);
        this.btnEditPassword.addActionListener(this);
        this.btnProfile.addActionListener(this);
    }

    public void refresh(){
        this.allUsers = NeigeSoleil.getAllUsers();
        this.tabModel.setDonnees(this.setData());
        this.cleanUserField();
    }

    public Object[][] setData(){
        Object [][] arr = new Object [this.allUsers.size()][6];
        int i =0;
        for(User unUser : this.allUsers){
            arr[i][0] = unUser.getId();
            arr[i][1] = unUser.getUsername();
            arr[i][2] = unUser.getLast_name();
            arr[i][3] = unUser.getFirst_name();
            arr[i][4] = unUser.getEmail();
            // arr[i][5] = "Oui";
            if(unUser.getProprietaire()){
                arr[i][5] = "Oui";
            } else { arr[i][5] = "Non"; }
            i++;
        }
        return arr;
    }

    public void ajouterUser(){
        User unUser = new User(this.txtUsername.getText(), this.txtNom.getText(), this.txtPrenom.getText(),
                this.txtEmail.getText(), (Boolean) this.cProprietaire.isSelected());
        if(NeigeSoleil.addUser(unUser)){
            JOptionPane.showMessageDialog(this, "Succes de l'ajout, Cliquez dessus pour lui attribuer un mot de passe et creer son profile");
            this.refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Echec de l'ajout, verifiez les informations");
        }
        this.cleanUserField();
    }

    public void editUser() {
        this.btnValider.setText("Modifier");
        this.btnEditPassword.setVisible(true);
        this.btnProfile.setVisible(true);
        int num = this.dataTable.getSelectedRow();
        int idUser = Integer.parseInt(this.dataTable.getValueAt(num, 0).toString());
        this.editingUser = NeigeSoleil.getUser(idUser);
        if(this.editingUser != null) {
            this.txtUsername.setText(this.editingUser.getUsername());
            this.txtNom.setText(this.editingUser.getLast_name());
            this.txtPrenom.setText(this.editingUser.getFirst_name());
            this.txtEmail.setText(this.editingUser.getEmail());
            this.cProprietaire.setVisible(false);
            this.editStatus = true;
        }
    }

    public void validateEdit(){
        if(this.editingUser != null){
            this.editingUser.setUsername(this.txtUsername.getText());
            this.editingUser.setFirst_name(this.txtPrenom.getText());
            this.editingUser.setLast_name(this.txtNom.getText());
            this.editingUser.setEmail(this.txtEmail.getText());
            if(NeigeSoleil.updateUser(this.editingUser)){
                JOptionPane.showMessageDialog(this, "Modification reussie !");
            } else {
                JOptionPane.showMessageDialog(this, "Echec de la modification!");
            }
        }

    }

    public void cleanUserField() {
        this.txtUsername.setText("");
        this.txtNom.setText("");
        this.txtPrenom.setText("");
        this.txtEmail.setText("");
        this.editStatus = false;
        this.cProprietaire.setVisible(true);
        this.cProprietaire.setSelected(false);
        this.btnValider.setText("Ajouter");
        this.btnProfile.setVisible(false);
        this.btnEditPassword.setVisible(false);
    }

    public void editPassword(){
        String password = JOptionPane.showInputDialog(this, "Saisissez le nouveau mot de passe");
        if(NeigeSoleil.editPassword(this.editingUser.getId(), password)){
            JOptionPane.showMessageDialog(this, "Changement de mot de passe reussi");
        } else {
            JOptionPane.showMessageDialog(this, "Echec de l'opération");
        }
    }

    public void profileHandler(){
        editingUser.setUserProfile(NeigeSoleil.getProfile(this.editingUser.getId()));
        editingUser.getUserProfile();
        if(editingUser.getUserProfile() != null) {
            this.editProfile(editingUser.getUserProfile());
        } else {
            int retour = JOptionPane.showConfirmDialog(this,
                    "Cet utilisateur n'as pas de profil, souhaitez vous en créer un ? ",
                    "Gestion du profil",
                    JOptionPane.YES_NO_OPTION);
            if(retour == 0) {
                this.createProfile();
            }
        }
    }

    public void createProfile() {
        int result = JOptionPane.showConfirmDialog(null, this.profilInputs, "Profile Utilisateur", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
            this.editingUser.setUserProfile( new Profile(
                    this.txtAdresse.getText(),
                    this.txtCodePostale.getText(),
                    this.txtVille.getText(), this.txtTelephone.getText(),
                    this.txtRib.getText()
            ));
            if (NeigeSoleil.addProfil(editingUser)) {
                JOptionPane.showMessageDialog(this, "Profile créer ! ");
            } else {
                JOptionPane.showMessageDialog(this, "Echec de la creation du profil");
            }
        }
    }

    public void editProfile(Profile unProfile){
        this.txtAdresse.setText(unProfile.getAdresse());
        this.txtCodePostale.setText(unProfile.getCode_postale());
        this.txtVille.setText(unProfile.getVille());
        this.txtTelephone.setText(unProfile.getTelephone());
        this.txtRib.setText(unProfile.getRib());
        int result = JOptionPane.showConfirmDialog(null, this.profilInputs, "Profile Utilisateur", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
            unProfile.setAdresse(this.txtAdresse.getText());
            unProfile.setCode_postale(this.txtCodePostale.getText());
            unProfile.setVille(this.txtVille.getText());
            unProfile.setTelephone(this.txtTelephone.getText());
            unProfile.setRib(this.txtRib.getText());
            this.editingUser.setUserProfile(unProfile);
            if (NeigeSoleil.updateProfile(this.editingUser)) {
                JOptionPane.showMessageDialog(this, "Profile a jour ! ");
            } else {
                JOptionPane.showMessageDialog(this, "Echec de la mise à jour du profil");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()== btnValider) {
            if(this.editStatus){
                this.validateEdit();
                this.cleanUserField();
                this.refresh();
            } else {
                this.ajouterUser();
            }

        }

        if(e.getSource() == btnSupprimer) {
            int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cet utilisateur ?",
                    "Supression d'un client", JOptionPane.YES_NO_OPTION);
            if (retour == 0)
            {
                int num = this.dataTable.getSelectedRow();
                int idUser = Integer.parseInt(this.dataTable.getValueAt(num, 0).toString());
                NeigeSoleil.deleteUser(idUser);
                this.refresh();
            }
        }

        if (e.getSource() == btnAnnuler){
            this.cleanUserField();
        }

        if (e.getSource() == btnModifier) {
            this.editUser();
        }

        if (e.getSource() == btnEditPassword){
            this.editPassword();
        }

        if (e.getSource() == btnProfile){
            this.profileHandler();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
            this.editUser();
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
