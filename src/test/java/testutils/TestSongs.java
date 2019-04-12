package testutils;



import com.songservice.songservice.models.Song;

import java.util.*;

public class TestSongs {

    public static List<Song> getSongs(){
        List<Song> songs = new ArrayList<Song>();
        songs.add(new Song("Africa","Weezer", "243"));
        songs.add(new Song("Africa","Toto", "274"));
        songs.add(new Song("Take On Me","a-Ha", "227"));
        songs.add(new Song("Billie Jean","Michael Jackson", "210"));
        songs.add(new Song("I Would Do Anything for Love","Meatloaf","756"));
        songs.add(new Song("Closer","The Chainsmokers","244"));
        songs.add(new Song("Something like this" , "The Chainsmokers" , "247"));
        return songs;
    }
}
