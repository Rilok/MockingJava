package Mockito;

import Data.Interfaces.IItemRepository;
import Data.Interfaces.IOrderItemRepository;
import Data.Interfaces.IOrderRepository;
import Data.Repositories.OrderRepository;
import Models.Client;
import Models.Item;
import Models.Order;
import Models.Order_The_Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private IItemRepository MockItemRepository;
    private IOrderRepository MockOrderRepository;
    private IOrderItemRepository MockOrderItemRepository;
    private OrderRepository orderControl;

    private Client testClient;
    private Order testOrder;
    private Item testItem;
    private Order_The_Item testOrderItem;

    @BeforeEach
    public void initialize(){
        MockItemRepository = Mockito.mock(IItemRepository.class);
        MockOrderRepository = Mockito.mock(IOrderRepository.class);
        MockOrderItemRepository = Mockito.mock(IOrderItemRepository.class);
        orderControl = new OrderRepository(MockItemRepository, MockOrderRepository,
                MockOrderItemRepository);
        testClient = new Client(1, "Jan", "Kowalski", "janeczek@o2.pl");
        testOrder = new Order(1, 1);
        testItem = new Item(1, "Screen", 213.7);
        testOrderItem = new Order_The_Item(1, 1);
    }

    @Test
    public void GetEmptyOrderList(){
        List<Order> emptyList = new ArrayList<Order>();
        doReturn(emptyList).when(MockOrderRepository).GetAll();

        assertEquals(emptyList, orderControl.GetAll());
    }

    @Test
    public void GetOrderList_OneElement(){
        List<Order> orders = new ArrayList<Order>();
        orders.add(testOrder);
        doReturn(orders).when(MockOrderRepository).GetAll();

        assertEquals(orders, orderControl.GetAll());
    }

    @Test
    public void GetOrderFromList_OneElement(){
        List<Order> orders = new ArrayList<Order>();
        orders.add(testOrder);
        doReturn(testOrder).when(MockOrderRepository).GetOrder(testOrder.getId());

        assertEquals(testOrder, orderControl.GetOrder(testOrder.getId()));
    }

    @Test
    public void GetNullClientOrders_ShouldThrowException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {orderControl.GetClientOrders(null);});
        assertThat(exception.getMessage()).isEqualTo("Brak klienta");
    }

    @Test
    public void GetClientOrders_NothingFound(){
        List<Order> orders = new ArrayList<Order>();
        doReturn(orders).when(MockOrderRepository).GetClientOrder(1);

        assertEquals(orders, orderControl.GetClientOrders(testClient));
    }

    @Test
    public void GetClientOrders_SomethingFound(){
        List<Order> orders = new ArrayList<Order>();
        orders.add(testOrder);
        doReturn(orders).when(MockOrderRepository).GetClientOrder(1);

        assertEquals(orders, orderControl.GetClientOrders(testClient));
    }

    @Test
    public void GetNullOrderProducts_ShouldThrowException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {orderControl.GetOrderedItems(null);});
        assertThat(exception.getMessage()).isEqualTo("Order nie moze byc pusty");
    }

    @Test
    public void GetOrderProducts_SomethingFound(){
        List<Order_The_Item> order_the_items = Arrays.asList(testOrderItem);
        List<Item> items = Arrays.asList(testItem);
        doReturn(order_the_items).when(MockOrderItemRepository).GetItemsFromOrder(testOrder.getId());
        doReturn(testItem).when(MockItemRepository).GetItem(testItem.getId());

        assertEquals(items, orderControl.GetOrderedItems(testOrder));
    }

    @Test
    public void AddNullOrder_ShouldThrowException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {orderControl.AddOrder(null);});
        assertThat(exception.getMessage()).isEqualTo("Brak podanego zamowienia");
    }

    @Test
    public void AddOrderProperly(){
        doReturn(true).when(MockOrderRepository).AddOrder(testOrder);

        assertTrue(orderControl.AddOrder(testOrder));
    }

    @Test
    public void AddNullItemToOrder_ShouldThrowException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {orderControl.AddOrderItem(testOrder, null);});
        assertThat(exception.getMessage()).isEqualTo("Przedmiot nie moze byc pusty");
    }

    @Test
    public void AddItemToNullOrder_ShouldThrowException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {orderControl.AddOrderItem(null, testItem);});
        assertThat(exception.getMessage()).isEqualTo("Order nie moze byc pusty");
    }

    @Test
    public void AddTakenItemInOrderToSameOne(){
        doReturn(testOrderItem).when(MockOrderItemRepository).GetOrderedItem(anyInt());

        assertFalse(orderControl.AddOrderItem(testOrder, testItem));
    }

    @Test
    public void AddAvalibleItemToOrder(){
         doReturn(null).when(MockOrderItemRepository).GetOrderedItem(anyInt());
         doReturn(true).when(MockOrderItemRepository).AddOrderItem(any(Order_The_Item.class));

         assertTrue(orderControl.AddOrderItem(testOrder, testItem));
    }

    @Test
    public void DeleteNullOrder_ShouldThrowException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {orderControl.RemoveOrder(null);});
        assertThat(exception.getMessage()).isEqualTo("Brak zamowienia do usuniecia");
    }

    @Test
    public void DeleteOrderItem(){
        List<Order_The_Item> order_the_items = Arrays.asList(testOrderItem);
        doReturn(order_the_items).when(MockOrderItemRepository).GetItemsFromOrder(testOrder.getId());
        doReturn(true).when(MockOrderItemRepository).RemoveOrderItem(any(Order_The_Item.class));
        doReturn(true).when(MockOrderRepository).RemoveOrder(testOrder);

        assertTrue(orderControl.RemoveOrder(testOrder));
    }

    @AfterEach
    public void tearDown(){
        MockItemRepository = null;
        MockOrderRepository = null;
        MockOrderItemRepository = null;
        orderControl = null;

        testClient = null;
        testOrder = null;
        testItem = null;
        testOrderItem = null;

    }
}
