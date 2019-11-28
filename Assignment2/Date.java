/**
 * Date.java
 * 
 * This class represents a Date object
 * 
 * @version: (2020a)
 * @author: David Rashba
 */

public class Date
{
    // days of a month - between 1-31
    private int _day;
    // months of a year - between 1-12
    private int _month;
    // year represented by 4 digit integer
    private int _year;

    public final int MIN_DAYS_OF_MONTH = 1;

    public final int FEBRUARY = 2;
    public final int MAX_MONTHS_OF_YEAR = 12;
    public final int MIN_MONTHS_OF_YEAR = 1;

    public final int MIN_YEAR = 1000;
    public final int MAX_YEAR = 9999;
    
    public final int DEFAULT_DAY = 1;
    public final int DEFAULT_MONTH = 1;
    public final int DEFAULT_YEAR = 2000;
    

    // Constructors
    /**
     * creates a new Date object if the date is valid, otherwise creates the date 1/1/2000
     * 
     * @param day the day in the month (1-31)
     * @param month the month in the year (1-12)
     * @param year the year (4 digits)
     */
    public Date(int day, int month, int year){
        // True if day between 1 - maxDaysOfMonth, and month between 1 - 12
        boolean isValidDate = (day >= MIN_DAYS_OF_MONTH) && (day <= maxDaysOfMonth(month, year)) 
            && (month >= MIN_MONTHS_OF_YEAR) && (month <= MAX_MONTHS_OF_YEAR)
            && (year >= MIN_YEAR) && (year <= MAX_YEAR);

        if(isValidDate){
            this._day = day;
            this._month = month;
            this._year = year;
        }else{
            this._day = DEFAULT_DAY;
            this._month = DEFAULT_MONTH;
            this._year = DEFAULT_YEAR;
        }

    }

    /**
     * copy constructor
     * 
     * @param other the date to be copied
     */
    public Date(Date other){
        this._day = other.getDay();
        this._month = other.getMonth();
        this._year = other.getYear();
    }

    // Getters

    /**
     * gets the day
     * 
     * @return the day
     */
    public int getDay(){
        return this._day;
    }

    /**
     * gets the month
     * 
     * @return the month
     */
    public int getMonth(){
        return this._month;
    }

    /**
     * gets the year
     * 
     * @return the year
     */
    public int getYear(){
        return this._year;
    }

    //Setters

    /**
     * sets the day (only if date remains valid)
     * 
     * @param dayToSet the day value to be set
     */
    public void setDay(int dayToSet){
        // Only set new day if its a valid day at objects' month
        if(isValidDayOfMonth(dayToSet, this._month, this._year)){
            this._day = dayToSet;
        }
    }

    /**
     * set the month (only if date remains valid)
     * 
     * @param monthToSet the month value to be set
     */
    public void setMonth(int monthToSet){
        boolean isValidMonth = (monthToSet >= MIN_MONTHS_OF_YEAR) && (monthToSet <= MAX_MONTHS_OF_YEAR);
        // Only set month if the objects' day is valid in new month, and month is valid
        if(isValidDayOfMonth(this._day, monthToSet, this._year) && isValidMonth == true){
            this._month = monthToSet;
        }
    }

    /**
     * sets the year (only if date remains valid)
     * 
     * @param yearToSet the year value to be set
     */
    public void setYear(int yearToSet){
        // avoid setting 29th of february on a non leap year 
        if(this._month == FEBRUARY && isLeapYear(yearToSet) == false && this._day > 28){
            // dont set new year, because it is 29/02 in a non leap year
        }else if(yearToSet >= MIN_YEAR && yearToSet <= MAX_YEAR){
            this._year = yearToSet;
        }
    }

    /**
     * check if 2 dates are the same
     * 
     * @param other the date to compare this date to
     * @return true if the dates are the same
     */
    public boolean equals(Date other){
        boolean isDayEqual = this._day == other.getDay();
        boolean isMonthEqual = this._month == other.getMonth();
        boolean isYearEqual = this._year == other.getYear();

        return isDayEqual && isMonthEqual && isYearEqual;
    }

