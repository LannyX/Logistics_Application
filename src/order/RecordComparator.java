package order;

import java.util.Comparator;

/**
 * Comparator for comparing two facility records based on their arrival days.
 */
public class RecordComparator implements Comparator<FcltRecord> {

    @Override
    public int compare(FcltRecord r1, FcltRecord r2) {
        return (r1.arrivalDay-r2.arrivalDay);
    }
    
}
