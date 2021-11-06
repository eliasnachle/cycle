/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerMachineInfo;
import controller.ControllerRegistry;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import model.MachineInfoModel;
import model.MachineRegistryModel;

public class DadosCapturados extends javax.swing.JFrame {
    
    public DadosCapturados() {
        initComponents();
        insertInInputMachineInfo();
        
        Timer timer = new Timer();
        int delay = 50;
        int interval = 1000;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                insertResgistryInDatabase();
                insertInInputRegistryInfo();
            }
        }, delay, interval);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        InpApelidoMaquina = new javax.swing.JFormattedTextField();
        InpTipoMaquina = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        InpSistemaOperacional = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        InpModeloCpu = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        InpFrequenciaCpu = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        InpModeloDisco = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        InpEspacoTotalDisco = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        InpEspacoTotalRam = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        DiscoDisponivel = new javax.swing.JLabel();
        DiscoUsado = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        UsoCpu = new javax.swing.JLabel();
        TemperaturaCpu = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        UsoRam = new javax.swing.JLabel();
        RamDisponivel = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 800));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Dashboard");

        jLabel3.setText("Abrir chamado");

        jLabel4.setText("Sair");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da máquina"));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel5.setText("Apelido da máquina:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel6.setText("Tipo da máquina:");

        InpApelidoMaquina.setEditable(false);
        InpApelidoMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InpApelidoMaquinaActionPerformed(evt);
            }
        });

        InpTipoMaquina.setEditable(false);
        InpTipoMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InpTipoMaquinaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel7.setText("Sistema operacional:");

        InpSistemaOperacional.setEditable(false);
        InpSistemaOperacional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InpSistemaOperacionalActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel8.setText("Modelo CPU:");

        InpModeloCpu.setEditable(false);
        InpModeloCpu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InpModeloCpuActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel9.setText("Frequencia da CPU:");

        InpFrequenciaCpu.setEditable(false);
        InpFrequenciaCpu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InpFrequenciaCpuActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel10.setText("Modelo Disco");

        InpModeloDisco.setEditable(false);
        InpModeloDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InpModeloDiscoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setText("Espaco total no disco:");

        InpEspacoTotalDisco.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel12.setText("Total de mémoria RAM:");

        InpEspacoTotalRam.setEditable(false);
        InpEspacoTotalRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InpEspacoTotalRamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(InpApelidoMaquina)
                    .addComponent(InpTipoMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(InpSistemaOperacional)
                    .addComponent(InpModeloCpu, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(InpModeloDisco)
                    .addComponent(InpFrequenciaCpu, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InpEspacoTotalDisco, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(InpEspacoTotalRam)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InpApelidoMaquina)
                    .addComponent(InpSistemaOperacional)
                    .addComponent(InpFrequenciaCpu)
                    .addComponent(InpEspacoTotalDisco))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InpTipoMaquina)
                    .addComponent(InpModeloCpu)
                    .addComponent(InpModeloDisco)
                    .addComponent(InpEspacoTotalRam))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Disco"));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Em uso:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Disponivel:");

        DiscoDisponivel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        DiscoDisponivel.setText(".");

        DiscoUsado.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        DiscoUsado.setText(".");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DiscoUsado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DiscoDisponivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(0, 177, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DiscoUsado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DiscoDisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("CPU"));
        jPanel7.setMaximumSize(new java.awt.Dimension(500, 500));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Em uso:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Temperatura:");

        UsoCpu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        UsoCpu.setText(".");

        TemperaturaCpu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TemperaturaCpu.setText(".");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(TemperaturaCpu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                .addComponent(UsoCpu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsoCpu, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TemperaturaCpu, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("RAM"));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Em uso:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Disponivel:");

        UsoRam.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        UsoRam.setText(".");

        RamDisponivel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        RamDisponivel.setText(".");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UsoRam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 154, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(0, 167, Short.MAX_VALUE))
                            .addComponent(RamDisponivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsoRam, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RamDisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(40, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InpEspacoTotalRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InpEspacoTotalRamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InpEspacoTotalRamActionPerformed

    private void InpApelidoMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InpApelidoMaquinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InpApelidoMaquinaActionPerformed

    private void InpSistemaOperacionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InpSistemaOperacionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InpSistemaOperacionalActionPerformed

    private void InpFrequenciaCpuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InpFrequenciaCpuActionPerformed

    }//GEN-LAST:event_InpFrequenciaCpuActionPerformed

    private void InpModeloDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InpModeloDiscoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InpModeloDiscoActionPerformed

    private void InpTipoMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InpTipoMaquinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InpTipoMaquinaActionPerformed

    private void InpModeloCpuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InpModeloCpuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InpModeloCpuActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new DadosCapturados().setVisible(true);
        });
    }
    
    private void insertInInputMachineInfo() {
        System.out.println("Inserindo dados no banco");
        ControllerMachineInfo controllerMachine = new ControllerMachineInfo();
        
        List<MachineInfoModel> machineInfo = controllerMachine.consultMachineInfo();
        
        InpApelidoMaquina.setText(machineInfo.get(0).getApelidoMaquina());
        InpEspacoTotalDisco.setText(machineInfo.get(0).getEspacoTotalDisco().toString());
        InpEspacoTotalRam.setText(machineInfo.get(0).getEspacoTotalRam().toString());
        InpFrequenciaCpu.setText(machineInfo.get(0).getCpuFrequencia().toString());
        InpModeloCpu.setText(machineInfo.get(0).getModeloCpu());
        InpSistemaOperacional.setText(machineInfo.get(0).getSistemaOperacionalMaquina());
        InpModeloDisco.setText(machineInfo.get(0).getModeloDisco());
        InpTipoMaquina.setText(machineInfo.get(0).getTipoMaquina());
    }
    
    private void insertInInputRegistryInfo() {
        System.out.println("Inserindo dados nos inputs");
        ControllerRegistry controllerRegistry = new ControllerRegistry();
        
        ControllerMachineInfo controllerMachine = new ControllerMachineInfo();
        List<MachineInfoModel> infoMachineDataBase = controllerMachine.consultMachineInfo();
        List<MachineRegistryModel> infoRegistryDataBase = controllerRegistry.consultMachineRegister();
        
        // Inserindo valores nos inputs
        UsoCpu.setText(infoRegistryDataBase.get(0).getCpuEmUso().toString() + " %");
        TemperaturaCpu.setText(infoRegistryDataBase.get(0).getTemperaturaCpu().toString() + " Cº");
        UsoRam.setText(infoMachineDataBase.get(0).getEspacoTotalRam() - infoRegistryDataBase.get(0).getEspacoLivreRam() + " GB");
        RamDisponivel.setText(infoRegistryDataBase.get(0).getEspacoLivreRam().toString() + " GB");
        DiscoUsado.setText(infoMachineDataBase.get(0).getEspacoTotalDisco() - infoRegistryDataBase.get(0).getEspacoLivreDisco() + " GB");
        DiscoDisponivel.setText(infoRegistryDataBase.get(0).getEspacoLivreDisco().toString() + " GB");
    }
    
    private void insertResgistryInDatabase() {
        ControllerRegistry registry = new ControllerRegistry();
        registry.registerInDatabaseNewRegistry();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DiscoDisponivel;
    private javax.swing.JLabel DiscoUsado;
    private javax.swing.JFormattedTextField InpApelidoMaquina;
    private javax.swing.JFormattedTextField InpEspacoTotalDisco;
    private javax.swing.JFormattedTextField InpEspacoTotalRam;
    private javax.swing.JFormattedTextField InpFrequenciaCpu;
    private javax.swing.JFormattedTextField InpModeloCpu;
    private javax.swing.JFormattedTextField InpModeloDisco;
    private javax.swing.JFormattedTextField InpSistemaOperacional;
    private javax.swing.JFormattedTextField InpTipoMaquina;
    private javax.swing.JLabel RamDisponivel;
    private javax.swing.JLabel TemperaturaCpu;
    private javax.swing.JLabel UsoCpu;
    private javax.swing.JLabel UsoRam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
