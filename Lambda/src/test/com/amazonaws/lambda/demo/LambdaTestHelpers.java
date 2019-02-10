package com.amazonaws.lambda.demo;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.ui.PlainTextOutputSpeech;

public class LambdaTestHelpers {
    /**
     * Permet de créer un HashMap contenant un seul slot.
     *
     * @param name  - Nom du slot.
     * @param value - Valeur du slot.
     */
    protected HashMap<String, Slot> slot(String name, String value) {
        HashMap<String, Slot> slots = new HashMap<>();

        slots.put(name, Slot.builder()
            .withName(name)
            .withValue(value)
            .build());

        return slots;
    }

    /**
     * Permet de vérifier qu'une card avec le titre de notre Skill est
     * bien attachée à la réponse renvoyée.
     *
     * @param response - La réponse renvoyée.
     */
    protected void assertCard(SpeechletResponse response) {
        assertEquals("Monomalpoly", response.getCard().getTitle());
    }

    /**
     * Permet de vérifier qu'une réponse a bien un reprompt attaché.
     *
     * @param response - La réponse renvoyée.
     */
    protected void assertReprompt(SpeechletResponse response) {
        assertNotNull(response.getReprompt());
    }

    /**
     * Permet de vérifier que le speech d'une réponse contient une
     * sous chaîne de caratères.
     *
     * @param response - La réponse renvoyée.
     */
    protected void assertSpeechContains(SpeechletResponse response, String substr) {
        PlainTextOutputSpeech speech = (PlainTextOutputSpeech)response.getOutputSpeech();

        assertThat(speech.getText(), containsString(substr));
    }

    /**
     * Permet de simuler l'envoie d'un intent avec le nom donné.
     *
     * @param name  - Nom de l'intent.
     */
    protected SpeechletResponse sendIntent(String name) throws Exception {
        MonopolySpeechlet speechlet = new MonopolySpeechlet();

        return speechlet.onIntent(buildIntentRequest(name), null);
    }

    /**
     * Permet de simuler l'envoie d'un intent avec le nom donné et les
     * slots spécifiés.
     *
     * @param name  - Nom de l'intent.
     * @param slots - Slots de l'intent.
     */
    protected SpeechletResponse sendIntent(String name, HashMap<String, Slot> slots) throws Exception {
        MonopolySpeechlet speechlet = new MonopolySpeechlet();

        return speechlet.onIntent(buildIntentRequest(name, slots), null);
    }

    /**
     * Permet de créer une `IntentRequest` avec un `Intent` ayant le
     * nom donné.
     *
     * @param name - Nom de l'intent.
     */
    protected IntentRequest buildIntentRequest(String name) {
        return IntentRequest.builder()
            .withIntent(intentFrom(name))
            .withRequestId("foo")
            .build();
    }

    /**
     * Permet de créer une `IntentRequest` avec un `Intent` ayant le nom
     * donné et un ensemble de slots fournits.
     *
     * @param name  - Nom de l'intent.
     * @param slots - Slots attachés à l'intent.
     */
    protected IntentRequest buildIntentRequest(String name, HashMap<String, Slot> slots) {
        return IntentRequest.builder()
            .withIntent(intentFrom(name, slots))
            .withRequestId("foo")
            .build();
    }

    /**
     * Permet de créer une instance de la classe `Intent` avec un nom
     * donné.
     *
     * @param name - Nom de l'intent.
     */
    protected Intent intentFrom(String name) {
        return Intent.builder()
            .withName(name)
            .build();
    }

    /**
     * Permet de créer une instance de la classe `Intent` avec un nom
     * donné et un ensemble de et leur valeurs.
     *
     * @param name  - Nom de l'intent.
     * @param slots - Slots de l'intent.
     */
    protected Intent intentFrom(String name, HashMap<String, Slot> slots) {
        return Intent.builder()
            .withName(name)
            .withSlots(slots)
            .build();
    }
}
