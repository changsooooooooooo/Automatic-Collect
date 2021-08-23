package com.autotradeserver.dto;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

class DTOTest {

    @Test
    void testGetJson() throws IOException, ParseException, JSONException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();

        FileReader fr = new FileReader("./coin_list.json");
        Reader reader = new BufferedReader(fr);
        jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONObject tempJson = (JSONObject)jsonObject.get("coin");
        JSONArray jsonArray = (JSONArray) tempJson.get("ethereum");
        System.out.println(jsonArray.get(0));
    }

}
