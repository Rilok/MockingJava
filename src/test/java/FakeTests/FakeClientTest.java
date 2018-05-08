package FakeTests;

import Data.Interfaces.IClientRepository;
import Data.Repositories.ClientRepository;
import Fakes.FakeClientRepository;
import Models.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FakeClientTest {
    private IClientRepository fakeClientRepository;
    private ClientRepository clientControl;
    Client ziomeczek;

    @BeforeEach
    public void initialize(){
        fakeClientRepository = new FakeClientRepository();
        clientControl = new ClientRepository(fakeClientRepository);
        ziomeczek = new Client(1, "Jan", "Kowalski", "janeczek@o2.pl");

    }

    @Test
    public void GetEmptyList(){
        assertTrue(clientControl.GetAll().isEmpty());
    }

    @Test
    public void GetNullClient(){
        assertNull(clientControl.GetClient(1));
    }

    @Test
    public void AddNewClient(){
        clientControl.AddClient(ziomeczek);
        assertNotNull(clientControl.GetClient(1));
    }

    @Test
    public void AddExistedClient(){
        clientControl.AddClient(ziomeczek);
        boolean result = clientControl.AddClient(ziomeczek);
        assertFalse(result);
    }

    @Test
    public void RemoveCreatedClient(){
        clientControl.AddClient(ziomeczek);
        boolean result = clientControl.RemoveClient(ziomeczek);
        assertTrue(result);
    }

    @Test
    public void RemoveClientWhichDoesntExist(){
        boolean result = clientControl.RemoveClient(ziomeczek);
        assertFalse(result);
    }

    @Test
    public void UpdateExistedClient(){
        clientControl.AddClient(ziomeczek);
        Client ziomeczekUpdated = new Client(1, "Janek", "Kowalski", "janeczek12@o2.pl");
        clientControl.UpdateClient(ziomeczekUpdated);
        assertEquals(clientControl.GetClient(1).getImie(), "Janek");
    }

    @Test
    public void UpdateClientWhichDoesntExist(){
        boolean result = clientControl.UpdateClient(ziomeczek);
        assertFalse(result);
    }
}
