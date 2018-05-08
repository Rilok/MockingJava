package Data.Interfaces;

import Models.Client;

import java.util.List;

public interface IClientRepository {
    List<Client> GetAll();
    Client GetClient(int clientId);
    boolean AddClient(Client client);
    boolean RemoveClient(Client client);
    boolean UpdateClient(Client client);
}
