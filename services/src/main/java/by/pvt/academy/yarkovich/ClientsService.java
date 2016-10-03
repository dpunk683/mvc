package by.pvt.academy.yarkovich;

import by.pvt.academy.yarkovich.dao.ClientDAO;
import by.pvt.academy.yarkovich.entity.Client;
import by.pvt.academy.yarkovich.exceptions.CardNumErrorException;
import by.pvt.academy.yarkovich.exceptions.DAOException;
import by.pvt.academy.yarkovich.exceptions.EmailExistsErrorException;
import by.pvt.academy.yarkovich.exceptions.PhoneExistsErrorException;
import by.pvt.academy.yarkovich.logger.RestLogger;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static by.pvt.academy.yarkovich.utils.HibernateUtil.beginTransaction;
import static by.pvt.academy.yarkovich.utils.HibernateUtil.commitTransaction;
import static by.pvt.academy.yarkovich.utils.HibernateUtil.rollbackTransaction;

/**
 * Created by dima on 03.09.2016.
 */
@Service
@Transactional
public class ClientsService {
    private static ClientsService instance;
    private ClientDAO clientDAO_inst = ClientDAO.getInstance();

    private ClientsService() {
        super();
    }

    public static ClientsService getInstance() {
        if (instance == null) {
            instance = new ClientsService();
        }
        return instance;
    }

    public synchronized void addClient(Client client) {
            beginTransaction();
            clientDAO_inst.addClient(client);
            commitTransaction();
    }

    public synchronized void validation(Client client) throws EmailExistsErrorException, PhoneExistsErrorException, CardNumErrorException {
        try {
            beginTransaction();
            //Ищем клиента с такой же картой
            if (client.getLoyalityCardNo() != "") {
                List<Client> clientsSameCard = clientDAO_inst.getClientbyCard(client.getLoyalityCardNo());
                if (clientsSameCard.size() != 0) {
                    rollbackTransaction();
                    throw new CardNumErrorException();
                }
            }
            List<Client> clientsSameEmail = clientDAO_inst.getClientByEmail(client.getEmail());
            //Ищем пользователя с таким Email
            if (clientsSameEmail.size() != 0) {
                rollbackTransaction();
                throw new EmailExistsErrorException();
            }
            List<Client> clientsSamePhone = clientDAO_inst.getClientByPhone(client.getPhone());
            //Ищем пользователя с таким phone number
            if (clientsSamePhone.size() != 0) {
                rollbackTransaction();
                throw new PhoneExistsErrorException();
            }
            commitTransaction();
        } catch (DAOException e) {
            RestLogger.getInstance(this.getClass()).writeError("validation Clients Service: " + e);
            rollbackTransaction();
        }
    }

//    public List<Client> getAll() {
//        beginTransaction();
//       // List <Client> list = ClientDAO.getInstance().getAll();
//        commitTransaction();
//       // return list;
//    }
}
