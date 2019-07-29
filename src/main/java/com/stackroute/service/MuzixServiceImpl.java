package com.stackroute.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.domain.Muzix;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.List;
@Service        //Service class
public class MuzixServiceImpl implements MuzixService {     //class implementing MuzixService
    @Autowired
    private MuzixRepository muzixRepository;

    public MuzixServiceImpl(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }



    @Override   //Implementing saveTrack()
    public Muzix saveTrack(Muzix muzix)
    {
      return  muzixRepository.save(muzix);
    }

    @Override   //Implementing getAllTracks()
    public List<Muzix> getAllTracks()

    {
        return muzixRepository.findAll();
    }

    @Override   //Implementing deleteTrack() by Id
    public List<Muzix> deleteTrack(int trackId) {
        muzixRepository.deleteById(trackId);
        return muzixRepository.findAll();
    }

    @Override   //Updates track
    public Muzix updateTrack(Muzix muzix,int id)  {
        if(muzixRepository.existsById(id))
        {
            muzix.setTrackId(id);
            muzixRepository.save(muzix);
        }
        else
        {
            try {
                throw new TrackNotFoundException();
            }
            catch (TrackNotFoundException e) {
                e.printStackTrace();
            }
        }



        return muzix;
    }
    //using third party URL to get the tracks
public void getList() {
    RestTemplate restTemplate = new RestTemplate();
    //Third party URL
    String url = "http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=spain&api_key=41d2fd3867c52c09eb248e430238ebea&format=json";
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode root = null;
    try {
        root = (JsonNode) objectMapper.readTree(responseEntity.getBody());
    } catch (IOException e) {
        e.printStackTrace();
    }
    //creating root path
    ArrayNode arraynode = (ArrayNode) root.path("tracks").path("track");
    try {
        for (int i = 0; i <= arraynode.size(); i++) {
            Muzix track = new Muzix();
            track.setTrackId(i + 1);
            track.setTrackName(arraynode.get(i).path("name").asText());
            track.setComments(arraynode.get(i).path("artist").path("name").asText());
            muzixRepository.save(track);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public List<Muzix> getTracksByName(String name) {

        return muzixRepository.getTrackByName(name);

    }
}
