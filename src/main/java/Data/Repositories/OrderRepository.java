package Data.Repositories;

import Data.Interfaces.IItemRepository;
import Data.Interfaces.IOrderItemRepository;
import Data.Interfaces.IOrderRepository;
import Models.Client;
import Models.Item;
import Models.Order;
import Models.Order_The_Item;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private IOrderItemRepository orderItemRepository;
    private IOrderRepository orderRepository;
    private IItemRepository itemRepository;

    public OrderRepository (IItemRepository _itemRepository,
                            IOrderRepository _orderRepository,
                            IOrderItemRepository _orderItemRepository){
        itemRepository = _itemRepository;
        orderRepository = _orderRepository;
        orderItemRepository = _orderItemRepository;
    }

    public List<Order> GetAll(){
        return orderRepository.GetAll();
    }

    public Order GetOrder(int orderId){
        return orderRepository.GetOrder(orderId);
    }

    public List<Order> GetClientOrders(Client client){
        if (client == null)
            throw new IllegalArgumentException("Brak klienta");
        return orderRepository.GetClientOrder(client.getId());
    }

    public List<Item> GetOrderedItems(Order order){
        if(order == null)
            throw new IllegalArgumentException("Order nie moze byc pusty");
        List<Item> items = new ArrayList<Item>();
        for (Order_The_Item oti : orderItemRepository.GetItemsFromOrder(order.getId()))
            items.add(itemRepository.GetItem(oti.getPrzedmiotId()));
        return items;
    }

    public boolean AddOrder(Order order){
        if (order == null)
            throw new IllegalArgumentException("Brak podanego zamowienia");
        return orderRepository.AddOrder(order);
    }

    public boolean RemoveOrder(Order order){
        if (order == null)
            throw new IllegalArgumentException("Brak zamowienia do usuniecia");
        for (Order_The_Item oti : orderItemRepository.GetItemsFromOrder(order.getId()))
            orderItemRepository.RemoveOrderItem(oti);
        return orderRepository.RemoveOrder(order);
    }

    public boolean AddOrderItem(Order order, Item item){
        if(order == null)
            throw new IllegalArgumentException("Order nie moze byc pusty");
        if(item == null)
            throw new IllegalArgumentException("Przedmiot nie moze byc pusty");

        if ((orderItemRepository.GetOrderedItem(item.getId())) != null)
            return false;

        Order_The_Item orderedItem = new Order_The_Item(order.getId(), item.getId());
        return orderItemRepository.AddOrderItem(orderedItem);
    }
}
