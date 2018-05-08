package Data.Repositories;

import Data.Interfaces.IItemRepository;
import Data.Interfaces.IOrderItemRepository;
import Models.Item;
import Models.Order_The_Item;

import java.util.List;

public class ItemRepository {
    private IOrderItemRepository orderItemRepository;
    private IItemRepository itemRepository;

    public ItemRepository(IOrderItemRepository _orderItemRepository,
                           IItemRepository _itemRepository){
        orderItemRepository = _orderItemRepository;
        itemRepository = _itemRepository;
    }

    public List<Item> GetAll(){
        return itemRepository.GetAll();
    }
    public Item GetItem(int itemId){
        return itemRepository.GetItem(itemId);
    }
    public boolean AddItem(Item item){
        if (item == null)
            throw new IllegalArgumentException("Nie dodam pustego przedmiotu. Bo tak!");

        return itemRepository.AddItem(item);
    }
    public boolean RemoveItem(Item item){
        if (item == null)
            throw new IllegalArgumentException("Usuniecie przedmiotu 'null'. Co?");
        Order_The_Item orderedItem = orderItemRepository.GetOrderedItem(item.getId());
        orderItemRepository.RemoveOrderItem(orderedItem);
        return itemRepository.RemoveItem(item);
    }
    public boolean UpdateItem(Item item){
        if (item == null)
            throw new IllegalArgumentException("Nie zaktualizjuję pustym przedmiotem.");
        return itemRepository.UpdateItem(item);
    }

}
