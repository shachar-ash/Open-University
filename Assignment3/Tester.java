public class Tester
{
    public static void main(String[] args)
    {
        boolean MAX_INT = true;
        Date t1 = new Date(12, 1, 2000);                                                                                                                                                     
        Date t2 = new Date(13, 2, 2000);                                                                                                                                                     
        Date t3 = new Date(15, 2, 2000);                                                                                                                                                     
        Date t4 = new Date(13, 3, 2001);                                                                                                                                                      
        Date t5 = new Date(17, 3, 2001);                                                                                                                                                     
        Date t6 = new Date(22, 3, 2001);                                                                                                                                                     
        Date t7 = new Date(8, 4, 2002);                                                                                                                                                      
        Date t8 = new Date(15, 4, 2002);                                                                                                                                                     
        Date t9 = new Date(20, 4, 2002);                                                                                                                                                     
        Date t10 = new Date(30, 1, 2002);                                                                                                                                                       
        Date t11 = new Date(1, 5, 2002);                                                                                                                                                   
        Date t12 = new Date(25, 5, 2002);                                                                                                                                                    
        Date t13 = new Date(31, 5, 2002);                                                                                                                                                     
        Date t14 = new Date(9, 6, 2002);                                                                                                                                                    
        Date t15 = new Date(11, 6, 2002);                                                                                                                                                    
        Date t16 = new Date(18, 6, 2002);                                                                                                                                                     
        Date t17 = new Date(28, 6, 2002);                                                                                                                                                    
        Date t18 = new Date(1, 9, 2003);                                                                                                                                                    
        Date t19 = new Date(20, 9, 2003);

        FoodItem f12 = new FoodItem("Milk3", 1010, 12, t16, t18, 8, 23, 9);
        FoodItem f13 = new FoodItem("Hone4", 1112, 1, t15, t17, 9, 21, 10);
        FoodItem f14 = new FoodItem("PopC5", 1415, 9, t14, t16, 1, 22, 20);
        FoodItem f9 = new FoodItem("PopC2", 1456, 5, t17, t18, 5, 23, 8);
        FoodItem f10 = new FoodItem("PopC2", 1456, 10, t18, t19, 5, 22, 8);
        FoodItem f11 = new FoodItem("PopC2", 1456, 15, t18, t19, 5, 22, 8);
        FoodItem f1 = new FoodItem("Milk", 1547, 12, t1, t2, 8, 21, 0);
        FoodItem f18 = new FoodItem("Milk29", 2345, 41, t10, t12, 8, 23, 60);
        FoodItem f15 = new FoodItem("Milk16", 2352, 14, t13, t15, 2, 23, 30);
        FoodItem f21 = new FoodItem("PopC29", 2365, 10, t8, t10, 5, 23, 90);
        FoodItem f3 = new FoodItem("PopC", 3586, 9, t5, t6, 1, 23, 2);
        FoodItem f7 = new FoodItem("Milk2", 3658, 41, t13, t14, 8, 21, 6);
        FoodItem f4 = new FoodItem("Milk1", 4214, 14, t7, t8, 2, 21, 3);
        FoodItem f5 = new FoodItem("Hone1", 6859, 22, t9, t10, 3, 22, 4);
        FoodItem f8 = new FoodItem("Hone2", 7459, 0, t15, t16, 6, 22, 7);
        FoodItem f6 = new FoodItem("PopC1", 7652, 36, t11, t2, 10, 23, 5);
        FoodItem f22 = new FoodItem("PopC29", 8542, 15, t6, t8, 5, 21, 100);
        FoodItem f16 = new FoodItem("Hone17", 8546, 22, t12, t14, 3, 21, 40);
        FoodItem f2 = new FoodItem("Hone", 8561, 1, t3, t4, 9, 22, 1);
        FoodItem f19 = new FoodItem("Hone29", 8569, 0, t9, t11, 6, 21, 70);
        FoodItem f17 = new FoodItem("PopC18", 8795, 36, t11, t13, 10, 22, 50);
        FoodItem f20;
        if(MAX_INT) {
            f20 = new FoodItem("PopC29", 9741, 5, t8, t10, 50, 60, 80);
        }else{
            f20 = new FoodItem("PopC29", 9741, 5, t8, t10, 5, 22, 80);
        }
        Stock st = new Stock();
        st.addItem(f1);
        st.addItem(f2);
        st.addItem(f3);
        st.addItem(f4);
        st.addItem(f5);
        st.addItem(f6);
        st.addItem(f7);
        st.addItem(f8);
        st.addItem(f9);
        st.addItem(f10);
        st.addItem(f11);
        st.addItem(f12);
        st.addItem(f13);
        st.addItem(f14);
        st.addItem(f15);
        st.addItem(f16);
        st.addItem(f17);
        st.addItem(f18);
        st.addItem(f19);
        st.addItem(f20);
        st.addItem(f21);
        st.addItem(f22);

        System.out.println("Added 22 items, but catNumber 1456 should be merged.");
        System.out.println("getNumOfItems() should return 21:\t" + st.getNumOfItems());

        if(MAX_INT) {
            System.out.println("\ngetTempOfStock should return 2147483647:\t" + st.getTempOfStock());
            System.out.println("\nTo check if getTempOfStock return minimum temperature, change boolean value MAX_INT in head of tester file to false");
        }else{
            System.out.println("\ngetTempOfStock should return 10:\t" + st.getTempOfStock());
            System.out.println("\nTo check if getTempOfStock return max value of int, change boolean value MAX_INT in head of tester file to true");
        }
        System.out.println("\nhowMany(0) should return 0:\t" + st.howMany(0));

        System.out.println("howMany(1) should return 18:\t" + st.howMany(1));
        System.out.println("howMany(5) should return 150:\t" + st.howMany(5));
        System.out.println("howMany(11) should return 330:\t" + st.howMany(11));
        System.out.println("howMany(24) should return 0:\t" + st.howMany(24));

        System.out.println("\nhowManyPieces() should return 330:\t" + st.howManyPieces());

        System.out.println("\nmostExpensive() should return catNumber 8542:\t" + st.mostExpensive().getCatalogueNumber());

        System.out.println("\norder(0) should return empty string:\t" + st.order(0));
        System.out.println("order(1) should return exactly [Hone2, Hone29]:\t" + st.order(1));
        System.out.println("order(2) should return exactly [Hone4, Hone2, Hone, Hone29]:\t" + st.order(2));
        System.out.println("order(10) should return exactly [Hone4, PopC5, PopC, Hone2, Hone, Hone29, PopC29]:\t" + st.order(10));

        System.out.println("\nStock toString() should print:");
        System.out.println(
                "\n\tFoodItem: Milk3\tCatalogueNumber: 1010\tProductionDate: 18/06/2002\tExpiryDate: 01/09/2003\tQuantity: 12\n" +
                "\tFoodItem: Hone4\tCatalogueNumber: 1112\tProductionDate: 11/06/2002\tExpiryDate: 28/06/2002\tQuantity: 1\n" +
                "\tFoodItem: PopC5\tCatalogueNumber: 1415\tProductionDate: 09/06/2002\tExpiryDate: 18/06/2002\tQuantity: 9\n" +
                "\tFoodItem: PopC2\tCatalogueNumber: 1456\tProductionDate: 01/09/2003\tExpiryDate: 20/09/2003\tQuantity: 25\n" +
                "\tFoodItem: PopC2\tCatalogueNumber: 1456\tProductionDate: 28/06/2002\tExpiryDate: 01/09/2003\tQuantity: 5\n" +
                "\tFoodItem: Milk\tCatalogueNumber: 1547\tProductionDate: 12/01/2000\tExpiryDate: 13/02/2000\tQuantity: 12\n" +
                "\tFoodItem: Milk29\tCatalogueNumber: 2345\tProductionDate: 30/01/2002\tExpiryDate: 25/05/2002\tQuantity: 41\n" +
                "\tFoodItem: Milk16\tCatalogueNumber: 2352\tProductionDate: 31/05/2002\tExpiryDate: 11/06/2002\tQuantity: 14\n" +
                "\tFoodItem: PopC29\tCatalogueNumber: 2365\tProductionDate: 15/04/2002\tExpiryDate: 16/04/2002\tQuantity: 10\n" +
                "\tFoodItem: PopC\tCatalogueNumber: 3586\tProductionDate: 17/03/2001\tExpiryDate: 22/03/2001\tQuantity: 9\n" +
                "\tFoodItem: Milk2\tCatalogueNumber: 3658\tProductionDate: 31/05/2002\tExpiryDate: 09/06/2002\tQuantity: 41\n" +
                "\tFoodItem: Milk1\tCatalogueNumber: 4214\tProductionDate: 08/04/2002\tExpiryDate: 15/04/2002\tQuantity: 14\n" +
                "\tFoodItem: Hone1\tCatalogueNumber: 6859\tProductionDate: 20/04/2002\tExpiryDate: 21/04/2002\tQuantity: 22\n" +
                "\tFoodItem: Hone2\tCatalogueNumber: 7459\tProductionDate: 11/06/2002\tExpiryDate: 18/06/2002\tQuantity: 0\n" +
                "\tFoodItem: PopC1\tCatalogueNumber: 7652\tProductionDate: 01/05/2002\tExpiryDate: 02/05/2002\tQuantity: 36\n" +
                "\tFoodItem: PopC29\tCatalogueNumber: 8542\tProductionDate: 22/03/2001\tExpiryDate: 15/04/2002\tQuantity: 15\n" +
                "\tFoodItem: Hone17\tCatalogueNumber: 8546\tProductionDate: 25/05/2002\tExpiryDate: 09/06/2002\tQuantity: 22\n" +
                "\tFoodItem: Hone\tCatalogueNumber: 8561\tProductionDate: 15/02/2000\tExpiryDate: 13/03/2001\tQuantity: 1\n" +
                "\tFoodItem: Hone29\tCatalogueNumber: 8569\tProductionDate: 20/04/2002\tExpiryDate: 01/05/2002\tQuantity: 0\n" +
                "\tFoodItem: PopC18\tCatalogueNumber: 8795\tProductionDate: 01/05/2002\tExpiryDate: 31/05/2002\tQuantity: 36\n" +
                "\tFoodItem: PopC29\tCatalogueNumber: 9741\tProductionDate: 15/04/2002\tExpiryDate: 16/04/2002\tQuantity: 5");

        System.out.println("\nStock toString() prints:");
        System.out.println(st);

        System.out.println("******************************************************************");
        System.out.println("************  Next step performs removal of elements      ********");
        System.out.println("**************          Will redo tests             **************");
        System.out.println("******************************************************************");
        System.out.println("\npreforming removeAfterDate - [13/02/2000] should not remove any item\t");
        st.removeAfterDate(t2);
        System.out.println("Number of items after operation should be 21: \t" + st.getNumOfItems());
        System.out.println("Check that all food items in place:\n\n" + st);

        System.out.println("\npreforming removeAfterDate - [13/03/2001] should remove all items with expiry date of year 2000\t");
        st.removeAfterDate(t4);
        System.out.println("Number of items after operation should be 20: \t" + st.getNumOfItems());
        System.out.println("Check that all food items in place:\n\n" + st);

        System.out.println("\nAdding item back to stock");
        st.addItem(f1);

        System.out.println("\npreforming removeAfterDate - [8/04/2002] should remove all items with expiry date of year 2000, 2001\t");
        st.removeAfterDate(t7);
        System.out.println("Number of items after operation should be 18: \t" + st.getNumOfItems());
        System.out.println("Check that all food items in place:\n\n" + st);

        System.out.println("\nAdding items back to stock");
        st.addItem(f1);
        st.addItem(f2);
        st.addItem(f3);
        System.out.println("getNumOfItems() should return 21:\t" + st.getNumOfItems());


        System.out.println("Performing updateStock on item Hone4");
        System.out.println("Hone4 has 1 in quantity and we updateStock 4 times so it should be deleted");
        String[] list = {"Hone4","Hone4","Hone4","Hone4","Hone4","Hone4"};
        st.updateStock(list);
        System.out.println("\ngetNumOfItems() should return 20:\t" + st.getNumOfItems());
        System.out.println("updateStock() should remove item with catNumber 1112\t");
        System.out.println("\nCheck list: \n" + st);

        System.out.println("Performing updateStock on item PopC2");
        System.out.println("PopC2 has 30 in quantity and we updateStock 5 times so it should be 25");
        String[] list1 = {"PopC2","PopC2","PopC2","PopC2","PopC2"};
        st.updateStock(list1);
        System.out.println("\ngetNumOfItems() should return 20:\t" + st.getNumOfItems());
        System.out.println("updateStock() should change to 25 quantity of item with catNumber 1456\t");
        System.out.println("First place PopC2 appear should be with quantity 20 \t");
        System.out.println("\nCheck list: \n" + st);


    }
}
