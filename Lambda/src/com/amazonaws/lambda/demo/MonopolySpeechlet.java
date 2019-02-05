package com.amazonaws.lambda.demo;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

public class MonopolySpeechlet implements Speechlet {
	// private static final Logger log = LoggerFactory.getLogger(MonopolySpeechlet.class);

	@Override
	public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException {
		
	}

	@Override
	public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {
		return getWelcomeResponse();
	}

	@Override
	public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException {
		Intent intent = request.getIntent();
		String intentName = (intent != null) ? intent.getName() : null;

		switch(intentName) {
		case "StartIntent" :
			return getStartResponse();
		case "DiceDrawIntent" :
			return getStartResponse();
		case "AMAZON.HelpIntent":
			return getHelpResponse();
		default :
			throw new SpeechletException("Invalid Intent");
		}
	}

	@Override
	public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException {
		
	}

	/**
	 * Crée et retourne une {@code SpeechletResponse} avec un message de bienvenue.
	 */
	private SpeechletResponse getWelcomeResponse() {
		String speechText = "Eh bien le bonjour !";

		SimpleCard card = new SimpleCard();
		card.setTitle("Monomalpoly");
		card.setContent(speechText);

		// Réponse texte
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt, card);
	}

	/**
	 * Gère le début d'une partie. Toute la logique de création d'une
	 * partie doit être lancé à ce moment (appel au backend).
	 *
	 * @return SpeechletResponse - Réponse textuelle.
	 */
	private SpeechletResponse getStartResponse() {
		String speechText = "Eh bien le bonjour !";

		SimpleCard card = new SimpleCard();
		card.setTitle("Monomalpoly");
		card.setContent(speechText);

		// Réponse texte
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		return SpeechletResponse.newTellResponse(speech, card);
	}

	/**
	 * Crée et retourne une {@code SpeechletResponse} pour l'aide.
	 */
	private SpeechletResponse getHelpResponse() {
		String speechText = "Besoin d'aide ?";

		SimpleCard card = new SimpleCard();
		card.setTitle("Monomalpoly");
		card.setContent(speechText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt, card);
	}
	
	/**
	 * Crée et retourne une {@code SpeechletResponse} pour le lancé de dé.
	 */
	private SpeechletResponse getDiceDrawResponse() {
		String speechText = "Je vais faire un lancé de dé !";

		SimpleCard card = new SimpleCard();
		card.setTitle("Monomalpoly");
		card.setContent(speechText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt, card);
	}
}

