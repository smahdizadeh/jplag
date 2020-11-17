package jplag.options.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jplag.ExitException;
import jplag.Program;
import jplag.java19.Language;
import jplag.options.Options;
import jplag.options.ProgrammaticOptions;

/**
 * Test class to ensure the programmatic options behave the same ways as the CLI options. Also checks whether the
 * programmatic options control the {@link Program} the same way.
 * @author Timur Saglam
 */
public class ProgrammaticOptionsTest {

    private static final String TEST_FILE_DIRECTORY = "src/test/resources/";
    private static final String BASE_DIRECTORY = "base";
    private static final String ROOT_DIRECTORY = "testSubmissions";
    private static final String RESULT_PREFIX = "results_";
    private static final boolean CLEAR_RESULTS = true; // Set to false if you want to look at the result files
    private File root;
    private File base;
    private File results;

    @Before
    public void setUp() throws IOException {
        File testFiles = new File(TEST_FILE_DIRECTORY);
        root = new File(testFiles, ROOT_DIRECTORY);
        base = new File(root, BASE_DIRECTORY);
        if (!testFiles.exists() || !root.exists() || !base.exists()) {
            throw new IOException("Test files are missing!");
        }
        results = new File(testFiles, RESULT_PREFIX + System.currentTimeMillis());
        if (results.exists()) {
            throw new FileAlreadyExistsException("Cannot override existing results!");
        }
    }

    @Test
    public void testWithRootAndBaseAndSimilarity() {
        try {
            // setup input:
            Program program = new Program();
            Language language = new Language(program); // Java 1.9 language
            Options options = new ProgrammaticOptions(language, root.getAbsolutePath(), base.getName(), 70);
            // ensure initial state:
            options.result_dir = results.getAbsolutePath();
            assertEquals(0, options.getProgress());
            assertEquals(Options.DEFAULT_STATE, options.getState());
            // run and check results:
            program.run(options);
            assertEquals(100, options.getProgress()); // TODO TS: Check programmatic results when implemented
            assertEquals(Options.GENERATING_RESULT_FILES, options.getState());
        } catch (ExitException exception) {
            fail("Fail: " + exception.getReport());
        }
    }

    @After
    public void tearDown() throws IOException {
        if (CLEAR_RESULTS) {
            try {
                Files.walk(Path.of(results.toURI())).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            } catch (IOException exception) {
                throw new IOException("Could not delete results: " + exception.getMessage());
            }
        }
    }
}
