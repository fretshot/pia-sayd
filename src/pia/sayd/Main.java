
package pia.sayd;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class Main extends javax.swing.JFrame {

    String stringTemperaturaActual;
    float floatTemperaturaActual;
    
    String stringTemperaturaMaxima;
    float floatTemperaturaMaxima = (float)40.00;
    
    String stringTemperaturaMinima;
    float floatTemperaturaMinima = (float)20.00;
    
    String port = "";
    
    SerialPort serialPort;
            
    public Main() {
        initComponents();
        
        btnDisminuirMinima.setEnabled(false);
        btnAumentarMinima.setEnabled(false);
        btnDisminuirMaxima.setEnabled(false);
        btnAumentarMaxima.setEnabled(false);
        
        temperaturaMaxima.setText(String.valueOf(floatTemperaturaMaxima)+ " °C");
        temperaturaMinima.setText(String.valueOf(floatTemperaturaMinima)+ " °C");
        
        stringTemperaturaActual = temperaturaActual.getText();
        floatTemperaturaActual = Float.parseFloat(stringTemperaturaActual);

        readPorts();
        
        serialPort = new SerialPort(port);
    }
    
    
    public void initThreadReceive(){
        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    ReceiveData();
                } catch (Exception ex) {
                    System.err.println(ex);
                }
            }
        };
        t.start();
    }
    
    public void SendData(float temp){
        
        String mensaje = String.valueOf(temp);
        System.out.println("Parametro enviado: "+mensaje);
        try {
            if(!serialPort.isOpened()){
                serialPort.openPort();
                serialPort.setParams(9600, 8, 1, 0);
            }
            // Pause necesaria
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println("Error."+ex);
            }
            
            boolean send = serialPort.writeBytes(mensaje.getBytes());
            
            if (send == true){
                System.out.println("Mensaje enviado a Arduino con exito");
            }else{
                System.out.println("Mensaje no recibido por Arduino");
                fieldEstado.setText("Error: Parametros no recibidos por Arduino");
                fieldEstado.setBackground(Color.orange);
            }
            
            
            //serialPort.closePort(); 
            
            
        }catch (SerialPortException ex){
            System.err.println(ex);
        }
    }
    
    public void ReceiveData() throws IOException{
                
        while(true){
            
            try {
            
            fieldEstado.setText("Conectado a: "+port);
            fieldEstado.setBackground(Color.green);
                
            if(!serialPort.isOpened()){
                serialPort.openPort();
                serialPort.setParams(9600, 8, 1, 0);
            }
            
            byte[] buffer = serialPort.readBytes(5);//Leer 10 bytes
            stringTemperaturaActual = new String(buffer, "UTF-8"); // Parseando los bytes a objeto String
            temperaturaActual.setText(stringTemperaturaActual+" °C");

            //serialPort.closePort();

            }catch (SerialPortException ex) {
                fieldEstado.setText("Error: Puerto no encontrado");
                fieldEstado.setBackground(Color.RED);
                System.out.println(ex);
            }
        }
        
    }
    
    
    public void readPorts(){
        String[] portNames = SerialPortList.getPortNames();

        comboPortList.setModel(new javax.swing.DefaultComboBoxModel<>(portNames));
        port = comboPortList.getSelectedItem().toString();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        fieldEstado = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        temperaturaMaxima = new javax.swing.JLabel();
        btnDisminuirMaxima = new javax.swing.JButton();
        btnAumentarMaxima = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        temperaturaMinima = new javax.swing.JLabel();
        btnDisminuirMinima = new javax.swing.JButton();
        btnAumentarMinima = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        temperaturaActual = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        comboPortList = new javax.swing.JComboBox<>();
        btnConectar = new javax.swing.JButton();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sensor de Temperatura");
        setBackground(java.awt.Color.darkGray);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 12))); // NOI18N

        fieldEstado.setText("Desconectado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldEstado)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(fieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Temperatura máxima", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 12))); // NOI18N

        temperaturaMaxima.setFont(new java.awt.Font("Consolas", 1, 48)); // NOI18N
        temperaturaMaxima.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        temperaturaMaxima.setText("00.00");

        btnDisminuirMaxima.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        btnDisminuirMaxima.setText("DISMINUIR");
        btnDisminuirMaxima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisminuirMaximaActionPerformed(evt);
            }
        });

        btnAumentarMaxima.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        btnAumentarMaxima.setText("AUMENTAR");
        btnAumentarMaxima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAumentarMaximaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(temperaturaMaxima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnDisminuirMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAumentarMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(temperaturaMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDisminuirMaxima)
                    .addComponent(btnAumentarMaxima))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Temperatura mínima", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 12))); // NOI18N

        temperaturaMinima.setFont(new java.awt.Font("Consolas", 1, 48)); // NOI18N
        temperaturaMinima.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        temperaturaMinima.setText("00.00");

        btnDisminuirMinima.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        btnDisminuirMinima.setText("DISMINUIR");
        btnDisminuirMinima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisminuirMinimaActionPerformed(evt);
            }
        });

        btnAumentarMinima.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        btnAumentarMinima.setText("AUMENTAR");
        btnAumentarMinima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAumentarMinimaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(temperaturaMinima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnDisminuirMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAumentarMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(temperaturaMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDisminuirMinima)
                    .addComponent(btnAumentarMinima))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Temperatura actual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 12))); // NOI18N

        temperaturaActual.setFont(new java.awt.Font("Consolas", 1, 70)); // NOI18N
        temperaturaActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        temperaturaActual.setText("00.00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(temperaturaActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(temperaturaActual, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Conexión", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 12))); // NOI18N

        comboPortList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnConectar.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        btnConectar.setForeground(new java.awt.Color(0, 0, 204));
        btnConectar.setText("CONECTAR");
        btnConectar.setFocusable(false);
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboPortList, 0, 228, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboPortList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConectar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDisminuirMaximaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisminuirMaximaActionPerformed

        floatTemperaturaMaxima -= 0.5;
        temperaturaMaxima.setText(String.valueOf(floatTemperaturaMaxima)+ " °C");
        
        SendData(floatTemperaturaMaxima);
        
        if(floatTemperaturaMaxima <= 24){
           btnDisminuirMaxima.setEnabled(false);
        }
        
        if(!btnAumentarMaxima.isEnabled()){
            btnAumentarMaxima.setEnabled(true);
        }
    }//GEN-LAST:event_btnDisminuirMaximaActionPerformed

    private void btnAumentarMaximaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAumentarMaximaActionPerformed
        
        floatTemperaturaMaxima += 0.5;
        temperaturaMaxima.setText(String.valueOf(floatTemperaturaMaxima)+ " °C");
        
        SendData(floatTemperaturaMaxima);
        
        if(floatTemperaturaMaxima >= 50){
           btnAumentarMaxima.setEnabled(false);
        }
        
        if(!btnDisminuirMaxima.isEnabled()){
            btnDisminuirMaxima.setEnabled(true);
        }
    }//GEN-LAST:event_btnAumentarMaximaActionPerformed

    private void btnAumentarMinimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAumentarMinimaActionPerformed
        floatTemperaturaMinima += 0.5;
        temperaturaMinima.setText(String.valueOf(floatTemperaturaMinima)+ " °C");
        
        if(floatTemperaturaMinima >= 24){
           btnAumentarMinima.setEnabled(false);
        }
        
        if(!btnDisminuirMinima.isEnabled()){
            btnDisminuirMinima.setEnabled(true);
        }
    }//GEN-LAST:event_btnAumentarMinimaActionPerformed

    private void btnDisminuirMinimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisminuirMinimaActionPerformed
        floatTemperaturaMinima -= 0.5;
        temperaturaMinima.setText(String.valueOf(floatTemperaturaMinima)+ " °C");
        
        if(floatTemperaturaMinima <= 16){
           btnDisminuirMinima.setEnabled(false);
        }
        
        if(!btnAumentarMinima.isEnabled()){
            btnAumentarMinima.setEnabled(true);
        }
        
    }//GEN-LAST:event_btnDisminuirMinimaActionPerformed

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        initThreadReceive();
        btnConectar.setEnabled(false);
        
        btnDisminuirMinima.setEnabled(true);
        btnAumentarMinima.setEnabled(true);
        btnDisminuirMaxima.setEnabled(true);
        btnAumentarMaxima.setEnabled(true);
    }//GEN-LAST:event_btnConectarActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAumentarMaxima;
    private javax.swing.JButton btnAumentarMinima;
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnDisminuirMaxima;
    private javax.swing.JButton btnDisminuirMinima;
    private javax.swing.JComboBox<String> comboPortList;
    private javax.swing.JTextField fieldEstado;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel temperaturaActual;
    private javax.swing.JLabel temperaturaMaxima;
    private javax.swing.JLabel temperaturaMinima;
    // End of variables declaration//GEN-END:variables
}
