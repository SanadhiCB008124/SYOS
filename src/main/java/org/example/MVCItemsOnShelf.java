package org.example;

import org.example.Controller.ItemsOnShelfController;
import org.example.View.ItemsOnShelfView;

public class MVCItemsOnShelf {
    public static  void main(String [] args){
        ItemsOnShelfView view = new ItemsOnShelfView();
        Items model=new Items( );
        ItemsOnShelfController controller = new ItemsOnShelfController(view, model);

        view.setVisible(true);
    }
}
