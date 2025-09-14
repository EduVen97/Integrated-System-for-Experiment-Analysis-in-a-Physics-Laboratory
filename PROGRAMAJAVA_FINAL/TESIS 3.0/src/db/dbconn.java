/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.lang.String;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tesis.pkg3.pkg0.Experiment;
/**
 *
 * @author eduardot
 */
public class dbconn {
    Connection conn;
    PreparedStatement pst,pstaux;
    ResultSet rs,rsaux;
    String URL;
    String User;
    String Pass;
    String sql,Newsql;  
     public dbconn(){//Constructor
                      this.conn = null;
                      this.pst = null;
                      this.pstaux = null;
                      this.rs = null;
                      this.rsaux = null;
                      this.sql=null;
                      this.URL = "jdbc:mysql://localhost:3306/tesis";//URL DE CONEXCION. CAMBIAR POR EL TUYO
                      this.User = "root";// USERNAME DEL ADMINISTRADOR DE BASE DE DATOS. CAMBIAR POR EL TUYO
                      this.Pass = "";//CONTRASENA DEL ADMINISTRADOR DE BASE DE DATOS. CAMBIAR POR EL TUYO
                      this.Newsql=null;
    }//dbconn Constructor
     
    /*
         PUBLIC VOID INSERTEXPTITLE
        ===============================================================
        Esta clase inserta los datos del experimento a la tabla 
        de experimento de la base de datos.
        Primero se guardan los datos como el nombre del experimento la fecha
        y el tipo de experimento. Esto es previo a subir toda la informacion    
        obtenida de los sensores de arduino. 
        
        Requiere cuatro strings.
        Name es el nombre del experimento,
        ExpType es el tipo de experimento T= temperatura, C= CaidaLibre
        VarXName es el nombre que se le dio a la Variable X durante la grafica
        VarYName es el nombre que se le dio a la Variable Y durante la grafica 
     */
    public int InsertExpTitle (String Name, String ExpType, String VarXName, String VarYName){
        int ObtainedID=0;
        Calendar calendar = Calendar.getInstance();
        DateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        String Today = datef.format(calendar.getTime());
        try {   
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(URL,User,Pass);
                conn.setAutoCommit(false);
                sql = "INSERT INTO tesis.experimento "
                        + "(experimento.Nombre, experimento.Tipo_exp, "
                        + "experimento.fecha_exp, experimento.VarXName,"
                        + "experimento.VarYName) "
                        + "VALUES (?,?,?,?,?)";
                pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                pst.setString(1,Name);
                pst.setString(2, ExpType);
                pst.setString(3, Today);
                pst.setString(4, VarXName);
                pst.setString(5, VarYName);
                int RowAffected = 0;
                RowAffected = pst.executeUpdate();
                if (RowAffected>=1){
                    rs=pst.getGeneratedKeys();
                    if (rs.next()){
                        ObtainedID = rs.getInt(1);
                    }//is rs.next()
                }//ifRowAffected 
                conn.commit();
                conn.close();
        } //InsertExpTitle
        catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbconn.class.getName()).log(Level.SEVERE, null, ex);
        }//catch//catch
        return ObtainedID;
    }//InsertExpTitle
    
    public void InsertExpData (float[] DataX, String VarTypeX, float[] DataY, 
                               String VarTypeY, String ExpType, int ID, int Count){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, User, Pass);
            conn.setAutoCommit(false);
            if (ExpType.equals("T")==true){
                 sql = "INSERT INTO tesis.Temperatura "
                        + "(Temperatura.Temp_Y, Temperatura.Tipo_Y,"
                         + "Temperatura.Tiempo_X, Temperatura.Tipo_X,"
                         + "Experimento_id_Experimento) "
                        + "VALUES (?,?,?,?,?)";
            }//IF 
            if (ExpType.equals("C")==true){
                 sql = "INSERT INTO tesis.Caida_Libre"
                        + "(Caida_Libre.tiempo_x, Caida_Libre.Tipo_Var_X,"
                         + "Caida_Libre.ExpAltura_y, Caida_Libre.Tipo_Var_Y,"
                         + "Experimento_id_Experimento) "
                        + "VALUES (?,?,?,?,?)";
            }//IF 
            for (int j=0; j<Count;j++){
                pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                pst.setFloat(1, DataX[j]);
                pst.setString(2, VarTypeX);
                pst.setFloat(3, DataY[j]);
                pst.setString(4, VarTypeY);
                pst.setInt(5,ID);
                int RowAffected = 0;
                RowAffected = pst.executeUpdate();
                if (RowAffected>=1){
                    rs=pst.getGeneratedKeys();
                    if (rs.next()){
                        int IdR = rs.getInt(1);
                        System.out.println("Inserted: "+IdR);
                    }//if      
                }//rowAffected1
           }//for jcount
        conn.commit();
        conn.close();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbconn.class.getName()).log(Level.SEVERE, null, ex);
        }//catc//catc
    }//InsertExpData
    
    public ResultSet GetDataFromID (String ExpId, String ExpType, String ExpName){
           ResultSet Resultado = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, User, Pass);
            conn.setAutoCommit(false);
             if (ExpType.equals("T")==true){
                 sql = "SELECT * from Temperatura where Experimento_id_Experimento = ?";
                 
            }//IF 
            if (ExpType.equals("C")==true){
                 sql = "SELECT * from Caida_Libre where Experimento_id_Experimento = ?";
            }//IF 
            pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, ExpId);
            Resultado = pst.executeQuery();
            OpenExpData(Resultado, ExpType, ExpName);
            conn.commit();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbconn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(dbconn.class.getName()).log(Level.SEVERE, null, ex);
        }
          catch (NullPointerException ex){
               Logger.getLogger(dbconn.class.getName()).log(Level.SEVERE, null, ex);
          }
        return Resultado;
    }//GetDataFromID
    
    public  void DeleteExpData (String IdGet, String ExpType){
    }//DeleteExpData
    private void OpenExpData(ResultSet Sql, String ExpType, String ExpName) throws SQLException{
        ArrayList<Integer>Id = new ArrayList<Integer>();
        ArrayList<Float>Var1 = new ArrayList<Float>();
        String VarType1 = "";
        ArrayList<Float>Var2 = new ArrayList<Float>();
        String VarType2 = "";
        try {
            while (Sql.next()){
                if (ExpType.equals("T")==true){
                    Id.add(Sql.getInt("id_Temperatura"));
                    Var1.add(Sql.getFloat("Temp_Y"));
                    VarType1 = Sql.getString("Tipo_Y");
                    Var2.add(Sql.getFloat("Tiempo_X"));
                    VarType2 = Sql.getString("Tipo_X");
                }//exp type == t 
                if (ExpType.equals("C")==true){
                    Id.add(Sql.getInt("idCaida_Libre"));
                    Var1.add(Sql.getFloat("tiempo_X"));
                    VarType1 = Sql.getString("Tipo_Var_X");
                    Var2.add(Sql.getFloat("ExpAltura_y"));
                    VarType2 = Sql.getString("Tipo_Var_Y");
                }//exp type == t 
            }//while sql.next
        } catch (SQLException ex) {
            Logger.getLogger(Experiment.class.getName()).log(Level.SEVERE, null, ex);
        } 
        float [] Var1Array = new float [Var1.size()];
        float [] Var2Array = new float [Var2.size()];
        int Count=0;
        for (int i=0; i<Var1.size();i++){
            Var1Array[i] = Var1.get(i);
            Var2Array[i] = Var2.get(i);
            Count++;
        }//for 
        FRAMES.ComienzoExp Temp = new FRAMES.ComienzoExp(true, Var1Array, Var2Array,
                                                         Count, VarType1, VarType2,
                                                         ExpName, ExpType);
        Temp.setLocationRelativeTo(null);
        Temp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Temp.setResizable(false);
        Temp.setVisible(true);
    }//OpenExpData
   
}//public class dbconn
