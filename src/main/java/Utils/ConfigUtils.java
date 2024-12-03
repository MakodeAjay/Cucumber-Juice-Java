package Utils;

import org.aeonbits.owner.Config;

/**
 * ConfigUtils class
 * @author AjayMakode
 *
 */
@Config.Sources("File:./src/test/resources/Config.properties")
public interface ConfigUtils extends Config {

    @Key("coreProductURL")
    String getCoreProductURL();

    @Key("derviedProduct1URL")
    String getDerviedProduct1URL();

    @Key("derviedProduct2URL")
    String getDerviedProduct2URL();

    @Key("browser")
    String getBrowser();

    @Key("EnvironmentName")
    String getEnvironmentName();

    @Key("ImplicitlyWait")
    String getImplicitlyWait();

    @Key("feature_file_path")
    String getFeatureFilePath();

    @Key("runner_file_path")
    String getRunnerFilePath();
}
