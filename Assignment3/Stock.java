/**
 * This class represents the current stock in the supermarket.
 *
 * @author David Rashba
 * @version 2020a
 */
public class Stock
{
    // an array of all the supermarket food items
    private FoodItem[] _stock;

    // the actual number of food items present in the supermarket
    private int _noOfItems;

    // maximun size of _stock array is 100 food items
    private int MAX_ITEMS_IN_ARRAY = 100;

    /**
     * Default constructor
     * 
     * Creates a Stock object with new array of FoodItems with size of 100.
     */
    public Stock(){
        _stock = new FoodItem[MAX_ITEMS_IN_ARRAY];
    }

    /**
     * A method that returns the amount of items in the _stock array
     * 
     * @return number of items in the _stock array
     */
    public int getNoOfItems(){
        return _noOfItems;
    }

    /**
     * adds a new item to stock, and returns true if item added and false if not.
     * 
     * @param item the FoodItem object to add to stock
     * @return true if item added succesfully
     */
    public boolean addItem(FoodItem newItem){
        /*
         * 1. define indices to relevant cases -
         *    a. when there is a null object in array
         *    b. when there is an object with same fields but different dates in the array (comparing to item parameter)
         *    c. when there is an object which is exactly the same as the item in parameter
         *
         * 2. loop over the _stock array, and check -
         *    if all indices are >= 0 -> found all first occurences, no need to check more, break the loop
         *    a. is this the first null item in array? -> assign index to firstNullArray
         *    b. is this the first item where only the dates are different then item? -> assign index to sameNameDiffDateIndex
         *    c. is this the first item which is the same as given item? -> assign index to sameItemIndex
         * 
         * ******** if all indices are -1, cant add new item -> return false
         * 
         * 3. add item by following level of importance -
         *    a. the item is already in array -> just add quantity - increament _noOfItems, return true
         *    b. the item is in the array, but with different dates, and there are null places after the index - return insert method (if inserted, increaments _noOfItems and returns true, else returns false)
         *    c. the item is not in the array at all -> insert item in first null index - increament _noOfItems, return true
         *    d. if still could not add item, return false
         */

        // the index of first null object in the array
        int firstNullIndex = -1;

        // the index of first object with different dates in the array
        int sameNameDiffDateIndex = -1;

        // the index of the object in the array which is the same as item
        int sameItemIndex = -1;

        /*
         ** To prevent possibilty where there is a hole in array, 
         ** and we've assigned an item in null place, but it is already existing in the list after that null place, 
         ** we first check all available possibilities, and only then assign the item.
         */
        for(int i = 0; i < _stock.length; i++){
            // if all indices are >= 0 -> found all first occurences, no need to check more, break the loop
            if(firstNullIndex >= 0 && sameNameDiffDateIndex >= 0 && sameItemIndex >= 0)
                break;

            // if didnt find first null index yet, and current item in array is null -> assign index to first null index
            if(firstNullIndex < 0 && _stock[i] == null){
                firstNullIndex = i;

                // if didnt find item with same name but different dates, and current item in index has same name with different dates -> assign index to same name diff date
            }else if(sameNameDiffDateIndex < 0 && isSameFoodItemDiffDate(_stock[i], newItem)){
                sameNameDiffDateIndex = i;

                // if didnt find same item, and current item in index is same item -> assign index to same item
            }else if(sameItemIndex < 0 && isSameFoodItemAndDate(_stock[i], newItem)){
                sameItemIndex = i;
            }
        }

        //  if all indices are -1, cant add new item -> return false
        if(firstNullIndex < 0 && sameNameDiffDateIndex < 0 && sameItemIndex < 0)
            return false;

        if(sameItemIndex >= 0){
            // item already exists -> add quntity and return true
            int q = _stock[sameItemIndex].getQuantity() + newItem.getQuantity();
            _stock[sameItemIndex].setQuantity(q);
            return true;
        }else if(sameNameDiffDateIndex >= 0 && firstNullIndex >= 0){
            // item exists but with different dates, return insert item (method returns true and increaments _noOfItems if successful, else returns false)
            return insertItem(newItem, sameNameDiffDateIndex);
        }else if(firstNullIndex >= 0){
            // there is no such item in array, and theres is a free place in array, assign item to that place, increament _noOfItems and return true
            _stock[firstNullIndex] = new FoodItem(newItem);
            _noOfItems++;
            return true;
        }else{
            // unable to add item, return false
            return false;
        }

    }

