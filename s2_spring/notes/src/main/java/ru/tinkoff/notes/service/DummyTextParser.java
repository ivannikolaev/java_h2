package ru.tinkoff.notes.service;

import java.util.Collections;
import java.util.List;

//@Component
public class DummyTextParser implements TextParser {
    @Override
    public List<String> parse(String text) {
        return Collections.emptyList();
    }
}
