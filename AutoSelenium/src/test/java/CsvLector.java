import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvLector {

    public static Object[][] leerCSV(String path) throws IOException {
        List<Object[]> datos = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String linea;


        br.readLine();

        while ((linea = br.readLine()) != null) {
            String[] campos = linea.split(",");
            datos.add(campos);
        }
        br.close();
        return datos.toArray(new Object[0][]);
    }
}
