package com.myfirstapp.markgeohelper.repositories.benchmark;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.myfirstapp.markgeohelper.repositories.BenchMarkRepository;
import com.myfirstapp.markgeohelper.repositories.repint.Repository;
import com.myfirstapp.markgeohelper.types.mydata.elevation.BenchMark;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class BenchMarkRepositoryTest {
    private BenchMarkRepository repository;
    private BenchMark testBenchMark;
    Context appContext;
    @Before
    public void setUp() throws Exception {
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        repository = BenchMarkRepository.getRepository(appContext);
    }


    @Test
    public void testGetItem() {
        repository.addItem();
        BenchMark bm = repository.getLastItem();
        UUID id = bm.getId();
        assertEquals(bm,repository.getItem(id));
        repository.removeItem();
    }

    @Test
    public void testGetLastItem() {
        repository.addItem();
        BenchMark bm = repository.getLastItem();
        System.out.println(bm.getId());
        repository.removeItem();
    }

    @Test
    public void testGetRepository() {
        Repository repositoryTest = BenchMarkRepository.getRepository(appContext);
        assertThat(repository,is(repositoryTest));
    }

    @Test
    public void testAddItem() {
        for (int i = 0; i < 100; i++ ){
            repository.addItem();
        }
        assertEquals(100,repository.getList().size());
        for (int i = 0; i < 100; i++ ){
            repository.removeItem();
        }
        assertEquals(0,repository.getList().size());

    }
    @Test
    public void testGetList() {
        assertEquals(0,repository.getList().size());
        repository.addItem();
        assertEquals(1,repository.getList().size());
        repository.removeItem();
    }

    @Test
    public void testRemoveItem() {
        repository.addItem();
        repository.removeItem();
        assertEquals(0,repository.getList().size());
    }

}