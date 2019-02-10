package com.amazonaws.lambda.demo;

import org.junit.Test;
import static org.junit.Assert.*;

import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;

public class MonopolySpeechletTest extends LambdaTestHelpers {

    @Test
    public void getStartResponse() throws Exception {
        SpeechletResponse response = sendIntent("StartIntent", slot("NbUser", "4"));

        assertCard(response);
        assertReprompt(response);
        assertSpeechContains(response, "4 joueurs vont jouer");
    }

    @Test
    public void simulateValidGame() throws Exception {
        /**
         * +------------------------+
         * | Lancement de la partie |
         * +------------------------+
         */
        sendIntent("StartIntent", slot("NbUser", "2"));

        /**
         * +-------------------+
         * | Ajout des pseudos |
         * +-------------------+
         */
        assertSpeechContains(
            sendIntent("PlayerName", slot("Name", "Mouloud")),
            "Joueur suivant dites Mon pseudo est"
        );

        assertSpeechContains(
            sendIntent("PlayerName", slot("Name", "Abdoul")),
            "Tout les joueurs ont annoncé leur pseudo.  La partie va bientot commencer"
        );

        /**
         * +--------------+
         * | Tire les dés |
         * +--------------+
         */
        SpeechletResponse response = sendIntent("DiceDrawIntent");

        assertCard(response);
        assertReprompt(response);
        assertSpeechContains(response, "Vous avez fait");
    }

    @Test
    public void simulateInvalidGame() throws Exception {
        // Démarre une nouvelle partie
        sendIntent("StartIntent", slot("NbUser", "2"));

        // Ajout d'un seul utilisateur sur les deux requis
        sendIntent("PlayerName", slot("Name", "Jean-Michel"));

        SpeechletResponse response = sendIntent("DiceDrawIntent");

        assertCard(response);
        assertReprompt(response);
        assertSpeechContains(
            response,
            "Cette instruction n'est pas disponible dans l'état actuel de la partie."
        );
    }

    @Test
    public void addPlayerInInvalidState() throws Exception {
        SpeechletResponse response;

        // Sans démarrer de partie
        MonopolySpeechlet.resetDataBase();

        response = sendIntent("PlayerName", slot("Name", "Woot"));

        assertCard(response);
        assertReprompt(response);

        assertSpeechContains(
            response,
            "Cette instruction n'est pas disponible dans l'état actuel de la partie."
        );

        // En démarrant une partie
        sendIntent("StartIntent", slot("NbUser", "1"));

        sendIntent("PlayerName", slot("Name", "Jean-Claude"));
        response = sendIntent("PlayerName", slot("Name", "Kylian"));

        assertCard(response);
        assertReprompt(response);

        assertSpeechContains(
            response,
            "Cette instruction n'est pas disponible dans l'état actuel de la partie."
        );
    }

    @Test
    public void askRules() throws Exception {
        SpeechletResponse response = sendIntent("RulesIntent");

        assertCard(response);
        assertReprompt(response);

        assertSpeechContains(
            response,
            "Le vainqueur est le dernier joueur n’ayant pas fait faillite"
        );
    }
}
