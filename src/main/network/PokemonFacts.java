package network;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Call to api taken from   https://www.baeldung.com/java-http-request
// JSON parsing from        https://dzone.com/articles/how-to-parse-json-data-from-a-rest-api-using-simpl
public class PokemonFacts {

    // EFFECTS: starts the api calls
    public static void start() throws MalformedURLException, IOException, ParseException {
        String theURL = "https://pokeapi.co/api/v2/pokemon/?limit=60";
        JSONArray pokemon = getPageData(theURL, "results");
        List<String> pokemonURL = new ArrayList<>();
        for (int i = 0; i < pokemon.size(); i++) {
            JSONObject jsonObject = (JSONObject)pokemon.get(i);
            pokemonURL.add((String) jsonObject.get("url"));
        }
        printMessage();
        choosePokemon((ArrayList<String>) pokemonURL);
    }

    // EFFECTS: prints the options for the fun facts method
    private static void printMessage() {
        System.out.println("Here are some facts about: ");
        System.out.println("Press [1] for bulbasaur");
        System.out.println("Press [2] for charmander");
        System.out.println("Press [3] for squirtle");
    }

    // EFFECTS: initializes json array from given content
    private static JSONArray initializeJson(String content, String key) throws ParseException {
        JSONParser parse = new JSONParser();
        JSONObject jobj = (JSONObject)parse.parse(content);
        JSONArray jsonArray = (JSONArray) jobj.get(key);
        return jsonArray;
    }

    // EFFECTS: initialize URL connection to API
    private static void initializeConnection(HttpURLConnection con) throws ProtocolException {
        con.setRequestProperty("User-Agent", "");
        con.setRequestProperty("Content-Type", "application /json");
        con.setRequestMethod("GET");
        con.setDoOutput(true);
    }

    // EFFECTS: returns the content of the URL connection
    public static String readInput(HttpURLConnection con) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputline;
        String content = "";

        while ((inputline = br.readLine()) != null) {
            content += inputline;
        }
        return content;
    }

    // EFFECTS: gets the content from url
    private static JSONArray getPageData(String theURL, String key) throws IOException, ParseException {
        URL url = new URL(theURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        initializeConnection(con);
        String content = readInput(con);
        JSONArray jsonData = initializeJson(content, key);
        return jsonData;
    }

    // EFFECTS: gets the abilities data from the specific pokemon based on its index number i
    private static JSONArray pickPokemon(int i, ArrayList<String> lop) throws IOException, ParseException {
        JSONArray specificPokemon = getPageData(lop.get(i), "abilities");
        return specificPokemon;
    }


    // EFFECTS: chooses the specific pokemon
    private static void choosePokemon(ArrayList<String> lop) throws IOException, ParseException {
        Scanner reader = new Scanner(System.in);
        String num = reader.nextLine();
        if (num.equals("1")) {
            JSONArray bulb = pickPokemon(0, lop);
            System.out.println("Did you know, Bulbasaur has the abilities: ");
            printAbilities(bulb);
        }
        if (num.equals("2")) {
            JSONArray charm = pickPokemon(3, lop);
            System.out.println("Did you know, Charmander has the abilities: ");
            printAbilities(charm);
        }
        if (num.equals("3")) {
            JSONArray squirt = pickPokemon(6, lop);
            System.out.println("Did you know, Squirtle has the abilities: ");
            printAbilities(squirt);
        }
    }

    // EFFECTS: prints the abilities of the given pokemon
    public static void printAbilities(JSONArray pokemon) {
        for (int i = 0; i < pokemon.size(); i++) {
            JSONObject poke = (JSONObject) pokemon.get(i);
            JSONObject ability = (JSONObject) poke.get("ability");
            System.out.println(ability.get("name"));
        }
    }
}