    /**
     * returns a string with all food items that are less then [amount] in stock, and need to order
     * 
     * @param amount the minimum quantity that a food item should be in stock
     * @return a string of food items to order, seperated by comma
     */
    public String order(int amount){
        String orderList = "";

        int lastItemCount = 0;
        String lastItemName = null;

        for(int i = 0; i < _stock.length; i++){
            // make sure not to check null position
            if(_stock[i] != null){
                // if last item name is null, we are currently checking the first item
                if(lastItemName == null){
                    // initialize variables to first item in array
                    lastItemName = _stock[i].getName();
                    lastItemCount = _stock[i].getQuantity();
                    // if last item and current item have the same name  
                }else if(_stock[i].getName().equals(lastItemName)){
                    // add current item quantity to item count
                    lastItemCount += _stock[i].getQuantity();
                }else{
                    // if its a different item -> check last quantity and add last name to list if its less then amount
                    if(lastItemCount < amount){
                        // add last item name to end of list with comma and space
                        orderList.concat(lastItemName + ", ");
                    }
                    // reset item count
                    lastItemCount = 0;
                    // change last item name
                    lastItemName = _stock[i].getName();
                }
            }
        }
        // if oderList is not blank, and last 2 characters are ", " (comma and space) remove those characters
        if(!orderList.isBlank() && orderList.charAt(orderList.length() - 2) == ',' && orderList.charAt(orderList.length() - 1) == ' '){
            orderList = orderList.substring(0, orderList.length() - 2);
        }
        return orderList;
    }

    /**
     * given a fridge temp, calculates how many food items (by quantity) in stock can move to the fridge.
     * 
     * @param temp the temperature of the fridge
     * @return total quantity of items that can move to the fridge
     */
    public int howMany(int temp){
        // counter for how many food items can move to fridge
        int count = 0;

        for(int i = 0; i < _stock.length; i++){
            // if item is not null, and temp is between min-max of item temperature
            if(_stock[i] != null && temp <= _stock[i].getMaxTemperature() && temp >= _stock[i].getMinTemperature()){ 
                // add item quantity to count
                count += _stock[i].getQuantity();
            }
        }

        return count;
    }

    /**
     * removes all food items from stock if their expiry passed given date
     * 
     * @param date the expiration date to check for
     */
    public void removeAfterDate(Date date){
        for(int i = 0; i < _stock.length; i++){
            if(_stock[i] != null && _stock[i].getExpiryDate().after(date)){
                removeItemAtIndex(i);
            }
        }
    }

    /**
     * return the most expensive item in stock
     * 
     * @return the most expensive item in stock or null if array is empty
     */
    public FoodItem mostExpensive(){
        // expensiveItem will be returned as null if no items in array
        FoodItem expensiveItem = null;
        // save the price of most expensive item for comparison
        int price = 0;

        for(int i = 0; i < _stock.length; i++){
            // if current item is not null and is more expensive than largest price
            if(_stock[i] != null && _stock[i].getPrice() > price){
                // save price for camparison
                price = _stock[i].getPrice();
                // assign expensiveItem to current item
                expensiveItem = new FoodItem(_stock[i]);
            }
        }
        return expensiveItem;
    }

    /**
     * return the total quantity of all items in stock
     * 
     * @return total quantity of items in stock
     */
    public int howManyPieces(){
        // total quantity
        int total = 0;
        for(int i = 0; i < _stock.length; i++){
            // if item is not null
            if(_stock[i] != null){
                // add item quantity to total
                total += _stock[i].getQuantity();
            }
        }
        return total;
    }

    /**
     * returns a string representation of Stock:
     * each item in stock is printed in new line
     * FoodItem: xxxx   Catalougue num: xxxxx   ProductionDate: xxxxx ExpiryDate: xxxx  Quantity: xxxx
     * 
     * @return string with data on every fooditem in stock
     */
    public String toString(){
        String list = "";

        for(int i = 0; i < _stock.length; i++){
            // if item is not null
            if(_stock[i] != null){
                // add item.toString to list
                list.concat(_stock[i].toString() + "\n");
            }
        }

        return list;
    }

    /**
     * updates stock using array of item names.
     * removes item from stock if qunatity is 0
     * 
     * @param itemsList string array of food items
     */
    public void updateStock(String[] itemsList){
        // loop over items list names
        for(String name : itemsList){
            // loop over _stock
            for(int i = 0; i < _stock.length; i++){
                // if current item is not null and has same name as current item in itemsList
                if(_stock[i] != null && _stock[i].getName().equals(name)){
                    // remove 1 from item quantity
                    _stock[i].setQuantity(_stock[i].getQuantity() - 1);
                    // if quantity is 0
                    if(_stock[i].getQuantity() == 0){
                        // remove item
                        removeItemAtIndex(i);
                    }
                    // after operation move to next item in itemsList
                    break;
                }
            }

        }
    }

