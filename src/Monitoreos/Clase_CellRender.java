package Monitoreos;




import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;



/**
 *
 * @author VS3XXBD
 */
public class Clase_CellRender extends JCheckBox implements TableCellRenderer{
    private final JComponent component = new JCheckBox();

    
    public Clase_CellRender()                                                   //Constructor de la clase
    {
        setOpaque(true);
    }

    @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      
      ( (JCheckBox) component).setBackground( new Color(167,218,251) );         //Color de fondo de la celda
      ((JCheckBox) component).setHorizontalAlignment(0);                        
      boolean b = ((Boolean) value).booleanValue();                             //Obtiene valor boolean y coloca valor en el JCheckBox
      ( (JCheckBox) component).setSelected( b );      
      return ( (JCheckBox) component);
  }
}
