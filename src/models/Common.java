package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

import java.sql.*;
import java.util.ArrayList;

public class Common {
    //constantes
    static final String MENSAJE_ERROR="Algo no ha funcionado de manera esperada";
    static final String MENSAJE_EXITO_INSERCION="Exito en la operación!!!";

    //Ventanas informativas.
    public void vtnAlertaError(){
        Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
        dialogoAlerta.setTitle("ERROR");
        dialogoAlerta.setHeaderText(null);
        dialogoAlerta.setContentText(MENSAJE_ERROR);
        dialogoAlerta.initStyle(StageStyle.UTILITY);
        dialogoAlerta.showAndWait();
    }
    public void vtnMensajeExitoInsercion(){
        Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
        dialogoAlerta.setTitle("ÉXITO");
        dialogoAlerta.setHeaderText(null);
        dialogoAlerta.setContentText(MENSAJE_EXITO_INSERCION);
        dialogoAlerta.initStyle(StageStyle.UTILITY);
        dialogoAlerta.showAndWait();
    }

    //Metodo crear conexion base de datos
    public Connection getConexion(){
        Connection conexion=null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_sistemas",
                    "sistemas",
                    "sistemas");
        } catch (SQLException throwables) {
            new Common().vtnAlertaError();
            System.out.println("Error en conexion");
        }
        return conexion;
    }
    //metodo para sacar datos de un empleado de una tabla de la base de datos
    public ObservableList<Employe> obtenerEmpleados(){
        ObservableList<Employe> listaEmpleados = FXCollections.observableArrayList();
        Connection connection = getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = connection.prepareStatement("SELECT * FROM personal");
            datos = query.executeQuery();
            while(datos.next()){
                int id = datos.getInt(1);
                String nombre = datos.getString(2);
                String apellidos = datos.getString(3);
                long numSS = datos.getLong(4);
                int sueldo = datos.getInt(5);
                String dni = datos.getString(6);

                Employe empleado = new Employe(id, nombre, apellidos, numSS, sueldo, dni);

                listaEmpleados.add(empleado);
            }
        } catch (SQLException e) {
            vtnAlertaError();
        }
        return listaEmpleados;
    }

    //metodo para sacar datos de un cliente de una tabla de la base de datos
    public ObservableList<Clientes> obtenerClientes(){
        ObservableList<Clientes> listaClientes = FXCollections.observableArrayList();
        Connection connection = getConexion();
        PreparedStatement query;
        ResultSet datos;
        try {
            query = connection.prepareStatement("SELECT * FROM clientes");
            datos = query.executeQuery();
            while(datos.next()){
                int idCliente = datos.getInt(1);
                String nombre = datos.getString(2);
                String apellidos = datos.getString(3);
                String email = datos.getString(4);
                int poblacion = datos.getInt(5);

                Clientes clientes = new Clientes(idCliente,nombre, apellidos, poblacion, email);
                listaClientes.add(clientes);
            }
        } catch (SQLException e) {
            vtnAlertaError();
        }
        return listaClientes;
    }

    //metodo para sacar datos de un empleado de una tabla de la base ded atos
    public ObservableList<Proveedores> obtenerProveedores(){
        ObservableList<Proveedores> listaProveedores = FXCollections.observableArrayList();
        Connection connection = getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = connection.prepareStatement("SELECT * FROM proveedores");
            datos = query.executeQuery();
            while(datos.next()){
                int id = datos.getInt(1);
                String nombre = datos.getString(2);


                Proveedores proveedores = new Proveedores(id, nombre);
                listaProveedores.add(proveedores);
            }
        } catch (SQLException e) {
            vtnAlertaError();
            e.printStackTrace();
        }
        return listaProveedores;
    }

    //Metodo para recorrer ventas
    public ObservableList<Ventas> obtenerVentas(){
        ObservableList<Ventas> listaVentas = FXCollections.observableArrayList();
        ObservableList<Productos> listaProductos = obtenerProductos();
        ObservableList<Clientes> listaClientes = obtenerClientes();
        ObservableList<Employe> listaVendedores = obtenerEmpleados();
        Connection connection = getConexion();
        PreparedStatement query;
        ResultSet datos;
        try {
            query = connection.prepareStatement("SELECT * FROM ventas");
            datos = query.executeQuery();
            while(datos.next()){
                String producto="", cliente="", vendedor="";
                int id_cliente = datos.getInt(2);
                int id_producto = datos.getInt(3);
                int id_personal = datos.getInt(4);
                int cantidad = datos.getInt(5);
                for (Productos p:listaProductos) {
                    if(p.getIdProducto()==id_producto){
                        producto = p.getMarca()+" "+p.getModelo();
                        System.out.println(producto);
                    }
                }
                for (Clientes c:listaClientes) {
                    if(c.getIdCliente()==id_cliente){
                        cliente = c.getNombre()+" "+c.getApellidos();
                        System.out.println(cliente);
                    }
                }
                for (Employe e:listaVendedores) {
                    if(e.getIdEmpleado() == id_personal){
                        vendedor = e.getNombre()+" "+e.getApellido();
                        System.out.println(vendedor);
                    }
                }

                Ventas ventas = new Ventas(producto, cliente, vendedor, id_producto, id_cliente, id_personal, cantidad);
                listaVentas.add(ventas);
            }
        } catch (SQLException e) {
            vtnAlertaError();
        }
        return listaVentas;
    }

    public ObservableList<Poblacion> obtenerPoblaciones() {
        ObservableList<Poblacion> listaPoblaciones = FXCollections.observableArrayList();
        Connection connection = getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        try {
            query = connection.prepareStatement("SELECT * FROM poblaciones");
            datos = query.executeQuery();
            while(datos.next()){
                int cod_postal = datos.getInt(1);
                String poblacion = datos.getString(2);
                String provincia = datos.getString(3);

                Poblacion newPoblacion = new Poblacion(cod_postal, poblacion,provincia);

                listaPoblaciones.add(newPoblacion);
            }
        } catch (SQLException e) {
            vtnAlertaError();
        }
        return listaPoblaciones;
    }

    public ObservableList<Productos> obtenerProductos() {
        ObservableList<Productos> listaProductos = FXCollections.observableArrayList();
        Connection connection = getConexion();
        PreparedStatement query;
        ResultSet datos = null;
        String nuevo=null;
        try {
            query = connection.prepareStatement("SELECT * FROM productos");
            datos = query.executeQuery();
            while(datos.next()){
                int id = datos.getInt(1);
                Boolean isNuevo = datos.getBoolean(2);
                String tipoProducto = datos.getString(3);
                int stock = datos.getInt(4);
                String marca = datos.getString(5);
                String modelo = datos.getString(6);
                float precioCompra = datos.getInt(7);
                float precioVenta = datos.getInt(8);

                if(isNuevo){
                    nuevo="Sí";
                }else{
                    nuevo="No";
                }

                Productos newProducto = new Productos(id, nuevo, tipoProducto, stock, marca, modelo, precioCompra, precioVenta);

                listaProductos.add(newProducto);
            }
        } catch (SQLException e) {
            vtnAlertaError();
        }
        return listaProductos;
    }
}
