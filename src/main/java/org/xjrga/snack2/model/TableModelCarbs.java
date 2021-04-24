/*
 * Snack: Learning Software for Nutrition
 * Copyright (C) 2018 Jorge R Garcia de Alba
 * License: http://www.gnu.org/licenses/gpl.html GPL version 2 or higher
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package io.github.xjrga.snack2.model;

import io.github.xjrga.snack2.data.DbLink;
import io.github.xjrga.snack2.other.Log;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class TableModelCarbs extends DefaultTableModel implements RoundUp {
    private final DbLink dbLink;
    private Vector columns;
    private Integer precision = 0;

    public TableModelCarbs(DbLink dbLink) {
        this.dbLink = dbLink;
        this.setColumnIdentifiers();
    }

    private void setColumnIdentifiers() {
        columns = new Vector();
        columns.add("Name");
        columns.add("Weight");
        columns.add("Carbs");
        columns.add("Fiber");
        columns.add("Insoluble");
        columns.add("Soluble");
        columns.add("Sucrose");
        columns.add("Fructose");
        columns.add("Lactose");
        this.setColumnIdentifiers(columns);
    }

    public Class getColumnClass(int i) {
        Class returnValue = Object.class;
        if (i == 0) {
            returnValue = String.class;
        } else {
            returnValue = Double.class;
        }
        return returnValue;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    public void reload(Integer mixid) {
        Vector row = null;
        Vector table = new Vector();
        try {
            LinkedList list = (LinkedList) dbLink.MixResult_Select(mixid, precision);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HashMap rowm = (HashMap) it.next();
                String Name = (String) rowm.get("Name"); //0
                Double Sucrose = (Double) rowm.get("Sucrose");
                Double Fructose = (Double) rowm.get("Fructose");
                Double Lactose = (Double) rowm.get("Lactose");
                Double Fiber = (Double) rowm.get("Fiber");
                Double Weight = (Double) rowm.get("Weight");
                Double DigestibleCarbohydrate = (Double) rowm.get("DigestibleCarbs");
                Double FiberInsoluble = (Double) rowm.get("FiberInsoluble");
                Double FiberSoluble = (Double) rowm.get("FiberSoluble");
                row = new Vector();
                row.add(Name);
                row.add(Weight);
                row.add(DigestibleCarbohydrate);
                row.add(Fiber);
                row.add(FiberInsoluble);
                row.add(FiberSoluble);
                row.add(Sucrose);
                row.add(Fructose);
                row.add(Lactose);
                table.add(row);
            }
            this.setDataVector(table, columns);
        } catch (SQLException e) {
            Log.getLog().start("files/exception.log");
            Log.getLog().logMessage(e.toString());
            Log.getLog().write();
            Log.getLog().close();
            e.printStackTrace();
        }
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }
}
