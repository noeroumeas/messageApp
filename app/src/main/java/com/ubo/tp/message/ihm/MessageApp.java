package com.ubo.tp.message.ihm;

import com.ubo.tp.message.connected.ConnectedComponent;
import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.core.database.IDatabase;
import com.ubo.tp.message.core.directory.IWatchableDirectory;
import com.ubo.tp.message.core.directory.WatchableDirectory;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.session.ISession;
import com.ubo.tp.message.ihm.session.ISessionObserver;
import com.ubo.tp.message.ihm.session.Session;
import com.ubo.tp.message.login.LoginComponent;

import javax.swing.*;
import java.io.File;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class MessageApp implements ISessionObserver {
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected MessageAppMainView mMainView;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;
    /**
     * Composant de gestion du Login
     */
    protected LoginComponent loginComponent;
	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;
    /**
     * Session
     */
    protected ISession session;

    /**
     * Vue de la page d'acceuil quand l'utilisateur est connecté
     */
    ConnectedComponent connectedComponent;
    /**
	 * Constructeur.
	 *
	 * @param entityManager
	 * @param database
	 */
	public MessageApp(IDatabase database, EntityManager entityManager) {
		this.mDatabase = database;
		this.mEntityManager = entityManager;
	}

	/**
	 * Initialisation de l'application.
	 */
	public void init() {
		// Init du look and feel de l'application
        this.initSession();
		this.initLookAndFeel();

        // Initialisation du répertoire d'échange
        this.initDirectory();

		// Initialisation de l'IHM
		this.initGui();
        this.initLoginComponent();
        this.mMainView.setMainPanel(this.loginComponent);
	}

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
        this.mMainView = new MessageAppMainView();
		this.mMainView.init();
        this.connectedComponent = new ConnectedComponent(this.session, this.mDatabase, this.mEntityManager);
	}

    /**
     * Initialisation de la session
     */
    protected void initSession(){
        this.session = new Session();
        this.session.addObserver(this);
    }

    /**
     * Initialisation du composant login
     */
    protected void initLoginComponent(){
        this.loginComponent = new LoginComponent(this.mEntityManager, this.mDatabase, this.session);
    }

	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	protected void initDirectory() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.getFolder(null);
        while(!isValideExchangeDirectory(file)) {
            file = fileChooser.getFolder(null);
        }
        this.initDirectory(file.getPath());
        //this.initDirectory("dataSpaceTest");
	}

	/**
	 * Indique si le fichier donné est valide pour servir de répertoire d'échange
	 *
	 * @param directory , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du répertoire d'échange.
	 *
	 * @param directoryPath
	 */
	protected void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}

	public void show() {
		// ... setVisible?
	}

    @Override
    public void notifyLogin(User connectedUser) {
        this.mMainView.setMainPanel(this.connectedComponent);
    }

    @Override
    public void notifyLogout() {
        this.mMainView.setMainPanel(this.loginComponent);
    }
}
