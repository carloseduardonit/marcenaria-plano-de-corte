/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marcenaria.tela.pessoa;

import marcenaria.Marcenaria;
import marcenaria.dado.ModuloConector;
import marcenaria.pessoa1.Cliente;
import marcenaria.pessoa1.Layout;

/**
 *
 * @author Carlos
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    private static final long serialVersionUID = 1L;
    Marcenaria m = new Marcenaria();

    /**
     * Creates new form TelaFornecedor
     */
    public TelaCliente() {
        if (ModuloConector.VerificarNaoExistirTabela(Cliente.getTABELA())) {
            Cliente.criarCliente();
        }
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbTipoPessoa = new javax.swing.JComboBox<>();
        btnADD = new javax.swing.JButton();
        lblSenha = new javax.swing.JLabel();
        btnEDIT = new javax.swing.JButton();
        txtSenha = new javax.swing.JTextField();
        btmSEACH = new javax.swing.JButton();
        lblNome = new javax.swing.JLabel();
        txtConfSenha1 = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        lblConfSenha = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        btnDELETE = new javax.swing.JButton();
        txtLogin = new javax.swing.JTextField();
        lblTipoPessoa = new javax.swing.JLabel();
        lblDocumento = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle(Cliente.getTABELA());
        setMaximumSize(Layout.getMaximo());
        setMinimumSize(Layout.getMinimo());
        setPreferredSize(Layout.getPerfil());

        cbTipoPessoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NI", "PF", "PJ" }));
        cbTipoPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoPessoaActionPerformed(evt);
            }
        });

        btnADD.setText("ADD");
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });

        lblSenha.setText("Senha:");

        btnEDIT.setText("EDIT");
        btnEDIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEDITActionPerformed(evt);
            }
        });

        btmSEACH.setText("SEACH");
        btmSEACH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmSEACHActionPerformed(evt);
            }
        });

        lblNome.setText("Nome:");

        txtConfSenha1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConfSenha1KeyReleased(evt);
            }
        });

        lblConfSenha.setText("Confirme a Senha:");

        lblLogin.setText("Login:");

        btnDELETE.setText("DELETE");
        btnDELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDELETEActionPerformed(evt);
            }
        });

        lblTipoPessoa.setText("Tipo de Pessoa:");

        lblDocumento.setText("Documento:");

        txtDocumento.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblConfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtConfSenha1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTipoPessoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDocumento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addComponent(btnADD, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEDIT, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btmSEACH, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDELETE)))
                .addGap(112, 112, 112))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLogin)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConfSenha)
                    .addComponent(txtConfSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoPessoa)
                    .addComponent(cbTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDocumento)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnADD)
                    .addComponent(btnEDIT)
                    .addComponent(btmSEACH)
                    .addComponent(btnDELETE)))
        );

        setBounds(366, 252, 302, 252);
    }// </editor-fold>//GEN-END:initComponents

    private void cbTipoPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoPessoaActionPerformed
        Control.DesabilitaeMudaDoc(cbTipoPessoa, lblDocumento, txtDocumento);
    }//GEN-LAST:event_cbTipoPessoaActionPerformed

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed
        Control.ADDCliente(txtLogin, txtSenha, txtConfSenha1, cbTipoPessoa, txtNome, txtDocumento);
        Control.LimpaDados(txtLogin, txtSenha, txtConfSenha1, cbTipoPessoa, txtNome, txtDocumento);
    }//GEN-LAST:event_btnADDActionPerformed

    private void btnEDITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEDITActionPerformed
        Control.EDITCliente(txtLogin, txtLogin, txtSenha, txtConfSenha1, cbTipoPessoa, txtNome, txtDocumento);
        Control.LimpaDados(txtLogin, txtSenha, txtConfSenha1, cbTipoPessoa, txtNome, txtDocumento);
    }//GEN-LAST:event_btnEDITActionPerformed

    private void btmSEACHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmSEACHActionPerformed
        Control.SEACHCliente(txtLogin, txtSenha, txtConfSenha1, cbTipoPessoa, txtNome, txtDocumento);
        
    }//GEN-LAST:event_btmSEACHActionPerformed

    private void btnDELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDELETEActionPerformed
        Control.DELETECliente(txtLogin, txtSenha, txtConfSenha1, cbTipoPessoa, txtNome, txtDocumento);
        Control.LimpaDados(txtLogin, txtSenha, txtConfSenha1, cbTipoPessoa, txtNome, txtDocumento);
    }//GEN-LAST:event_btnDELETEActionPerformed

    private void txtConfSenha1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfSenha1KeyReleased
        Control.MudarCor(txtConfSenha1, txtSenha);
    }//GEN-LAST:event_txtConfSenha1KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btmSEACH;
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnDELETE;
    private javax.swing.JButton btnEDIT;
    private javax.swing.JComboBox<String> cbTipoPessoa;
    private javax.swing.JLabel lblConfSenha;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblTipoPessoa;
    private javax.swing.JTextField txtConfSenha1;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSenha;
    // End of variables declaration//GEN-END:variables
}
