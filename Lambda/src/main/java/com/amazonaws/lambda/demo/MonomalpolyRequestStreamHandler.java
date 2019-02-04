package com.amazonaws.lambda.demo;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public final class MonomalpolyRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<>();
    static {
    	//    /\
    	//   /  \    NE PAS INCLURE L'ID DANS L'HISTORIQUE GIT.
    	//  / !! \
    	//  ------
    	//
    	// Au moment du déploiement, il faut inclure l'ID du Skill.
    	//
    	// * Aller sur https://developer.amazon.com/edw/home.html#/
    	// * Cliquer sur "Edit" sur le projet concerné
    	// * Cliquer sur "Endpoints" (dans la barre gauche ou en bas à droite)
    	// * Copier l'identifiant à côté de "Your Skill ID"
        supportedApplicationIds.add("eh bien le bonjour");
    }

    public MonomalpolyRequestStreamHandler() {
        super(new MonopolySpeechlet(), supportedApplicationIds);
    }
}
