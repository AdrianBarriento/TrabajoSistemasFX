import bbdd_manager.Usuarios;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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

    public void onExitButtonClicked(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void comprobarEmpleado(MouseEvent mouseEvent){new Usuarios().login(id_txtLogin, id_paneLogin, id_base);
    }

}
