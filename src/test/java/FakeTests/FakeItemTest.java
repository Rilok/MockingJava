package FakeTests;

import Data.Interfaces.IItemRepository;
import Data.Interfaces.IOrderItemRepository;
import Data.Repositories.ItemRepository;
import Fakes.FakeItemRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class FakeItemTest {
    private IItemRepository fakeItemRepository;
    private IOrderItemRepository fakeOrderItemRepository;
    private ItemRepository itemControl;

    @BeforeEach
    public void initialize(){
        fakeItemRepository = new FakeItemRepository();
        //fakeOrderItemRepository = new FakeOrderItemRepository();
        itemControl = new ItemRepository(fakeOrderItemRepository,
                fakeItemRepository);
    }
}