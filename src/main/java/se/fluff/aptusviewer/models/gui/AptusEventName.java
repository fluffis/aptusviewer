package se.fluff.aptusviewer.models.gui;

import java.util.HashMap;
import java.util.Map;

public class AptusEventName {

    private static Map<Integer, String> eventnames = new HashMap<>();

    public static String get(int id) {
        eventnames.put(2, "Dörr upplåst");
        eventnames.put(128, "Öppningsförsök - fel tid");
        eventnames.put(129, "Öppningsförsök - fel dörr");

        eventnames.put(145, "Ringer - upptaget");
        eventnames.put(146, "Ringer - inget svar");
        eventnames.put(147, "Uppringning OK");
        eventnames.put(148, "Uppringning pågår");

        eventnames.put(154,  "Ingång aktiv");
        eventnames.put(155,  "Ingång inaktiv");
        eventnames.put(166, "Centralenhet inaktiv");
        eventnames.put(167, "Centralenhet aktiv");
        eventnames.put(168, "Centralenhet uppdaterad");
        eventnames.put(169, "Programavbrott");
        eventnames.put(172, "Centralenhet spänningssatt");
        eventnames.put(176, "Öppningförsök - fel kod");
        eventnames.put(177, "Öppningsförsök - fel kort");

        eventnames.put(248, "Aptus485-enhet inaktiv");
        eventnames.put(250, "Aptus485-enhet omstartad");
        eventnames.put(252, "Krypteringsnyckel sänd");
        eventnames.put(278, "Porttelefondisplay uppdaterad");
        eventnames.put(282, "Kommunikationsfel Aptus485-buss");
        eventnames.put(283, "Kommunikationsfel Aptus485-buss återställt");

        eventnames.put(512, "MultiServer");
        eventnames.put(518, "Kontakt med master OK");
        eventnames.put(519, "Ingen kontakt med master");

        eventnames.put(768, "Operatör - inloggad");
        eventnames.put(769, "Operatör - utloggad");
        eventnames.put(771, "Databas - uppdaterad");
        eventnames.put(772, "Databas - ny");
        eventnames.put(773, "Databas - radera");
        eventnames.put(776, "Personuppgiftsutdrag");

        return eventnames.getOrDefault(id, "Unknown event");
    }
}
