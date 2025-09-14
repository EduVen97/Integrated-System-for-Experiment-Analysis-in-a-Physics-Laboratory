/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FRAMES;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.prefs.Preferences;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author eduarot
 */
public class GraficaCaida extends javax.swing.JFrame {
     boolean Options = false;
     Calendar calendar = Calendar.getInstance();
     DateFormat datef = new SimpleDateFormat("dd-MM-yyyy");
     String Today = datef.format(calendar.getTime());
     String COM = "COM9";
     String VariableX = "Segundos";
     String VarXType = "(s)";
     String VariableY = "Experimento";
     String VarYType = "(E)";
     String Title = "CaidaLibre_"+Today;
     float []TempDato = new float[10000]; //almacena los valores de temperatura
     float [] TimeDato = new float[10000];//almacena los valores tiempo
     boolean Paused = false;
     final XYSeries Serie = new XYSeries ("Segundos");
     final XYSeriesCollection Collection = new XYSeriesCollection();
     JFreeChart Grafics; 
     
       public GraficaCaida(){
        initComponents();
            try{
             ino.arduinoRXTX(COM,9600,Listener);
        }//try
        catch (ArduinoException ex){
            Logger.getLogger(GraficaCaida.class.getName()).log(Level.SEVERE, null, ex);
        }//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch
        Preferences pref = Preferences.userNodeForPackage(GraficaCaida.class);
        Calendar calendar = Calendar.getInstance();
        DateFormat datef = new SimpleDateFormat("dd-MM-yyyy");
        String Today = datef.format(calendar.getTime());
        this.Title = pref.get("ExpName","Exp_Caida_Altura3"+Today);
        this.VarXType = pref.get("VariableTypeX","Segundos(s)");
        this.VarYType = pref.get("VariableTypeY","Experimento(E)");
        //Grafica los datos que se reciben del arduino. 
        Serie.add(0,0);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            ClosingAction();
        }
        });
        Collection.addSeries(Serie);
        Grafics = ChartFactory.createXYBarChart(Title,VarYType,false,
                                                 VarXType, Collection);
       
        ChartPanel Panel = new ChartPanel(Grafics);
        this.setTitle(Title);
        //Panel.add(Cancel);
        Serie.add(0,0);
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(Panel);
        jPanel1.validate();
        jPanel2.validate();
         try {
             ino.killArduinoConnection();
         } catch (ArduinoException ex) {
             Logger.getLogger(GraficaCaida.class.getName()).log(Level.SEVERE, null, ex);
         }
        Paused = true;
        pausebtn.setText("Empezar Exp");
    }//public GraficaXYTemp()
       
    public GraficaCaida(boolean Options){
        initComponents();
         Preferences pref = Preferences.userNodeForPackage(GraficaCaida.class);
         this.Options = Options; 
         if (Options == true){
            this.Title = pref.get("ExpName", "Exp_Caida");
            this.VarXType = pref.get("VariableTypeX","Segundos(s)");
            this.VarYType = pref.get("VariableTypeY","Experiento(E)");
         }
            try{
             ino.arduinoRXTX(COM,9600,Listener);
        }//try
        catch (ArduinoException ex){
            Logger.getLogger(GraficaCaida.class.getName()).log(Level.SEVERE, null, ex);
        }//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch//catch
        //Grafica los datos que se reciben del arduino. 
        Serie.add(0,0);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            ClosingAction();
        }
        });
        Collection.addSeries(Serie);
        Grafics = ChartFactory.createXYBarChart(Title,VarXType,false,
                                                 VarYType, Collection);
       
        ChartPanel Panel = new ChartPanel(Grafics);
        this.setTitle(Title);
        //Panel.add(Cancel);
        Serie.add(0,0);
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(Panel);
        jPanel1.validate();
        jPanel2.validate();
         try {
             ino.killArduinoConnection();
         } catch (ArduinoException ex) {
             Logger.getLogger(GraficaCaida.class.getName()).log(Level.SEVERE, null, ex);
         }
        Paused = true;
        pausebtn.setText("Empezar Exp");
    }//public GraficaXYTemp()
       
       
     //Conexion con Arduino 
     float i=0;
     int count =0;
     PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
     SerialPortEventListener Listener = new SerialPortEventListener(){
          @Override 
          public void serialEvent(SerialPortEvent spe) {
              Boolean IsCommand = false;
            try {
                if (ino.isMessageAvailable()== true){
                    String Temp = ino.printMessage();
                    System.out.println(Temp);
                    IsCommand = CommandCheck(Temp);//Verifica si el string ingresado es un comando
                    if (IsCommand==true){
                        SendCommand();
                    }//if 
                    else{     
                    float DataTemp = GetTemp(Temp);
                    try{
                        TempDato[count]=DataTemp/1000;
                         Serie.add(i,DataTemp/1000);
                        TimeDato[count]=i;
                        i=i+(float)1;
                        count = count+1;
                        ChangeBtnStatus();
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println(e);
                    };
                   }//else
                }//if 
            } catch (SerialPortException ex) {
                Logger.getLogger(GraficaCaida.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ArduinoException ex) {
                Logger.getLogger(GraficaCaida.class.getName()).log(Level.SEVERE, null, ex);
            }
          }//public void serialEvent
     };//SerialPortEventListener
    /**
     * Creates new form GraficaXY
     */
  
    
    
    private boolean CommandCheck(String Command){
      boolean CommandConfirm = false;
      if (Command.contains("Tiempo de caida|")==true){
          CommandConfirm=false;
      }//if  
      else{
          CommandConfirm=true;
      }//else
     return CommandConfirm;
    }//ComandCheck
    
    private void SendCommand (){
           
    }//SendCommand
    
    private float GetTemp (String Command) throws ArduinoException,SerialPortException{
        float Result = 0;
        String Trimmed1;
        int LargoCommand = Command.length();
        int DataStart = Command.indexOf("|");
        Trimmed1 = Command.substring(DataStart+1,LargoCommand);
        System.out.println(Trimmed1);
        Result = Float.parseFloat(Trimmed1);
        return Result;
    }//GetTemp
    /**
     * Thisli method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        Finishbtn = new javax.swing.JButton();
        pausebtn = new javax.swing.JButton();
        cancelbtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Finishbtn.setText("Terminar Exp");
        Finishbtn.setEnabled(false);
        Finishbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishbtnActionPerformed(evt);
            }
        });

        pausebtn.setText("Pausar Exp");
        pausebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pausebtnActionPerformed(evt);
            }
        });

        cancelbtn.setText("Cancelar Exp");
        cancelbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Finishbtn)
                .addGap(18, 18, 18)
                .addComponent(pausebtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelbtn)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Finishbtn)
                    .addComponent(pausebtn)
                    .addComponent(cancelbtn))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 702, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClosingAction(){
        int ConfirmCancel = 2;
        ConfirmCancel = JOptionPane.showConfirmDialog(null,"DESEA CANCELAR EL EXPERIMENTO?"+"\n"+
                                                      "Los datos NO seran guardados","Atención",
                                                      JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (ConfirmCancel==0){
            try {
                ino.killArduinoConnection();
            } catch (ArduinoException ex) {
                Logger.getLogger(GraficaCaida.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            }//windowsClosing
          ComienzoExp temp = new ComienzoExp("C");
          temp.setResizable(false);
          temp.setLocationRelativeTo(null);
          temp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          temp.setVisible(true);
          this.dispose();
    }//ClosingAction 
    
    private void FinishbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishbtnActionPerformed
        // TODO add your handling code here:
        int conn = JOptionPane.showConfirmDialog(null,"Desea guardar los datos en la Base de datos?",
                                               "Guardar en base de datos", JOptionPane.YES_NO_OPTION);
        if (conn == JOptionPane.YES_OPTION){
            db.dbconn conexion = new db.dbconn();
            int id = conexion.InsertExpTitle(Title,"C",VariableX,VariableY);
            if (id>=0){
                   conexion.InsertExpData(TempDato,VarXType,TimeDato,VarYType,"C", id, count);
            }//if 
            JOptionPane.showMessageDialog(null,"Subida a la base de datos completada!");
        }// IF YES
        int exit = JOptionPane.showConfirmDialog(null,"Desea salir del experimento?",
                                               "Salir", JOptionPane.YES_NO_OPTION);
        if (exit == JOptionPane.YES_OPTION){
          ComienzoExp temp = new ComienzoExp(true,TempDato,TimeDato,count,
                                            VariableX,VariableY,Title,"C");
          temp.setLocationRelativeTo(null);
          temp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          temp.setVisible(true);
         try {
             ino.killArduinoConnection();
         } catch (ArduinoException ex) {
             Logger.getLogger(GraficaCaida.class.getName()).log(Level.SEVERE, null, ex);
         }
           this.dispose();
        }//JOptionPane == Yes
    }//GEN-LAST:event_FinishbtnActionPerformed
    //fINISHbtnActionPerformed
    private void pausebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pausebtnActionPerformed
        // TODO add your handling code here:
        ChangeBtnStatus();
    }//GEN-LAST:event_pausebtnActionPerformed

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
        // TODO add your handling code here:
        ClosingAction();
    }//GEN-LAST:event_cancelbtnActionPerformed
    
    private void ChangeBtnStatus(){
        if (Paused==false){
            Paused=true;
            try {
                ino.setStopBits(1);
                ino.sendData("3");
                ino.killArduinoConnection();
            } catch (ArduinoException ex) {
                Logger.getLogger(GraficaCaida.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SerialPortException ex) {
                Logger.getLogger(GraficaCaida.class.getName()).log(Level.SEVERE, null, ex);
            }
            pausebtn.setText("Continuar Exp");
            Finishbtn.setEnabled(true);
        }//if paused false
        else{
             Paused=false;
            try {
                ino.arduinoRXTX(COM,9600, Listener);
                ino.setStopBits(1);
                 ino.sendData("1");
            } catch (ArduinoException ex) {
                Logger.getLogger(GraficaCaida.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SerialPortException ex) {
                Logger.getLogger(GraficaCaida.class.getName()).log(Level.SEVERE, null, ex);
            }
            pausebtn.setText("Pausar Exp");
            Finishbtn.setEnabled(false);
        }//else
    }//ChangeBtnStatus
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraficaCaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraficaCaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraficaCaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraficaCaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraficaCaida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Finishbtn;
    private javax.swing.JButton cancelbtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton pausebtn;
    // End of variables declaration//GEN-END:variables

   }
             
