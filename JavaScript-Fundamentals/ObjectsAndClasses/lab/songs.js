function solve(arr) {

    let indexIter = arr.slice(0, 1);
    let songsData = arr.slice(1, arr.length - 1);
    

    let songs = [];

    let Song = class {
        constructor(typeList, name, time) {
            this.typeList = typeList;
            this.name = name;
            this.time = time;
        }

        searchByType(type){
            if (this.typeList === type || type === 'all'){
                console.log(this.name);;
            }
        }
    }

    for (let i = 0; i < indexIter; i++) {
        let currentSong = songsData[i].split('_');
        songs.push(new Song(currentSong[0], currentSong[1], currentSong[2]));
    }

    let searchType = arr[arr.length - 1];

    for (const song of songs) {
        song.searchByType(searchType);
    }

}

solve([3,
    'favourite_DownTown_3:14',
    'favourite_Kiss_4:16',
    'favourite_Smooth Criminal_4:01',
    'favourite']);

solve([4,
    'favourite_DownTown_3:14',
    'listenLater_Andalouse_3:24',
    'favourite_In To The Night_3:58',
    'favourite_Live It Up_3:48',
    'listenLater']);

solve([2,
    'like_Replay_3:15',
    'ban_Photoshop_3:48',
    'all']);