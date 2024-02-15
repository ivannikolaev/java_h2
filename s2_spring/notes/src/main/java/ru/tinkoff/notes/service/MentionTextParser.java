package ru.tinkoff.notes.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MentionTextParser implements TextParser {
    private final Pattern MENTION_PATTERN = Pattern.compile("@\\w+");
    public List<String> parse(String text) {
        List<String> result = new ArrayList<>();
        Matcher matcher = MENTION_PATTERN.matcher(text);
        while (matcher.find()) {
            String mention = matcher.group().substring(1);
            result.add(mention);
        }
        return result;
    }
}
