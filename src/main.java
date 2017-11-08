import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class main {
    ArrayList<Double> lat = new ArrayList<>(10);
    ArrayList<Double> lon = new ArrayList<>(10);
    ArrayList<Integer> rad = new ArrayList<>(10);
    int limit = 20;

    String url;
    int locationindex = 0;
    public static void main(String[] args) {
        new main().run();
    }

    private void run(){
//        init();
        try {
//            PrintWriter out = new PrintWriter("filename3.txt");
            getResults();
//            PrintWriter out = new PrintWriter("filename1.txt");
//            for (int i= 0; i < 10; i++) {
//                for (int j = 0; j < limit; j++) {
//                    doGet(j * 50, out, lat.get(i), lon.get(i), rad.get(i));
//                }
//            }

//            out.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void getResults() throws Exception{
        FileInputStream is = new FileInputStream("filename1.txt");
        BufferedReader file = new BufferedReader(new InputStreamReader(is));
        String line;
        JSONObject object;
//        while ((line = file.readLine()) != null){
//            object = ;
//            ArrayList<String> id = new ArrayList<>();
            for ( int i = 0; i < 50; i++){
                ArrayList<String> id = new ArrayList<>();
                for (int j = 0; j < 140; j++){
                    id.add(new JSONObject(file.readLine()).get("id").toString());
                }
                PrintWriter out = new PrintWriter("filename3.txt");
                CallThread a = new CallThread(id, out);
                a.start();
            }
//            doBusinessget(object.get("id").toString(), out);
//        }

        is.close();
        file.close();
    }
    private void init(){
        lat.add(0, 40.530753);
        lat.add(1, 40.605990);
        lat.add(2, 40.624432);
        lat.add(3, 40.627268);
        lat.add(4, 40.708772);
        lat.add(5, 40.712313);
        lat.add(6, 40.722228);
        lat.add(7, 40.816601);
        lat.add(8, 40.797120);
        lat.add(9, 40.876510);

        lon.add(0, -74.219902);
        lon.add(1, -74.134874);
        lon.add(2,  -73.847085);
        lon.add(3, -73.966685);
        lon.add(4, -73.781678);
        lon.add(5, -73.888197);
        lon.add(6, -73.974160);
        lon.add(7, -73.934913);
        lon.add(8, -73.835919);
        lon.add(9, -73.867597);

        rad.add(0, 2000);
        rad.add(1, 3000);
        rad.add(2, 4000);
        rad.add(3, 4000);
        rad.add(4, 4000);
        rad.add(5, 3000);
        rad.add(6, 2000);
        rad.add(7, 3000);
        rad.add(8, 4000);
        rad.add(9, 4000);
    }

    private void getIds(){
        InputStream is;

        try {
            JSONObject object;
            Object objectx;
            JSONObject result;
            String line;
            PrintWriter out = new PrintWriter("filename1.txt");
            is = new FileInputStream("filename.txt");
            BufferedReader file = new BufferedReader(new InputStreamReader(is));

            while ((line = file.readLine()) != null){

                object = new JSONObject(line);
                System.out.println(object.toString());
                result = new JSONObject().put("phone",object.get("phone")).put("id", object.get("id"));
                out.println(result.toString());
            }
            is.close();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void doBusinessget(String id, PrintWriter out) throws Exception{
        JSONObject object;
        Object objectx;
        JSONObject result;
        String line;

        url = "https://api.yelp.com/v3/businesses/" + id;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        final String USER_AGENT = "Mozilla/5.0";
        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Authorization", "Bearer 210BDrXEy368L3lIufoUWRTN7upFkGdTG5lzwKihbtaXqdWM0RyKC8REFM6ZsGGmvWncMyLY1McYJffInq-0G7RINXUrjPauPegkcS9k2tiEwNs1xgGJjaVlOOzkWXYx");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        object = new JSONObject(in.readLine());
//        System.out.println(object.toString());
//        result = new JSONObject().put("phone",object.get("phone")).put("id", object.get("id"));
        out.println(object.toString());

    }

    private void doGet(int offset, PrintWriter out, Double lat, Double lon, int rad){
        try {


            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");
            final String USER_AGENT = "Mozilla/5.0";
            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Authorization", "Bearer 210BDrXEy368L3lIufoUWRTN7upFkGdTG5lzwKihbtaXqdWM0RyKC8REFM6ZsGGmvWncMyLY1McYJffInq-0G7RINXUrjPauPegkcS9k2tiEwNs1xgGJjaVlOOzkWXYx");

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            System.out.println("output coming in");
            String json = "";
            while ((inputLine = in.readLine()) != null) {
                json += inputLine;
            }
            JSONObject  object = new JSONObject(json);

            System.out.println(limit);
            JSONArray businesses = (object.getJSONArray("businesses"));
            for (int i = 0 ; i < businesses.length(); i++) {
                out.println(businesses.get(i));
            }

            in.close();
            System.out.println("output done");

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
