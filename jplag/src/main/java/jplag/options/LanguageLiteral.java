package jplag.options;

import java.util.ArrayList;
import java.util.List;

import jplag.Language;

/**
 * Language literals that encapsulate the available languages with their abbreviations and the correlating language
 * class names. Intended to help with the reflective creation of language objects.
 * @author Timur Saglam
 */
public enum LanguageLiteral { // TODO TS: better name?
    JAVA_19("java19", "jplag.java19.Language"),
    JAVA_17("java17", "jplag.java17.Language"),
    JAVA_15("java15", "jplag.java15.Language"),
    JAVA_15_DM("java15dm", "jplag.java15.LanguageWithDelimitedMethods"),
    JAVA_12("java12", "jplag.java.Language"),
    JAVA_11("java11", "jplag.javax.Language"),
    PYTHON_3("python3", "jplag.python3.Language"),
    CPP("c/c++", "jplag.cpp.Language"),
    C_SHARP("c#-1.2", "jplag.csharp.Language"),
    CHAR("char", "jplag.chars.Language"),
    TEXT("text", "jplag.text.Language"),
    SCHEME("scheme", "jplag.scheme.Language");

    private static final String DEFAULT = " (default)";
    private static final String COMMA = ", ";
    private String abbreviation;
    private String languageClass;

    private LanguageLiteral(String abbreviation, String languageClass) {
        this.abbreviation = abbreviation; // TODO TS: This is a relic of the previous implementation and just the short name from {@link Language#getShortName()}.
        this.languageClass = languageClass;
    }

    /**
     * Returns the abbreviation of the language, as used with the language parameter in the CLI. This matches {@link Language#getShortName()}.
     * @return the abbreviation such as <code>"java19"</code> or <code>"c/c++"</code>.
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Returns the fully qualified name of the language class in the correlating language frontend
     * @return the language class name, such as <code>"jplag.java19.Language"</code> or <code>"jplag.cpp.Language"</code>.
     */
    public String getClassName() { // TODO TS: maybe rename to getClassName()
        return languageClass;
    }

    /**
     * Returns the default literal, which represents the default language for JPlag.
     * @return {@link LanguageLiteral#JAVA_19}
     */
    public static LanguageLiteral getDefault() {
        return JAVA_19;
    }

    /**
     * Returns a string containing all language abbreviations separated by commas.
     * @return a string of all available languages.
     */
    public static String availableLanguages() {
        List<String> abbreviations = new ArrayList<>();
        for (LanguageLiteral language : values()) {
            if (language == getDefault()) {
                abbreviations.add(language.getAbbreviation() + DEFAULT);
            } else {
                abbreviations.add(language.getAbbreviation());
            }
        }
        return String.join(COMMA, abbreviations);
    }
}
