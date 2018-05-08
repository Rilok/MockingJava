package EasyMock;

import Data.Interfaces.IItemRepository;
import Data.Interfaces.IOrderItemRepository;
import Data.Repositories.ItemRepository;
import Models.Item;
import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private IItemRepository MockItemRepository;
    private IOrderItemRepository MockOrderItemRepository;
    private ItemRepository itemControl;
    private Item testItem;

    @BeforeEach
    public void initialize(){
        MockItemRepository = EasyMock.createMock(IItemRepository.class);
        MockOrderItemRepository = EasyMock.createMock(IOrderItemRepository.class);
        itemControl = new ItemRepository(MockOrderItemRepository, MockItemRepository);
        testItem = new Item(1, "Ser", 2.13);
    }

    @Test
    public void GetEmptyItemList(){
        expect(MockItemRepository.GetAll()).andReturn(new ArrayList<Item>());
        replay(MockItemRepository);
        assertEquals(new ArrayList<Item>(), itemControl.GetAll());
    }

    @Test
    public void GetItemList_OneElement(){
        List<Item> expectedList = new ArrayList<Item>();
        expectedList.add(testItem);
        expect(MockItemRepository.GetAll()).andReturn(expectedList);
        replay(MockItemRepository);

        assertEquals(expectedList, itemControl.GetAll());
    }
    @Test
    public void GetNullItem_EmptyList(){
        expect(MockItemRepository.GetItem(1)).andReturn(null);
        replay(MockItemRepository);

        assertThat(itemControl.GetItem(1)).isNull();
    }
    @Test
    public void AddNullItem_ShouldThrowException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {itemControl.AddItem(null);});
        assertThat(exception.getMessage()).isEqualTo("Nie dodam pustego przedmiotu. Bo tak!");
    }

    @Test
    public void AddItemToList(){
        expect(MockItemRepository.AddItem(testItem)).andReturn(true);
        replay(MockItemRepository);

        assertThat(itemControl.AddItem(testItem)).isTrue();
    }

    @Test
    public void DeleteNullItem_ShouldThrowException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {itemControl.RemoveItem(null);});
        assertThat(exception.getMessage()).isEqualTo("Usuniecie przedmiotu 'null'. Co?");
    }

    @Test
    public void DeleteExistedItem(){
        expect(MockItemRepository.RemoveItem(testItem)).andReturn(true);
        replay(MockItemRepository);

        expect(MockOrderItemRepository.GetOrderedItem(testItem.getId())).andReturn(null);
        expect(MockOrderItemRepository.RemoveOrderItem(null)).andReturn(false);
        replay(MockOrderItemRepository);

        assertThat(itemControl.RemoveItem(testItem)).isTrue();
    }

    @Test
    public void DeleteNotExistedItem(){
        expect(MockItemRepository.RemoveItem(testItem)).andReturn(false);
        replay(MockItemRepository);

        expect(MockOrderItemRepository.GetOrderedItem(testItem.getId())).andReturn(null);
        expect(MockOrderItemRepository.RemoveOrderItem(null)).andReturn(false);
        replay(MockOrderItemRepository);

        assertThat(itemControl.RemoveItem(testItem)).isFalse();
    }

    @Test
    public void UpdateItemByNull_ShouldThrowException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {itemControl.UpdateItem(null);});
        assertThat(exception.getMessage()).isEqualTo("Nie zaktualizjuję pustym przedmiotem.");
    }

    @Test
    public void UpdateNotExistedItem(){
        expect(MockItemRepository.UpdateItem(testItem)).andReturn(false);
        replay(MockItemRepository);

        assertThat(itemControl.UpdateItem(testItem)).isFalse();
    }

    @Test
    public void UpdateExistedItem(){
        expect(MockItemRepository.UpdateItem(testItem)).andReturn(true);
        replay(MockItemRepository);

        assertThat(itemControl.UpdateItem(testItem)).isTrue();
    }

    @AfterEach
    public void tearDown(){
        MockItemRepository = null;
        MockOrderItemRepository = null;
        itemControl = null;
        testItem = null;
    }
}
