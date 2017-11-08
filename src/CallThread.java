import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CallThread extends Thread {
    PrintWriter out;
    ArrayList<String> id;
    String url;

    public CallThread(ArrayList<String> ids, PrintWriter out){
        this.id = ids;
        this.out = out;
    }
    public void run(){
        System.out.println("Thread started");
        try {
            for (String ids : id) {
                doBusinessget(ids);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("thread finished");
        out.close();
    }
    private void doBusinessget(String id) throws Exception{
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
//  System.out.println(object.toString());
//        result = new JSONObject().put("phone",object.get("phone")).put("id", object.get("id"));

        out.println(object.toString());

    }

}
