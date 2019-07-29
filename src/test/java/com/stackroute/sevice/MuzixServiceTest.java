package com.stackroute.sevice;

import com.stackroute.domain.Muzix;
import com.stackroute.repository.MuzixRepository;
import com.stackroute.service.MuzixServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
public class MuzixServiceTest {

    @Mock
    private MuzixRepository muzixRepository;

    private Muzix muzix;
    @InjectMocks
    private MuzixServiceImpl muzixServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
          }

    @Test   //Tests saving a track
    public void testSaveTrack() throws Exception {
        Muzix muzix = new Muzix(1, "Track1", "Good");
        when(muzixRepository.save(muzix)).thenReturn(muzix);
        Muzix savedMuzix=muzixServiceImpl.saveTrack(muzix);
        Assert.assertEquals(muzix,savedMuzix);
       verify(muzixRepository, times(1)).save(muzix);
        verifyNoMoreInteractions(muzixRepository);
    }
    @Test   //tests deleting a track
    public void testDeleteTrack()
    {
        Muzix muzix1=new Muzix(1, "Track1", "Good");
        Muzix muzix2=new Muzix(2, "Track2", "Good");
      List<Muzix> list=new ArrayList<>();
      list.add(muzix2);
      when(muzixRepository.findAll()).thenReturn(list);
      List<Muzix> list1=muzixServiceImpl.deleteTrack(muzix1.getTrackId());
        Assert.assertEquals(list,list1);
      verify(muzixRepository,times(1));
    }
    @Test   //Tests deleting a track
    public void testUpdateTrack()
    {
        Muzix muzix=new Muzix(1, "Track1", "Good");
       Muzix muzix2=new Muzix(1, "Track1", "Bad");
       when(muzixRepository.save(muzix)).thenReturn(muzix2);
        Muzix muzix3=muzixServiceImpl.updateTrack(muzix2,muzix.getTrackId()) ;
       Assert.assertEquals(muzix2,muzix3);

    }
    @Test      //Tests retrieving tasks
    public void testGetAllTrack(){
        Muzix muzix1=new Muzix(1, "Track1", "Good");
        Muzix muzix2=new Muzix(2, "Track2", "Good");
        List<Muzix> list=new ArrayList<>();
        list.add(muzix1);
        list.add(muzix2);

        when(muzixRepository.findAll()).thenReturn(list);
        List<Muzix> muzixList= muzixServiceImpl.getAllTracks();
        Assert.assertEquals(list,muzixList);
        verify(muzixRepository,times(1));
    }


}

