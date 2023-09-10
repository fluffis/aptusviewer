package se.fluff.aptusviewer.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import se.fluff.aptusviewer.models.gui.AptusRow;

import java.text.Normalizer;
import java.util.ArrayList;

public class SearchboxChangeListener implements ChangeListener<String> {

    private final FilteredList<AptusRow> filteredList;

    public SearchboxChangeListener(FilteredList<AptusRow> filteredList) {
        this.filteredList = filteredList;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        filteredList.setPredicate(row -> {
            if(newValue == null || newValue.isEmpty()) {
                return true;
            }

            ArrayList<String> strings = new ArrayList<>();
            String match = normalize(newValue);
            strings.add(normalize(row.getCustomer()));
            strings.add(normalize(row.getObjectAuthorities()));
            strings.add(normalize(row.getSurname()));

            return strings.stream().anyMatch(p -> p.contains(match));
        });
    }

    private String normalize(String n) {
        return Normalizer.normalize(n.toLowerCase(), Normalizer.Form.NFKD).replaceAll("\\p{M}", "");
    }
}
