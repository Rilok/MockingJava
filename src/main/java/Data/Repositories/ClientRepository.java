package Data.Repositories;

import Data.Interfaces.IClientRepository;
import Models.Client;

import java.util.List;

public class ClientRepository {
    private IClientRepository clientRepository;

    public ClientRepository(IClientRepository _clientRepository){
        clientRepository = _clientRepository;
    }

    public List<Client> GetAll(){
        return clientRepository.GetAll();
    }

    public Client GetClient(int clientId){
        return clientRepository.GetClient(clientId);
    }

    public boolean AddClient(Client client) {
        if(client == null){
            throw new IllegalArgumentException("Dajesz pustego klienta? Żartujesz?");
        }
        return clientRepository.AddClient(client);
    }
    public boolean RemoveClient(Client client){
        if(client == null){
            throw new IllegalArgumentException("Usuwać nie podając klienta??");
        }
        return clientRepository.RemoveClient(client);
    }
    public boolean UpdateClient(Client client){
        if(client == null){
            throw new IllegalArgumentException("Aktalizacja klienta bez podania danych??");
        }
        return clientRepository.UpdateClient(client);
    }
}
