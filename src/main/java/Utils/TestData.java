package Utils;

import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * TestData class
 * @author AjayMakode
 *
 */
@Data
public class TestData {

    private List<String> tickets;
    private List<String> team;
    public List<String> getYamlData(String datavalue){
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        List<String> list = null;
        try {
            inputStream = new FileInputStream("src/test/resources/testData.yaml");
            if (inputStream == null) {
                throw new IOException("File not found");
            }
            Map<String, Object> data = yaml.load(inputStream);
            list = (List<String>) data.get(datavalue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
