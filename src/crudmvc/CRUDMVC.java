
package crudmvc;

import Controlador.ctrlProducto;
import Modelo.ConsultasProducto;
import Modelo.Producto;
import Vista.frmProducto;

public class CRUDMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        Producto mod=new Producto();
        ConsultasProducto modC=new ConsultasProducto();
        frmProducto frm=new frmProducto();
        
        ctrlProducto ctrl=new ctrlProducto(mod, modC, frm);
        ctrl.iniciar();
        frm.setVisible(true);
    }
    
}
