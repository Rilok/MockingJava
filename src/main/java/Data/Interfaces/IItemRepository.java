package Data.Interfaces;

import Models.Item;

import java.util.List;

public interface IItemRepository {
    List<Item> GetAll();
    Item GetItem(int itemId);
    boolean AddItem(Item item);
    boolean RemoveItem(Item item);
    boolean UpdateItem(Item item);
}
