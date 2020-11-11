package jplag.options;

import jplag.ExitException;
import jplag.Language;
import jplag.Program;

/**
 * Options class for programmatic use of JPlag, for example when integrating JPlag into third party applications. This
 * class is not meant for CLI use.
 * @author Timur Saglam
 */
public class ProgrammaticOptions extends Options { // TODO TS: Add param checks for all the constructors, especially directory checks and percentage checks

    /**
     * Creates a options configuration.
     * @param language is the language of the files to check. For supported languages see {@link LanguageLiteral}.
     */
    ProgrammaticOptions(Language language) {
        if (language == null) {
            throw new IllegalArgumentException("Language should not be null!");
        }
        this.language = language;
        languageName = language.getShortName();
    }

    /**
     * Creates a options configuration.
     * @param language is the language of the files to check. For supported languages see {@link LanguageLiteral}.
     * @param sourceDirectory is the source directory to look in for programs.
     */
    ProgrammaticOptions(Language language, String sourceDirectory) {
        this(language);
        checkDirectory(sourceDirectory, "source directory");
        sub_dir = sourceDirectory;
        read_subdirs = true;
    }

    /**
     * Creates a options configuration.
     * @param language is the language of the files to check. For supported languages see {@link LanguageLiteral}.
     * @param sourceDirectory is the source directory to look in for programs.
     * @param baseCodeFolder is the directory which contains the base code (common framework given to the students)
     */
    ProgrammaticOptions(Language language, String sourceDirectory, String baseCodeDirectory) {
        this(language, sourceDirectory);
        checkDirectory(baseCodeDirectory, "base code directory");
        basecode = baseCodeDirectory;
        useBasecode = true;
    }

    /**
     * Creates a options configuration.
     * @param language is the language of the files to check. For supported languages see {@link LanguageLiteral}.
     * @param sourceFolder is the source directory to look in for programs.
     * @param baseCodeFolder is the directory which contains the base code (common framework given to the students)
     * @param similarity is the similarity required to for two programs to be saved as match.
     */
    ProgrammaticOptions(Language language, String sourceDirectory, String baseCodeDirectory, int similarity) { // TODO constructor for -m without %
        this(language, sourceDirectory, baseCodeDirectory);
        store_matches = similarity;
        store_percent = true;
    }

    @Override
    public void initializeSecondStep(Program program) throws ExitException {
        // defaults TODO TS: could be in super class? Or maybe remove the second step mechanic completely
        if (!min_token_match_set) {
            this.min_token_match = this.language.min_token_match();
        }
        if (!suffixes_set) {
            this.suffixes = this.language.suffixes();
        }
        checkBasecodeOption();
    }

    private void checkDirectory(String directory, String directoryName) {
        if (directory == null || directory.length() == 0) {
            throw new IllegalArgumentException("Illegal options parameter, " + directoryName + ": " + directory);
        }
    }

}
