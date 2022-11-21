package Polyfill;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class PFFile {
    private static final Logger LOGGER = Logger.getLogger(PFFile.class.getName());
    
    public PFFile(String csvPath0, String... csvPath) {
        path = Paths.get(csvPath0, csvPath);
    }

    public PFFile(Path csvPath) {
        path = csvPath;
    }

    public PFArray<String[]> read() {
        PFArray<String[]> lines = new PFArray<>();
        try (Reader r = Files.newBufferedReader(path, charset)) {
            try (CSVReader cr = new CSVReader(r)) {
                // As List usage is not allowed we cannot use CSV.readAll() as it returns a List<String[]>
                String[] record;
                while((record = cr.readNext()) != null) {
                    lines.push_back(record);
                }
            }
        } catch (CsvValidationException e) {
            LOGGER.log(Level.WARNING, "Error during read " + path, e);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Error opening " + path, e);
        }
        return lines;
    }

    public boolean write(PFArray<String[]> lines) {
        // no OpenOptions: combination of CREATE | TRUNCATE_EXISTING | WRITE
        try (Writer w = Files.newBufferedWriter(path, charset)) {
            try (CSVWriter cw = new CSVWriter(w)) {
                // don't worry PFArray is iterable
                cw.writeAll(lines);
            }
            return true; //success
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Error writing " + path, e);
        }
        return false; //failure
    }

    private Path path;
    private static final Charset charset = StandardCharsets.UTF_8;
}
