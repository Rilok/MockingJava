package Data.Interfaces;

import Models.Order_The_Item;

import java.util.List;

public interface IOrderItemRepository {
    List<Order_The_Item> GetAll();
    List<Order_The_Item> GetItemsFromOrder(int orderId);
    Order_The_Item GetOrderedItem(int itemId);
    boolean AddOrderItem(Order_The_Item order);
    boolean RemoveOrderItem(Order_The_Item order);
    boolean UpdateOrderItem(Order_The_Item order);
}
