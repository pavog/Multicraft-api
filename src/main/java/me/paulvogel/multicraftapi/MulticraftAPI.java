package me.paulvogel.multicraftapi;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.DatatypeConverter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MulticraftAPI {
    private String url;
    private String user;
    private String key;

    public MulticraftAPI(String url, String user, String key) {
        this.url = url;
        this.user = user;
        this.key = key;
    }

    public JsonObject call(String method, Map<String, String> parameters) {
        parameters = new HashMap<>(parameters);
        try {
            URL url = new URL(this.url);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // Add neccessary parameters
            parameters.put("_MulticraftAPIMethod", method);
            parameters.put("_MulticraftAPIUser", user);

            StringBuilder apiKeySalt = new StringBuilder();
            StringBuilder query = new StringBuilder();

            for (Map.Entry<String, String> param : parameters.entrySet()) {
                final String parameterKey = param.getKey();
                final String parameterValue = param.getValue();

                // The api key is hashed with all params put after each other (with their values)
                apiKeySalt.append(parameterKey).append(parameterValue);
                query.append("&").append(URLEncoder.encode(parameterKey)).append("=").append(URLEncoder.encode(parameterValue));
            }

            // Append apikey (build by a hash of the apikey and the concatinated params as salt)
            query.append("&_MulticraftAPIKey=").append(MulticraftAPI.getMulticraftEncodedAPIKey(apiKeySalt.toString(), this.key));

            // Write all query params
            DataOutputStream output = new DataOutputStream(conn.getOutputStream());
            output.writeBytes(query.toString());
            output.close();

            JsonParser parser = new JsonParser();
            JsonObject result = parser.parse(new InputStreamReader(conn.getInputStream())).getAsJsonObject();

            if (!(result.get("success").getAsBoolean())) {
                JsonArray errors = result.get("errors").getAsJsonArray();
                StringBuilder exc = new StringBuilder();
                for (JsonElement jsonElement : errors) {
                    exc.append(jsonElement.getAsString());
                    exc.append(", ");
                }
                throw new Exception(exc.toString());
            }
            return result.get("data").getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMulticraftEncodedAPIKey(String parameterQuery, String apiKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hasher = Mac.getInstance("HmacSHA256");
        hasher.init(new SecretKeySpec(apiKey.getBytes(), "HmacSHA256"));

        byte[] hash = hasher.doFinal(parameterQuery.getBytes());

        return DatatypeConverter.printHexBinary(hash).toLowerCase();
    }
}
