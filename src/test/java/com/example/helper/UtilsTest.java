package com.example.helper;

import com.example.cityroutes.helper.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class UtilsTest {

    @Test
    public void testloadFile(){

        File file = Utils.loadFile("city.txt");
        Assert.assertNotNull(file);
    }

    @Test(expected = IllegalArgumentException.class)
     public void testloadNonExistingFile(){
        Utils.loadFile("city1.txt");
    }
}
