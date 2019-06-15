package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.Set;

public class TestBase {

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    return !getIssueById(issueId).getState_name().equals("Resolved");
  }

  public Issue getIssueById(int id) throws IOException {
    Issue issue = new Issue();
    getIssues().stream().findFirst().map((i) -> issue.withId(i.getId()).withSubject(i.getSubject())
            .withDescription(i.getDescription())
            .withState_name(i.getState_name()));
    return issue;
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public Set<Issue> getIssues() throws IOException {
    String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json?limit=1000").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

}
