package com.stackroute.service;

import com.stackroute.domain.Muzix;
import com.stackroute.exception.TrackNotFoundException;

import java.util.List;

public interface MuzixService { //interface with method declarations
    Muzix saveTrack(Muzix muzix);
    List<Muzix> getAllTracks();
    List<Muzix> deleteTrack(int trackId);
    Muzix updateTrack(Muzix muzix,int id) throws TrackNotFoundException;
     void getList();
    public List<Muzix> getTracksByName(String name);

}
