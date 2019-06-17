package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.util.Set;

public class TestBase {


  public boolean isIssueOpen(int issueId) {
    Issue issue = getIssueById(issueId);
    String status = issue.getState_name();
    return !status.equals("Resolved");
  }

  public Issue getIssueById(int issueId) {
    String json = RestAssured.get(String.format("http://bugify.stqa.ru/api/issues/%s.json", issueId)).asString();
    JsonElement issues = new JsonParser().parse(json).getAsJsonObject().get("issues");
    Set<Issue> setIssue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    Issue issue = setIssue.iterator().next();
    return issue;
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
