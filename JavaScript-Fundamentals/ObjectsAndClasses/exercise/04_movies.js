function solve(arr) {
    let movies = [];

    let movieInfo = arr.map(line => line.split(' '));



    movieInfo.forEach(m => {
        if (m[0] === 'addMovie') {
            let movie = {};
            let name = m.slice(1).join(' ');
            movie.name = name;
            movies.push(movie);

        } else if (m.join(" ").match('directedBy')) {
            let index = m.join(" ").match('directedBy').index;
            let movieName = m.join(' ').slice(0, index).trim();
            let direktor = m.join(' ').slice(index + 10).trim()

            sarchMovie(movies, 'name', movieName, 'director', direktor);

        } else if (m.join(' ').match('onDate')) {
            let index = m.join(' ').match('onDate').index;
            let movieName = m.join(' ').slice(0, index).trim();
            let date = m.join(' ').slice(index + 6).trim();

            sarchMovie(movies, 'name', movieName, 'date', date);

        }



    })

    function sarchMovie(obj1, name, newParams, director, addParams) {
        obj1.forEach(m => {
            if (m[name] == newParams) {
                m[director] = addParams;
            }
        })
    }

    for (const movie of movies) {
        if (Object.keys(movie).length > 2) {
            let result = JSON.stringify(movie);
            console.log(result);
        }
    }
}
