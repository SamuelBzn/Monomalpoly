package com.amazonaws.lambda.demo;

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
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.net.MalformedURLException;

import java.io.UnsupportedEncodingException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;

import org.json.JSONException;
import org.json.JSONObject;


public class MonopolySpeechlet implements Speechlet {
	public static final String API_URL = "http://52.47.78.233:8080";

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
		String state;

		try {
			state = getStateGame();
		} catch (Exception e) {
			e.printStackTrace();

			return askResponse("Une erreur est survenue lors de la récupération des informations.");
		}

		switch(intentName) {
		case "StartIntent" :
			return getStartResponse(intent);
		case "DiceDrawIntent" :
			if(state.equals("game_started")) {
				return getDiceDrawResponse();
			}else{
				return getNotAllowedResponse();
			}
		case "PlayerName" :
			if(state.equals("choix_pseudo")) {
				return getPlayerNameResponse(intent);
			}else{
				return getNotAllowedResponse();
			}
		case "WhoStart" :
			if(state.equals("game_started")) {
				return getCurrentPlayerResponse();
			}else{
				return getNotAllowedResponse();
			}
		case "MoneyIntent" :
			if(state.equals("game_started")) {
				return getMoneyResponse();
			}else{
				return getNotAllowedResponse();
			}
		case "AMAZON.HelpIntent":
			return getHelpResponse();
		case "AMAZON.StopIntent":
			return stopResponse();
		case "AMAZON.CancelIntent":
			return stopResponse();
		case "RulesIntent":
			return getRulesResponse();
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
				+ "Pour avoir les règles du jeu dites Règles du jeu. "
				+ "Pour créer une nouvelle partie dites Lancer une partie. ";

		return askResponse(speechText);
	}

	/**
	 * Gère le début d'une partie. Toute la logique de création d'une
	 * partie doit être lancée à  ce moment (appel au backend).
	 *
	 * @return SpeechletResponse - Réponse textuelle.
	 */
	private SpeechletResponse getStartResponse(Intent intent) {
		Slot s = intent.getSlot("NbUser");

		try {
			resetDataBase();
			createGame("choix_pseudo", s.getValue());
		} catch (Exception e) {
			e.printStackTrace();

			return askResponse("Une erreur est survenue pendant la création de la partie");
		}

		String speechText = "Bienvenue dans votre nouvelle partie "
				+ "de Monomalpoly. " + s.getValue() + " joueurs vont jouer. "
				+ "Maintenant vous devez choisir vos pseudos,"
				+ "pour cela dites Mon pseudo est Toto par exemple.";

		return askResponse(speechText);
	}

	/**
	 * Crée et retourne une {@code SpeechletResponse} pour l'aide.
	 */
	private SpeechletResponse getHelpResponse() {
		return askResponse("Besoin d'aide ?");
	}

	/**
	 * Crée et retourne une {@code SpeechletResponse} pour le lancé de dé.
	 */
	private SpeechletResponse getDiceDrawResponse() {
		String speechText;

		try {
			speechText = get("/dice").getString("message");
		} catch (IOException e) {
			e.printStackTrace();

			speechText = "Une erreur est survenue pendant la requête.";
		}

		return askResponse(speechText);
	}

	private SpeechletResponse getMoneyResponse() {
		String speechText;

		try {
			speechText = get("/consultAccount").getString("response");
		} catch (IOException e) {
			e.printStackTrace();

			speechText = "Une erreur est survenue pendant la requête.";
		}

		return askResponse(speechText);
	}

	private SpeechletResponse stopResponse() {
		return tellResponse("Au revoir");
	}

