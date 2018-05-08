package Fakes;

import Data.Interfaces.IClientRepository;
import Models.Client;

import java.util.ArrayList;
import java.util.List;

public class FakeClientRepository implements IClientRepository {

    private List<Client> clients;

    public FakeClientRepository(){
        clients = new ArrayList<Client>();
    }
    @Override
    public List<Client> GetAll(){
        return clients;
    }
    @Override
    public Client GetClient(int clientId){
        return this.clients.stream()
                .filter(x -> x.getId() == clientId)
                .findFirst().orElse(null);
    }
    @Override
    public boolean AddClient(Client client){
        if(this.GetClient(client.getId()) != null)
            return false;
        clients.add(client);
        return true;
    }
    @Override
    public boolean RemoveClient(Client client){
        Client removedClient = clients.stream()
                .filter(x -> x.getId() == client.getId())
                .findFirst().orElse(null);
        if (removedClient == null)
            return false;
        clients.remove(removedClient);
        return true;
    }
    @Override
    public boolean UpdateClient(Client client){
        Client updated = clients.stream()
                .filter(x -> x.getId() == client.getId())
                .findFirst().orElse(null);
        if (updated == null)
            return false;
        clients.remove(updated);
        clients.add(client);
        return true;
    }
}
