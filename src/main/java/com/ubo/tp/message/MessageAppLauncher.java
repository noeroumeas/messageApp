package main.java.com.ubo.tp.message;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.Database;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.ConsoleDatabaseObserver;
import main.java.com.ubo.tp.message.ihm.MessageApp;
import mock.MessageAppMock;

import java.util.HashSet;
import java.util.UUID;

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
    protected static boolean IS_TEST_MODE = false;
	/**
	 * Launcher.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

        IDatabase database = new Database();
        IDatabase database2 = new Database();
        EntityManager entityManager = new EntityManager(database);
        EntityManager entityManager2 = new EntityManager(database2);

		if (IS_MOCK_ENABLED) {
			MessageAppMock mock = new MessageAppMock(database, entityManager);
			mock.showGUI();
		}

        MessageApp messageApp = new MessageApp(database, entityManager);
		messageApp.init();
		messageApp.show();
        MessageApp messageApp2 = new MessageApp(database2, entityManager2);
        messageApp2.init();
        messageApp2.show();


        ConsoleDatabaseObserver consoleDatabaseObserver = new ConsoleDatabaseObserver();
        database.addObserver(consoleDatabaseObserver);

        if(IS_TEST_MODE) {
            entityManager.writeUserFile(new User(UUID.randomUUID(), "testTag", "test", "testName", new HashSet<>(), "src/main/resources/images/logo_50.png"));

            for(int i = 0; i < 9; i++) {
                entityManager.writeUserFile(new User(UUID.randomUUID(), "testTag" + i, "test", "testName" + i, new HashSet<>(), "src/main/resources/images/logo_50.png"));
            }
        }
	}
}
