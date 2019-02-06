package com.amazonaws.lambda.demo;

import java.net.URL;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
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

import java.net.URL;
import java.nio.charset.Charset;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;

import org.json.JSONException;
import org.json.JSONObject;


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
			return getStartResponse(intent);
		case "DiceDrawIntent" :
			return getDiceDrawResponse();
		case "PlayerName" :
			return getPlayersNameResponse();
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
		String speechText = "Bienvenue dans la Skill Monomalpoly ! "
				+ "Pour avoir les raigles du jeu dites Raigles du jeu. "
				+ "Pour creer une nouvelle partie dites Creer une partie.";

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
	private SpeechletResponse getStartResponse(Intent intent) {
		Slot s = intent.getSlot("NbUser");

		String speechText = "Bienvenue dans votre nouvelle partie "
				+ "de Monomalpoly. " + s.getValue() + " joueurs vont jouer. "
				+ "Pour avoir les raigles du jeu dites Raigles du jeu. "
				+ "Pour des marer une partie dites Des marer une partie";

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

		String speechText;

		String url = "http://52.47.35.192:8080/dice";

		try {
			JSONObject json = readJsonFromUrl(url);
			speechText = json.getString("message");
		} catch (IOException e) {
			speechText = "Une erreur est survenue pendant la requête.";
		}

		SimpleCard card = new SimpleCard();
		card.setTitle("Monomalpoly");
		card.setContent(speechText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);
		
		return SpeechletResponse.newTellResponse(speech, card);
	}

	private SpeechletResponse getPlayerNameResponse() {

		String speechText;

		String url = new Url("http://52.47.35.192:8080/player/add");
		URLConnection conn = url.openConnection();

		SimpleCard card = new SimpleCard();
		card.setTitle("Monomalpoly");
		card.setContent(speechText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);
		
		return SpeechletResponse.newTellResponse(speech, card);
	}
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	}
}

