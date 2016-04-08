/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonSAP;

import java.awt.Component;
import java.util.Hashtable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author DESASOLRPV
 */
public class EachRowRenderer implements TableCellRenderer {
  protected Hashtable renderers;

  protected TableCellRenderer renderer, defaultRenderer;

  public EachRowRenderer() {
    renderers = new Hashtable();
    defaultRenderer = new DefaultTableCellRenderer();
  }

  public void add(int row, TableCellRenderer renderer) {
    renderers.put(new Integer(row), renderer);
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    renderer = (TableCellRenderer) renderers.get(new Integer(row));
    if (renderer == null) {
      renderer = defaultRenderer;
    }
    return renderer.getTableCellRendererComponent(table, value, isSelected,
        hasFocus, row, column);
  }
}