    /**
     * check if this date is before other date
     * 
     * @param other date to compare this date to
     * @return true if this date is before other date
     */
    public boolean before(Date other){
        // calculate how many days since begining of counting christian years passed for each object
        int thisDateDays = calculateDate(getDay(), getMonth(), getYear());
        int otherDateDays = calculateDate(other.getDay(), other.getMonth(), other.getYear());

        return thisDateDays < otherDateDays;
    }

    /**
     * check if this date is after other date
     * 
     * @param other date to compare this date to
     * @return true if this date is before other date
     */
    public boolean after(Date other){
        // True if other date object is before this object
        return other.before(this);
    }

    /**
     * calculates the difference in days between two dates
     * 
     * @param other the date to calculate the difference between
     * @return the number of days between the dates
     */
    public int difference(Date other){
        // calculate how many days since begining of counting christian years passed for each object
        int thisDateDays = calculateDate(getDay(), getMonth(), getYear());
        int otherDateDays = calculateDate(other.getDay(), other.getMonth(), other.getYear());
        // subtruct and return as absolute value
        int difference = thisDateDays - otherDateDays;
        return Math.abs(difference);
    }

    /**
     * returns a String that represents this date
     * 
     * @return String that represents this date in the following format: day/month/year for example: 02/03/1998
     */
    public String toString() {
        // string shoud be in format of day/month/year
        // for single digits value pad with 0 (01/02/2020)
        return String.format("%02d/%02d/%04d", this.getDay(), this.getMonth(), this.getYear());
    }

    /**
     * calculate the date of tomorrow
     * 
     * @return the date of tomorrow
     */
    public Date tomorrow(){
        // next day is a valid day
        if(isValidDayOfMonth(getDay() + 1, getMonth(), getYear())){
            return new Date(getDay() + 1, getMonth(), getYear());      
            
        }else if(getMonth() + 1 > MAX_MONTHS_OF_YEAR && getYear() + 1 > MAX_YEAR){ // not valid day, not valid month, not valid year
            // next day is 1st of next month, next month is first of next year, next year is 10,000 -> return 31/12/9999
            return new Date(31,12,9999);
            
        }else if(getMonth() + 1 > MAX_MONTHS_OF_YEAR){ // not valid day, not valid month but valid year
            // next day is 1st of next month, next month is first of next year -> return 1.1 of next year
            return new Date(MIN_DAYS_OF_MONTH,MAX_MONTHS_OF_YEAR, getYear() + 1);
            
        }else{ // not valid day but valid month and year
            // next day is 1st of next month -> return 1st of next month
            return new Date(MIN_DAYS_OF_MONTH, getMonth() + 1, getYear());
        }
    }

    /**
     * calculate the day of the week that this date occurs on 0-Saturday 1-Sunday 2-Monday etc.
     * 
     * @return the day of the week that this date occurs on
     */
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

    /**
     * checks if given year is a leap year
     * 
     * @param year The year to check
     * @return true if given year is a leap year
     */
    private boolean isLeapYear(int year){
        return (year % 4 == 0) || ((year % 100 == 0) && (year % 400 == 0));
    }

    /**
     * calculate how many days are in a given month, handles special case of February
     * 
     * @param month the month to check
     * @param year to check february on a leap year
     * @return the number of days in given month
     */
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

    /**
     * checks if a given day is valid on a given date
     * 
     * @param day the day of month to check
     * @param month the month to check
     * @param year the year to check
     * @return true if given day is valid on given date
     */
    private boolean isValidDayOfMonth(int day, int month, int year){
        if(day < MIN_DAYS_OF_MONTH || day > maxDaysOfMonth(month, year)){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * computes the day number since the beginning of the Christian counting of years 
     * 
     * @param day the day of the date
     * @param month the month of the date
     * @param year the year of the date
     * @return number of days since the beginning of the christian counting of years to given date
     */
    private int calculateDate (int day, int month, int year) { 
        if (month < 3) { 
            year--; 
            month = month + 12; 
        } 
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62); 
    }  

}