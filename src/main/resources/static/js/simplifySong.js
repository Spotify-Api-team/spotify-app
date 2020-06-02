

function convertSong(track){

    var name = track.name;
    var id = track.id;
    var image = track.album.images[2];
    var artist = track.artists[0].name;


    simpleTrack = {

        "name": name,
        "id": id,
        "image": image,
        "artist": artist

    }

    return simpleTrack;

}