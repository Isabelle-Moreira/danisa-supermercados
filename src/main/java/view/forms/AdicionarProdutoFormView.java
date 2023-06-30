/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.forms;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author danilo
 */
public class AdicionarProdutoFormView extends javax.swing.JFrame {

    /**
     * Creates new form AdicionarProdutoFormView
     */
    public AdicionarProdutoFormView() {
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

        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nomeProdutoInput = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        adicionarProdutoBtn = new javax.swing.JButton();
        fecharFormBtn = new javax.swing.JButton();
        quantidadeSpinner = new javax.swing.JSpinner();
        valorUnitarioInput = new javax.swing.JTextField();

        jLabel3.setText("jLabel3");

        jLabel5.setText("jLabel5");

        jTextField3.setText("jTextField3");

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Adicionar produto");

        jLabel2.setText("Informe o nome do produto");

        jLabel4.setText("Informe o valor unitário do produto (em R$):");

        jLabel6.setText("Informe a quantidade que deseja fornecer:");

        adicionarProdutoBtn.setBackground(new java.awt.Color(255, 239, 100));
        adicionarProdutoBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        adicionarProdutoBtn.setForeground(new java.awt.Color(253, 1, 0));
        adicionarProdutoBtn.setText("Adicionar");
        adicionarProdutoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarProdutoBtnActionPerformed(evt);
            }
        });

        fecharFormBtn.setBackground(new java.awt.Color(253, 1, 0));
        fecharFormBtn.setForeground(new java.awt.Color(255, 255, 255));
        fecharFormBtn.setText("Fechar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(nomeProdutoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantidadeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valorUnitarioInput, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jLabel1)))
                .addContainerGap(121, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(adicionarProdutoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(fecharFormBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeProdutoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(valorUnitarioInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(quantidadeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(adicionarProdutoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fecharFormBtn)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarProdutoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarProdutoBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adicionarProdutoBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarProdutoBtn;
    private javax.swing.JButton fecharFormBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField nomeProdutoInput;
    private javax.swing.JSpinner quantidadeSpinner;
    private javax.swing.JTextField valorUnitarioInput;
    // End of variables declaration//GEN-END:variables

    public JButton getAdicionarProdutoBtn() {
        return adicionarProdutoBtn;
    }

    public JButton getFecharFormBtn() {
        return fecharFormBtn;
    }

    public JTextField getNomeProdutoInput() {
        return nomeProdutoInput;
    }

    public JSpinner getQuantidadeSpinner() {
        return quantidadeSpinner;
    }

    public JTextField getValorUnitarioInput() {
        return valorUnitarioInput;
    }

    
}
