
/**
 * This class will define an FoodItem object
 *
 * @version (2020a)
 * @author David Rashba
 */

public class FoodItem
{
 
    private final String _name;
    private final long _catalogueNumber;
    private int _quantity;
    private Date _productionDate;
    private Date _expiryDate;
    private final int _minTemperature;
    private final int _maxTemperature;
    private int _price;
    
    public final String DEFAULT_ITEM_NAME = "item";
    public final int MIN_CATALOUGE_NUM_LENGTH = 1000;
    public final int MAX_CATALOUGE_NUM_LENGTH = 9999;
    public final int DEFAULT_QUANTITY = 0;
    public final int DEFAULT_PRICE = 1;
    
    /**
     * creates a new FoodItem object
     * 
     * @param name name of food item
     * @param catalogueNumber catalogue number of food item
     * @param quantity quantity of food item
     * @param productionDate production date
     * @param expiryDate expiry date
     * @param minTemperature minimum storage temperature 
     * @param maxTemperature maximum storage temperature
     * @param price unit price 
     */
    public FoodItem(String name, long catalogueNumber, int quantity, 
                    Date productionDate, Date expiryDate,
                    int minTemperature, int maxTemperature,
                    int price){
        // Name
        this._name = name.isEmpty() ?  DEFAULT_ITEM_NAME : name;
        // Catalouge number
        this._catalogueNumber = catalogueNumber < MIN_CATALOUGE_NUM_LENGTH 
                                || catalogueNumber > MAX_CATALOUGE_NUM_LENGTH
                                ? MAX_CATALOUGE_NUM_LENGTH : catalogueNumber;
        // Quantity                        
        this._quantity = quantity < 0 ? DEFAULT_QUANTITY : quantity;
        // Production date
        this._productionDate = new Date(productionDate);
        // Expiry date
        this._expiryDate = expiryDate.before(productionDate) 
                           ? productionDate.tomorrow() : expiryDate;
        // Temperature
        if(minTemperature > maxTemperature){
         this._minTemperature = maxTemperature;
         this._maxTemperature = minTemperature;
        }else{
         this._minTemperature = minTemperature;
         this._maxTemperature = maxTemperature;
        }
        
        // Price
        this._price = price < 0 ? DEFAULT_PRICE : price;
    }
    
    /**
     * copy constructor
     * 
     * @param other the food item to be copied
     */
    public FoodItem(FoodItem other){
        this._name = other.getName();
        this._catalogueNumber = other.getCatalougeNumber();
        this._quantity = other.getQuantity();
        this._productionDate = new Date(other.getProductionDate());
        this._expiryDate = new Date(other.getExpiryDate());
        this._minTemperature = other.getMinTemperature();
        this._maxTemperature = other.getMaxTemperature();
        this._price = other.getPrice();
        
    }
    
    /**
     * gets the name
     * 
     * @return the name
     */
    public String getName(){
        return this._name;
    }
    
    /**
     * gets the catalogue number
     * 
     * @return the catalouge number
     */
    public long getCatalougeNumber(){
        return this._catalogueNumber;
    }
    
    /**
     * gets the quantity
     * 
     * @return the quantity
     */
    public int getQuantity(){
        return this._quantity;
    }
    
    /**
     * gets the production date
     * 
     * @return the production date
     */
    public Date getProductionDate(){
        return this._productionDate;
    }
    
    /**
     * gets the expiry date
     * 
     * @return the expiry date
     */
    public Date getExpiryDate(){
        return this._expiryDate;
    }
    
    /**
     * gets the minimum storage temperature
     * 
     * @return the minimum storage temperature
     */
    public int getMinTemperature(){
        return this._minTemperature;
    }
    
    /**
     * gets the maximum storage temperature
     * 
     * @return the maximum storage temperature
     */
    public int getMaxTemperature(){
        return this._maxTemperature;
    }
    
    /**
     * gets the unit price
     * 
     * @return the unit price
     */
    public int getPrice(){
        return this._price;
    }
    
    /**
     * set the quantity (only if not negative)
     * 
     * @param n the quantity value to be set
     */
    public void setQuantity(int n){
        this._quantity = n < 0 ? this._quantity : n;
    }
    
    /**
     * set the production date (only if not after expiry date )
     * 
     * @param d production date value to be set 
     */
    public void setProductionDate(Date d){
        this._productionDate = d.before(this._expiryDate) 
                               ? this._productionDate : new Date(d);
    }
    
    /**
     * set the expiry date (only if not before production date )
     * 
     * @param d expiry date value to be set
     */
    public void setExpiryDate(Date d){
        this._expiryDate = d.after(_productionDate) ? _expiryDate : new Date(d);
    }
    
    /**
     * set the price (only if positive)
     * 
     * @param n price value to be set
     */
    public void setPrice(int n){
        this._price = n < 1 ? this._price : n;
    }
    
    /**
     * check if 2 food items are the same (excluding the quantity values)
     * 
     * @param other the food item to compare this food item to
     * @return true if the food items are the same
     */
    public boolean equals(FoodItem other){
        boolean isSameName = this._name == other.getName();
        boolean isSameCatalougeNumber = this._catalogueNumber == other.getCatalougeNumber();
        boolean isSameProductionDate = this._productionDate.equals(other.getProductionDate());
        boolean isSameExpiryDate = this._expiryDate.equals(other.getExpiryDate());
        boolean isSameMinTemp = this._minTemperature == other.getMinTemperature();
        boolean isSameMaxTemp = this._maxTemperature == other.getMaxTemperature();
        boolean isSamePrice = this._price == other.getPrice();
        
        return isSameName && isSameCatalougeNumber && isSameProductionDate && isSameExpiryDate
               && isSameMinTemp && isSameMaxTemp && isSamePrice;
    }
    
    /**
     * check if this food item is fresh on the date d
     * 
     * @param d date to check
     * @return true if this food item is fresh on the date d
     */
    public boolean isFresh(Date d){
        return d.before(this._expiryDate) && d.after(this._productionDate);
    }
    
    /**
     * returns a String that represents this food item
     * 
     * @return String that represents this food item in the following format:
     * <br>FoodItem: milk CatalogueNumber: 1234 ProductionDate: 14/12/2019 ExpiryDate: 21/12/2019 Quantity: 3
     */
    public String toString(){
        return "FoodItem: " + this._name + "\tCatalougeNumber: " + this._catalogueNumber 
                + "\tProductionDate: " + this._productionDate.toString() + "\tExpiryDate: "
                + this._expiryDate.toString() + "\tQuantity: " + this._quantity;
    }
    
    /**
     * check if this food item is older than other food item
     * 
     * @param other food item to compare this food item to
     * @return true if this food item is older than other date
     */
    public boolean olderFoodItem(FoodItem other){
        return this._productionDate.before(other.getProductionDate());
    }
    
    /**
     * returns the number of units of products that can be purchased for a given amount
     * 
     * @param amount amount to purchase
     * @return the number of units can be purchased
     */
    public int howManyItems(int amount){
        int maxItems = amount / this._price;
        return maxItems < this._quantity ? maxItems : this._quantity;
        
    }
    
    /**
     * check if this food item is cheaper than other food item
     * 
     * @param other food item to compare this food item to
     * @return true if this food item is cheaper than other date
     */
    public boolean isCheaper(FoodItem other){
        return this._price < other.getPrice();
    }
}
