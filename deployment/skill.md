---
layout: default
---

## Déploiement de la Skill

Après avoir déployé le [back-end](spring.html) et la [Lambda](lambda.html)
il faut créer la Skill en elle même.

Rendez-vous sur la [console Alexa](https://developer.amazon.com/alexa/console/ask#/)
et créez une nouvelle Skill.

Editez ensuite votre Skill et dans le panneau de gauche, cliquer sur
"JSON Editor" et importer le fichier JSON suivant :

~~~json
{
    "interactionModel": {
        "languageModel": {
            "invocationName": "monomalpoly",
            "intents": [
                {
                    "name": "AMAZON.CancelIntent",
                    "samples": []
                },
                {
                    "name": "AMAZON.HelpIntent",
                    "samples": []
                },
                {
                    "name": "AMAZON.StopIntent",
                    "samples": []
                },
                {
                    "name": "AMAZON.NavigateHomeIntent",
                    "samples": []
                },
                {
                    "name": "StartIntent",
                    "slots": [
                        {
                            "name": "NbUser",
                            "type": "AMAZON.NUMBER",
                            "samples": [
                                "{NbUser}",
                                "{NbUser} joueurs"
                            ]
                        }
                    ],
                    "samples": [
                        "lancer une partie"
                    ]
                },
                {
                    "name": "DiceDrawIntent",
                    "slots": [],
                    "samples": [
                        "lance les dés",
                        "lancer les dés"
                    ]
                },
                {
                    "name": "RulesIntent",
                    "slots": [],
                    "samples": [
                        "Règles du jeu",
                        "c'est quoi le principe",
                        "quelles sont les règles",
                        "Donne moi les règles",
                        "Donne les règles",
                        "Les règles"
                    ]
                },
                {
                    "name": "PlayerName",
                    "slots": [
                        {
                            "name": "Name",
                            "type": "AMAZON.FirstName",
                            "samples": [
                                "mon nom est {Name}"
                            ]
                        }
                    ],
                    "samples": [
                        "Mon pseudo est {Name}"
                    ]
                },
                {
                    "name": "MoneyIntent",
                    "slots": [],
                    "samples": [
                        "Quel est mon solde",
                        "Mon solde"
                    ]
                },
                {
                    "name": "WhoStart",
                    "slots": [],
                    "samples": [
                        "Qui va commencer"
                    ]
                },
                {
                    "name": "BuyHouseIntent",
                    "slots": [],
                    "samples": [
                        "Oui je veux améliorer"
                    ]
                },
                {
                    "name": "NotBuyHouseIntent",
                    "slots": [],
                    "samples": [
                        "Non je ne veux pas améliorer"
                    ]
                },
                {
                    "name": "BuyPropertyIntent",
                    "slots": [],
                    "samples": [
                        "Oui je veux acheter le terrain",
                        "Je veux acheter le terrain"
                    ]
                },
                {
                    "name": "NotBuyPropertyIntent",
                    "slots": [],
                    "samples": [
                        "Je ne veux pas acheter le terrain",
                        "Non je ne veux pas acheter le terrain"
                    ]
                }
            ],
            "types": []
        },
        "dialog": {
            "intents": [
                {
                    "name": "StartIntent",
                    "confirmationRequired": false,
                    "prompts": {},
                    "slots": [
                        {
                            "name": "NbUser",
                            "type": "AMAZON.NUMBER",
                            "confirmationRequired": true,
                            "elicitationRequired": true,
                            "prompts": {
                                "confirmation": "Confirm.Slot.1472315400348.1282908526876",
                                "elicitation": "Elicit.Slot.836740363172.997297356046"
                            },
                            "validations": [
                                {
                                    "type": "isGreaterThan",
                                    "prompt": "Slot.Validation.1085414299038.421296174718.369271779244",
                                    "value": "1"
                                }
                            ]
                        }
                    ]
                },
                {
                    "name": "PlayerName",
                    "confirmationRequired": false,
                    "prompts": {},
                    "slots": [
                        {
                            "name": "Name",
                            "type": "AMAZON.FirstName",
                            "confirmationRequired": true,
                            "elicitationRequired": true,
                            "prompts": {
                                "confirmation": "Confirm.Slot.1324155836959.270573806281",
                                "elicitation": "Elicit.Slot.1324155836959.270573806281"
                            }
                        }
                    ]
                }
            ],
            "delegationStrategy": "ALWAYS"
        },
        "prompts": [
            {
                "id": "Confirm.Slot.1472315400348.1282908526876",
                "variations": [
                    {
                        "type": "PlainText",
                        "value": "{NbUser} joueurs ?"
                    },
                    {
                        "type": "PlainText",
                        "value": "{NbUser}  ?"
                    }
                ]
            },
            {
                "id": "Elicit.Slot.836740363172.997297356046",
                "variations": [
                    {
                        "type": "PlainText",
                        "value": "Combien de joueurs vont jouer ?"
                    }
                ]
            },
            {
                "id": "Slot.Validation.1085414299038.421296174718.369271779244",
                "variations": [
                    {
                        "type": "PlainText",
                        "value": "Vous devez être au moins deux joueurs"
                    }
                ]
            },
            {
                "id": "Confirm.Intent.1191001551202",
                "variations": [
                    {
                        "type": "PlainText",
                        "value": "{Name} ?"
                    }
                ]
            },
            {
                "id": "Elicit.Slot.1209541348876.334315187580",
                "variations": [
                    {
                        "type": "PlainText",
                        "value": "Quel est votre nom ?"
                    }
                ]
            },
            {
                "id": "Confirm.Slot.681316994037.164474231168",
                "variations": [
                    {
                        "type": "PlainText",
                        "value": "{Name} ?"
                    }
                ]
            },
            {
                "id": "Elicit.Slot.1324155836959.270573806281",
                "variations": [
                    {
                        "type": "PlainText",
                        "value": "Quel est votre nom ?"
                    }
                ]
            },
            {
                "id": "Confirm.Slot.1324155836959.270573806281",
                "variations": [
                    {
                        "type": "PlainText",
                        "value": "{Name} ?"
                    }
                ]
            }
        ]
    }

~~~

Cliquez sur <kbd>Save Model</kbd> et <kbd>Build Model</kbd> en haut de
la page.

Ensuite, cliquez dans le panneau de gauche sur "Endpoint" et spécifiez
l'ARN de votre fonction Lambda précedemment déployée. Cliquez ensuite
sur <kbd>Save Endpoints</kbd>.

