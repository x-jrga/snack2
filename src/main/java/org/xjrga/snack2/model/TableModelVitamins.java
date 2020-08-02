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
package org.xjrga.snack2.model;

import org.xjrga.snack2.data.DbLink;
import org.xjrga.snack2.other.Log;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class TableModelVitamins extends DefaultTableModel implements RoundUp {
    private final DbLink dbLink;
    private Vector columns;
    private Integer precision = 0;

    public TableModelVitamins(DbLink dbLink) {
        this.dbLink = dbLink;
        this.setColumnIdentifiers();
    }

    private void setColumnIdentifiers() {
        columns = new Vector();
        columns.add("Name");
        columns.add("Weight");
        columns.add("VitaminA");
        columns.add("VitaminD");
        columns.add("VitaminE");
        columns.add("VitaminC");
        columns.add("Thiamin");
        columns.add("Riboflavin");
        columns.add("Niacin");
        columns.add("VitaminB6");
        columns.add("Folate");
        columns.add("VitaminB12");
        columns.add("VitaminK");
        columns.add("Pantothenic Acid");
        columns.add("Choline");
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
            LinkedList list = (LinkedList) dbLink.MixResultDW_Select(mixid, precision);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HashMap rowm = (HashMap) it.next();
                String Name = (String) rowm.get("Name"); //0
                Double VitaminA = (Double) rowm.get("VitaminA");
                Double VitaminE = (Double) rowm.get("VitaminE");
                Double VitaminD = (Double) rowm.get("VitaminD");
                Double VitaminC = (Double) rowm.get("VitaminC");
                Double Thiamin = (Double) rowm.get("Thiamin");
                Double Riboflavin = (Double) rowm.get("Riboflavin");
                Double Niacin = (Double) rowm.get("Niacin");
                Double Pantothenic = (Double) rowm.get("Pantothenic");
                Double VitaminB6 = (Double) rowm.get("VitaminB6");
                Double VitaminB12 = (Double) rowm.get("VitaminB12");
                Double Choline = (Double) rowm.get("Choline");
                Double VitaminK = (Double) rowm.get("VitaminK");
                Double Folate = (Double) rowm.get("Folate");
                Double Weight = (Double) rowm.get("Weight");
                row = new Vector();
                row.add(Name);
                row.add(Weight);
                row.add(VitaminA);
                row.add(VitaminD);
                row.add(VitaminE);
                row.add(VitaminC);
                row.add(Thiamin);
                row.add(Riboflavin);
                row.add(Niacin);
                row.add(VitaminB6);
                row.add(Folate);
                row.add(VitaminB12);
                row.add(VitaminK);
                row.add(Pantothenic);
                row.add(Choline);
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
