package com.example.helper;

import com.example.cityroutes.CityRoutesApplication;
import com.example.cityroutes.helper.Loader;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = CityRoutesApplication.class)
@RunWith(SpringRunner.class)
public class LoaderTest {

@Autowired
Loader loader;

@Test
    public void validateExisingRoute(){
    Assertions.assertThat(loader.checkRoute("Boston", "Albany")).isEqualTo("yes");
    Assertions.assertThat(loader.checkRoute("Trenton", "Albany")).isEqualTo("yes");
    Assertions.assertThat(loader.checkRoute("Trenton", "Boston")).isEqualTo("no");

}

}
