function solve(arr) {

    let map = new Map();

    for (const word of arr) {
        if (map.has(word)) {
            map.set(word, map.get(word) + 1);
        } else {
            map.set(word, 1);
        }
    }

    let prinresult = [...map.entries()]
        .sort((a, b) => b[1] - a[1])
        .forEach(r => console.log(`${r[0]} -> ${r[1]}`))

}
