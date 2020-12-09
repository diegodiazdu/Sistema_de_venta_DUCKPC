package Controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;

public class alertaColor extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            int stock = Integer.parseInt((String) table.getValueAt(row, 3));

            if (stock <= 3) {
                
                setBackground(Color.RED);
                setForeground(Color.WHITE);
                
                
            }else if(stock>3){
                
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
