package Data.Interfaces;

import Models.Order;

import java.util.List;

public interface IOrderRepository {
    List<Order> GetAll();
    Order GetOrder(int orderId);
    List<Order> GetClientOrder(int clientId);
    boolean AddOrder(Order order);
    boolean RemoveOrder(Order order);
    boolean UpdateOrder(Order order);
}
