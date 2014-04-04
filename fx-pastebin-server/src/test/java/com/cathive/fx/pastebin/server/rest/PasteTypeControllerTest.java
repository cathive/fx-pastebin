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

import com.cathive.fx.pastebin.common.model.PasteType;
import com.cathive.fx.pastebin.server.configuration.Fixture;
import com.cathive.fx.pastebin.server.repository.PasteTypeRepository;
import com.cathive.fx.pastebin.server.repository.Repository;
import com.cathive.fx.pastebin.server.repository.jpa.JpaPasteRepository;
import com.cathive.fx.pastebin.server.service.DefaultPasteService;
import com.cathive.fx.pastebin.server.service.PasteTypeService;
import com.jayway.restassured.RestAssured;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @author Alexander Erben
 */
@RunWith(Arquillian.class)
@RunAsClient
public class PasteTypeControllerTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Fixture.class, PasteTypeController.class,
                        PasteTypeService.class, DefaultPasteService.class,
                        PasteTypeRepository.class, Repository.class,
                        JpaPasteRepository.class, PasteType.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testGetPasteTypeById() throws Exception {
        RestAssured.get("/pasteTypes/id/1").then().assertThat().body("lotto.lottoId", equalTo(5));

    }
}
