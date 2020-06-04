

function convertSong(track){

    var name = track.name;
    var id = track.id;
    var image = track.album.images[2];
    var artist = track.artists[0].name;


    simpleTrack = {

        "id": id,
        "name": name,
        "artist": artist,
        "image": image

    }

    return simpleTrack;

}