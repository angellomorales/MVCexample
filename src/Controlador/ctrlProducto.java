
package Controlador;

import Modelo.ConsultasProducto;
import Modelo.Producto;
import Vista.frmProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ctrlProducto implements ActionListener{
    
    private Producto mod;
    private ConsultasProducto modC;
    private frmProducto frm;

    public ctrlProducto(Producto mod, ConsultasProducto modC, frmProducto frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        //agregar listener a los botones dentro del constructor
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
    }
    
    public void iniciar(){
        frm.setTitle("Productos");
        frm.setLocationRelativeTo(null);
        frm.txtId.setVisible(false);
    }
    
    //metodo actionPerformed es el mismo qus se crea cuando se hace 
    //doble click en el form y crea los metodos automaticamente en botones etc..
    @Override
    public void actionPerformed(ActionEvent e){
        
        //detecta que boton se presiono
        if(e.getSource()==frm.btnGuardar){
            //tomar valores de la caja de texto y meterlas al modelo
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.txtCantidad.getText()));
            try {
                //despues llamar al metodo de guardar el cual llama el modelo y hace la
                //insercion a la base de datos
                if(modC.registrar(mod)){
                    JOptionPane.showMessageDialog(null, "Registro guardado");
                    limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                    limpiar();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ctrlProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //modificar
        if(e.getSource()==frm.btnModificar){
            //tomar valores de la caja de texto y meterlas al modelo
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.txtCantidad.getText()));
            try {
                //despues llamar al metodo modificar el cual llama el modelo y hace la
                //modificacion la base de datos
                if(modC.modificar(mod)){
                    JOptionPane.showMessageDialog(null, "Registro modificado");
                    limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al modificar");
                    limpiar();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ctrlProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         //Eliminar
        if(e.getSource()==frm.btnEliminar){
            //tomar valores de la caja de texto y meterlas al modelo
            mod.setId(Integer.parseInt(frm.txtId.getText()));

            try {
                //despues llamar al metodo elminar el cual llama el modelo y hace la
                //eliminacion en la base de datos
                if(modC.eliminar(mod)){
                    JOptionPane.showMessageDialog(null, "Registro eliminado");
                    limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                    limpiar();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ctrlProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Buscar
        if(e.getSource()==frm.btnBuscar){
            //tomar valores de la caja de texto y meterlas al modelo
            mod.setCodigo(frm.txtCodigo.getText());

            try {
                //despues llamar al metodo buscar el cual llama el modelo y hace la
                //busqueda en la base de datos
                if(modC.buscar(mod)){
                   frm.txtId.setText(String.valueOf(mod.getId()));
                   frm.txtCodigo.setText(mod.getCodigo());
                   frm.txtNombre.setText(mod.getNombre());
                   frm.txtPrecio.setText(String.valueOf(mod.getPrecio()));
                   frm.txtCantidad.setText(String.valueOf(mod.getCantidad()));
                }else{
                    JOptionPane.showMessageDialog(null, "Registro no encontrado");
                    limpiar();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ctrlProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Limpiar
        if(e.getSource()==frm.btnLimpiar){
            limpiar();
        }
    }
    
    public void limpiar(){
        frm.txtCantidad.setText(null);
        frm.txtCodigo.setText(null);
        frm.txtNombre.setText(null);
        frm.txtPrecio.setText(null);
        frm.txtId.setText(null);
    }
}
