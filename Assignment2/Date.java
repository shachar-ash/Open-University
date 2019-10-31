/**
 * Date.java
 * 
 * This class will define a Date object.
 * 
 * @author: David Rashba
 * @version: 30/10/19
 */

public class Date
{
    // days of a month - between 1-31
    private int _day;
    // months of a year - between 1-12
    private int _month;
    // year represented by 4 digit integer
    private int _year;
    
    
    private final int MIN_DAYS_IN_MONTH = 1;
    
    private final int FEBRUARY = 2;
    private final int MAX_MONTHS_IN_YEAR = 12;
    private final int MIN_MONTHS_IN_YEAR = 1;
    
    private final int MIN_YEAR = 1000;
    
    // Constructors
    public Date(int day, int month, int year){
        // True if day between 1 - maxDaysOfMonth, and month between 1 - 12
        boolean isValidDate = (day >= MIN_DAYS_IN_MONTH) && (day <= maxDaysOfMonth(month, year)) 
                           && (month >= MIN_MONTHS_IN_YEAR) && (month <= MAX_MONTHS_IN_YEAR)
                           && (year >= MIN_YEAR);
        
        if(isValidDate){
            this._day = day;
            this._month = month;
            this._year = year;
        }else{
            this._day = 1;
            this._month = 1;
            this._year = 2000;
        }
        
    }
    
    public Date(Date other){
        this._day = other.getDay();
        this._month = other.getMonth();
        this._year = other.getYear();
    }
    
    // Getters
    public int getDay(){
        return this._day;
    }
    
    public int getMonth(){
        return this._month;
    }
    
    public int getYear(){
        return this._year;
    }
    
    //Setters
    public void setDay(int dayToSet){
        // Only set new day if its a valid day at objects' month
        if(isValidDayOfMonth(dayToSet, this._month, this._year)){
            this._day = dayToSet;
        }
    }
    
    public void setMonth(int monthToSet){
        boolean isValidMonth = (monthToSet >= MIN_MONTHS_IN_YEAR) && (monthToSet <= MAX_MONTHS_IN_YEAR);
        // Only set month if the objects' day is valid in new month, and month is valid
        if(isValidDayOfMonth(this._day, monthToSet, this._year) && isValidMonth == true){
            this._month = monthToSet;
        }
    }
    
    public void setYear(int yearToSet){
        // avoid setting 29th of february on a non leap year 
        if(this._month == FEBRUARY && isLeapYear(yearToSet) == false && this._day > 28){
          // dont set new year, because it is probably 29/02 in a non leap year
        }else if(yearToSet >= MIN_YEAR){
            this._year = yearToSet;
        }
    }
    
    // Returns true if Dates are equal
    public boolean equals(Date other){
        boolean isDayEqual = getDay() == other.getDay();
        boolean isMonthEqual = getMonth() == other.getMonth();
        boolean isYearEqual = getYear() == other.getYear();
        
        if(isDayEqual == true && isMonthEqual == true && isYearEqual == true){
            return true;
        }
        else{
            return false;
        }
    }
    
    // Returns true if this date is before other date
    public boolean before(Date other){
        // calculate how many days since begining of counting christian years passed for each object
        int thisDateDays = calculateDate(getDay(), getMonth(), getYear());
        int otherDateDays = calculateDate(other.getDay(), other.getMonth(), other.getYear());
        
        return thisDateDays < otherDateDays;
    }
    
    // Returns true if this date is after other date
    public boolean after(Date other){
        // True if other date object is before this object
        return other.before(this);
    }
    
    // Returns the difference in days between 2 dates
    public int difference(Date other){
        // calculate how many days since begining of counting christian years passed for each object
        int thisDateDays = calculateDate(getDay(), getMonth(), getYear());
        int otherDateDays = calculateDate(other.getDay(), other.getMonth(), other.getYear());
        // subtruct and return as absolute value
        int difference = thisDateDays - otherDateDays;
        return Math.abs(difference);
    }
    
    // Returns a string representation of the date 
    public String toString() {
        // string shoud be in format of day/month/year
        // for single digits value pad with 0 (01/02/2020)
        return String.format("%02d/%02d/%04d", this.getDay(), this.getMonth(), this.getYear());
    }
    
    // Returns a new Date object representing tomorrows date
    public Date tomorrow(){
        // check if tommorows date is a valid day
        if(isValidDayOfMonth(getDay() + 1, getMonth(), getYear())){
            // tommorow is valid, can return a new date
            return new Date(getDay() + 1, getMonth(), getYear());
        }else if(getMonth() + 1 <= MAX_MONTHS_IN_YEAR){
            // tommorow is 1st of next month
            return new Date(1, getMonth() + 1, getYear());
        }else{
            // tommorow is 01/01 of next year
            return new Date(1, 1, getYear() + 1);
        }
    }
    
    // Returns a numeric representation of day of the week,
    // 0 = Saturday... 6 = Friday 
    public int dayInWeek(){
        // Day = (D + (26×(M+1))/10 + Y + Y/4 + C/4 - 2×C) mod 7
        // D = day, 
        //M = month 3 = march.. 
        //january = 13, february = 14 trated as months of last year
        // Y = last 2 digit of the year
        // C = first 2 digits of the year
        int month = getMonth();
        int year = getYear() % 100;
        int century = Math.floorDiv(getYear(), 100);
        
        if(getMonth() < 3){
            // month is jan/feb
            month = getMonth() + 12;
        }
        int day = (getDay() + (26 * (month + 1)) / 10 + year + year / 4 + century / 4 - 2 * century) % 7;
        return day;
    }
    
    
    //////// Private methods
    
    // Return true if it is a leap year
    private boolean isLeapYear(int year){
        return (year % 4 == 0) && (year % 100 == 0) && (year % 400 == 0);
    }
    
    // A formula to calculate how many days are in a given month
    private int maxDaysOfMonth(int month, int year){
        // Formula gives 30/31 to months as needed, and 28 to february
        int maxDays = 28 + (month + Math.floorDiv(month, 8)) % 2 + 2 % month + 2 * Math.floorDiv(1,month);
        // Add 1 to february maxDays when it is a leap year
        if(month == FEBRUARY && isLeapYear(year))
        {
            maxDays++;
        }
        return maxDays;
    }
    
    // Return true if a given day is valid in a given date
    private boolean isValidDayOfMonth(int day, int month, int year){
        if(day < MIN_DAYS_IN_MONTH || day > maxDaysOfMonth(month, year)){
            return false;
        }
        else{
            return true;
        }
    }
    
    // computes the day number since the beginning of the Christian counting of years 
    private int calculateDate (int day, int month, int year) { 
        if (month < 3) { 
           year--; 
           month = month + 12; 
        } 
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62); 
    }  
    
}
