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

public class TableModelMixComparison extends DefaultTableModel implements RoundUp {

    public static int c = 0;
    private final DbLink dbLink;
    private Vector columns;
    private Integer precision = 0;

    public TableModelMixComparison(DbLink dbLink) {
        this.dbLink = dbLink;
        this.setColumnIdentifiers();
    }

    private void setColumnIdentifiers() {
        columns = new Vector();
        columns.add("Nutrient");
        columns.add("Mix 1");
        columns.add("Mix 2");
        columns.add("Diff");
        this.setColumnIdentifiers(columns);
    }

    public Class getColumnClass(int i) {
        Class returnValue = Object.class;
        switch (i) {
            case 0:
                //Nutrient
                returnValue = String.class;
                break;
            case 1:
                //Mix 1 Value
                returnValue = Double.class;
                break;
            case 2:
                //Mix 2 Value
                returnValue = Double.class;
                break;
            case 3:
                //Difference
                returnValue = Double.class;
                break;
        }
        return returnValue;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    public void reload(Integer MixId1, Integer MixId2) {
        Vector row = null;
        Vector table = new Vector();
        try {
            LinkedList list = (LinkedList) dbLink.Mix_GetDiff(MixId1, MixId2, precision);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HashMap rowm = (HashMap) it.next();
                String nutrient = (String) rowm.get("NUTRIENT");
                double mix1 = (double) rowm.get("MIX1");
                double mix2 = (double) rowm.get("MIX2");
                double diff = (double) rowm.get("DIFF");
                row = new Vector();
                row.add(nutrient);
                row.add(mix1);
                row.add(mix2);
                row.add(diff);
                table.add(row);
            }
            list = (LinkedList) dbLink.Mix_GetFQDiff(MixId1, MixId2);
            it = list.iterator();
            while (it.hasNext()) {
                HashMap rowm = (HashMap) it.next();
                String nutrient = (String) rowm.get("NUTRIENT");
                double mix1 = (double) rowm.get("MIX1");
                double mix2 = (double) rowm.get("MIX2");
                double diff = (double) rowm.get("DIFF");
                row = new Vector();
                row.add(nutrient);
                row.add(mix1);
                row.add(mix2);
                row.add(diff);
                table.add(row);
            }
            list = (LinkedList) dbLink.Mix_GetMealGIDiff(MixId1, MixId2, precision);
            it = list.iterator();
            while (it.hasNext()) {
                HashMap rowm = (HashMap) it.next();
                String nutrient = (String) rowm.get("NUTRIENT");
                double mix1 = (double) rowm.get("MIX1");
                double mix2 = (double) rowm.get("MIX2");
                double diff = (double) rowm.get("DIFF");
                row = new Vector();
                row.add(nutrient);
                row.add(mix1);
                row.add(mix2);
                row.add(diff);
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