	private SpeechletResponse getPlayerNameResponse(Intent intent) {
		Slot s = intent.getSlot("Name");

		try {
			JSONObject json = get("/player/add/" + urlEncode(s.getValue()));
			String speechText = "Le pseudo " + s.getValue() + " a bien été ajouté.";

			int pseudoLeft = decreaseUser();

			if (pseudoLeft > 0) {
				speechText += " Joueur suivant dites Mon pseudo est ";
			} else {
				speechText += " Tout les joueurs ont annoncé leur pseudo. "
						+ " La partie va bientot commencer, pour savoir qui va commencer dites Qui va commencer";

				updateState("game_started");
			}

			return askResponse(speechText);
		} catch (Exception e) {
			e.printStackTrace();

			return askResponse("Une erreur est survenue pendant la requête");
		}

	}

	private SpeechletResponse getCurrentPlayerResponse(){
		String speechText;

		try {
			speechText = "" + get("/whoStart").getString("name") + "va commencer la partie !";
		} catch (IOException e) {
			e.printStackTrace();

			speechText = "Une erreur est survenue pendant la requête.";
		}

		return askResponse(speechText);
	}

	private SpeechletResponse getRulesResponse() {
		String speechText = "Le Monomalpolie est un jeu de société. " +
		" Le but du jeu consiste à ruiner ses concurrents par des opérations immobilières. " +
		" Le jeu se déroule en tour par tour, avec deux dés ordinaires à 6 faces. "+
		" Au début de la partie, chaque joueur annonce son pseudo. L'ordre de jeu est défini à l'aide d'un lancer de dés et le joueur ayant fait le plus grand nombre commence. "
		" Pour jouer, chaque joueur lance les dés, avance son pion sur le parcours, puis selon la case sur laquelle il s’arrête, effectue une action correspondante."+
		" Le vainqueur est le dernier joueur n’ayant pas fait faillite, et qui possède de ce fait le monopole";

		return askResponse(speechText);
	}

	private SpeechletResponse getNotAllowedResponse() {
		String speechText = "Cette instruction n'est pas disponible dans l'état actuel de la partie.";

		return askResponse(speechText);
	}

	/**
	 * Permet de retourner une simple réponse (sans reprompt).
	 *
	 * @param speechText - Le texte à faire dire à Alexa.
	 */
	private SpeechletResponse tellResponse(String speechText) {
		SimpleCard card = new SimpleCard();
		card.setTitle("Monomalpoly");
		card.setContent(speechText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		return SpeechletResponse.newTellResponse(speech, card);
	}

	/**
	 * Permet de retourner une réponse avec reprompt.
	 *
	 * @param speechText - Le texte à faire dire à Alexa.
	 */
	private SpeechletResponse askResponse(String speechText) {
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

	public static JSONObject resetDataBase() throws Exception {
		return get("/reset");
	}

	public static int decreaseUser() throws Exception {
		return get("/game/decreaseCountNbUsers").getInt("value");
	}

	public static JSONObject createGame(String state, String nbUsers) throws Exception {
		return get("/game/new/" + nbUsers + "/" + state);
	}

	public static JSONObject updateState(String state) throws Exception {
		return get("/game/editState/" + state);
	}

	public static String getStateGame() throws Exception {
		return get("/game").getString("state");
	}

	/**
	 * Permet d'accéder à un endpoint de l'API et de retourner un objet
	 * JSON contenant le résultat de la requête.
	 *
	 * @param endpoint - L'endpoint auquel accéder (avec ou sans slash
	 *                   au début de la chaîne).
	 */
	public static JSONObject get(String endpoint) throws IOException, JSONException {
		StringBuilder builder = new StringBuilder(API_URL);
		JSONObject json = new JSONObject();

		if (endpoint.charAt(0) != '/')
			builder.append("/");

		builder.append(endpoint);

		InputStream is = new URL(builder.toString()).openStream();

		try {
			BufferedReader rd = new BufferedReader(
				new InputStreamReader(is, Charset.forName("UTF-8"))
			);

			json = new JSONObject(readAll(rd));
		} finally {
			is.close();
		}

		return json;
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();

		int cp;

		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}

		return sb.toString();
	}

	private static String urlEncode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

			return s;
		}
	}
}
