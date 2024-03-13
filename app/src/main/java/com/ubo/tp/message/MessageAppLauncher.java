package com.ubo.tp.message;

import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.core.database.Database;
import com.ubo.tp.message.core.database.IDatabase;
import com.ubo.tp.message.datamodel.Message;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.ConsoleDatabaseObserver;
import com.ubo.tp.message.ihm.MessageApp;
import mock.MessageAppMock;

import java.time.Instant;
import java.util.*;

/**
 * Classe de lancement de l'application.
 *
 * @author S.Lucas
 */
public class MessageAppLauncher {

	/**
	 * Indique si le mode bouchoné est activé.
	 */
	protected static boolean IS_MOCK_ENABLED = false;
    protected static boolean IS_GENERATE_DATA_MODE = false;
    protected static boolean IS_DOUBLE_APP_MODE = false;
	/**
	 * Launcher.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

        IDatabase database = new Database();
        EntityManager entityManager = new EntityManager(database);

		if (IS_MOCK_ENABLED) {
			MessageAppMock mock = new MessageAppMock(database, entityManager);
			mock.showGUI();
		}

        MessageApp messageApp = new MessageApp(database, entityManager);
		messageApp.init();
		messageApp.show();


        ConsoleDatabaseObserver consoleDatabaseObserver = new ConsoleDatabaseObserver();
        database.addObserver(consoleDatabaseObserver);
        if(IS_GENERATE_DATA_MODE) {
            String[] usersName = {"John", "Alice", "Bob", "Emily", "Michael", "Sophia", "David", "Emma", "James", "Olivia"};
            List<User> users = new ArrayList<>();
            for(int i = 0; i < 10; i++) {
                User user = new User(UUID.randomUUID(), usersName[i], "test", usersName[i], new HashSet<>(), "src/main/resources/images/logo_50.png");
                users.add(user);
                entityManager.writeUserFile(user);
            }

            entityManager.writeMessageFile(new Message(UUID.randomUUID(), users.get(0), 1636400000L, "Hello everyone!")); // October 9, 2021
            entityManager.writeMessageFile(new Message(UUID.randomUUID(), users.get(1), 1636450000L, "Hi " + users.get(0).getUserTag() + ", how are you? #greetings")); // October 10, 2021
            entityManager.writeMessageFile(new Message(UUID.randomUUID(), users.get(0), 1636490000L, "I'm doing well, thanks! #feelinggood")); // October 11, 2021
            entityManager.writeMessageFile(new Message(UUID.randomUUID(), users.get(2), 1636520000L, "Hey " + users.get(0).getUserTag() + ", did you finish that task we discussed?")); // October 12, 2021
            entityManager.writeMessageFile(new Message(UUID.randomUUID(), users.get(0), 1636600000L, "Not yet, but I'll have it done by tomorrow. #workinprogress")); // October 13, 2021
            entityManager.writeMessageFile(new Message(UUID.randomUUID(), users.get(2), 1636610000L, "Okay, let me know if you need any help with it.")); // October 13, 2021
            entityManager.writeMessageFile(new Message(UUID.randomUUID(), users.get(3), 1636650000L, "Hello everyone, how's your day going?")); // October 14, 2021
            entityManager.writeMessageFile(new Message(UUID.randomUUID(), users.get(0), 1636680000L, "It's going well, just busy with work.")); // October 14, 2021
            entityManager.writeMessageFile(new Message(UUID.randomUUID(), users.get(4), 1636700000L, "Hey " + users.get(0).getUserTag() + ", do you have a minute? I need to discuss something urgent.")); // October 15, 2021
            entityManager.writeMessageFile(new Message(UUID.randomUUID(), users.get(0), 1636720000L, "Sure, what's up?")); // October 15, 2021
            entityManager.writeMessageFile(new Message(UUID.randomUUID(), users.get(4), 1636740000L, "I'll tell you in person, let's meet at the coffee shop at 2 PM.")); // October 15, 2021
            entityManager.writeMessageFile(new Message(UUID.randomUUID(), users.get(0), 1636750000L, "Okay, see you there.")); // October 15, 2021

        }

        if(IS_DOUBLE_APP_MODE){
            IDatabase database2 = new Database();
            EntityManager entityManager2 = new EntityManager(database2);
            MessageApp messageApp2 = new MessageApp(database2, entityManager2);
            messageApp2.init();
            messageApp2.show();
        }
	}
}
