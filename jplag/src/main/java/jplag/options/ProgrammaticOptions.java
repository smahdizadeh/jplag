package jplag.options;

import jplag.ExitException;
import jplag.Language;

/**
 * Options class for programmatic use of JPlag, for example when integrating JPlag into third party applications. This
 * class is not meant for CLI use.
 * @author Timur Saglam
 */
public class ProgrammaticOptions extends Options { // TODO TS: Add param checks for all the constructors, especially directory and percentage checks

    /**
     * Creates an options configuration.
     * @param language is the language of the files to check. For supported languages see {@link LanguageLiteral}.
     * @param rootDirectory is the directory that contains submissions.
     */
    public ProgrammaticOptions(Language language, String rootDirectory) {
        if (language == null) {
            throw new IllegalArgumentException("Language should not be null!");
        }
        setLanguage(language);
        languageName = language.getShortName();
        root_dir = rootDirectory;
    }

    /**
     * Creates an options configuration.
     * @param language is the language of the files to check. For supported languages see {@link LanguageLiteral}.
     * @param rootDirectory is the main working directory that all the files.
     * @param baseCodeDirectory is the directory relative to the root directory which contains the base code (common framework given to the students).
     * @throws ExitException throws an exception if the base code directory is not valid.
     */
    public ProgrammaticOptions(Language language, String rootDirectory, String baseCodeDirectory) throws ExitException {
        this(language, rootDirectory);
        checkDirectory(baseCodeDirectory, "base code directory");
        basecode = baseCodeDirectory;
        useBasecode = true;
        checkBasecodeOption();
    }
    
    /**
     * Creates an options configuration.
     * @param language is the language of the files to check. For supported languages see {@link LanguageLiteral}.
     * @param rootDirectory is the main working directory that all the files.
     * @param similarity is the similarity required to for two programs to be saved as match.
     * @throws ExitException throws an exception if the base code directory value is not valid.
     */
    public ProgrammaticOptions(Language language, String rootDirectory, int similarity) {
        this(language, rootDirectory);
        store_matches = similarity;
        store_percent = true;
    }

    /**
     * Creates an options configuration.
     * @param language is the language of the files to check. For supported languages see {@link LanguageLiteral}.
     * @param rootDirectory is the main working directory that all the files.
     * @param baseCodeDirectory is the directory relative to the root directory which contains the base code (common framework given to the students).
     * @param similarity is the similarity required to for two programs to be saved as match.
     */
    public ProgrammaticOptions(Language language, String rootDirectory, String baseCodeDirectory, int similarity) throws ExitException {
        this(language, rootDirectory, baseCodeDirectory);
        store_matches = similarity;
        store_percent = true;
    }

    private void checkDirectory(String directory, String directoryName) {
        if (directory == null || directory.length() == 0) {
            throw new IllegalArgumentException("Illegal options parameter, " + directoryName + ": " + directory);
        }
    }
    
    /*
     * TODO TS: Support sub_dir in constructors
     * TODO TS: Support read_subdirs in constructors
     * TODO TS: Support -m without % in constructors
     */

}
