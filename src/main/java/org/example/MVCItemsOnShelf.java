package org.example;

import org.example.Controller.ItemsOnShelfController;
import org.example.View.ItemsOnShelfView;

public class MVCItemsOnShelf {
    public static  void main(String [] args){
        ItemsOnShelfView view = new ItemsOnShelfView();
        ItemRepository itemRepository=new ItemRepository();
        Item model=new Item( );
        ItemsOnShelfController controller = new ItemsOnShelfController(view, model,itemRepository);

        view.setVisible(true);
    }
}
