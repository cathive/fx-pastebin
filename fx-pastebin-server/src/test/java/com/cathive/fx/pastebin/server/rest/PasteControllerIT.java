/*
 * Copyright (C) 2014 The Cat Hive Developers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cathive.fx.pastebin.server.rest;

import com.cathive.fx.pastebin.server.configuration.Fixture;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.json.JsonObject;

import static com.jayway.restassured.RestAssured.*;
import static javax.json.Json.createReader;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.jboss.shrinkwrap.api.Filters.exclude;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;

/**
 * Integration Test for {@link PasteTypeController}
 * Runs via Arquillian in a managed or embedded application server.
 * Currently configured to run on a managed JBoss Wildfly.
 *
 * @author Alexander Erben (a_erben@outlook.com)
 */
@RunWith(Arquillian.class)
@RunAsClient
public class PasteControllerIT {

    private static final boolean RECURSIVE = true;

    private static final String CONTROLLER_URI = "/fx-pastebin/api/pastes/";

    private String firstPasteType;

    private int firstUserProfile;

    /**
     * Add new resources or package namespaces necessary for the execution of this IT here.
     *
     * @return the readily wrapped archive.
     */
    @Deployment
    public static WebArchive setupDeployment() {
        return create(WebArchive.class, "fx-pastebin.war")
                .addPackages(RECURSIVE, exclude(Fixture.class), "com.cathive")
                .addClass(TestFixture.class)
                .addAsResource("META-INF/beans.xml")
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("orm.xml");
    }

    @Before
    public void setupProfileAndPasteType() {
        firstPasteType = firstJsonObject("/fx-pastebin/api/pasteTypes").getString("name");
        firstUserProfile = firstJsonObject("/fx-pastebin/api/userProfiles").getInt("id");
    }

    @Test
    public void testGetAllPastes() throws Exception {
        get(CONTROLLER_URI)
                .then().assertThat()
                .body("title", hasItems("c main", "d main", "delphi main"))
                .and()
                .body("content", hasItems("int main(int argc, char *argv[]){}", "void main() {}", "no such thing"));
    }

    @Test
    public void testFindPasteById() throws Exception {
        get("/fx-pastebin/api/pastes/id/" + firstJsonObject(CONTROLLER_URI).getInt("id"))
                .then().assertThat()
                .body("title", is(firstJsonObject(CONTROLLER_URI).getString("title")))
                .and()
                .body("content", is(firstJsonObject(CONTROLLER_URI).getString("content")));
    }

    @Test
    public void testSavePaste() {
        final String locationHeader = given()
                .contentType("application/json")
                .body("{\"title\":\"java_example\",\"content\":\"a=b\"}")
                .post(saveUri())
                .header("location");
        get(locationHeader)
                .then().assertThat()
                .body("title", is("java_example"))
                .and()
                .body("content", is("a=b"));
    }

    @Test
    public void testSaveAndDelete() {
        String locationHeader = given()
                .contentType("application/json")
                .body("{\"title\":\"scala\",\"content\":\"Scala Programming Language\"}")
                .post(saveUri())
                .header("location");
        get(locationHeader)
                .then().assertThat()
                .body("title", is("scala"))
                .and()
                .body("content", is("Scala Programming Language"));
        delete(locationHeader)
                .then().assertThat()
                .body("title", is("scala"))
                .and()
                .body("content", is("Scala Programming Language"));
        get(locationHeader)
                .then().assertThat().statusCode(SC_NO_CONTENT);
    }

    @Test
    public void testGetPasteTypeWrongNameShould402() {
        get(CONTROLLER_URI + "id/99999")
                .then().assertThat().statusCode(SC_NO_CONTENT);
    }

    @Test
    public void testWrongUriShould404() throws Exception {
        get("/fx-pastebin/api/pasteTypes/foo").then().statusCode(SC_NOT_FOUND);
        get("/fx-pastebin/api/pasteTypes/id/c").then().statusCode(SC_NOT_FOUND);
    }

    private JsonObject firstJsonObject(String controllerUri) {
        return createReader(
                get(controllerUri).getBody().asInputStream())
                .readArray()
                .getJsonObject(0);
    }

    private String saveUri() {
        return CONTROLLER_URI + "userProfile/" + firstUserProfile + "/pasteType/" + firstPasteType;
    }
}
