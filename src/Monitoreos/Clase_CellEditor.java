package Monitoreos;




import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author VS3XXBD
 */
public class Clase_CellEditor extends DefaultCellEditor implements TableCellRenderer{
    private final JComponent component = new JCheckBox();                       //Crea el objeto checkBox
    private boolean value = false;                                              //Establece los valores de la celda como falsos
    
    
    public Clase_CellEditor()                                                   //Constructor de la clase
    {                                                     
        super( new JCheckBox() );                                               //Utiliza el constructor de JCheckBox
    }

    
    @Override
    public Object getCellEditorValue() {                                        //Obtiene el valor de la celda
        return ((JCheckBox)component).isSelected();        
    }

   
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) 
    {                                                                           //Edita el componente
        ( (JCheckBox) component).setBackground( new Color(200,200,0) );         //Establece el color de fondo
        boolean b = ((Boolean) value).booleanValue();                           //Obtiene el valor de la celda y lo coloca en el CheckBox
        ( (JCheckBox) component).setSelected( b );
        return ( (JCheckBox) component);     
    }

    

    public boolean stopCellEditing()                                            //Finaliza la edición de la celda definiendo el valor definido
    {        
        value = ((Boolean)getCellEditorValue()).booleanValue() ;                
        ((JCheckBox)component).setSelected( value );
        return super.stopCellEditing();
    }

    
    @Override                                                                   //Regresa el componente para ser insertado
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
         if (value == null)
            return null;         
         return ( (JCheckBox) component );
    }
}
