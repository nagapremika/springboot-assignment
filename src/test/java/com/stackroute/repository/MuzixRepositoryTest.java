package com.stackroute.repository;

import com.stackroute.domain.Muzix;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MuzixRepositoryTest {
@Autowired
MuzixRepository muzixRepository;
    @Autowired
    private Muzix muzix;
    @Before
    public void setUp()
    {
        muzix = new Muzix(1,"track1","Good");


    }

    @After
    public void tearDown(){

        muzixRepository.deleteAll();
    }

    @Test
    public void testSaveTrack(){
        muzixRepository.save(muzix);
        Muzix fetchTrack = muzixRepository.findById(muzix.getTrackId()).get();
        Assert.assertEquals(1,fetchTrack.getTrackId());
    }

    @Test
    public void testSaveTrackFailure(){
        Muzix testTrack = new Muzix(1,"majili","tamman");
        muzixRepository.save(muzix);
        Muzix fetchTrack = muzixRepository.findById(muzix.getTrackId()).get();
        Assert.assertNotSame(fetchTrack,testTrack);
    }

    @Test
    public void getAllTracks()
    {
        List<Muzix> tracks = new ArrayList<>();
        Muzix track1 = new Muzix(1,"track1","Bad");
        Muzix track2 = new Muzix(2,"track2","Good");
        tracks.add(track1);
        tracks.add(track2);
        List<Muzix> trackslist = muzixRepository.findAll();
        Assert.assertEquals("track1",tracks.get(0).getTrackName());
    }

}
