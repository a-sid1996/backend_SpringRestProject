package com.spring.BackendProject.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JukeboxTest {

    @Test
    void getId() {
        Component[] componentTest = new Component[2];
        for (int i = 0; i < componentTest.length; i++){
            componentTest[i] = new Component();
            componentTest[i].setName("component name" + i);
        }

        Jukebox jukeboxTest = new Jukebox("idTest", "modelTest", componentTest);

        assertEquals("idTest", jukeboxTest.getId());
    }

    @Test
    void setId() {
        Component[] componentTest = new Component[2];
        for (int i = 0; i < componentTest.length; i++){
            componentTest[i] = new Component();
            componentTest[i].setName("component name" + i);
        }

        Jukebox jukeboxTest = new Jukebox("idTest", "modelTest", componentTest);

        jukeboxTest.setId("modelId1");
        assertEquals("modelId1", jukeboxTest.getId());

    }

    @Test
    void getModel() {
        Component[] componentTest = new Component[2];
        for (int i = 0; i < componentTest.length; i++){
            componentTest[i] = new Component();
            componentTest[i].setName("component name" + i);
        }

        Jukebox jukeboxTest = new Jukebox("idTest", "modelTest", componentTest);

        assertEquals("modelTest", jukeboxTest.getModel());
    }

    @Test
    void setModel() {
        Component[] componentTest = new Component[2];
        for (int i = 0; i < componentTest.length; i++){
            componentTest[i] = new Component();
            componentTest[i].setName("component name" + i);
        }

        Jukebox jukeboxTest = new Jukebox("idTest", "modelTest", componentTest);

        jukeboxTest.setModel("modelTest1");
        assertEquals("modelTest1", jukeboxTest.getModel());

    }

    @Test
    void getComponents() {
        Component[] componentTest = new Component[2];
        for (int i = 0; i < componentTest.length; i++){
            componentTest[i] = new Component();
            componentTest[i].setName("component name" + i);
        }

        Jukebox jukeboxTest = new Jukebox("idTest", "modelTest", componentTest);

        assertEquals(componentTest, jukeboxTest.getComponents());
    }

    @Test
    void setComponents() {
        Component[] componentTest = new Component[2];
        for (int i = 0; i < componentTest.length; i++){
            componentTest[i] = new Component();
            componentTest[i].setName("component name" + i);
        }

        Jukebox jukeboxTest = new Jukebox("idTest", "modelTest", componentTest);

        Component[] componentTest2 = new Component[2];
        for (int i = 0; i < componentTest2.length; i++){
            componentTest[i] = new Component();
            componentTest[i].setName("component name" + i);
        }

        jukeboxTest.setComponents(componentTest2);
        assertEquals(componentTest2, jukeboxTest.getComponents());

    }
}