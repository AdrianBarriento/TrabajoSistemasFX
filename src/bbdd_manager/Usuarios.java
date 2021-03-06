package bbdd_manager;


import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import models.Common;
import models.Employe;
import views.ModeloTablaVentas;

import javax.naming.NamingEnumeration;
import java.sql.*;

public class Usuarios {


    public void login(TextField txtDni, AnchorPane id_paneLogin, AnchorPane id_base){
        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = conexion.prepareStatement("SELECT DNI FROM personal");
            datos = query.executeQuery();
            while(datos.next()){
                if(datos.getString("DNI").compareToIgnoreCase(txtDni.getText()) == 0){
                    id_paneLogin.setVisible(false);
                    id_base.setVisible(true);
                }
            }
        } catch (SQLException e) {
            new Common().vtnAlertaError();
        }
    }

    public void loginEnter(TextField txtDni, AnchorPane id_paneLogin, AnchorPane id_base){
        txtDni.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                boolean okAcces = false;
                if(keyEvent.getCode()== KeyCode.ENTER){
                    Connection conexion=new Common().getConexion();
                    PreparedStatement query;
                    ResultSet datos = null;
                    try {
                        query = conexion.prepareStatement("SELECT DNI FROM personal");
                        datos = query.executeQuery();
                        while(datos.next()){
                            if(datos.getString("DNI").compareToIgnoreCase(txtDni.getText()) == 0) {

                                okAcces = true;
                            }
                        }
                        if(okAcces){
                            id_paneLogin.setVisible(false);
                            id_base.setVisible(true);
                        }else{
                            new Common().vtnAlertaErrorLogin();
                        }

                    } catch (SQLException e) {
                        new Common().vtnAlertaError();
                    }
                }
            }
        });

    }

    public void newEmploye(TableView<Employe> tableView, TextField nombre, TextField apellido, TextField numSegSocial, TextField sueldo, TextField dni){
        Connection conexion=new Common().getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = conexion.prepareStatement("INSERT INTO personal(Nombre, Apellidos, NumSegSocial, Sueldo, DNI) VALUES (?,?,?,?,?)");

            query.setString(1, nombre.getText());
            query.setString(2, apellido.getText());
            query.setLong(3, Long.parseLong(numSegSocial.getText()));
            query.setInt(4, Integer.parseInt(sueldo.getText()));
            query.setString(5, dni.getText());
            query.execute();

            ObservableList<Employe> data = new Common().obtenerEmpleados();

            tableView.setItems(data);
            new Common().vtnMensajeExitoInsercion();

        } catch (SQLException e) {
            new Common().vtnAlertaError();
        }
    }
}
