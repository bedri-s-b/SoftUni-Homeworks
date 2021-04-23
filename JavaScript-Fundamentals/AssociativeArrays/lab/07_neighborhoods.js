function solve(arr) {

    let map = new Map();

    let a = arr.shift(0).split(', ')
        .forEach(el => map.set(el, []));

    arr.forEach(el => {
        let [neigh, manName] = el.split(' - ')
        if (map.has(neigh)) {
            map.get(neigh).push(manName);
        }
    })

    let b = [...map.entries()]
        .sort((a, b) => b[1].length - a[1].length)
        .forEach(el => {
            console.log(`${el[0]}: ${el[1].length}`)
            el[1].forEach(p => console.log(`--${p}`))
        });
}
