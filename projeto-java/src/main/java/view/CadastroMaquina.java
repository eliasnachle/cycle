package view;

import controller.ControllerMachineInfo;
import javax.swing.JOptionPane;
import model.MachineInfoModel;

public class CadastroMaquina extends javax.swing.JFrame {
    
    private String idContratante;
    
    public CadastroMaquina(String idContratante) {
        initComponents();
        InsertInInputValues();
        this.idContratante = idContratante;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ComboTipoMaquina = new javax.swing.JComboBox<>();
        InpTotalRam = new javax.swing.JTextField();
        InpApelidoMaquina = new javax.swing.JTextField();
        InpFrequenciaCpu = new javax.swing.JTextField();
        InpTipoDisco1 = new javax.swing.JTextField();
        InpSistemaOperacional = new javax.swing.JTextField();
        InpEspacoTotal1 = new javax.swing.JTextField();
        InpModeloCpu = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        RegisterButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        InpTipoDisco2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        InpEspacoTotal2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Apelido da máquina:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Tipo da máquina:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Sistema operacional:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Modelo CPU:");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Frequencia da CPU:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("modelo de disco 1:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Espaco total no disco 1:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Total de mémoria RAM:");

        ComboTipoMaquina.setBackground(new java.awt.Color(220, 220, 220));
        ComboTipoMaquina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Totens", "Atendimento", "Outros" }));
        ComboTipoMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTipoMaquinaActionPerformed(evt);
            }
        });

        InpTotalRam.setEditable(false);
        InpTotalRam.setBackground(new java.awt.Color(47, 79, 79));

        InpApelidoMaquina.setBackground(new java.awt.Color(220, 220, 220));
        InpApelidoMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InpApelidoMaquinaActionPerformed(evt);
            }
        });

        InpFrequenciaCpu.setEditable(false);
        InpFrequenciaCpu.setBackground(new java.awt.Color(47, 79, 79));

        InpTipoDisco1.setEditable(false);
        InpTipoDisco1.setBackground(new java.awt.Color(47, 79, 79));
        InpTipoDisco1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InpTipoDisco1ActionPerformed(evt);
            }
        });

        InpSistemaOperacional.setEditable(false);
        InpSistemaOperacional.setBackground(new java.awt.Color(47, 79, 79));

        InpEspacoTotal1.setEditable(false);
        InpEspacoTotal1.setBackground(new java.awt.Color(47, 79, 79));

        InpModeloCpu.setEditable(false);
        InpModeloCpu.setBackground(new java.awt.Color(47, 79, 79));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Yu Gothic Light", 1, 24)); // NOI18N
        jLabel1.setText("Cadastro de máquina");

        RegisterButton.setText("Cadastrar");
        RegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("Cancelar");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("modelo de disco 2:");

        InpTipoDisco2.setEditable(false);
        InpTipoDisco2.setBackground(new java.awt.Color(47, 79, 79));
        InpTipoDisco2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InpTipoDisco2ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Espaco total no disco 2:");

        InpEspacoTotal2.setEditable(false);
        InpEspacoTotal2.setBackground(new java.awt.Color(47, 79, 79));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(InpModeloCpu, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InpSistemaOperacional, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(ComboTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(InpApelidoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(RegisterButton)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(InpEspacoTotal1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                                            .addComponent(InpTipoDisco1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(InpFrequenciaCpu, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(InpEspacoTotal2, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(InpTipoDisco2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel12)))
                                    .addComponent(jLabel9)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(CancelButton))
                                .addGap(0, 706, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(InpTotalRam, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel1))))
                .addGap(111, 111, 111)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(InpApelidoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(InpFrequenciaCpu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InpTipoDisco1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InpSistemaOperacional, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InpEspacoTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InpModeloCpu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(5, 5, 5)
                        .addComponent(InpTotalRam, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InpTipoDisco2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InpEspacoTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelButton)
                    .addComponent(RegisterButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void RegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterButtonActionPerformed
        RegisterButton.setEnabled(false);
        InsertInDatabaseNewMachine();
    }//GEN-LAST:event_RegisterButtonActionPerformed

    private void InpTipoDisco1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InpTipoDisco1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InpTipoDisco1ActionPerformed

    private void InpApelidoMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InpApelidoMaquinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InpApelidoMaquinaActionPerformed

    private void ComboTipoMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTipoMaquinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboTipoMaquinaActionPerformed

    private void InpTipoDisco2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InpTipoDisco2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InpTipoDisco2ActionPerformed
   
    private void InsertInInputValues() {
        MachineInfoModel machineInfo = new MachineInfoModel();
        
        InpFrequenciaCpu.setText(String.format("%.2f GHz", machineInfo.getCpuFrequencia() ));
        InpModeloCpu.setText(machineInfo.getModeloCpu());
        InpSistemaOperacional.setText(machineInfo.getSistemaOperacionalMaquina());
        InpTipoDisco1.setText(machineInfo.getModeloDisco1());
        InpEspacoTotal1.setText(String.format("%.2f GB", machineInfo.getEspacoTotalDisco1() ));
        InpTipoDisco2.setText(machineInfo.getModeloDisco2());
        InpEspacoTotal2.setText(String.format("%.2f GB", machineInfo.getEspacoTotalDisco2() ));
        InpTotalRam.setText(String.format("%.2f GB", machineInfo.getEspacoTotalRam() ));
    }
    
    private void InsertInDatabaseNewMachine() {
        MachineInfoModel infoMachineModel = new MachineInfoModel();
        ControllerMachineInfo controllerMachine = new ControllerMachineInfo();
        
        if(InpApelidoMaquina.getText().equals("")) {
               JOptionPane.showMessageDialog(rootPane, "Por favor coloque um apelido para a maquina");
               RegisterButton.setEnabled(true);
        }else {
            try {
                infoMachineModel.setApelidoMaquina(InpApelidoMaquina.getText());
                infoMachineModel.setTipoMaquina(ComboTipoMaquina.getSelectedItem().toString());
                controllerMachine.registerInDatabaseNewMachine(infoMachineModel, this.idContratante);
                setVisible(false);
                DadosCapturados frame3 = new DadosCapturados();
                frame3.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Algum erro inesperado aconteceu, por favor tente novamente mais tarde");
                RegisterButton.setEnabled(true);
            }
            
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JComboBox<String> ComboTipoMaquina;
    private javax.swing.JTextField InpApelidoMaquina;
    private javax.swing.JTextField InpEspacoTotal1;
    private javax.swing.JTextField InpEspacoTotal2;
    private javax.swing.JTextField InpFrequenciaCpu;
    private javax.swing.JTextField InpModeloCpu;
    private javax.swing.JTextField InpSistemaOperacional;
    private javax.swing.JTextField InpTipoDisco1;
    private javax.swing.JTextField InpTipoDisco2;
    private javax.swing.JTextField InpTotalRam;
    private javax.swing.JButton RegisterButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
