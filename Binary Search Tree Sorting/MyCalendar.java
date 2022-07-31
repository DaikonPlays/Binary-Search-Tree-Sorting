/**
 * Name: Kevin Yan
 * Email: kevinyan904@gmail.com
 * Sources used: tutors
 * 
 * calendar program to book events. 
 * Prevents double booking
 */

 /**
 * implements the methods for booking a time
 * instance variable:
 * calendar, map of booked times
 */
public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;
    /**
     * initiates calendar 
     */
    public MyCalendar() {
        calendar = new MyTreeMap<Integer, Integer>();
    }
    /**
     * books a time from start to end. if double booked,
     * return false
     * @param start time to book
     * @param end time to book
     * @return true if booked, false if not
     */
    public boolean book(int start, int end) {
        if(start < 0 || start >= end) {
            throw new IllegalArgumentException();
        }
        //calls ceilingKey and floorKey and saves variable for booking
        Integer firststart = calendar.ceilingKey(start); 
        Integer lastend = calendar.floorKey(start); 
        //checks if it can be booked
        if((lastend == null || calendar.get(lastend) <= start) && (firststart == null || firststart >= end)){
                calendar.put(start, end);
                return true;
        }
        //if double booked, returns false
        else{
            return false;
        }
    }
    /**
     * gets the calendar
     * @return calender tree 
     */
    public MyTreeMap<Integer, Integer> getCalendar(){
        return this.calendar;
    }
}