package ru.Yurkin.Queue.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class PartOfSeries implements Iterator {

    private Integer digitInPart;

    private String charInPart;

    public PartOfSeries() {
        this.charInPart="a";
        this.digitInPart=0;
    }

    public PartOfSeries(String part) {
        this.charInPart=part.substring(0,1);
        this.digitInPart=Character.digit(part.charAt(1), 10);
    }

    protected static Matcher splitSeriesOnParts(String series) throws IllegalArgumentException {
        if(!series.matches("([a-z]\\d)([a-z]\\d)+")){throw new IllegalArgumentException();}
        String regexp = "([a-z]\\d)";
        Pattern pattern = Pattern.compile(regexp);
        return pattern.matcher(series);
    }

    @Override
    public boolean hasNext() {
        return !this.toString().equals("z9");

    }

    @Override
    public Object next() {
        if(this.getDigitInPart()==9){this.setDigitInPart(0);
            if(this.getCharInPart().equals("z")){this.setCharInPart("a");}
            else {
                this.setCharInPart(String.valueOf(Character.toChars(this.getCharInPart().codePoints().findAny().getAsInt()+1)));
            }
        }
        else{
            this.setDigitInPart(this.getDigitInPart()+1);
        }
        return this;
    }

    @Override
    public String toString() {
        return charInPart+digitInPart.toString();
    }
}
