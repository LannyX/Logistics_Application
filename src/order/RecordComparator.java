/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.util.Comparator;

/**
 *
 * @author dell
 */
public class RecordComparator implements Comparator<FcltRecord> {

    @Override
    public int compare(FcltRecord r1, FcltRecord r2) {
        return (r1.arrivalDay-r2.arrivalDay);
    }
    
}
