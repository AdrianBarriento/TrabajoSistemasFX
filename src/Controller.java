<<<<<<< HEAD
import javafx.scene.control.TableView;
=======
>>>>>>> 0e9758ce37e961f3f8a62cbd4a4aba2e468bfebc
import bbdd_manager.Usuarios;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import views.ModeloTablaEmpleados;

public class Controller {
    //TextField del login
    public TextField id_txtLogin;
    public AnchorPane id_paneLogin;
    public AnchorPane id_base;

    //Textfield de crear empleados
    public TextField id_crearEmpleadoNombre;
    public TextField id_crearEmpleadoApellido;
    public TextField id_crearEmpleadoNumSS;
    public TextField id_crearEmpleadoSueldo;
    public TextField id_crearEmpleadoFecha;
    public TextField id_crearEmpleadoDNI;

    //AnchorPanes
    public AnchorPane id_crearEmpleado;
    public AnchorPane id_cmbSeleccion;
    public AnchorPane id_Footer;
    public AnchorPane id_tablaGestion;

    //Image Views
    public ImageView id_icUsuarios;
    public ImageView id_icGestion;
    public ImageView id_icOperaciones;
    public ImageView id_icSalir;

<<<<<<< HEAD

    //ComboBox
    public ComboBox id_cmbCat_gestiion;

    //Tabla
    public TableView id_tabla;

    private ObservableList<String> rellenarComboBox = FXCollections.observableArrayList("Usuarios", "Proveedores", "Clientes");

=======
>>>>>>> 0e9758ce37e961f3f8a62cbd4a4aba2e468bfebc
    public void onExitButtonClicked(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void comprobarEmpleado(MouseEvent mouseEvent){new Usuarios().login(id_txtLogin, id_paneLogin);}

    public void ventanaCrearEmpleado(MouseEvent mouseEvent){
        id_crearEmpleado.setVisible(true);
    }

    public void ventanaGestion(MouseEvent mouseEvent){
        id_crearEmpleado.setVisible(false);
        id_cmbSeleccion.setVisible(true);
    }
<<<<<<< HEAD

    public void ventanaTabla(MouseEvent mouseEvent){
        id_tablaGestion.setVisible(true);
        id_Gestion.setVisible(false);
        id_crearEmpleado.setVisible(false);

        switch (String.valueOf(id_cmbCat_gestiion.getValue())){
            case "Usuarios":
                new ModeloTablaEmpleados().crearTabla(id_tabla);
                break;
            case "Proveedores":
                break;
            case "Clientes":
                break;
        }

    }
=======
>>>>>>> 0e9758ce37e961f3f8a62cbd4a4aba2e468bfebc

}
