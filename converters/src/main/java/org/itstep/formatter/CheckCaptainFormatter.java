package org.itstep.formatter;

import org.itstep.model.isCaptain.IsCaptain;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CheckCaptainFormatter implements Formatter<Enum<IsCaptain>> {
    @Override
    public Enum<IsCaptain> parse(String s, Locale locale) throws ParseException {
        IsCaptain not = IsCaptain.STUDENT;

        if("on".equals(s)) {
            not = IsCaptain.CAPTAIN;
        }

        return not;

    }

    @Override
    public String print(Enum<IsCaptain> isCaptainEnum, Locale locale) {
        return String.valueOf(isCaptainEnum);
    }
}
