package actions;


import com.github.dvdme.ForecastIOLib.ForecastIO;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Jari Van Melckebeke
 */
public class Surroundings {

    public String showWeather(String text) {
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\GeoLiteCity.dat");
        System.out.println(file.isFile());
        Location locationServices = null;
        try {
            LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));

            String ip = in.readLine(); //you get the IP as a String
            System.out.println(ip);
            locationServices = lookup.getLocation(ip);
            System.out.println(locationServices);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("cannot find address");
            return "cannot find address";
        }

        String out;
        float latitude = locationServices.latitude;
        float longitude = locationServices.longitude;
        System.out.println(latitude + "," + longitude);

        ForecastIO forecastIO = new ForecastIO("78d83bb68f21b2ba86082370c54f0b54");
        System.out.println(forecastIO.toString());
        System.out.println(forecastIO.getForecast(String.valueOf(latitude), String.valueOf(longitude)));
        out = forecastIO.getCurrently().get("summary").toString().replaceAll("\"", "") + ". temperature of " + forecastIO.getCurrently().get("temperature") + " degrees";
        System.out.println(out);
        return out;

    }
}