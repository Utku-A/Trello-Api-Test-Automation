package ApiModel;

import static io.restassured.RestAssured.*;

public class Board {

    String token = "e79b6e428ea3c377f7b6a1c92ea6cafdd01d1202de75907139c6d8e4e4805eff";
    String apikey = "81817444f27c74313b64e8bcdd7350d1";

    String temporaryToken = "630a34f2cedffa0154b2055b%2FAJpBsm8Mnu7bPMWJFzJFcQddEeWbmBsszedcrz14Ezi4PukUkjy9kSycXx6FClsF";

    public String createBoard(String boardName) {
         return given()
                .contentType("application/json")
                .queryParam("name",boardName)
                .queryParam("key",apikey)
                .queryParam("token",token)
                .when()
                .post("https://api.trello.com/1/boards/")
                .then().log().all().statusCode(200).extract().path("id");
    }

    public String getListID(String boarcID) {
        String getBoardShortUrl  = given()
                .contentType("application/json")
                .queryParam("key",apikey)
                .queryParam("token",token)
                .when()
                .get("https://api.trello.com/1/boards/"+boarcID+"/")
                .then().log().all().statusCode(200).extract().path("shortUrl");

        return given()
                .contentType("application/json")
                .cookie("token="+temporaryToken+";")
                .when()
                .get(getBoardShortUrl+".json")
                .then().log().all().statusCode(200).extract().path("lists[0].id");

    }

    public Board deleteBoard(String cardID) {
        given()
                .contentType("application/json")
                .queryParam("key",apikey)
                .queryParam("token",token)
                .when()
                .delete("https://api.trello.com/1/boards/"+cardID+"/")
                .then().log().all().statusCode(200);
        return this;
    }

}
