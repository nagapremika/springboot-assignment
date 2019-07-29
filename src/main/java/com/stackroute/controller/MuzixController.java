package com.stackroute.controller;

import com.stackroute.domain.Muzix;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.MuzixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     //RestController which performs both Controller and ResponseBody functionality
@RequestMapping("api")
public class MuzixController {

    private  MuzixService muzixService;

    public MuzixController(MuzixService muzixService)
    {
        this.muzixService=muzixService;
    }
    // Handles creation operation
    @PostMapping("muzix")
    public ResponseEntity<?> saveMuzix(@RequestBody Muzix muzix) throws TrackAlreadyExistsException
    {
        ResponseEntity responseEntity;

        muzixService.saveTrack(muzix);  //Displays all tracks
        responseEntity=new ResponseEntity<>("Created successfully", HttpStatus.CREATED);    //Creates a track

        return responseEntity;
    }
    //Handles read operation
    @GetMapping("muzix")
    public ResponseEntity<?> getAllMuzix()

    {
        muzixService.getList(); //Retrieves the track data
        return new ResponseEntity<>(muzixService.getAllTracks(), HttpStatus.OK);
    }
    //Handles delete operation
    @DeleteMapping("muzix")
    public ResponseEntity<?> deleteMuzix(@RequestBody Muzix muzix) throws TrackNotFoundException {

//Deletes a particular row in table
        return new ResponseEntity<>(muzixService.deleteTrack(muzix.getTrackId()), HttpStatus.OK);

    }
    //Handles update operation
    @PutMapping("muzix")
    public ResponseEntity<?> updateMuzix(@RequestBody Muzix muzix) throws TrackNotFoundException {
//         Can update the track with the given id
        return new ResponseEntity<>(muzixService.updateTrack(muzix,muzix.getTrackId()), HttpStatus.OK);

    }
    @GetMapping("trackByName")
    public ResponseEntity<?> getTrackByName(@RequestParam String name) throws TrackNotFoundException
    {
        //tracks the track by the name of track
        return new ResponseEntity<List<Muzix>>(muzixService.getTracksByName(name), HttpStatus.OK);
    }

}
