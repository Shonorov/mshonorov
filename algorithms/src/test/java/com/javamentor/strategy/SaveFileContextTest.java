package com.javamentor.strategy;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SaveFileContextTest {

    @Test
    public void whenSaveAsJPEGThenReturnCorrectName() {
        SaveFileContext context = new SaveFileContext();
        context.setStrategy(new SaveFileJPEG());
        assertThat(context.saveFile("fileName"), is("fileName.jpeg"));
    }

    @Test
    public void whenSaveAsPNGThenReturnCorrectName() {
        SaveFileContext context = new SaveFileContext();
        context.setStrategy(new SaveFilePNG());
        assertThat(context.saveFile("fileName"), is("fileName.png"));
    }
}