    /**
     * returns the minimal temperature of fridge, according to food items temp range
     * if unable to fit temperature to all food items, or stock array is empty returns Integer.MAX_VALUE
     * 
     * @return the minimal temp for fridge, for all food items or Integer.MAX_VALUE
     */
    public int getTempOfStock(){
        int minTemp = Integer.MIN_VALUE;
        int maxTemp = Integer.MAX_VALUE;

        for(int i = 0; i < _stock.length; i++){
            // check that item is not null
            if(_stock[i] != null){
                // always assign the biggest minTemp in array
                if(_stock[i].getMinTemperature() > minTemp)
                    minTemp = _stock[i].getMinTemperature();
                // always assign the smallest maxTemp in array
                if(_stock[i].getMaxTemperature() < maxTemp)
                    maxTemp = _stock[i].getMaxTemperature();

            }
        }

        // return biggest minTemp in array if its smaller or equal to smallest maxTemp in array
        // return Integer.MAX_VALUE if biggest minTemp in array is bigger than the smallest maxTemp in array
        return maxTemp >= minTemp ? minTemp : Integer.MAX_VALUE;
    }

    // private methods
    /**
     * compre 2 food items by:
     * Name, CatalougeNumber, ExpiryDate, ProductionDate
     * 
     * @param stockItem the FoodItem in stock
     * @param otherItem the FoodItem to check
     * 
     * @return true if same name, catalougeNumber, expiryDate and productionDate
     */
    private boolean isSameFoodItemAndDate(FoodItem stockItem, FoodItem otherItem){
        boolean isSame = stockItem.getName().equals(otherItem.getName())
            && stockItem.getCatalogueNumber() == otherItem.getCatalogueNumber()
            && stockItem.getExpiryDate().equals(otherItem.getExpiryDate())
            && stockItem.getProductionDate().equals(otherItem.getProductionDate());
        return isSame;
    }

    /**
     * compre 2 food items by:
     * Name, CatalougeNumber
     * 
     * 
     * @param stockItem the FoodItem in stock
     * @param otherItem the FoodItem to check
     * 
     * @return true if same name, catalougeNumber, and different expiryDate or productionDate
     */
    private boolean isSameFoodItemDiffDate(FoodItem stockItem, FoodItem otherItem){
        boolean isSame = stockItem.getName().equals(otherItem.getName())
            && stockItem.getCatalogueNumber() == otherItem.getCatalogueNumber()
            && (!stockItem.getExpiryDate().equals(otherItem.getExpiryDate())
                || !stockItem.getProductionDate().equals(otherItem.getProductionDate()));
        return isSame;
    }

    /**
     * inserting given food item in given index
     * modifies _stock
     * 
     * @param item food item to insert
     * @param index index in which should insert the item
     * 
     * @return true if managed to insert item
     */
    private boolean insertItem(FoodItem item, int index){
        // check that there is enough space in array -> there are null places
        int count = 0;
        for(int i = index; i < _stock.length; i++){
            if (_stock[i] == null)
                count++;
        }
        // if there are null places in _stock
        if(count > 0){
            FoodItem temp = new FoodItem(null);
            for(int i = index; i < _stock.length -1; i++){
                // loop over _stock from [index]

                if(i == index){
                    // keep the item in the index 
                    temp = new FoodItem(_stock[i]);
                    // replace item with new item
                    _stock[i] = item;
                }else{
                    // save old item
                    FoodItem a = new FoodItem(_stock[i]);
                    // replace old _stock food item with new
                    _stock[i] = new FoodItem(temp);
                    // assign old item to temp
                    temp = new FoodItem(a);
                }

            }
            // inserted a new item to list, add 1 to _noOfItems
            _noOfItems++;
            return true; // after inserting
        }else return false; // no place to insert item
    }

    /**
     * gets rid of holes in array after deletion of item
     * modifies _stock
     * 
     * @param index index of deleted item
     */
    private void removeItemAtIndex(int index){
        // if index is not the last item in the array
        if(index < _stock.length - 1){
            // loop from index to the item before the last in the array
            for(int i = index; i < _stock.length - 1; i++){
                // assign next item to the current item
                _stock[i] = new FoodItem(_stock[i+1]);
            }
        }else{
            // index of last item in the array - make null
            _stock[index] = null;
        }
    }
}
