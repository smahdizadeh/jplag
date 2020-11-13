package jplag.options.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import jplag.ExitException;
import jplag.Language;
import jplag.Program;
import jplag.ProgramI;
import jplag.options.LanguageLiteral;

public class LanguageLiteralTest {

    /**
     * Since JPlag uses reflections to instantiate the language classes when using the command line options, this test
     * checks if the correlating language classes are found for each language literal. It also checks instantiation and
     * whether the short name matches the abbreviation of the literal.
     */
    @Test
    public void testInstantiationFromLiteral() {
        for (LanguageLiteral literal : LanguageLiteral.values()) {
            try {
                Class<?> languageClass = Class.forName(literal.getClassName());
                Constructor<?> constructor = languageClass.getConstructor(ProgramI.class);
                Object language = constructor.newInstance(new Program());
                assertTrue(language instanceof Language);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                    | SecurityException | ClassNotFoundException | ExitException exception) {
                fail("Could not instantiate from " + literal + ". Exception is " + exception);
            }
        }
    }
}
