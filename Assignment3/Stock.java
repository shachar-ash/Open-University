





/**
 * This class represents the current stock in the supermarket.
 *
 * @author David Rashba
 * @version 2020a
 */

// Bugs - NullPointerException when adding an FoodItem, deleting it, and adding the same object again and then trying to insert at index
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
    public int getNumOfItems(){
        return _noOfItems;
    }

    /**
     * adds a new item to stock, and returns true if item added and false if not.
     * 
     * @param item the FoodItem object to add to stock
     * @return true if item added succesfully
     */

    public boolean addItem(FoodItem newItem){
        // if _noOfItems = 0, just add newItem to first place in array
        if(_noOfItems == 0){
            _stock[0] = new FoodItem(newItem);
            // increament _noOfItems
            _noOfItems++;
            // item added, return true
            return true;
        }else{
            // if there are already items in the list
            
            // new item cat. number for convenience
            long newItemCatNumber = newItem.getCatalogueNumber();
            // save first null place index, -1 if didnt find
            int firstNullPlace = -1;
            for(int i = 0; i < _stock.length; i++){
                // check for null place
                if(_stock[i] == null && firstNullPlace == -1){
                    // found first null place, save index for later
                    firstNullPlace = i;
                }else if(_stock[i] != null){
                    // all the operations for existing item in array

                    if(isSameFoodItemAndDate(_stock[i], newItem)){  // 1. new item is exactly the same as item
                        // just add quantity and return true
                        _stock[i].setQuantity(_stock[i].getQuantity() + newItem.getQuantity());
                        return true;
                    }else if(isSameFoodItemDiffDate(_stock[i], newItem)){    // 2. new item is the same as item but different dates
                        // insert newItem before item
                        // insert method returns true and increaments _noOfItems if successfull
                        // else returns false (cant insert an item)
                        return insertItem(newItem, i);
                    }else if(_stock[i].getCatalogueNumber() > newItemCatNumber){    // 3. found an item with bigger cat. number
                        // insert newItem before item
                        // insert method returns true and increaments _noOfItems if successfull
                        // else returns false (cant insert an item)
                        return insertItem(newItem, i);
                    }

                }

            }
            // if got here -> there are no similar items in array, and no item with bigger cat number
            if(firstNullPlace > -1){
                // removeItemAtIndex with null reduction flag will make sure that if there are non-null items after firstNullPlace -> we get rid of them
                removeItemAtIndex(firstNullPlace, true);
                for(int i = firstNullPlace; i < _stock.length; i++){
                    // find the first null place
                    if(_stock[i] == null){
                        // assign newItem to null place, increament _noOfItems and return true
                        _stock[i] = new FoodItem(newItem);
                        _noOfItems++;
                        return true;
                    }
                }
            }else{
                // cant insert newItem -> return false
                return false;
            }
        }
        // only way to get here is through a worm hole in space-time
        return false;
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
                // remove item at index i, false means removing an item and not reducing null
                removeItemAtIndex(i, false);
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
                list = list.concat(_stock[i].toString() + "\n");
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
                        removeItemAtIndex(i,false);
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
        // if one of them is not an item, return false
        if(stockItem == null || otherItem == null)
            return false;

        // check equallity - name, cat numer, expiry date AND production date
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
        // if one of them is not an item, return false
        if(stockItem == null || otherItem == null)
            return false;

        // check equallity - name, cat numer, expiry date OR production date
        boolean isSame = stockItem.getName().equals(otherItem.getName())
            && stockItem.getCatalogueNumber() == otherItem.getCatalogueNumber()
            && (!stockItem.getExpiryDate().equals(otherItem.getExpiryDate())
                || !stockItem.getProductionDate().equals(otherItem.getProductionDate()));
        return isSame;
    }

    /**
     * inserting given food item in given index
     * increamenting _noOfItems if succesfull
     * modifies _stock
     * 
     * @param item food item to insert
     * @param index index in which should insert the item
     * 
     * @return true if managed to insert item
     */
    private boolean insertItem(FoodItem item, int index){
        // check _noOfItems is less than maximum -> there are null places
        if(_noOfItems < MAX_ITEMS_IN_ARRAY){
            FoodItem temp =null;
            for(int i = index; i < _stock.length -1; i++){
                // loop over _stock from [index]
                if(i == index){
                    // keep the item in the index 
                    temp = new FoodItem(_stock[i]);
                    // replace item with new item
                    _stock[i] = new FoodItem(item);
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
        }else {
            // no place to insert item
            return false; 
        }
    }

    /**
     * gets rid of holes or items in array
     * modifies _stock
     * 
     * @param nullReduction true if removing null, false if removing item
     * @param index index of deleted item
     */
    private void removeItemAtIndex(int index, boolean nullReduction){
        // if index is not the last item in the array
        if(index < _stock.length - 1){
            // loop from index to the item before the last in the array
            for(int i = index; i < _stock.length - 1; i++){
                // if next item isnt null, assign to the current item
                if(_stock[i+1] != null){
                    // move item to the index
                    _stock[i] = new FoodItem(_stock[i+1]);
                    //remove the item from original position
                    _stock[i+1] = null;
                }else{
                    //loop untill you find a non-null item, or untill end of array
                    int next = 1;
                    while(_stock[i+next] == null && i+next < _stock.length-1){
                        next++;
                    }
                    // here either we have a non-null FoodItem, or we are at the end of the array
                    if(_stock[i+next] != null){
                        // if its a non-null item just move it to the index of null place
                        _stock[i] = new FoodItem(_stock[i+next]);
                        //remove the item from original position
                        _stock[i+next] = null;
                    }else{
                        // we got to end of array and all items are null, just break the loop
                        break;
                    }
                }
            }
        }else{
            // index of last item in the array - make null
            _stock[index] = null;
        }
        if(!nullReduction){
            // we removed an item, decrement _noOfItems
            _noOfItems--;
        }
    }

}