package io.github.xjrga.snack2.other;

import javax.swing.table.TableColumnModel;
import java.awt.event.MouseEvent;

public class TableHeaderCarbs extends MyTableHeader {

    public TableHeaderCarbs(TableColumnModel columnModel) {
        super(columnModel, new String[]{
            "Food Name",
            "Weight (g)",
            "Energy Carbohydrate (Kcal)",
            "Carbohydrate By Difference (g)",
            "Fiber, Total (g)",
            "Digestible Carbohydrate (g)"
        });
    }

    public String getToolTipText(MouseEvent e) {
        return super.getToolTipText(e);
    }
}
