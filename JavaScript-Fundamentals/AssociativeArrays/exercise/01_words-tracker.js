function solve(arr) {

    let words = {};
    arr.shift().split(' ').map(e => words[e] = 0);


    arr.forEach(e => {
        if (words.hasOwnProperty(e)) {
            words[e] = words[e] + 1;
        }
    })

    Object.entries(words).sort((a, b) => b[1] - a[1])
        .forEach(w => console.log(`${w[0]} - ${w[1]}`));



}