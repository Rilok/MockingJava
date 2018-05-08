package Fakes;

import Data.Interfaces.IItemRepository;
import Models.Item;

import java.util.ArrayList;
import java.util.List;

public class FakeItemRepository implements IItemRepository {
    private List<Item> items;

    public FakeItemRepository(){
        items = new ArrayList<Item>();
    }

    @Override
    public List<Item> GetAll(){
        return items;
    }
    @Override
    public Item GetItem(int itemId){
        return this.items.stream()
                .filter(x -> x.getId() == itemId)
                .findFirst().orElse(null);
    }
    @Override
    public boolean AddItem(Item item){
        if(this.GetItem(item.getId()) != null)
            return false;
        items.add(item);
        return true;
    }
    @Override
    public boolean RemoveItem(Item item){
        Item removedItem = items.stream()
                .filter(x -> x.getId() == item.getId())
                .findFirst().orElse(null);
        if (removedItem == null)
            return false;
        items.remove(removedItem);
        return true;
    }
    @Override
    public boolean UpdateItem(Item item){
        Item updated = items.stream()
                .filter(x -> x.getId() == item.getId())
                .findFirst().orElse(null);
        if (updated == null)
            return false;
        items.remove(updated);
        items.add(item);
        return true;
    }
}
